package com.xier.sesame.attence.dao;

import com.xier.sesame.attence.domain.NewSwitchSetting;
import com.xier.sesame.common.base.domain.query.Pagination;

import java.util.List;
/**
 * Created by xxu on 2017/3/31.
 * 门禁开关配置Dao
 */
public interface NewSwitchSettingDao {


    /**
     * 增加门禁开关配置
     * @param switchSetting
     * @return
     */
    public void addSwitchSetting(NewSwitchSetting switchSetting);


    /**
     * 修改门禁开关配置
     * @param switchSetting
     * @return 修改记录条数
     */
    public int updateSwitchSetting(NewSwitchSetting switchSetting);

    /**
     * 根据orgid和groupid来修改门禁开关配置
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
     * @return 删除记录条数
     */
    public int removeSwitchSettingById(Long id, Long orgId,Long groupId);



    /**
     * 根据orgId,groupId删除门禁开关配置
     * @param groupId 组字段
     * @param orgId 分表字段
     * @return 删除记录条数
     */
    public int removeSwitchSettingByOrgIdAndGroupId(Long orgId,Long groupId);



    /**
     * 根据id获取门禁开关配置
     * @param id
     * @param orgId 分表字段
     * @param groupId 分组ID
     * @return
     */
    public NewSwitchSetting getSwitchSettingById(Long id, Long orgId,Long groupId);

    /**
     * 根据id获取门禁开关配置,同时加锁
     * @param id
     * @param orgId 分表字段
     * @param groupId 分组ID
     * @return
     */
    public NewSwitchSetting getSwitchSettingByIdForUpdate(Long id, Long orgId,Long groupId);


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


    /**
     * 根据orgId，groupId查询该机构下所有的门禁开关配置
     * @param orgId
     * @param groupId 分组ID
     * @return
     */
    public NewSwitchSetting getSwitchSettingByOrgIdAndGroupId(Long orgId,Long groupId);



    /**
     * 根据orgId查询该机构下所有的门禁开关配置
     * @param orgId
     * @return List<SwitchSetting>
     */
    public List<NewSwitchSetting> getSwitchSettingListByOrgId(Long orgId);


    /**
     * 根据设备和group的绑定关系，取出设备下真正起作用的配置
     * @param orgId
     * @param groupIdList
     * @return
     */
    public List<NewSwitchSetting> getDeviceBindingSwitchSettings(Long orgId,List<Long> groupIdList);



}


