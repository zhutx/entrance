package com.xier.sesame.attence.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xier.attendflysync.service.DataSyncService;
import com.xier.sesame.attence.dao.SwitchSettingDao;
import com.xier.sesame.attence.domain.SwitchSetting;
import com.xier.sesame.attence.manager.SwitchSettingManager;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.idgenerator.service.IdgeneratorService;

/**
 * 门禁开关配置Manager
 * @author zhutx
 */
@Service
public class SwitchSettingManagerImpl implements SwitchSettingManager{

	@Reference
	private IdgeneratorService idgeneratorService;
	@Reference
	private DataSyncService dataSyncService;
	@Autowired
	private SwitchSettingDao switchSettingDao;
	
	@Override
	public ServiceResponse<Long> addSwitchSetting(SwitchSetting switchSetting) {
		ServiceResponse<Long> serviceResponse = idgeneratorService.getNextIdByTypeName("com.xier.sesame.attence.SwitchSetting");
		if (serviceResponse.isSuccess()) {
			switchSetting.setSwitchSettingId(serviceResponse.getData());
			switchSettingDao.addSwitchSetting(switchSetting);
		}
		return serviceResponse;
	}
	
	@Override
	public int updateSwitchSetting(SwitchSetting switchSetting) {
		return switchSettingDao.updateSwitchSetting(switchSetting);
	}
	
	@Override
	public int updateSwitchSettingSelective(SwitchSetting switchSetting) {
		
		/*if(switchSetting.getAttenceSwitch() != null && switchSetting.getAttenceSwitch() == YesNoFlag.YES.getValue()) {
			dataSyncService.startSyncAllUserInfo(switchSetting.getOrgId());
		}*/
		
		return switchSettingDao.updateSwitchSettingSelective(switchSetting);
	}
	
	@Override
	public int removeSwitchSettingById(Long id, Long orgId) {
		return switchSettingDao.removeSwitchSettingById(id, orgId);
	}
	
	@Override
	public SwitchSetting getSwitchSettingById(Long id, Long orgId) {
		return switchSettingDao.getSwitchSettingById(id, orgId);
	}
	  
	@Override
	public Pagination<SwitchSetting> getPaginationSwitchSetting(Pagination<SwitchSetting> pagination,SwitchSetting switchSetting) {
		return switchSettingDao.getPaginationSwitchSetting(pagination, switchSetting);
	}
	
	@Override
	public int getSwitchSettingCount(SwitchSetting switchSetting) {
		return switchSettingDao.getSwitchSettingCount(switchSetting);
	}

	@Override
	public SwitchSetting getSwitchSettingByOrgId(Long orgId) {
		return switchSettingDao.getSwitchSettingByOrgId(orgId);
	}



}
