package com.xier.sesame.attence.dto;

import com.xier.sesame.common.rpc.dto.BaseDto;

import java.util.List;

/**
 * Created by xxu on 2017/4/8.
 */
public class AttenceDeployDto  extends BaseDto {

    private static final long serialVersionUID = -0L;

    //权限组列表
    private List<NewSwitchSettingDto> groupSettingList;

    //机构Id
    private Long orgId;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    //设备ID
    private Long deviceId;

    public List<NewSwitchSettingDto> getGroupSettingList() {
        return groupSettingList;
    }

    public void setGroupSettingList(List<NewSwitchSettingDto> groupSettingList) {
        this.groupSettingList = groupSettingList;
    }
}
