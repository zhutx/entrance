package com.xier.sesame.attence.convertor;

import com.xier.sesame.attence.domain.TimeSetting;
import com.xier.sesame.common.rpc.dto.PaginationDto;
import com.xier.sesame.attence.dto.TimeSettingDto;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.base.converter.PaginationConvertor;
import java.util.List;
import java.util.ArrayList;

/**
 * 门禁日历配置数据转换器
 * @author zhutx
 */
public class TimeSettingConvertor {
	
	
	public static TimeSettingDto timeSettingToTimeSettingDto(TimeSetting timeSetting) {
		if (timeSetting == null)
			return null;
		TimeSettingDto timeSettingDto = new TimeSettingDto();		
		timeSettingDto.setTimeSettingId(timeSetting.getTimeSettingId());
		timeSettingDto.setOrgId(timeSetting.getOrgId());
		timeSettingDto.setDisableDate(timeSetting.getDisableDate());
		timeSettingDto.setGmtCreate(timeSetting.getGmtCreate());
		timeSettingDto.setGmtModify(timeSetting.getGmtModify());
		return timeSettingDto;
	}
	
	public static TimeSetting timeSettingDtoToTimeSetting(TimeSettingDto timeSettingDto) {
		if (timeSettingDto == null)
			return null;
		TimeSetting timeSetting = new TimeSetting();		
		timeSetting.setTimeSettingId(timeSettingDto.getTimeSettingId());
		timeSetting.setOrgId(timeSettingDto.getOrgId());
		timeSetting.setDisableDate(timeSettingDto.getDisableDate());
		timeSetting.setGmtCreate(timeSettingDto.getGmtCreate());
		timeSetting.setGmtModify(timeSettingDto.getGmtModify());
		return timeSetting;
	}	
	
	public static List<TimeSettingDto> timeSettingListToTimeSettingDtoList(List<TimeSetting> timeSettingList) {
		if (timeSettingList == null)
			return null;
		List<TimeSettingDto> TimeSettingDtoList = new ArrayList<TimeSettingDto>();
		for (TimeSetting timeSetting : timeSettingList) {
			TimeSettingDtoList.add(timeSettingToTimeSettingDto(timeSetting));
		}
		return TimeSettingDtoList;
	}
	
	public static List<TimeSetting> timeSettingDtoListToTimeSettingList(List<TimeSettingDto> timeSettingDtoList) {
		if (timeSettingDtoList == null)
			return null;
		List<TimeSetting> TimeSettingList = new ArrayList<TimeSetting>();
		for (TimeSettingDto timeSettingDto : timeSettingDtoList) {
			TimeSettingList.add(timeSettingDtoToTimeSetting(timeSettingDto));
		}
		return TimeSettingList;
	}
	
	public static PaginationDto<TimeSettingDto> paginationTimeSettingToPaginationTimeSettingDto(Pagination<TimeSetting> pagination) {
		PaginationDto<TimeSettingDto> paginationDto = PaginationConvertor.paginationToPaginationDto(pagination, new PaginationDto<TimeSettingDto>());
		if (paginationDto == null)
			return null;
		paginationDto.setData(timeSettingListToTimeSettingDtoList(pagination.getData()));
		return paginationDto;
	}
	
	public static Pagination<TimeSetting> paginationTimeSettingDtoToPaginationTimeSetting(PaginationDto<TimeSettingDto> paginationDto) {
		Pagination<TimeSetting> pagination = PaginationConvertor.paginationDtoToPagination(paginationDto, new Pagination<TimeSetting>());
		if (pagination == null)
			return null;
		pagination.setData(timeSettingDtoListToTimeSettingList(paginationDto.getData()));
		return pagination;
	}
	
}