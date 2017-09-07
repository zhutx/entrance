package com.xier.sesame.attence.convertor;

import java.util.ArrayList;
import java.util.List;

import com.xier.sesame.attence.domain.DeviceRecognizeCountToday;
import com.xier.sesame.attence.dto.DeviceRecognizeCountTodayDto;
import com.xier.sesame.common.base.converter.PaginationConvertor;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.rpc.dto.PaginationDto;

/**
 * 分用户统计的识别记录数据转换器
 * @author zhutx
 */
public class DeviceRecognizeCountTodayConvertor {
	
	
	public static DeviceRecognizeCountTodayDto deviceRecognizeCountTodayToDeviceRecognizeCountTodayDto(DeviceRecognizeCountToday deviceRecognizeCountToday) {
		if (deviceRecognizeCountToday == null)
			return null;
		DeviceRecognizeCountTodayDto recognizeRecordTodayDto = new DeviceRecognizeCountTodayDto();		
		recognizeRecordTodayDto.setUniqueNumber(deviceRecognizeCountToday.getUniqueNumber());
		recognizeRecordTodayDto.setRecognizeCount(deviceRecognizeCountToday.getRecognizeCount());
		return recognizeRecordTodayDto;
	}
	
	public static DeviceRecognizeCountToday deviceRecognizeCountTodayDtoToDeviceRecognizeCountToday(DeviceRecognizeCountTodayDto deviceRecognizeCountTodayDto) {
		if (deviceRecognizeCountTodayDto == null)
			return null;
		DeviceRecognizeCountToday recognizeRecordToday = new DeviceRecognizeCountToday();		
		recognizeRecordToday.setUniqueNumber(deviceRecognizeCountTodayDto.getUniqueNumber());
		recognizeRecordToday.setRecognizeCount(deviceRecognizeCountTodayDto.getRecognizeCount());
		return recognizeRecordToday;
	}	
	
	public static List<DeviceRecognizeCountTodayDto> deviceRecognizeCountTodayListToDeviceRecognizeCountTodayDtoList(List<DeviceRecognizeCountToday> deviceRecognizeCountTodayList) {
		if (deviceRecognizeCountTodayList == null)
			return null;
		List<DeviceRecognizeCountTodayDto> RecognizeRecordTodayDtoList = new ArrayList<DeviceRecognizeCountTodayDto>();
		for (DeviceRecognizeCountToday recognizeRecordToday : deviceRecognizeCountTodayList) {
			RecognizeRecordTodayDtoList.add(deviceRecognizeCountTodayToDeviceRecognizeCountTodayDto(recognizeRecordToday));
		}
		return RecognizeRecordTodayDtoList;
	}
	
	public static List<DeviceRecognizeCountToday> deviceRecognizeCountTodayDtoListToDeviceRecognizeCountTodayList(List<DeviceRecognizeCountTodayDto> deviceRecognizeCountTodayDtoList) {
		if (deviceRecognizeCountTodayDtoList == null)
			return null;
		List<DeviceRecognizeCountToday> recognizeRecordTodayList = new ArrayList<DeviceRecognizeCountToday>();
		for (DeviceRecognizeCountTodayDto recognizeRecordTodayDto : deviceRecognizeCountTodayDtoList) {
			recognizeRecordTodayList.add(deviceRecognizeCountTodayDtoToDeviceRecognizeCountToday(recognizeRecordTodayDto));
		}
		return recognizeRecordTodayList;
	}
	
	public static PaginationDto<DeviceRecognizeCountTodayDto> paginationRecognizeRecordTodayToPaginationRecognizeRecordTodayDto(Pagination<DeviceRecognizeCountToday> pagination) {
		PaginationDto<DeviceRecognizeCountTodayDto> paginationDto = PaginationConvertor.paginationToPaginationDto(pagination, new PaginationDto<DeviceRecognizeCountTodayDto>());
		if (paginationDto == null)
			return null;
		paginationDto.setData(deviceRecognizeCountTodayListToDeviceRecognizeCountTodayDtoList(pagination.getData()));
		return paginationDto;
	}
	
	public static Pagination<DeviceRecognizeCountToday> paginationDeviceRecognizeCountTodayDtoToPaginationDeviceRecognizeCountToday(PaginationDto<DeviceRecognizeCountTodayDto> paginationDto) {
		Pagination<DeviceRecognizeCountToday> pagination = PaginationConvertor.paginationDtoToPagination(paginationDto, new Pagination<DeviceRecognizeCountToday>());
		if (pagination == null)
			return null;
		pagination.setData(deviceRecognizeCountTodayDtoListToDeviceRecognizeCountTodayList(paginationDto.getData()));
		return pagination;
	}
	
}