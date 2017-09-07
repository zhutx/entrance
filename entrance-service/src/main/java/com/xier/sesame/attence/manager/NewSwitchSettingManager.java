package com.xier.sesame.attence.manager;

import com.xier.sesame.attence.domain.NewSwitchSetting;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.rpc.ServiceResponse;

import java.util.List;

/**
 * 门禁开关配置Manager
 * Created by xxu on 2017/3/31.
 */
public interface NewSwitchSettingManager {

    /**
     * 增加门禁开关配置
     * @param switchSetting
     * @return 主键
     */
    public ServiceResponse<Long> addSwitchSetting(NewSwitchSetting switchSetting);


    /**
     * 修改门禁开关配置
     * @param switchSetting
     * @return
     */
    public int updateSwitchSetting(NewSwitchSetting switchSetting);

    /**
     * 修改门禁开关配置，根据orgid和groupid
     * @param switchSetting
     * @return
     */
    public int updateSwitchSettingByGroupIdAndOrgId(NewSwitchSetting switchSetting);

    /**
     * 修改门禁开关配置,只修改属性不为null的字段
     * @param switchSetting
     * @return 修改记录条数
     */
    public int updateSwitchSettingSelective(NewSwitchSetting switchSetting);

    /**
     * 根据ID删除门禁开关配置
     * @param id
     * @param orgId 分表字段
     * @param groupId 组的ID
     * @return
     */
    public int removeSwitchSettingById(Long id, Long orgId,Long groupId);



    /**
     * 根据groupId,orgId删除门禁开关配置
     * @param orgId 分表字段
     * @param groupId 组的ID
     * @return
     */
    public int removeSwitchSettingByOrgIdAndGroupId(Long orgId, Long groupId);


    /**
     * 根据id获取门禁开关配置
     * @param id
     * @param orgId 分表字段
     * @param groupId 组的ID
     * @return
     */
    public NewSwitchSetting getSwitchSettingById(Long id, Long orgId,Long groupId);


    /**
     * 分页查询门禁开关配置
     * @param pagination
     * @param switchSetting
     * @return
     */
    public Pagination<NewSwitchSetting> getPaginationSwitchSetting(Pagination<NewSwitchSetting> pagination, NewSwitchSetting switchSetting);


    /**
     * 获取门禁开关配置数量
     * @param switchSetting
     * @return
     */
    public int getSwitchSettingCount(NewSwitchSetting switchSetting);

    public NewSwitchSetting getSwitchSettingByOrgId(Long orgId,Long groupId);

    public List<NewSwitchSetting> getSwitchSettingListByOrgId(Long orgId);

    public List<NewSwitchSetting> getDeviceBindingSwitchSettings(Long orgId,List<Long> groupIdList);




}
