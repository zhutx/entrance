package com.xier.sesame.attence.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.moredian.fishnet.device.request.DeviceUpdateRequest;
import com.moredian.fishnet.device.service.DeviceGroupRelationService;
import com.moredian.fishnet.device.service.DeviceService;
import com.moredian.fishnet.member.service.GroupRangeService;
import com.moredian.fishnet.org.model.GroupInfo;
import com.moredian.fishnet.org.request.GroupAddRequest;
import com.moredian.fishnet.org.service.GroupService;
import com.xier.sesame.attence.dto.AttenceDeployDto;
import com.xier.sesame.attence.dto.NewSwitchSettingDto;
import com.xier.sesame.attence.service.AttenceDeployService;
import com.xier.sesame.attence.service.DeviceConfigService;
import com.xier.sesame.attence.service.NewSwitchSettingService;
import com.xier.sesame.attence.service.SubjectConfigService;
import com.xier.sesame.attence.service.SubjectService;
import com.xier.sesame.attence.service.TimeSettingService;
import com.xier.sesame.attence.web.controller.req.DeviceAndGroupBindingModel;
import com.xier.sesame.attence.web.controller.req.UserGroupModel;
import com.xier.sesame.attence.web.controller.resp.GroupInfoWithSetting;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.common.web.BaseDataResponse;
import com.xier.sesame.pigeon.push.service.PushService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Created by xxu on 2017/4/1.
 */

@RestController
@RequestMapping("/attence")
public class UserController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("${global.beginTime:00:00}")
    private String globalBeginTime;

    @Value("${global.endTime:23:59}")
    public String globalEndTime;

    public static final String DEFAULT_WEEKDAYS="1,2,3,4,5,6,7";

    @Reference
    private NewSwitchSettingService switchSettingService;
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
    private AttenceDeployService attenceDeployService;

    @Reference
    private DeviceConfigService deviceConfigService;

    @Reference
    private GroupService groupService;

    @Reference
    private GroupService newGroupService;

    @Reference
    private GroupRangeService groupRangeService;
    @Reference
    private DeviceGroupRelationService deviceGroupRelationService;


//    @Reference
//    private GroupDeviceService groupDeviceService;

    /**
     * 创建用户组
     * 创建用户组一定不是全员组和访客组
     *
     * @param userGroupModel
     * @return BaseResponse 包含groupId和switchSettingId
     */
    @ApiOperation(value = "创建群组", httpMethod = "POST", notes = "创建一个群组")
    @RequestMapping(value = {"/user/groups", "/management/groups"}, method = RequestMethod.POST)
    public BaseDataResponse createGroup(@ApiParam(value = "用户组模型", name = "userGroupModel") @RequestBody UserGroupModel userGroupModel) {

        BaseDataResponse bdr = new BaseDataResponse();
        //first build GroupAddRequest

        GroupAddRequest groupAddRequest = buildGroupAddRequest(userGroupModel);

        com.moredian.bee.common.rpc.ServiceResponse<Long> addGroupResponse = newGroupService.addGroup(groupAddRequest);
//        ServiceResponse<Long> addGroupResponse = groupService.addSubOrg(groupAddRequest);
        if (!addGroupResponse.isSuccess()) {
            bdr.setResult("1");
            bdr.setData("");
            bdr.setMessage(addGroupResponse.getErrorContext().getMessage());
            return bdr;
        }

        //second to create group config data for attance
        Long groupId = addGroupResponse.getData();

        //bind member and dept to group
        if (BooleanUtils.isTrue(userGroupModel.getResetMemberDept())) {
            com.moredian.bee.common.rpc.ServiceResponse<Boolean> resetResult = groupRangeService.resetGroupRange(userGroupModel.getOrgId(), groupId, userGroupModel.getDepts(), userGroupModel.getMembers());

            if (!resetResult.isSuccess()) {
                logger.error("/attence/user/groups create error.Reset dept and member error.orgId is" + userGroupModel.getOrgId());
                bdr.setResult("1");
                bdr.setData("");
                bdr.setMessage(resetResult.getErrorContext().getMessage());
                return bdr;
            }
        }

        //need get groupCode after create it
        GroupInfo group = groupService.getGroupInfo(userGroupModel.getOrgId(), groupId);
        String groupCode = group.getGroupCode();

        NewSwitchSettingDto switchSettingDto = buildSettingDtoFromUserGroupModel(userGroupModel, groupId, groupCode,userGroupModel.getWeekdays());

        ServiceResponse<Long> sr_dto = switchSettingService.addSwitchSetting(switchSettingDto);
        Map<String, Long> resultMap = new HashMap();
        resultMap.put("groupId", addGroupResponse.getData());
        resultMap.put("switchSettingId", sr_dto.getData());
        bdr.setData(resultMap);

        bdr.setMessage("创建组成功");

        return bdr;
    }


    /**
     * 根据orgId，取得所有group信息，包括group的信息
     *
     * @param orgId
     * @return
     */
    private List<GroupInfo> getAllGroupInfoByOrgId(Long orgId) {

        List<GroupInfo> groupInfos = newGroupService.findGroup(orgId);
        return groupInfos;
    }


    /**
     * 查询机构下所有的群组信息
     *
     * @param orgId
     * @return
     */
    @RequestMapping(value = "/user/groups", method = RequestMethod.GET)
    public BaseDataResponse getAllGroupSettings(@RequestParam(required = true, value = "orgId") Long orgId) {

        BaseDataResponse bdr = new BaseDataResponse();

        //first,取得全员组信息

        List<GroupInfo> groupInfos = getAllGroupInfoByOrgId(orgId);
        if (CollectionUtils.isEmpty(groupInfos)) {
            logger.error("/attence/user/groups get error.No groups found from group service.orgId is" + orgId);
        }

        List<GroupInfoWithSetting> groupInfoWithSettings = new ArrayList<>();
        for (GroupInfo groupInfo : groupInfos) {
            ServiceResponse<NewSwitchSettingDto> sr = switchSettingService.getSwitchSettingByOrgIdAndGroupId(orgId, groupInfo.getGroupId());

            GroupInfoWithSetting groupInfoWithSetting = buildGroupInfoWithSetting(groupInfo, orgId);
            if (sr.isSuccess() && sr.getData() != null) {
                groupInfoWithSetting.setDayBeginTime(sr.getData().getDayBeginTime());
                groupInfoWithSetting.setDayEndTime(sr.getData().getDayEndTime());
                groupInfoWithSetting.setOpenWeekDays(sr.getData().getOpenWeekDays());
            }
            groupInfoWithSettings.add(groupInfoWithSetting);
        }


        bdr.setData(groupInfoWithSettings);
        bdr.setResult("0");

        bdr.setMessage("操作成功");

        return bdr;
    }

    /**
     * 查询和设备关联的所有的群组信息
     *
     * @param orgId
     * @return
     */
    @RequestMapping(value = "/user/device/groups", method = RequestMethod.GET)
    public BaseDataResponse getDeviceGroupSettings(@RequestParam(required = true, value = "orgId") Long orgId, @RequestParam(required = true, value = "deviceId") Long deviceId) {

        BaseDataResponse bdr = new BaseDataResponse();
//        List<com.moredian.fishnet.device.model.GroupInfo> groupInfos=deviceService.findDeviceGroup(orgId,deviceId );
        List<com.moredian.fishnet.device.model.GroupInfo> groupInfos = deviceGroupRelationService.findGroupByDeviceId(orgId, deviceId);
        // TODO
        List<GroupInfoWithSetting> groupInfoWithSettings = new ArrayList<>();
        for (com.moredian.fishnet.device.model.GroupInfo groupInfo : groupInfos) {
            ServiceResponse<NewSwitchSettingDto> sr = switchSettingService.getSwitchSettingByOrgIdAndGroupId(orgId, groupInfo.getGroupId());

            String groupName = "";
            String groupCode = "";

            GroupInfo groupInfo1 = newGroupService.getGroupInfo(orgId, groupInfo.getGroupId());
//            ServiceResponse<SubOrgDto> subOrgDtoServiceResponse = groupService.getSubOrgById(groupInfo.getSubOrgId(), orgId);
            //TODO need add group name,group default flag
            GroupInfoWithSetting groupInfoWithSetting = new GroupInfoWithSetting();

            if (null != groupInfo1) {
                groupName = groupInfo1.getGroupName();
                groupCode = groupInfo1.getGroupCode();
                groupInfoWithSetting.setGroupType(groupInfo1.getGroupType());
                groupInfoWithSetting.setAllMemberFlag(groupInfo1.getAllMemberFlag());
                groupInfoWithSetting.setMemberSize(groupInfo1.getMemberSize());
                groupInfoWithSetting.setSystemDefault(groupInfo1.getSystemDefault());

            }
            groupInfoWithSetting.setGroupId(groupInfo.getGroupId());
            groupInfoWithSetting.setGroupName(groupName);

            if (sr.isSuccess() && sr.getData() != null) {
                groupInfoWithSetting.setDayBeginTime(sr.getData().getDayBeginTime());
                groupInfoWithSetting.setDayEndTime(sr.getData().getDayEndTime());
                groupInfoWithSetting.setOpenWeekDays(sr.getData().getOpenWeekDays());

            } else {

                String beginTime = "";
                String endTime = "";
                //对于老机构或者机构创建时未同步过来的组信息，需要在这里补上
                logger.info("机构的权限组信息未同步，现在更新，机构Id是" + orgId);
                UserGroupModel userGroupModel = new UserGroupModel();
                userGroupModel.setOrgId(orgId);
                userGroupModel.setGroupName(groupName);
                beginTime = globalBeginTime;
                endTime = globalEndTime;
                userGroupModel.setDayBeginTime(beginTime);
                userGroupModel.setDayEndTime(endTime);

                NewSwitchSettingDto switchSettingDto = buildSettingDtoFromUserGroupModel(userGroupModel, groupInfo.getGroupId(), groupCode,DEFAULT_WEEKDAYS);

                ServiceResponse<Long> addResult = switchSettingService.addSwitchSetting(switchSettingDto);
                if (addResult.isSuccess() && addResult.isExistData()) {
                    logger.info("创建群组成功，群组Id是" + addResult.getData());
                    groupInfoWithSetting.setDayBeginTime(beginTime);
                    groupInfoWithSetting.setDayEndTime(endTime);
                }
            }

            groupInfoWithSettings.add(groupInfoWithSetting);
        }


        bdr.setData(groupInfoWithSettings);

        bdr.setMessage("操作成功");

        return bdr;
    }


    /**
     * Change the binding between device and group.Add or remove group from device
     *
     * @param deviceAndGroupBindingModel
     * @return BaseResponse
     */
    @RequestMapping(value = {"/user/groupbinding","/management/groupbinding"}, method = RequestMethod.PUT)
    public BaseDataResponse updateDeviceAndGroupBinding(@RequestBody DeviceAndGroupBindingModel deviceAndGroupBindingModel) {


        BaseDataResponse bdr = new BaseDataResponse();
        //It't not allowed to remove all groups from attence
        if (deviceAndGroupBindingModel != null && CollectionUtils.isEmpty(deviceAndGroupBindingModel.getGroupIdList())) {
            bdr.setResult("1");
            bdr.setMessage("设备至少要关联一个组，请重新选择");
            return bdr;

        }

        //add in 1.0.3,update device name
        if(StringUtils.isNotEmpty(deviceAndGroupBindingModel.getDeviceName())){
            DeviceUpdateRequest duRequest=new DeviceUpdateRequest();
            duRequest.setOrgId(deviceAndGroupBindingModel.getOrgId());
            duRequest.setDeviceId(deviceAndGroupBindingModel.getDeviceId());
            duRequest.setDeviceName(deviceAndGroupBindingModel.getDeviceName());
           com.moredian.bee.common.rpc.ServiceResponse<Boolean> duResult= deviceService.updateDevice(duRequest);
           if(!duResult.isSuccess()){
               bdr.setResult("1");
               bdr.setMessage("更新设备名称失败，请重试");
               return bdr;
           }
        }

        com.moredian.bee.common.rpc.ServiceResponse<Boolean> sr = deviceGroupRelationService.resetDeviceGroupRelation(deviceAndGroupBindingModel.getOrgId(), deviceAndGroupBindingModel.getDeviceId(), deviceAndGroupBindingModel.getGroupIdList());
        if(!sr.isSuccess()) {
        	sr.pickDataThrowException();
        }
        //4:generate xml for devices and push to xml file server
        //If group is used by devices, regenerate the xml
        List<Long> deviceIds = new ArrayList<>();
        deviceIds.add(deviceAndGroupBindingModel.getDeviceId());
        if (CollectionUtils.isNotEmpty(deviceIds)) {
            //TODO whether to record failed devices and push xml
            ServiceResponse<List<Long>> updateTask = deviceConfigService.notifyXmlServer(deviceAndGroupBindingModel.getOrgId(), deviceIds);


            if (!updateTask.isSuccess()) {
                bdr.setResult("1");
                bdr.setMessage("配置保存成功，通知设备失败");
            } else {
                bdr.setResult("0");
                bdr.setMessage("操作成功");
            }
        }
        //开始布控


        //TODO, NEED REMOVE FAILED GROUPID FROM GROUPID LIST

        List<NewSwitchSettingDto> settingDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(deviceAndGroupBindingModel.getGroupIdList())) {
            for (Long groupId : deviceAndGroupBindingModel.getGroupIdList()) {
                ServiceResponse<NewSwitchSettingDto> settingDto = switchSettingService.getSwitchSettingByOrgIdAndGroupId(deviceAndGroupBindingModel.getOrgId(), groupId);
//                ServiceResponse<SubOrgDto> subOrgDto = groupService.getSubOrgById(groupId, deviceAndGroupBindingModel.getOrgId());
                if (settingDto.isSuccess() && settingDto.isExistData()) {
                    settingDtoList.add(settingDto.getData());
                }
            }
        }
        AttenceDeployDto attenceDeployDto = new AttenceDeployDto();
        attenceDeployDto.setOrgId(deviceAndGroupBindingModel.getOrgId());
        attenceDeployDto.setDeviceId(deviceAndGroupBindingModel.getDeviceId());
        attenceDeployDto.setGroupSettingList(settingDtoList);

        try {
            attenceDeployService.addDeviceConfig(attenceDeployDto);
        } catch (Exception e) {
            bdr.setResult("1");
            bdr.setMessage("配置保存成功，通知设备失败");
            bdr.setData(null);
            return bdr;
        }

        //布控结束
        bdr.setData(bdr.getData());


        return bdr;
    }


    private GroupInfoWithSetting buildGroupInfoWithSetting(GroupInfo groupInfo, Long orgId) {
        GroupInfoWithSetting groupInfoWithSetting = new GroupInfoWithSetting();
        groupInfoWithSetting.setGroupId(groupInfo.getGroupId());
        groupInfoWithSetting.setOrgId(orgId);
        groupInfoWithSetting.setGroupName(groupInfo.getGroupName());
        groupInfoWithSetting.setMemberSize(groupInfo.getMemberSize());
        groupInfoWithSetting.setAllMemberFlag(groupInfo.getAllMemberFlag());
        groupInfoWithSetting.setGroupType(groupInfo.getGroupType());
//        groupInfoWithSetting.setDevices(groupInfo.getDevices());
//        groupInfoWithSetting.setMembers(groupInfo.getMembers());
        groupInfoWithSetting.setSystemDefault(groupInfo.getSystemDefault());
        return groupInfoWithSetting;

    }


    @RequestMapping(value = {"/user/group","/management/groups"}, method = RequestMethod.DELETE)
    public BaseDataResponse deleteGroup(@RequestParam(required = true, value = "orgId") Long orgId, @RequestParam(required = true, value = "groupId") Long groupId) {

        BaseDataResponse bdr = new BaseDataResponse();
        //1:get the device list from device service.Used to generate xml
        List<Long> deviceIds = deviceGroupRelationService.findDeviceIdByGroupId(orgId, groupId);

        //2:remove group in group service

        com.moredian.bee.common.rpc.ServiceResponse<Boolean> deleteGroupResult = newGroupService.deleteGroup(orgId, groupId);
        if (BooleanUtils.isNotTrue(deleteGroupResult.isSuccess())) {
            bdr.setResult("1");
            bdr.setData("");
            bdr.setMessage(deleteGroupResult.getErrorContext().getMessage());
            return bdr;
        }
// 3: to remove group config data from attance setting table

        ServiceResponse<Integer> sr_dto = switchSettingService.removeSwitchSettingByOrgIdAndGroupId(orgId, groupId);
        bdr.setData(sr_dto.getData());
        bdr.setMessage("删除组成功");

        //4:generate xml for devices and push to xml file server
        //If group is used by devices, regenerate the xml
        if (CollectionUtils.isNotEmpty(deviceIds)) {
            //TODO whether to record failed devices and push xml
            ServiceResponse<List<Long>> updateTask = deviceConfigService.notifyXmlServer(orgId, deviceIds);
        }


        //开始布控


        //TODO, NEED REMOVE FAILED GROUPID FROM GROUPID LIST

        for (Long deviceId : deviceIds) {
            List<NewSwitchSettingDto> settingDtoList = new ArrayList<>();
            
            List<Long> groupIdList = deviceGroupRelationService.findGroupIdByDeviceId(orgId, deviceId);
            
            for (Long bhgroupId : groupIdList) {
                ServiceResponse<NewSwitchSettingDto> settingDto;
                if (!groupId.equals(bhgroupId)) {
                    settingDto = switchSettingService.getSwitchSettingByOrgIdAndGroupId(orgId, bhgroupId);
                    if (settingDto.isSuccess() && settingDto.isExistData()) {
                        settingDtoList.add(settingDto.getData());
                    }
                }

//                ServiceResponse<SubOrgDto> subOrgDto = groupService.getSubOrgById(groupId, deviceAndGroupBindingModel.getOrgId());

            }

            AttenceDeployDto attenceDeployDto = new AttenceDeployDto();
            attenceDeployDto.setOrgId(orgId);
            attenceDeployDto.setDeviceId(deviceId);
            attenceDeployDto.setGroupSettingList(settingDtoList);

            try {
                attenceDeployService.addDeviceConfig(attenceDeployDto);
            } catch (Exception e) {
                logger.error("删除群组时布控失败，群组ID是" + groupId + "设备ID是" + deviceId);
            }
        }

        //布控结束


        return bdr;
    }


    private GroupAddRequest buildGroupAddRequest(UserGroupModel userGroupModel) {
        GroupAddRequest groupAddRequest = new GroupAddRequest();
        groupAddRequest.setMembers(userGroupModel.getMembers());
        groupAddRequest.setOrgId(userGroupModel.getOrgId());
        groupAddRequest.setGroupName(userGroupModel.getGroupName());
        groupAddRequest.setAllMember(userGroupModel.getIsAllMember());
        groupAddRequest.setDepts(userGroupModel.getDepts());
        return groupAddRequest;
    }


    private NewSwitchSettingDto buildSettingDtoFromUserGroupModel(UserGroupModel userGroupModel, Long groupId, String groupCode,String openWeekDays) {
        NewSwitchSettingDto switchSettingDto = new NewSwitchSettingDto();

        switchSettingDto.setGroupId(groupId);
        switchSettingDto.setDayBeginTime(userGroupModel.getDayBeginTime());
        switchSettingDto.setDayEndTime(userGroupModel.getDayEndTime());
        if(StringUtils.isEmpty(openWeekDays)){
            switchSettingDto.setOpenWeekDays(DEFAULT_WEEKDAYS);
        }else{
            switchSettingDto.setOpenWeekDays(openWeekDays);
        }
        switchSettingDto.setAttenceSwitch(0);
        switchSettingDto.setStangerSwitch(0);
        switchSettingDto.setOrgId(userGroupModel.getOrgId());
        switchSettingDto.setGroupCode(groupCode);
        return switchSettingDto;

    }


}
