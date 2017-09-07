package com.xier.sesame.attence.manager.impl;

import com.xier.sesame.attence.domain.DeviceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import com.xier.sesame.attence.manager.DeviceConfigManager;
import com.xier.sesame.attence.dao.DeviceConfigDao;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.idgenerator.service.IdgeneratorService;
import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 设备配置Manager
 * @author zhutx
 */
@Service
public class DeviceConfigManagerImpl implements DeviceConfigManager{

	@Reference
	private IdgeneratorService idgeneratorService;
	
	@Autowired
	private DeviceConfigDao deviceConfigDao;
	
	@Override
	public ServiceResponse<Long> addDeviceConfig(DeviceConfig deviceConfig) {
		ServiceResponse<Long> serviceResponse = idgeneratorService.getNextIdByTypeName("com.xier.sesame.attence.DeviceConfig");
		if (serviceResponse.isSuccess()) {
			deviceConfig.setDeviceConfigId(serviceResponse.getData());
			deviceConfigDao.addDeviceConfig(deviceConfig);
		}
		return serviceResponse;
	}
	
	@Override
	public int updateDeviceConfig(DeviceConfig deviceConfig) {
		return deviceConfigDao.updateDeviceConfig(deviceConfig);
	}
	
	@Override
	public int updateDeviceConfigSelective(DeviceConfig deviceConfig) {
		return deviceConfigDao.updateDeviceConfigSelective(deviceConfig);
	}
	
	@Override
	public int removeDeviceConfigById(Long id) {
		return deviceConfigDao.removeDeviceConfigById(id);
	}
	
	@Override
	public DeviceConfig getDeviceConfigById(Long id) {
		return deviceConfigDao.getDeviceConfigById(id);
	}
	  
	@Override
	public Pagination<DeviceConfig> getPaginationDeviceConfig(Pagination<DeviceConfig> pagination,DeviceConfig deviceConfig) {
		return deviceConfigDao.getPaginationDeviceConfig(pagination, deviceConfig);
	}
	
	@Override
	public int getDeviceConfigCount(DeviceConfig deviceConfig) {
		return deviceConfigDao.getDeviceConfigCount(deviceConfig);
	}

	@Override
	public DeviceConfig getOneDeviceConfig(DeviceConfig deviceConfig) {
		return deviceConfigDao.getOneDeviceConfig(deviceConfig);
	}

}
