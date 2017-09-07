package com.xier.sesame.attence.web.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.moredian.bee.common.web.BaseResponse;
import com.moredian.fishnet.device.enums.YesNoFlag;
import com.moredian.fishnet.device.service.DeviceGroupRelationService;
import com.moredian.fishnet.device.service.DeviceService;
import com.moredian.fishnet.member.service.GroupRangeService;
import com.moredian.fishnet.org.model.GroupInfo;
import com.moredian.fishnet.org.service.GroupService;
import com.xier.sesame.attence.dto.NewSwitchSettingDto;
import com.xier.sesame.attence.service.*;
import com.xier.sesame.attence.web.controller.req.SetGroupConfigModel;
import com.xier.sesame.attence.web.controller.resp.NewSettingData;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.common.web.BaseDataResponse;
import com.xier.sesame.pigeon.push.service.PushService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by xxu on 2017/4/8.
 */
@RestController
@RequestMapping("/attence/newsetting")
public class NewSwitchSettingController {
    private static Logger logger = LoggerFactory.getLogger(NewSwitchSettingController.class);

    @Reference
    private NewSwitchSettingService switchSettingService;
    @Reference
    private DeviceConfigService deviceConfigService;
    @Reference
    private SubjectConfigService subjectSettingService;
    @Reference
    private SubjectService subjectService;
    @Reference
    private PushService pushService;
    @Reference
    private DeviceService deviceService;
    @Reference
    private TimeSettingService timeSettingService;

    @Reference
    private GroupRangeService groupRangeService;

//    @SI
//    private MemberService memberService;

    @Reference
    private GroupService groupService;
    @Reference
    private DeviceGroupRelationService deviceGroupRelationService;

    /**
     * get setting according to groupId and orgId
     */
    @RequestMapping(
            value = "/groupconfig",
            method = RequestMethod.GET
    )
    @ResponseBody
    public BaseDataResponse getGroupConfig(@RequestParam(required = true, value = "orgId") Long orgId, @RequestParam(required = true, value = "groupId") Long groupId) {

        BaseDataResponse bdr = new BaseDataResponse();

        ServiceResponse<NewSwitchSettingDto> sr_dto = switchSettingService.getSwitchSettingByOrgIdAndGroupId(orgId,groupId);
        GroupInfo groupInfo=groupService.getGroupInfo(orgId,groupId);
        bdr.setData(this.buildSettingData(sr_dto.getData(),groupInfo));

        return bdr;
    }



//
//    /**
//     * get setting according to groupId and orgId
//     */
//    @RequestMapping(
//            value = "/mainpagedata",
//            method = RequestMethod.GET
//    )
//    @ResponseBody
//    public BaseResponse getMainPageData(@RequestParam(required = true, value = "orgId") Long orgId) {
//
//        MainPageData data=new MainPageData();
//        BaseResponse bdr = new BaseResponse();
//        try {
//            data.setNoImagePeopleCount(memberService.countNoVerifyImgs(orgId));
//            data.setGroupsCount(groupService.countGroup(orgId));
//            data.setDeviceInfoList(deviceService.findMenjinDevice(orgId));
//        }catch (Exception e){
//            bdr.setMessage("Operation failed");
//            bdr.setResult("1");
//
//        }
//
//
//        bdr.setData(data);
//        bdr.setMessage("操作成功");
//        bdr.setResult("0");
//
//        return bdr;
//    }


    /**
     * get device setting according to deviceSn and orgId
     */
    @RequestMapping(
            value = "/device",
            method = RequestMethod.GET
    )
    @ResponseBody
    public BaseResponse getDeviceSetting(@RequestParam(required = true, value = "orgId") Long orgId,@RequestParam(required = true, value = "deviceSn") String deviceSn) {

        BaseResponse bdr = new BaseResponse();

//		ServiceResponse<SwitchSettingDto> sr_dto = switchSettingService.getSwitchSettingByOrgIdAndGroupId(orgId,groupId);
//		bdr.setData(this.buildSettingData(sr_dto.getData()));

        return bdr;
    }




    @RequestMapping(
            value = "/groupconfig",
            method = RequestMethod.POST
    )
    @ResponseBody
    public BaseResponse createGroupConfig(@RequestBody SetGroupConfigModel setGroupConfigModel) {

        BaseResponse bdr = new BaseResponse();
        NewSwitchSettingDto switchSettingDto=buildSettingDtoFromGroupConfigModel(setGroupConfigModel);

        ServiceResponse<Long> sr_dto = switchSettingService.addSwitchSetting(switchSettingDto);
        bdr.setData(sr_dto.getData());

        return bdr;
    }


    /**
     * 修改成员组的通行时间
     * @param setGroupConfigModel
     * @return
     */
    @RequestMapping(
            value = {"/groupconfig","/management/groupconfig"},
            method = RequestMethod.PUT
    )
    @ResponseBody
    public BaseResponse updateGroupConfig(@RequestBody SetGroupConfigModel setGroupConfigModel) {

        BaseResponse bdr = new BaseResponse();
        NewSwitchSettingDto switchSettingDto=buildSettingDtoFromGroupConfigModel(setGroupConfigModel);

        ServiceResponse<Integer> sr_dto = switchSettingService.updateSwitchSetting(switchSettingDto);
        bdr.setData(sr_dto.getData());

        //update group name
        GroupInfo group = groupService.getGroupInfo(setGroupConfigModel.getOrgId(), setGroupConfigModel.getGroupId());
        if(group.getSystemDefault() == YesNoFlag.NO.getValue()) { // 非系统默认的群组，才允许修改群组名
        	com.moredian.bee.common.rpc.ServiceResponse<Boolean> editResult=groupService.editGroup(setGroupConfigModel.getOrgId(),setGroupConfigModel.getGroupId(),setGroupConfigModel.getGroupName());
        	if(!editResult.isSuccess()) {
//        		editResult.pickDataThrowException();
                bdr.setResult("1");
                bdr.setData("");
                bdr.setMessage(editResult.getErrorContext().getMessage());
                return bdr;
        	}
        }

        //如果修改成功，需要通知和该组相关联的设备

        //need to change member and dept
        if(BooleanUtils.isTrue(setGroupConfigModel.getResetMemberDept())){

            if(BooleanUtils.isTrue(setGroupConfigModel.getIsAllMember())){
              com.moredian.bee.common.rpc.ServiceResponse<Boolean>  updateResult=groupService.updateAllMemberFlag(setGroupConfigModel.getOrgId(),setGroupConfigModel.getGroupId(),1);

                if (!updateResult.isSuccess()) {
                    logger.error("/attence/management/groupconfig update error.UpdateAllMemberFlag error.orgId is" + setGroupConfigModel.getOrgId());
                    bdr.setResult("1");
                    bdr.setData("");
                    bdr.setMessage(updateResult.getErrorContext().getMessage());
                    return bdr;
                }
            }else {
                com.moredian.bee.common.rpc.ServiceResponse<Boolean> resetResult = groupRangeService.resetGroupRange(setGroupConfigModel.getOrgId(), setGroupConfigModel.getGroupId(), setGroupConfigModel.getDepts(), setGroupConfigModel.getMembers());

                if (!resetResult.isSuccess()) {
                    logger.error("/attence/management/groupconfig update error.Reset dept and member error.orgId is" + setGroupConfigModel.getOrgId());
                    bdr.setResult("1");
                    bdr.setData("");
                    bdr.setMessage(resetResult.getErrorContext().getMessage());
                    return bdr;
                }
            }
        }



        List<Long> deviceIds = deviceGroupRelationService.findDeviceIdByGroupId(setGroupConfigModel.getOrgId(),setGroupConfigModel.getGroupId());
        if (CollectionUtils.isEmpty(deviceIds)) {
            //如果组没有关联设备，不需要通知
            bdr.setMessage("没有发现权限组和设备关联");
            bdr.setResult("0");
            bdr.setData(true);
            return bdr;

        }

        //generate xml for devices and push to xml file server
        //If group is used by devices, regenerate the xml
        if(com.alibaba.dubbo.common.utils.CollectionUtils.isNotEmpty(deviceIds)){
            //TODO whether to record failed devices and push xml
            ServiceResponse<List<Long>> updateTask =  deviceConfigService.notifyXmlServer(setGroupConfigModel.getOrgId(),deviceIds);
        }

        //布控

        ServiceResponse<List<Long>> updateTask =  deviceConfigService.deployGroupInfo(setGroupConfigModel.getOrgId(),deviceIds);

        return bdr;
    }

    private NewSwitchSettingDto buildSettingDtoFromGroupConfigModel(SetGroupConfigModel setGroupConfigModel) {
        NewSwitchSettingDto switchSettingDto = new NewSwitchSettingDto();

        switchSettingDto.setGroupId(setGroupConfigModel.getGroupId());
        if(StringUtils.isNotEmpty(setGroupConfigModel.getDayBeginTime())){
            switchSettingDto.setDayBeginTime(setGroupConfigModel.getDayBeginTime());
        }
        if(StringUtils.isNotEmpty(setGroupConfigModel.getDayEndTime())){
            switchSettingDto.setDayEndTime(setGroupConfigModel.getDayEndTime());
        }
        if(StringUtils.isNotEmpty(setGroupConfigModel.getWeekdays())){
            switchSettingDto.setOpenWeekDays(setGroupConfigModel.getWeekdays());
        }
        switchSettingDto.setAttenceSwitch(0);
        switchSettingDto.setStangerSwitch(0);
        switchSettingDto.setOrgId(setGroupConfigModel.getOrgId());
        return switchSettingDto;
    }

    private NewSettingData buildSettingData(NewSwitchSettingDto dto, GroupInfo groupInfo) {
        NewSettingData data = new NewSettingData();
        data.setAttenceSwitch(dto.getAttenceSwitch());
        data.setStangerSwitch(dto.getStangerSwitch());
        data.setGroupId(dto.getGroupId());
        data.setOpenWeekDays(dto.getOpenWeekDays());
        data.setDayBeginTime(dto.getDayBeginTime());
        data.setDayEndTime(dto.getDayEndTime());
        data.setSwitchSettingId(dto.getSwitchSettingId());
        if(groupInfo!=null){
            data.setGroupName(groupInfo.getGroupName());
            data.setSystemDefault(groupInfo.getSystemDefault());
            data.setAllMemberFlag(groupInfo.getAllMemberFlag());
            data.setGroupType(groupInfo.getGroupType());
            data.setMemberSize(groupInfo.getMemberSize());
        }
        return data;
    }

}
