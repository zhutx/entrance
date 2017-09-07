package com.xier.sesame.attence.service;

import com.xier.sesame.attence.dto.NewSwitchSettingDto;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.common.rpc.dto.PaginationDto;

import java.util.List;

/**
 * Created by xxu on 2017/3/31.
 */
public interface NewSwitchSettingService {

    /**
     * 增加门禁开关配置
     *
     * @param switchSettingDto
     * @return
     */
    public ServiceResponse<Long> addSwitchSetting(NewSwitchSettingDto switchSettingDto);


    /**
     * 修改门禁开关配置
     *
     * @param switchSettingDto
     * @return
     */
    public ServiceResponse<Integer> updateSwitchSetting(NewSwitchSettingDto switchSettingDto);

    /**
     * 修改门禁开关配置,只修改属性不为null的字段
     *
     * @param switchSettingDto
     * @return
     */
    public ServiceResponse<Integer> updateSwitchSettingSelective(NewSwitchSettingDto switchSettingDto);


    /**
     * 根据ID删除门禁开关配置
     *
     * @param id
     * @param orgId 分表字段
     */
    public ServiceResponse<Integer> removeSwitchSettingById(Long id, Long orgId, Long groupId);


    /**
     * 根据id获取门禁开关配置
     *
     * @param id
     * @param orgId 分表字段
     * @return
     */
    public ServiceResponse<NewSwitchSettingDto> getSwitchSettingById(Long id, Long orgId, Long groupId);


    /**
     * 分页查询门禁开关配置
     *
     * @param PaginationDto
     * @param switchSettingDto
     * @return
     */
    public ServiceResponse<PaginationDto<NewSwitchSettingDto>> getPaginationSwitchSetting(PaginationDto<NewSwitchSettingDto> PaginationDto, NewSwitchSettingDto switchSettingDto);


    /**
     * 获取门禁开关配置数量
     *
     * @param switchSettingDto
     * @return
     */
    public ServiceResponse<Integer> getSwitchSettingCount(NewSwitchSettingDto switchSettingDto);

    /**
     * 获取机构的门禁配置信息
     *
     * @param orgId
     * @param groupId
     * @return
     */
    public ServiceResponse<NewSwitchSettingDto> getSwitchSettingByOrgIdAndGroupId(Long orgId, Long groupId);


    /**
     * 根据groupID,orgId删除门禁开关配置
     *
     * @param groupId
     * @param orgId   分表字段
     */
    public ServiceResponse<Integer> removeSwitchSettingByOrgIdAndGroupId(Long orgId, Long groupId);

    /**
     * 获取机构所有的门禁配置信息
     *
     * @param orgId
     * @return
     */
    public ServiceResponse<List<NewSwitchSettingDto>> getSwitchSettingListByOrgId(Long orgId);

    /**
     * 根据设备和group的绑定关系，取出设备下真正起作用的配置
     *
     * @param orgId
     * @param groupIdList
     * @return
     */
    public ServiceResponse<List<NewSwitchSettingDto>> getDeviceBindingSwitchSettings(Long orgId, List<Long> groupIdList);


}
