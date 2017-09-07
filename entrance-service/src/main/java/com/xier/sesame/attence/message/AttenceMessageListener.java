package com.xier.sesame.attence.message;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Reference;
import com.moredian.bee.rmq.EventBus;
import com.moredian.bee.rmq.annotation.Subscribe;
import com.moredian.cloudeye.core.api.rs.RecognizeType;
import com.moredian.cloudeye.core.api.rs.VerifyLog;
import com.moredian.cloudeye.core.api.rs.VerifyLogItem;
import com.moredian.fishnet.common.model.msg.ConfigGroupAtcDataMsg;
import com.moredian.fishnet.common.model.msg.RefreshDeviceConfigMsg;
import com.moredian.fishnet.device.model.DeviceInfo;
import com.moredian.fishnet.device.model.DeviceStateInfo;
import com.moredian.fishnet.device.request.TransferRequest;
import com.moredian.fishnet.device.service.DeviceService;
import com.xier.sesame.attence.domain.OffLinePassword;
import com.xier.sesame.attence.dto.NewSwitchSettingDto;
import com.xier.sesame.attence.enums.DefaultGroupType;
import com.xier.sesame.attence.manager.NewSwitchSettingManager;
import com.xier.sesame.attence.manager.OffLinePasswordManager;
import com.xier.sesame.attence.manager.RecognizeRecordManager;
import com.xier.sesame.attence.service.DeviceConfigService;
import com.xier.sesame.attence.service.NewSwitchSettingService;
import com.xier.sesame.attence.utils.StringEncoderUtil;
import com.xier.sesame.cloudeye.dto.RecognizeLogDto;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.common.utils.JsonUtils;

/**
 * Created by xxu on 2017/4/6.
 */
@Component
public class AttenceMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(AttenceMessageListener.class);

    @Value("${global.beginTime:00:00}")
    private  String globalBeginTime;

    @Value("${global.endTime:23:59}")
    public  String globalEndTime;

    @Autowired
    private NewSwitchSettingManager switchSettingManager;

    @Reference
    private DeviceConfigService deviceConfigService;

    @Reference
    private DeviceService deviceService;

    @Autowired
    private RecognizeRecordManager recognizeRecordManager;

    @Autowired
    private OffLinePasswordManager offLinePasswordManager;

    @Reference
    private NewSwitchSettingService switchSettingService;

    public AttenceMessageListener() {
        EventBus.registerListener(this);
    }


    @Subscribe
    public void subGroupInfo(ConfigGroupAtcDataMsg group) throws Exception {

        String json = JsonUtils.toJson(group);

        logger.info("收到GroupConfigMsg消息: " + json);
        //正常情况只有org创建时才收到消息
        //保存默认group的时间记录

        Long groupId = group.getGroupId();
        Long orgId = group.getOrgId();
        String orgCode=group.getGroupCode();

        NewSwitchSettingDto switchSettingDto = buildSettingDto(orgId, groupId,group.getGroupType(),orgCode);

        ServiceResponse<Long> sr_dto = switchSettingService.addSwitchSetting(switchSettingDto);
        if (!sr_dto.isSuccess()) {
            logger.error("AttenceMessageListener create group settings failed");

        } else {
            logger.info("AttenceMessageListener create group settings successfully");
        }
        //同时要保存离线密码
        //发多条创建组消息时，只取全员组消息作为依据
        if(group.getGroupType()==DefaultGroupType.ALL_GROUP) {
            OffLinePassword offLinePassword = new OffLinePassword();
            offLinePassword.setOrgId(group.getOrgId());
            String offPassword= "";
            try {
                offPassword= StringEncoderUtil.EncoderByMd5("201704");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            offLinePassword.setPassword(offPassword);
            logger.info("Start to save offline password");
            ServiceResponse<Long> offResult = offLinePasswordManager.addOffLinePassword(offLinePassword);
            if (offResult.isSuccess() && offResult.isExistData()) {
                logger.info("Save offline password successfully id is " + offResult.getData());
            }else{
                logger.error("Save offline password failed");
            }

        }



    }

    @Subscribe
    public void subRecognizeLogDto(VerifyLog verifyLog){
        logger.info("收到RecognizeLogDtoMQ消息: " );
        RecognizeLogDto recognizeLogDto=recognizeLogToRecognizeLogDto(verifyLog);
        recognizeRecordManager.receiveRecognizeLogDto(recognizeLogDto);

    }
    
    @Subscribe
    public void doSubDeviceConfigEvent(RefreshDeviceConfigMsg msg) throws Exception {

        logger.info("收到MQ消息[刷新设备XML配置]: " + JsonUtils.toJson(msg));
        //正常情况只有设备激活时才收到消息

        String deviceSn = msg.getDeviceSn();
        Long orgId = msg.getOrgId();

        //generate xml to fishnet
       DeviceInfo deviceInfo = deviceService.getDeviceBySn(deviceSn);
        if (deviceInfo == null || deviceInfo.getDeviceId() == null) {
            logger.error(String.format("Get device failed.DeviceSn is %s, and orgId is %d", deviceSn, orgId));
            throw new Exception(String.format("Get device failed.DeviceSn is %s, and orgId is %d", deviceSn, orgId));
        }

        List<Long> ids = new ArrayList<>( );
        ids.add(deviceInfo.getDeviceId());

        logger.info(String.format("start to notify xml service. device Id is %d",deviceInfo.getDeviceId()));

        deviceConfigService.notifyXmlServer(orgId, ids);

    }

    private RecognizeLogDto recognizeLogToRecognizeLogDto(VerifyLog log) {
        if (log == null)
            return null;
        RecognizeLogDto recognizeLogDto = new RecognizeLogDto();
        recognizeLogDto.setAge(log.getAge());
        recognizeLogDto.setFemale(log.getFemale());
        recognizeLogDto.setMale(log.getMale());
        recognizeLogDto.setQuality(log.getQuality());
        recognizeLogDto.setDeviceId(log.getDeviceId());
        recognizeLogDto.setImageUrl(log.getImageUrl());
        recognizeLogDto.setLogId(log.getVerifyId());
        recognizeLogDto.setRecognizeSubOrgId(log.getPosition());
        recognizeLogDto.setRecognizeTime(log.getRecognizeTime());
        recognizeLogDto.setRelationOrgId(log.getRelationOrgId());
        VerifyLogItem item = log.getHighConfidenceItem();
        if (item != null) {
            recognizeLogDto.setRelationUserGroup(item.getRelationUserType());
            recognizeLogDto.setRelationUserId(item.getRelationUserId());
            recognizeLogDto.setTargetFacePicId(item.getTargetFacePicId());
            recognizeLogDto.setUserName(item.getRelationUserName());
            recognizeLogDto.setRecognizeType(item.getRecognizeType());
            recognizeLogDto.setPersonId(item.getPersonId());
            recognizeLogDto.setConfidence(item.getConfidence());
        } else {
            recognizeLogDto.setRecognizeType(RecognizeType.STATIC_VERIFY_UNRECOGNIZED.intValue());
        }
        return recognizeLogDto;
    }

    private NewSwitchSettingDto buildSettingDto(Long orgId, Long groupId,Integer groupType,String groupCode) {
        NewSwitchSettingDto switchSettingDto = new NewSwitchSettingDto();

        switchSettingDto.setGroupId(groupId);
        //全员组时间
        //默认都使用全天
        switchSettingDto.setDayBeginTime(globalBeginTime);
        switchSettingDto.setDayEndTime(globalEndTime);


        switchSettingDto.setOpenWeekDays("1,2,3,4,5,6,7");
        switchSettingDto.setAttenceSwitch(0);
        switchSettingDto.setStangerSwitch(0);
        switchSettingDto.setOrgId(orgId);
        switchSettingDto.setGroupCode(groupCode);
        return switchSettingDto;
    }


    public DeviceStateInfo sendMessage(TransferRequest request){
        DeviceStateInfo deviceStateInfo=deviceService.transferDevice(request);
        return deviceStateInfo;
    }



}
