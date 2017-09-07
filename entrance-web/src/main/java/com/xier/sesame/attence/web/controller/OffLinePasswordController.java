package com.xier.sesame.attence.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.moredian.bee.common.web.BaseResponse;
import com.moredian.fishnet.device.enums.DeviceType;
import com.moredian.fishnet.device.service.DeviceService;
import com.moredian.fishnet.org.model.OrgInfo;
import com.moredian.fishnet.org.service.OrgService;
import com.xier.sesame.attence.dto.OffLinePasswordDto;
import com.xier.sesame.attence.service.DeviceConfigService;
import com.xier.sesame.attence.service.OffLinePasswordService;
import com.xier.sesame.attence.utils.SmsUtil;
import com.xier.sesame.attence.web.controller.req.OffLinePasswordModel;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.pigeon.enums.SMSType;
import com.xier.sesame.pigeon.mm.service.MMService;
import com.xier.sesame.pigeon.mm.smsParam.NewOrgDoorPasswordParams;

/**
 * Created by xxu on 2017/4/14.
 */

@RestController
@RequestMapping("/attence/admin")
public class OffLinePasswordController {

    @Reference
    private OffLinePasswordService offLinePasswordService;

    @Reference
    private DeviceConfigService deviceConfigService;

    @Reference
    private DeviceService deviceService;

    @Reference
    private MMService mmService;

    @Reference
    private OrgService orgService;

    /**
     * 创建用户离线密码
     * @param offLinePasswordModel
     * @return
     */
    @RequestMapping(
            value = "/offlinepassword",
            method = RequestMethod.POST
    )
    @ResponseBody
    public BaseResponse createOffLinePassword(@RequestBody OffLinePasswordModel offLinePasswordModel) {

        BaseResponse bdr = new BaseResponse();
        OffLinePasswordDto offLinePasswordDto=buildOffLinePasswordDtoFromModel(offLinePasswordModel);
//写入数据库
        ServiceResponse<Long> sr_dto = offLinePasswordService.addOffLinePassword(offLinePasswordDto);

        //get the org name according to orgId

        String orgName="您";
        OrgInfo org = orgService.getOrgInfo(offLinePasswordModel.getOrgId());
        orgName=org.getOrgName();

        //如果成功要发短信
        if(sr_dto.isSuccess() && sr_dto.isExistData() && StringUtils.isNotEmpty(offLinePasswordModel.getCellPhone())){
            sendCellPhoneMessage(offLinePasswordModel.getCellPhone(),offLinePasswordModel.getPassword(),orgName);
        }
        bdr.setData(sr_dto.getData());

        return bdr;
    }


    /**
     * 修改离线密码
     * @param offLinePasswordModel
     * @return
     */
    @RequestMapping(
            value = "/offlinepassword",
            method = RequestMethod.PUT
    )
    @ResponseBody
    public BaseResponse updateOffLinePassword(@RequestBody OffLinePasswordModel offLinePasswordModel) {

        BaseResponse bdr = new BaseResponse();
        OffLinePasswordDto offLinePasswordDto=buildOffLinePasswordDtoFromModel(offLinePasswordModel);
        //For those old customer, don't have chance to send message to create password, need creat it for them
        //first try to get it with orgid
        ServiceResponse<OffLinePasswordDto> getResult=offLinePasswordService.getOffLinePasswordByOrgId(offLinePasswordDto.getOrgId());

        //写入数据库
        ServiceResponse sr_dto;
        if(getResult.isSuccess() && getResult.isExistData()){
            sr_dto = offLinePasswordService.updateOffLinePasswordByOrgId(offLinePasswordDto);
        }else {
            //create it if not existed
            sr_dto = offLinePasswordService.addOffLinePassword(offLinePasswordDto);
        }

        //get the org name according to orgId

        String orgName="您";
        OrgInfo org = orgService.getOrgInfo(offLinePasswordModel.getOrgId());
        orgName=org.getOrgName();

        //如果成功要发短信
        if(sr_dto.isSuccess() && sr_dto.isExistData() && StringUtils.isNotEmpty(offLinePasswordModel.getCellPhone())){
            sendCellPhoneMessage(offLinePasswordModel.getCellPhone(),offLinePasswordModel.getPassword(),orgName);
        }

        //推送设备
        List<Long> deviceIds = deviceService.findDeviceIdByType(offLinePasswordModel.getOrgId(), DeviceType.BOARD_ATTENDANCE.getValue());

        //generate xml for devices and push to xml file server
        //If group is used by devices, regenerate the xml
        if(com.alibaba.dubbo.common.utils.CollectionUtils.isNotEmpty(deviceIds)){
            //TODO whether to record failed devices and push xml
            ServiceResponse<List<Long>> updateTask =  deviceConfigService.notifyXmlServer(offLinePasswordModel.getOrgId(),deviceIds);
        }
        bdr.setData(sr_dto.getData());

        return bdr;
    }

    private void sendCellPhoneMessage(String cellPhoneNumber,String password,String orgName){

        Map<String,String> params = new HashMap<>();
        params.put(NewOrgDoorPasswordParams.ORG_NAME.getValue(),orgName);
        params.put(NewOrgDoorPasswordParams.PASSWORD.getValue(),password);
        SmsUtil.sendSms(SMSType.NEW_ORG_DOOR_PASSWORD, cellPhoneNumber, params, mmService);
    }

    private OffLinePasswordDto buildOffLinePasswordDtoFromModel(OffLinePasswordModel offLinePasswordModel){
        OffLinePasswordDto offLinePasswordDto=new  OffLinePasswordDto();
        offLinePasswordDto.setOffLinePasswordId(offLinePasswordModel.getOffLinePasswordId());
        try {
            offLinePasswordDto.setPassword(SmsUtil.EncoderByMd5(offLinePasswordModel.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        offLinePasswordDto.setOrgId(offLinePasswordModel.getOrgId());
        return  offLinePasswordDto;


    }

}
