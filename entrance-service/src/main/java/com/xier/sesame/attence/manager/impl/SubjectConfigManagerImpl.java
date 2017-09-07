package com.xier.sesame.attence.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.moredian.bee.common.utils.JsonUtils;
import com.moredian.bee.rmq.EventBus;
import com.moredian.bee.rmq.annotation.Subscribe;
import com.moredian.fishnet.common.model.msg.BindDefaultSubjectMsg;
import com.moredian.fishnet.common.model.msg.RefreshDeviceConfigMsg;
import com.moredian.fishnet.common.model.msg.RemoveDeviceSubjectMsg;
import com.moredian.fishnet.device.enums.DeviceStatus;
import com.moredian.fishnet.device.model.DeviceInfo;
import com.moredian.fishnet.device.service.DeviceService;
import com.xier.sesame.attence.dao.OrgSubjectDao;
import com.xier.sesame.attence.dao.SubjectConfigDao;
import com.xier.sesame.attence.dao.SubjectDao;
import com.xier.sesame.attence.domain.OrgSubject;
import com.xier.sesame.attence.domain.Subject;
import com.xier.sesame.attence.domain.SubjectConfig;
import com.xier.sesame.attence.manager.SubjectConfigManager;
import com.xier.sesame.attence.request.SubjectConfigRequest;
import com.xier.sesame.common.exception.BizAssert;
import com.xier.sesame.common.uploadfile.manager.ImageFileManager;
import com.xier.sesame.idgenerator.service.IdgeneratorService;

@Service
public class SubjectConfigManagerImpl implements SubjectConfigManager{
	
	public static final Logger logger = LoggerFactory.getLogger(SubjectConfigManagerImpl.class);

	@Reference
	private IdgeneratorService idgeneratorService;
	@Autowired
	private SubjectConfigDao subjectConfigDao;
	@Autowired
	private SubjectDao subjectDao;
	@Autowired
	private ImageFileManager imageFileManager;
	@Autowired
	private OrgSubjectDao orgSubjectDao;
	@Reference
	private DeviceService deviceService;
	
	@Override
	public List<Long> findDeviceIdBySubject(Long orgId, Long subjectId) {
		BizAssert.notNull(orgId);
		BizAssert.notNull(subjectId);
		return subjectConfigDao.findDeviceIdBySubject(orgId, subjectId);
	}
	
	@Override
	@Transactional
	public boolean subjectConfig(SubjectConfigRequest request) {
		BizAssert.notNull(request.getOrgId());
		BizAssert.notNull(request.getSubjectId());
		BizAssert.notNull(request.getHoldSeconds());
		BizAssert.notNull(request.getDevices());
		
		OrgSubject orgSubject = this.buildOrgSubject(request);
		
		orgSubjectDao.insertOrUpdate(orgSubject);
		
		this.resetSubjectConfig(request.getOrgId(), request.getSubjectId(), request.getDevices());
		
		return true;
	}
	
	@Override
	@Transactional
	public boolean toggleSubjectOfDevice(Long orgId, Long deviceId, Long subjectId) {
		BizAssert.notNull(orgId);
		BizAssert.notNull(deviceId);
		BizAssert.notNull(subjectId);
		
		subjectConfigDao.updateSubjectId(orgId, deviceId, subjectId);
		
		RefreshDeviceConfigMsg msg = new RefreshDeviceConfigMsg();
		msg.setOrgId(orgId);
		DeviceInfo device = deviceService.getDeviceById(orgId, deviceId);
		msg.setDeviceSn(device.getDeviceSn());
		EventBus.publish(msg);
		logger.info("发出MQ消息[刷新设备XML配置]: "+JsonUtils.toJson(msg));
		
		return true;
	}

	private OrgSubject buildOrgSubject(SubjectConfigRequest request) {
		Subject subject = subjectDao.getSubjectById(request.getSubjectId());
		
		OrgSubject orgSubject = new OrgSubject();
		orgSubject.setOrgSubjectId(this.genOrgSubjectId());
		orgSubject.setOrgId(request.getOrgId());
		orgSubject.setSubjectId(request.getSubjectId());
		orgSubject.setSubjectCode(subject.getSubjectCode());
		orgSubject.setSubjectName(subject.getSubjectName());
		orgSubject.setSubjectDesc(subject.getSubjectDesc());
		orgSubject.setShowImg(subject.getShowImg());
		orgSubject.setHoldSeconds(request.getHoldSeconds());
		orgSubject.setBgImg(subject.getBgImg());
		orgSubject.setLogoImg(subject.getLogoImg());
		
		if(StringUtils.isNotBlank(request.getBgImg())) {
			orgSubject.setBgImg(imageFileManager.getRelativePath(request.getBgImg()));
		} 
		
		if(StringUtils.isNotBlank(request.getLogoImg())) {
			orgSubject.setLogoImg(imageFileManager.getRelativePath(request.getLogoImg()));
		} 
		
		return orgSubject;
	}
	
	private Long genOrgSubjectId() {
		return idgeneratorService.getNextIdByTypeName("com.xier.sesame.attence.OrgSubject").getData();
	}
	
	private void resetSubjectConfig(Long orgId, Long subjectId, List<Long> finalDeviceIdList) {
		List<Long> existDeviceIdList = subjectConfigDao.findDeviceIdBySubject(orgId, subjectId);
		
		List<Long> finalDeviceIdList_clone = new ArrayList<>();
		finalDeviceIdList_clone.addAll(finalDeviceIdList);
		
		List<Long> deviceIdForMsg = new ArrayList<>();
		deviceIdForMsg.addAll(finalDeviceIdList);
		
		finalDeviceIdList.removeAll(existDeviceIdList); // 定位要新增的
		for(Long deviceId : finalDeviceIdList) {
			subjectConfigDao.updateSubjectId(orgId, deviceId, subjectId); // 修改设备主题关系
		}
		
		existDeviceIdList.removeAll(finalDeviceIdList_clone); // 定位要删除的
		for(Long deviceId : existDeviceIdList) {
			Subject subject = subjectDao.getDefaultSubject();
			subjectConfigDao.updateSubjectId(orgId, deviceId, subject.getSubjectId()); // 修改设备主题关系（恢复默认主题）
			deviceIdForMsg.add(deviceId);
		}
		
		if(CollectionUtils.isNotEmpty(deviceIdForMsg)) {
			for(Long deviceId : deviceIdForMsg) {
				RefreshDeviceConfigMsg msg = new RefreshDeviceConfigMsg();
				msg.setOrgId(orgId);
				DeviceInfo device = deviceService.getDeviceById(msg.getOrgId(), deviceId);
				if(device == null) continue;
				msg.setDeviceSn(device.getDeviceSn());
				EventBus.publish(msg);
				logger.info("发出MQ消息[刷新设备XML配置]: "+JsonUtils.toJson(msg));
			}
		}
		
		
	}
	
	@Override
	public Long getSubjectId(Long orgId, Long deviceId) {
		return subjectConfigDao.getSubjectId(orgId, deviceId);
	}

	private Long genSubjectConfigId() {
		return idgeneratorService.getNextIdByTypeName("com.xier.sesame.attence.SubjectConfig").getData();
	}
	
	@Override
	public Long getSubjectIdByDevice(Long orgId, Long deviceId) {
		BizAssert.notNull(orgId);
		BizAssert.notNull(deviceId);
		return subjectConfigDao.getSubjectId(orgId, deviceId);
	}

	/**
	 * 订阅设备创建消息
	 * @param msg
	 */
	@Subscribe
	public void receiveMsg(BindDefaultSubjectMsg msg) {
		
		logger.info("收到MQ消息[创建设备]:" + JsonUtils.toJson(msg));
		logger.info("绑定设备默认主题");
		Subject subject = subjectDao.getDefaultSubject();
		SubjectConfig subjectSetting = new SubjectConfig();
		subjectSetting.setSubjectConfigId(this.genSubjectConfigId());
		subjectSetting.setOrgId(msg.getOrgId());
		subjectSetting.setDeviceId(msg.getDeviceId());
		subjectSetting.setSubjectId(subject.getSubjectId());
		subjectConfigDao.addSubjectConfig(subjectSetting);
		
		DeviceInfo device = deviceService.getDeviceById(msg.getOrgId(), msg.getDeviceId());
		if(device == null || DeviceStatus.UNACTIVE.getValue() == device.getStatus()) return;
		
		RefreshDeviceConfigMsg msgObj = new RefreshDeviceConfigMsg();
		msgObj.setOrgId(msg.getOrgId());
		msgObj.setDeviceSn(device.getDeviceSn());
		EventBus.publish(msgObj);
		logger.info("发出MQ消息[刷新设备XML配置]: "+JsonUtils.toJson(msgObj));
		
	}
	
	/**
	 * 订阅设备删除消息
	 * @param msg
	 */
	@Subscribe
	public void receiveMsg(RemoveDeviceSubjectMsg msg) {
		
		logger.info("收到MQ消息[删除设备]:" + JsonUtils.toJson(msg));
		subjectConfigDao.removeByDeviceId(msg.getOrgId(), msg.getDeviceId()); // 移除设备主题配置
		
	}
	


}
