package com.xier.sesame.attence.web.controller.subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.moredian.bee.common.utils.NetworkImage;
import com.moredian.bee.common.utils.Picture;
import com.moredian.bee.common.utils.RandomUtil;
import com.moredian.fishnet.device.service.DeviceService;
import com.xier.sesame.attence.enums.AttenceErrorCode;
import com.xier.sesame.attence.model.OrgSubjectInfo;
import com.xier.sesame.attence.model.SubjectInfo;
import com.xier.sesame.attence.request.SubjectAddRequest;
import com.xier.sesame.attence.request.SubjectConfigRequest;
import com.xier.sesame.attence.service.OrgSubjectService;
import com.xier.sesame.attence.service.SubjectConfigService;
import com.xier.sesame.attence.service.SubjectService;
import com.xier.sesame.attence.web.controller.BaseController;
import com.xier.sesame.attence.web.controller.subject.request.AddSubjectModel;
import com.xier.sesame.attence.web.controller.subject.request.SubjectConfigModel;
import com.xier.sesame.attence.web.controller.subject.request.ToggleDeviceSubjectModel;
import com.xier.sesame.attence.web.controller.subject.response.OrgSubjectData;
import com.xier.sesame.attence.web.controller.subject.response.SubjectData;
import com.xier.sesame.common.uploadfile.enums.FilePathType;
import com.xier.sesame.common.uploadfile.manager.ImageFileManager;
import com.xier.sesame.common.utils.BeanUtils;
import com.xier.sesame.common.utils.ExceptionUtils;
import com.xier.sesame.common.web.BaseDataResponse;
import com.xier.sesame.common.web.BaseResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Subject API", description = "主题接口")
@RestController
@RequestMapping("/subject")
public class SubjectController extends BaseController {
	
	@Reference
	private SubjectService subjectService;
	@Reference
	private DeviceService deviceService;
	@Reference
	private SubjectConfigService subjectConfigService;
	@Reference
	private OrgSubjectService orgSubjectService;
	@Autowired
	private ImageFileManager imageFileManager;
	
	private SubjectAddRequest buildRequest(AddSubjectModel model) {
		return BeanUtils.copyProperties(SubjectAddRequest.class, model);
	}
	
	@ApiOperation(value = "创建主题", notes = "创建主题")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public BaseResponse add(@RequestBody AddSubjectModel model) {
		subjectService.addSubject(this.buildRequest(model));
		return new BaseResponse();
	}
	
	private SubjectData subjectInfoToSubjectData(Long orgId, SubjectInfo subjectInfo, boolean mergeRelationDevices) {
		
		SubjectData subjectData = BeanUtils.copyProperties(SubjectData.class, subjectInfo);
		
		if(mergeRelationDevices) {
			List<Long> deviceIdList = subjectConfigService.findDeviceIdBySubject(orgId, subjectInfo.getSubjectId());
			if(CollectionUtils.isNotEmpty(deviceIdList)) {
				List<String> deviceNames = deviceService.findDeviceNameByIds(deviceIdList);
				subjectData.setDevices(deviceNames);
			}
		}
		
		return subjectData;
	}
	
	private List<SubjectData> subjectInfoListToSubjectDataList(Long orgId, List<SubjectInfo> subjectInfoList, boolean mergeRelationDevices) {
		List<SubjectData> subjectDataList = new ArrayList<>();
		if(CollectionUtils.isEmpty(subjectInfoList)) return subjectDataList;
		
		for(SubjectInfo subjectInfo : subjectInfoList) {
			subjectDataList.add(subjectInfoToSubjectData(orgId, subjectInfo, mergeRelationDevices));
		}
		
		return subjectDataList;
	}
	
	private List<OrgSubjectData> buildOrgSubjectDataList(Long orgId, List<SubjectInfo> subjectInfoList) {
		List<OrgSubjectData> orgSubjectDataList = new ArrayList<>();
		if(CollectionUtils.isEmpty(subjectInfoList)) return orgSubjectDataList;
		
		for(SubjectInfo subjectInfo : subjectInfoList) {
			OrgSubjectInfo orgSubjectInfo = orgSubjectService.getOrgSubject(orgId, subjectInfo.getSubjectId());
			OrgSubjectData orgSubjectData = this.buildOrgSubjectData(orgId, subjectInfo, orgSubjectInfo);
			orgSubjectDataList.add(orgSubjectData);
		}
		
		return orgSubjectDataList;
	}
	
	@ApiOperation(value = "查询全局主题", notes = "查询全局主题")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public BaseResponse list(@RequestParam(value = "orgId") Long orgId) {
		BaseDataResponse<List<SubjectData>> bdr = new BaseDataResponse<>();
		List<SubjectInfo> subjectInfoList = subjectService.findSubject();
		bdr.setData(this.subjectInfoListToSubjectDataList(orgId, subjectInfoList, true));
		return bdr;
	}
	
	@ApiOperation(value = "查询机构主题", notes = "查询机构主题")
	@RequestMapping(value = "/listForOrg", method = RequestMethod.GET)
	public BaseResponse listForOrg(@RequestParam(value = "orgId") Long orgId) {
		BaseDataResponse<List<OrgSubjectData>> bdr = new BaseDataResponse<>();
		List<SubjectInfo> subjectInfoList = subjectService.findSubject();
		bdr.setData(this.buildOrgSubjectDataList(orgId, subjectInfoList));
		return bdr;
	}
	
	private OrgSubjectData buildOrgSubjectData(Long orgId, SubjectInfo subjectInfo, OrgSubjectInfo orgSubjectInfo) {
		OrgSubjectData data = new OrgSubjectData();
		data.setOrgId(orgId);
		data.setSubjectId(subjectInfo.getSubjectId());
		data.setSubjectCode(subjectInfo.getSubjectCode());
		data.setSubjectName(subjectInfo.getSubjectName());
		data.setSubjectDesc(subjectInfo.getSubjectDesc());
		
		if(orgSubjectInfo != null) {
			data.setShowImg(imageFileManager.getImageUrl(orgSubjectInfo.getShowImg()));
			data.setBgImg(imageFileManager.getImageUrl(orgSubjectInfo.getBgImg()));
			data.setLogoImg(imageFileManager.getImageUrl(orgSubjectInfo.getLogoImg()));
			data.setHoldSeconds(orgSubjectInfo.getHoldSeconds());
		} else {
			data.setShowImg(imageFileManager.getImageUrl(subjectInfo.getShowImg()));
			data.setBgImg(imageFileManager.getImageUrl(subjectInfo.getBgImg()));
			data.setLogoImg(imageFileManager.getImageUrl(subjectInfo.getLogoImg()));
			data.setHoldSeconds(subjectInfo.getHoldSeconds());
		}
		
		List<Long> deviceIds = subjectConfigService.findDeviceIdBySubject(orgId, data.getSubjectId());
		data.setDevices(deviceIds);
		
		return data;
	}
	
	@ApiOperation(value = "查看主题配置", notes = "查看主题配置")
	@RequestMapping(value = "/config", method = RequestMethod.GET)
	public BaseResponse configDetail(@RequestParam(value = "orgId") Long orgId, @RequestParam(value = "subjectId") Long subjectId) {
		
		BaseDataResponse<OrgSubjectData> bdr = new BaseDataResponse<>();
		SubjectInfo subject = subjectService.getSubject(subjectId);
		OrgSubjectInfo orgSubject = orgSubjectService.getOrgSubject(orgId, subjectId);
		
		bdr.setData(buildOrgSubjectData(orgId, subject, orgSubject));
		return bdr;
	}
	
	private String storeImage(byte[] image, FilePathType filePathType) {
		
		Map<String, Object> map = null;
		String fileName = RandomUtil.getUUID() + ".jpg";
		try {
			map = imageFileManager.saveImage(image, filePathType, fileName);
		} catch (Exception e) {
			ExceptionUtils.throwException(AttenceErrorCode.IMAGE_SAVE_ERROR, AttenceErrorCode.IMAGE_SAVE_ERROR.getMessage());
		}
		if(!"0".equals(map.get("result"))){
			ExceptionUtils.throwException(AttenceErrorCode.IMAGE_SAVE_WRONG, AttenceErrorCode.IMAGE_SAVE_WRONG.getMessage());
		}
		
		return (String)map.get("url");
	}
	
	/**
	 * 临时图片转存目标图片目录
	 * @param sourceUrl
	 * @param filePathType
	 * @return
	 */
	private String changeStoreImg(String sourceUrl, FilePathType filePathType) {
		Picture picture = new NetworkImage(sourceUrl);
		byte[] imageData = picture.getImageData();
		return imageFileManager.getImageUrl(this.storeImage(imageData, filePathType));
	}
	
	private SubjectConfigRequest buildRequest(SubjectConfigModel model) {
		if(StringUtils.isNotBlank(model.getBgImg())) {
			model.setBgImg(this.changeStoreImg(model.getBgImg(), FilePathType.TYPE_SUBJECT));
		}
		if(StringUtils.isNotBlank(model.getLogoImg())) {
			model.setLogoImg(this.changeStoreImg(model.getLogoImg(), FilePathType.TYPE_SUBJECT));
		}
		return BeanUtils.copyProperties(SubjectConfigRequest.class, model);
	}
	
	@ApiOperation(value = "修改主题配置", notes = "修改主题配置")
	@RequestMapping(value = "/config", method = RequestMethod.PUT)
	public BaseResponse config(@RequestBody SubjectConfigModel model) {
		
		subjectConfigService.subjectConfig(buildRequest(model));
		
		return new BaseResponse();
	}
	
	@ApiOperation(value = "获取设备关联主题", notes = "获取设备关联主题")
	@RequestMapping(value = "/subjectOfDevice", method = RequestMethod.GET)
	public BaseResponse subjectOfDevice(@RequestParam(value = "orgId") Long orgId, @RequestParam(value = "deviceId") Long deviceId) {
		
		BaseDataResponse<SubjectData> bdr = new BaseDataResponse<>();
		
		Long subjectId = subjectConfigService.getSubjectIdByDevice(orgId, deviceId);
		SubjectInfo subjectInfo = subjectService.getSubject(subjectId);
		bdr.setData(this.subjectInfoToSubjectData(orgId, subjectInfo, false));
		return bdr;
	}
	
	@ApiOperation(value = "切换设备主题", notes = "切换设备主题")
	@RequestMapping(value = "/toggleForDevice", method = RequestMethod.PUT)
	public BaseResponse toggleForDevice(@RequestBody ToggleDeviceSubjectModel model) {
		
		subjectConfigService.toggleSubjectOfDevice(model.getOrgId(), model.getDeviceId(), model.getSubjectId());
		
		return new BaseResponse();
	}
	
}
