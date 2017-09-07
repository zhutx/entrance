package com.xier.sesame.attence.service.impl;

import com.xier.sesame.attence.domain.SwitchSetting;
import org.springframework.beans.factory.annotation.Autowired;
import com.xier.sesame.attence.manager.SwitchSettingManager;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.alibaba.dubbo.config.annotation.Service;
import com.xier.sesame.attence.dto.SwitchSettingDto;
import com.xier.sesame.common.rpc.dto.PaginationDto;
import com.xier.sesame.attence.service.SwitchSettingService;
import com.xier.sesame.attence.convertor.SwitchSettingConvertor;

/**
 * 门禁开关配置Service
 * @author zhutx
 */
@Service
public class SwitchSettingServiceImpl implements SwitchSettingService{
	
	@Autowired
	private SwitchSettingManager switchSettingManager;
	
	@Override
	public ServiceResponse<Long> addSwitchSetting(SwitchSettingDto switchSettingDto) {
		ServiceResponse<Long> serviceResponse = switchSettingManager.addSwitchSetting(SwitchSettingConvertor.switchSettingDtoToSwitchSetting(switchSettingDto));
		return serviceResponse;
	}
	
	@Override
	public ServiceResponse<Integer> updateSwitchSetting(SwitchSettingDto switchSettingDto) {
		Integer count = switchSettingManager.updateSwitchSetting(SwitchSettingConvertor.switchSettingDtoToSwitchSetting(switchSettingDto)); 
		return new ServiceResponse<Integer>(true, null, count);
	}
	
	@Override
	public ServiceResponse<Integer> updateSwitchSettingSelective(SwitchSettingDto switchSettingDto) {
		Integer count = switchSettingManager.updateSwitchSettingSelective(SwitchSettingConvertor.switchSettingDtoToSwitchSetting(switchSettingDto)); 
		return new ServiceResponse<Integer>(true, null, count);
	}
	
	@Override
	public ServiceResponse<Integer> removeSwitchSettingById(Long id, Long orgId) {
		Integer count = switchSettingManager.removeSwitchSettingById(id, orgId);
		return new ServiceResponse<Integer>(true, null, count);
	}
	
	@Override
	public ServiceResponse<SwitchSettingDto> getSwitchSettingById(Long id, Long orgId) {
		SwitchSetting switchSetting = switchSettingManager.getSwitchSettingById(id, orgId);
		return new ServiceResponse<SwitchSettingDto>(true, null, SwitchSettingConvertor.switchSettingToSwitchSettingDto(switchSetting));
	}
	  
	@Override
	public ServiceResponse<PaginationDto<SwitchSettingDto>> getPaginationSwitchSetting(PaginationDto<SwitchSettingDto> paginationDto,SwitchSettingDto switchSettingDto) {
		Pagination<SwitchSetting> pagination = switchSettingManager.getPaginationSwitchSetting(SwitchSettingConvertor.paginationSwitchSettingDtoToPaginationSwitchSetting(paginationDto) , SwitchSettingConvertor.switchSettingDtoToSwitchSetting(switchSettingDto));
		return new ServiceResponse<PaginationDto<SwitchSettingDto>>(true, null, SwitchSettingConvertor.paginationSwitchSettingToPaginationSwitchSettingDto(pagination));
	}
	
	@Override
	public ServiceResponse<Integer> getSwitchSettingCount(SwitchSettingDto switchSettingDto) {
		Integer count = switchSettingManager.getSwitchSettingCount(SwitchSettingConvertor.switchSettingDtoToSwitchSetting(switchSettingDto));
		return new ServiceResponse<Integer>(true, null, count);
	}

	@Override
	public ServiceResponse<SwitchSettingDto> getSwitchSettingByOrgId(Long orgId) {
		SwitchSetting switchSetting = switchSettingManager.getSwitchSettingByOrgId(orgId);
		return new ServiceResponse<SwitchSettingDto>(true, null, SwitchSettingConvertor.switchSettingToSwitchSettingDto(switchSetting));
	}




}
