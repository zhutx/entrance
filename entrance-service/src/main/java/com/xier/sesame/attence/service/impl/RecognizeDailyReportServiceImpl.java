package com.xier.sesame.attence.service.impl;

import com.xier.sesame.attence.domain.RecognizeDailyReport;
import org.springframework.beans.factory.annotation.Autowired;
import com.xier.sesame.attence.manager.RecognizeDailyReportManager;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.alibaba.dubbo.config.annotation.Service;
import com.xier.sesame.attence.dto.RecognizeDailyReportDto;
import com.xier.sesame.common.rpc.dto.PaginationDto;
import com.xier.sesame.attence.service.RecognizeDailyReportService;
import com.xier.sesame.attence.convertor.RecognizeDailyReportConvertor;

/**
 * 每日识别汇总Service
 * @author zhutx
 */
@Service
public class RecognizeDailyReportServiceImpl implements RecognizeDailyReportService{
	
	@Autowired
	private RecognizeDailyReportManager recognizeDailyReportManager;
	
	@Override
	public ServiceResponse<Long> addRecognizeDailyReport(RecognizeDailyReportDto recognizeDailyReportDto) {
		ServiceResponse<Long> serviceResponse = recognizeDailyReportManager.addRecognizeDailyReport(RecognizeDailyReportConvertor.recognizeDailyReportDtoToRecognizeDailyReport(recognizeDailyReportDto));
		return serviceResponse;
	}
	
	@Override
	public ServiceResponse<Integer> updateRecognizeDailyReport(RecognizeDailyReportDto recognizeDailyReportDto) {
		Integer count = recognizeDailyReportManager.updateRecognizeDailyReport(RecognizeDailyReportConvertor.recognizeDailyReportDtoToRecognizeDailyReport(recognizeDailyReportDto)); 
		return new ServiceResponse<Integer>(true, null, count);
	}
	
	@Override
	public ServiceResponse<Integer> updateRecognizeDailyReportSelective(RecognizeDailyReportDto recognizeDailyReportDto) {
		Integer count = recognizeDailyReportManager.updateRecognizeDailyReportSelective(RecognizeDailyReportConvertor.recognizeDailyReportDtoToRecognizeDailyReport(recognizeDailyReportDto)); 
		return new ServiceResponse<Integer>(true, null, count);
	}
	
	@Override
	public ServiceResponse<Integer> removeRecognizeDailyReportById(String id, Long orgId) {
		Integer count = recognizeDailyReportManager.removeRecognizeDailyReportById(id, orgId);
		return new ServiceResponse<Integer>(true, null, count);
	}
	
	@Override
	public ServiceResponse<RecognizeDailyReportDto> getRecognizeDailyReportById(String id, Long orgId) {
		RecognizeDailyReport recognizeDailyReport = recognizeDailyReportManager.getRecognizeDailyReportById(id, orgId);
		return new ServiceResponse<RecognizeDailyReportDto>(true, null, RecognizeDailyReportConvertor.recognizeDailyReportToRecognizeDailyReportDto(recognizeDailyReport));
	}
	  
	@Override
	public ServiceResponse<PaginationDto<RecognizeDailyReportDto>> getPaginationRecognizeDailyReport(PaginationDto<RecognizeDailyReportDto> paginationDto,RecognizeDailyReportDto recognizeDailyReportDto) {
		Pagination<RecognizeDailyReport> pagination = recognizeDailyReportManager.getPaginationRecognizeDailyReport(RecognizeDailyReportConvertor.paginationRecognizeDailyReportDtoToPaginationRecognizeDailyReport(paginationDto) , RecognizeDailyReportConvertor.recognizeDailyReportDtoToRecognizeDailyReport(recognizeDailyReportDto));
		return new ServiceResponse<PaginationDto<RecognizeDailyReportDto>>(true, null, RecognizeDailyReportConvertor.paginationRecognizeDailyReportToPaginationRecognizeDailyReportDto(pagination));
	}

	@Override
	public ServiceResponse<Integer> getRecognizeDailyReportCount(RecognizeDailyReportDto recognizeDailyReportDto) {
		Integer count = recognizeDailyReportManager.getRecognizeDailyReportCount(RecognizeDailyReportConvertor.recognizeDailyReportDtoToRecognizeDailyReport(recognizeDailyReportDto));
		return new ServiceResponse<Integer>(true, null, count);
	}

	@Override
	public ServiceResponse<RecognizeDailyReportDto> getRecordCountByTimeRangeAndOrgId(String startTime, String endTime, Long orgId) {
		RecognizeDailyReport recognizeDailyReport=recognizeDailyReportManager.getRecordCountByTimeRangeAndOrgId(startTime,endTime,orgId);
		return new ServiceResponse<RecognizeDailyReportDto>(true, null, RecognizeDailyReportConvertor.recognizeDailyReportToRecognizeDailyReportDto(recognizeDailyReport));
	}

	@Override
	public ServiceResponse<RecognizeDailyReportDto> getSourceRecordCountByTimeRangeAndOrgId(Long startTime, Long endTime, Long orgId) {
		RecognizeDailyReport recognizeDailyReport=recognizeDailyReportManager.getSourceRecordCountByTimeRangeAndOrgId(startTime,endTime,orgId);
		return new ServiceResponse<RecognizeDailyReportDto>(true, null, RecognizeDailyReportConvertor.recognizeDailyReportToRecognizeDailyReportDto(recognizeDailyReport));

	}
}
