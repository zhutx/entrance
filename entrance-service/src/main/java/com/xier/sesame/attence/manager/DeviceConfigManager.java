package com.xier.sesame.attence.manager;

import com.xier.sesame.attence.domain.DeviceConfig;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.rpc.ServiceResponse;

/**
 * 设备配置Manager
 * @author zhutx
 *
 */
public interface DeviceConfigManager {
	
	/**
	 * 增加设备配置
	 * @param deviceConfig
	 * @return 主键
	 */
	public ServiceResponse<Long> addDeviceConfig(DeviceConfig deviceConfig);
	
	
	/**
	 * 修改设备配置
	 * @param deviceConfig
	 * @return
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
	 * @return
	 */
	public int removeDeviceConfigById(Long id);
	
	
	/**
	 * 根据id获取设备配置
	 * @param id
	 * @return
	 */
	public DeviceConfig getDeviceConfigById(Long id);
	
    
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
