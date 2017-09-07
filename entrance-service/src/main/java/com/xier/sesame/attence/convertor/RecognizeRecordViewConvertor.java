package com.xier.sesame.attence.convertor;

import java.util.ArrayList;
import java.util.List;

import com.xier.sesame.attence.domain.RecognizeRecordView;
import com.xier.sesame.attence.dto.RecognizeRecordViewDto;
import com.xier.sesame.common.base.converter.PaginationConvertor;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.rpc.dto.PaginationDto;
import com.xier.sesame.common.utils.BeanUtils;

/**
 * 识别记录数据转换器
 * @author zhutx
 */
public class RecognizeRecordViewConvertor {
	
	
	public static RecognizeRecordViewDto recognizeRecordViewToRecognizeRecordViewDto(RecognizeRecordView recognizeRecordView) {
		if (recognizeRecordView == null)
			return null;
		RecognizeRecordViewDto recognizeRecordViewDto = new RecognizeRecordViewDto();		
		BeanUtils.copyProperties(recognizeRecordViewDto,recognizeRecordView);
		return recognizeRecordViewDto;
	}
	
	public static RecognizeRecordView recognizeRecordViewDtoToRecognizeRecordView(RecognizeRecordViewDto recognizeRecordViewDto) {
		if (recognizeRecordViewDto == null)
			return null;
		RecognizeRecordView recognizeRecordView = new RecognizeRecordView();		
		BeanUtils.copyProperties(recognizeRecordView,recognizeRecordViewDto);
		return recognizeRecordView;
	}	
	
	public static List<RecognizeRecordViewDto> recognizeRecordViewListToRecognizeRecordViewDtoList(List<RecognizeRecordView> recognizeRecordViewList) {
		if (recognizeRecordViewList == null)
			return null;
		List<RecognizeRecordViewDto> RecognizeRecordViewDtoList = new ArrayList<RecognizeRecordViewDto>();
		for (RecognizeRecordView recognizeRecordView : recognizeRecordViewList) {
			RecognizeRecordViewDtoList.add(recognizeRecordViewToRecognizeRecordViewDto(recognizeRecordView));
		}
		return RecognizeRecordViewDtoList;
	}
	
	public static List<RecognizeRecordView> recognizeRecordViewDtoListToRecognizeRecordViewList(List<RecognizeRecordViewDto> recognizeRecordViewDtoList) {
		if (recognizeRecordViewDtoList == null)
			return null;
		List<RecognizeRecordView> RecognizeRecordViewList = new ArrayList<RecognizeRecordView>();
		for (RecognizeRecordViewDto recognizeRecordViewDto : recognizeRecordViewDtoList) {
			RecognizeRecordViewList.add(recognizeRecordViewDtoToRecognizeRecordView(recognizeRecordViewDto));
		}
		return RecognizeRecordViewList;
	}
	
	public static PaginationDto<RecognizeRecordViewDto> paginationRecognizeRecordViewToPaginationRecognizeRecordViewDto(Pagination<RecognizeRecordView> pagination) {
		PaginationDto<RecognizeRecordViewDto> paginationDto = PaginationConvertor.paginationToPaginationDto(pagination, new PaginationDto<RecognizeRecordViewDto>());
		if (paginationDto == null)
			return null;
		paginationDto.setData(recognizeRecordViewListToRecognizeRecordViewDtoList(pagination.getData()));
		return paginationDto;
	}
	
	public static Pagination<RecognizeRecordView> paginationRecognizeRecordViewDtoToPaginationRecognizeRecordView(PaginationDto<RecognizeRecordViewDto> paginationDto) {
		Pagination<RecognizeRecordView> pagination = PaginationConvertor.paginationDtoToPagination(paginationDto, new Pagination<RecognizeRecordView>());
		if (pagination == null)
			return null;
		pagination.setData(recognizeRecordViewDtoListToRecognizeRecordViewList(paginationDto.getData()));
		return pagination;
	}
	
}