package com.xier.sesame.attence.dao;

import com.xier.sesame.attence.domain.DeviceConfig;
import com.xier.sesame.common.base.domain.query.Pagination;

/**
 * 设备配置Dao
 * @author zhutx
 *
 */
public interface DeviceConfigDao {
	
	/**
	 * 增加设备配置
	 * @param deviceConfig
	 * @return
	 */
	public void addDeviceConfig(DeviceConfig deviceConfig);
	
	
	/**
	 * 修改设备配置
	 * @param deviceConfig
	 * @return 修改记录条数
	 */
	public int updateDeviceConfig(DeviceConfig deviceConfig);
	
	/**
	 * 修改设备配置,只修改属性不为null的字段
	 * @param deviceConfig
	 * @return 修改记录条数
	 */
	public int updateDeviceConfigSelective(DeviceConfig deviceConfig);
	
	
	/**
	 * 根据ID删除设备配置
	 * @param id
	 * @return 删除记录条数
	 */
	public int removeDeviceConfigById(Long id);
	
	
	/**
	 * 根据id获取设备配置
	 * @param id
	 * @return 
	 */
	public DeviceConfig getDeviceConfigById(Long id);
	
	/**
	 * 根据id获取设备配置,同时加锁
	 * @param id
	 * @return 
	 */
	public DeviceConfig getDeviceConfigByIdForUpdate(Long id);
		
    
	/**
	 * 分页查询设备配置
	 * @param pagination
	 * @param deviceConfig
	 * @return
	 */
	public Pagination<DeviceConfig> getPaginationDeviceConfig(Pagination<DeviceConfig> pagination,DeviceConfig deviceConfig);
	
	/**
	 * 获取设备配置数量
	 * @param deviceConfig
	 * @return
	 */
	public int getDeviceConfigCount(DeviceConfig deviceConfig);
	

	public DeviceConfig getOneDeviceConfig(DeviceConfig deviceConfig);



}
