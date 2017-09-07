package com.xier.sesame.attence.dao;

import com.xier.sesame.attence.domain.SwitchSetting;
import com.xier.sesame.common.base.domain.query.Pagination;

/**
 * 门禁开关配置Dao
 * @author zhutx
 *
 */
public interface SwitchSettingDao {
	
	/**
	 * 增加门禁开关配置
	 * @param switchSetting
	 * @return
	 */
	public void addSwitchSetting(SwitchSetting switchSetting);
	
	
	/**
	 * 修改门禁开关配置
	 * @param switchSetting
	 * @return 修改记录条数
	 */
	public int updateSwitchSetting(SwitchSetting switchSetting);
	
	/**
	 * 修改门禁开关配置,只修改属性不为null的字段
	 * @param switchSetting
	 * @return 修改记录条数
	 */
	public int updateSwitchSettingSelective(SwitchSetting switchSetting);
	
	
	/**
	 * 根据ID删除门禁开关配置
	 * @param id
	 * @param orgId 分表字段
	 * @return 删除记录条数
	 */
	public int removeSwitchSettingById(Long id, Long orgId);
	
	
	/**
	 * 根据id获取门禁开关配置
	 * @param id
	 * @param orgId 分表字段
	 * @return 
	 */
	public SwitchSetting getSwitchSettingById(Long id, Long orgId);
	
	/**
	 * 根据id获取门禁开关配置,同时加锁
	 * @param id
	 * @param orgId 分表字段
	 * @return 
	 */
	public SwitchSetting getSwitchSettingByIdForUpdate(Long id, Long orgId);
		
    
	/**
	 * 分页查询门禁开关配置
	 * @param pagination
	 * @param switchSetting
	 * @return
	 */
	public Pagination<SwitchSetting> getPaginationSwitchSetting(Pagination<SwitchSetting> pagination,SwitchSetting switchSetting);
	
	/**
	 * 获取门禁开关配置数量
	 * @param switchSetting
	 * @return
	 */
	public int getSwitchSettingCount(SwitchSetting switchSetting);
	

	public SwitchSetting getSwitchSettingByOrgId(Long orgId);



}
