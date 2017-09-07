package com.xier.sesame.attence.dao.mybatis;

import com.xier.sesame.attence.dao.DeviceConfigDao;
import com.xier.sesame.attence.domain.DeviceConfig;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.base.dao.BaseDaoMybatis;
import org.springframework.stereotype.Repository;

/**
 * 设备配置Dao
 * @author zhutx
 *
 */
@Repository
public class DeviceConfigDaoMybatis extends BaseDaoMybatis implements DeviceConfigDao {
	
	@Override
	public void addDeviceConfig(DeviceConfig deviceConfig) {
		this.getSqlSession().insert("com.xier.sesame.attence.DeviceConfig.addDeviceConfig", deviceConfig);
	}
		
	@Override
	public int updateDeviceConfig(DeviceConfig deviceConfig) {
		return this.getSqlSession().update("com.xier.sesame.attence.DeviceConfig.updateDeviceConfig", deviceConfig);
	}
	
	@Override
	public int updateDeviceConfigSelective(DeviceConfig deviceConfig) {
		return this.getSqlSession().update("com.xier.sesame.attence.DeviceConfig.updateDeviceConfigSelective", deviceConfig);
	}
	
	@Override
	public int removeDeviceConfigById(Long id) {
		return this.getSqlSession().delete("com.xier.sesame.attence.DeviceConfig.removeDeviceConfigById", id);
	}
	
	@Override
	public DeviceConfig getDeviceConfigById(Long id) {
		return (DeviceConfig)this.getSqlSession().selectOne("com.xier.sesame.attence.DeviceConfig.getDeviceConfigById", id);
	}
	
	@Override
	public DeviceConfig getDeviceConfigByIdForUpdate(Long id) {
		return (DeviceConfig)this.getSqlSession().selectOne("com.xier.sesame.attence.DeviceConfig.getDeviceConfigByIdForUpdate", id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pagination<DeviceConfig> getPaginationDeviceConfig(Pagination<DeviceConfig> pagination,DeviceConfig deviceConfig) {
		return this.getPagination(pagination,deviceConfig, "com.xier.sesame.attence.DeviceConfig.getDeviceConfigCount", "com.xier.sesame.attence.DeviceConfig.getPaginationDeviceConfig");
	}	
	
	@Override
	public int getDeviceConfigCount(DeviceConfig deviceConfig) {
		return this.getSqlSession().selectOne("com.xier.sesame.attence.DeviceConfig.getDeviceConfigCount", deviceConfig);
	}

	@Override
	public DeviceConfig getOneDeviceConfig(DeviceConfig deviceConfig) {
		return this.getSqlSession().selectOne("com.xier.sesame.attence.DeviceConfig.getDeviceConfig", deviceConfig);
	}
	



}
