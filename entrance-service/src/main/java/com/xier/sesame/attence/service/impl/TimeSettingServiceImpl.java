package com.xier.sesame.attence.service.impl;

import com.xier.sesame.attence.domain.TimeSetting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.xier.sesame.attence.manager.TimeSettingManager;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.alibaba.dubbo.config.annotation.Service;
import com.xier.sesame.attence.dto.TimeSettingDto;
import com.xier.sesame.common.rpc.dto.PaginationDto;
import com.xier.sesame.attence.service.TimeSettingService;
import com.xier.sesame.attence.convertor.TimeSettingConvertor;

/**
 * 门禁日历配置Service
 * @author zhutx
 */
@Service
public class TimeSettingServiceImpl implements TimeSettingService{
	
	@Autowired
	private TimeSettingManager timeSettingManager;
	
	@Override
	public ServiceResponse<Long> addTimeSetting(TimeSettingDto timeSettingDto) {
		ServiceResponse<Long> serviceResponse = timeSettingManager.addTimeSetting(TimeSettingConvertor.timeSettingDtoToTimeSetting(timeSettingDto));
		return serviceResponse;
	}
	
	@Override
	public ServiceResponse<Integer> updateTimeSetting(TimeSettingDto timeSettingDto) {
		Integer count = timeSettingManager.updateTimeSetting(TimeSettingConvertor.timeSettingDtoToTimeSetting(timeSettingDto)); 
		return new ServiceResponse<Integer>(true, null, count);
	}
	
	@Override
	public ServiceResponse<Integer> updateTimeSettingSelective(TimeSettingDto timeSettingDto) {
		Integer count = timeSettingManager.updateTimeSettingSelective(TimeSettingConvertor.timeSettingDtoToTimeSetting(timeSettingDto)); 
		return new ServiceResponse<Integer>(true, null, count);
	}
	
	@Override
	public ServiceResponse<Integer> removeTimeSettingById(Long id, Long orgId) {
		Integer count = timeSettingManager.removeTimeSettingById(id, orgId);
		return new ServiceResponse<Integer>(true, null, count);
	}
	
	@Override
	public ServiceResponse<TimeSettingDto> getTimeSettingById(Long id, Long orgId) {
		TimeSetting timeSetting = timeSettingManager.getTimeSettingById(id, orgId);
		return new ServiceResponse<TimeSettingDto>(true, null, TimeSettingConvertor.timeSettingToTimeSettingDto(timeSetting));
	}
	  
	@Override
	public ServiceResponse<PaginationDto<TimeSettingDto>> getPaginationTimeSetting(PaginationDto<TimeSettingDto> paginationDto,TimeSettingDto timeSettingDto) {
		Pagination<TimeSetting> pagination = timeSettingManager.getPaginationTimeSetting(TimeSettingConvertor.paginationTimeSettingDtoToPaginationTimeSetting(paginationDto) , TimeSettingConvertor.timeSettingDtoToTimeSetting(timeSettingDto));
		return new ServiceResponse<PaginationDto<TimeSettingDto>>(true, null, TimeSettingConvertor.paginationTimeSettingToPaginationTimeSettingDto(pagination));
	}
	
	@Override
	public ServiceResponse<Integer> getTimeSettingCount(TimeSettingDto timeSettingDto) {
		Integer count = timeSettingManager.getTimeSettingCount(TimeSettingConvertor.timeSettingDtoToTimeSetting(timeSettingDto));
		return new ServiceResponse<Integer>(true, null, count);
	}

	@Override
	public ServiceResponse<List<TimeSettingDto>> getTimeSetting(TimeSettingDto timeSettingDto) {
		List<TimeSetting> timeSettingList = timeSettingManager.getTimeSetting(TimeSettingConvertor.timeSettingDtoToTimeSetting(timeSettingDto));
		return new ServiceResponse<List<TimeSettingDto>>(true, null, TimeSettingConvertor.timeSettingListToTimeSettingDtoList(timeSettingList));
	}

	@Override
	public ServiceResponse<TimeSettingDto> getOneTimeSetting(TimeSettingDto timeSettingDto) {
		TimeSetting timeSetting = timeSettingManager.getOneTimeSetting(TimeSettingConvertor.timeSettingDtoToTimeSetting(timeSettingDto));
		return new ServiceResponse<TimeSettingDto>(true, null, TimeSettingConvertor.timeSettingToTimeSettingDto(timeSetting));
	}




}
