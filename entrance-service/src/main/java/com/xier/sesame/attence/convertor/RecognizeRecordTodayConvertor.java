package com.xier.sesame.attence.convertor;

import com.xier.sesame.attence.domain.RecognizeRecordToday;
import com.xier.sesame.common.rpc.dto.PaginationDto;
import com.xier.sesame.attence.dto.RecognizeRecordTodayDto;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.base.converter.PaginationConvertor;
import java.util.List;
import java.util.ArrayList;

/**
 * 分用户统计的识别记录数据转换器
 * @author zhutx
 */
public class RecognizeRecordTodayConvertor {
	
	
	public static RecognizeRecordTodayDto recognizeRecordTodayToRecognizeRecordTodayDto(RecognizeRecordToday recognizeRecordToday) {
		if (recognizeRecordToday == null)
			return null;
		RecognizeRecordTodayDto recognizeRecordTodayDto = new RecognizeRecordTodayDto();		
		recognizeRecordTodayDto.setRecognizeUserType(recognizeRecordToday.getRecognizeUserType());
		recognizeRecordTodayDto.setRecognizeCount(recognizeRecordToday.getRecognizeCount());
		return recognizeRecordTodayDto;
	}
	
	public static RecognizeRecordToday recognizeRecordTodayDtoToRecognizeRecordToday(RecognizeRecordTodayDto recognizeRecordTodayDto) {
		if (recognizeRecordTodayDto == null)
			return null;
		RecognizeRecordToday recognizeRecordToday = new RecognizeRecordToday();		
		recognizeRecordToday.setRecognizeUserType(recognizeRecordTodayDto.getRecognizeUserType());
		recognizeRecordToday.setRecognizeCount(recognizeRecordTodayDto.getRecognizeCount());
		return recognizeRecordToday;
	}	
	
	public static List<RecognizeRecordTodayDto> recognizeRecordTodayListToRecognizeRecordTodayDtoList(List<RecognizeRecordToday> recognizeRecordTodayList) {
		if (recognizeRecordTodayList == null)
			return null;
		List<RecognizeRecordTodayDto> RecognizeRecordTodayDtoList = new ArrayList<RecognizeRecordTodayDto>();
		for (RecognizeRecordToday recognizeRecordToday : recognizeRecordTodayList) {
			RecognizeRecordTodayDtoList.add(recognizeRecordTodayToRecognizeRecordTodayDto(recognizeRecordToday));
		}
		return RecognizeRecordTodayDtoList;
	}
	
	public static List<RecognizeRecordToday> recognizeRecordTodayDtoListToRecognizeRecordTodayList(List<RecognizeRecordTodayDto> recognizeRecordTodayDtoList) {
		if (recognizeRecordTodayDtoList == null)
			return null;
		List<RecognizeRecordToday> RecognizeRecordTodayList = new ArrayList<RecognizeRecordToday>();
		for (RecognizeRecordTodayDto recognizeRecordTodayDto : recognizeRecordTodayDtoList) {
			RecognizeRecordTodayList.add(recognizeRecordTodayDtoToRecognizeRecordToday(recognizeRecordTodayDto));
		}
		return RecognizeRecordTodayList;
	}
	
	public static PaginationDto<RecognizeRecordTodayDto> paginationRecognizeRecordTodayToPaginationRecognizeRecordTodayDto(Pagination<RecognizeRecordToday> pagination) {
		PaginationDto<RecognizeRecordTodayDto> paginationDto = PaginationConvertor.paginationToPaginationDto(pagination, new PaginationDto<RecognizeRecordTodayDto>());
		if (paginationDto == null)
			return null;
		paginationDto.setData(recognizeRecordTodayListToRecognizeRecordTodayDtoList(pagination.getData()));
		return paginationDto;
	}
	
	public static Pagination<RecognizeRecordToday> paginationRecognizeRecordTodayDtoToPaginationRecognizeRecordToday(PaginationDto<RecognizeRecordTodayDto> paginationDto) {
		Pagination<RecognizeRecordToday> pagination = PaginationConvertor.paginationDtoToPagination(paginationDto, new Pagination<RecognizeRecordToday>());
		if (pagination == null)
			return null;
		pagination.setData(recognizeRecordTodayDtoListToRecognizeRecordTodayList(paginationDto.getData()));
		return pagination;
	}
	
}