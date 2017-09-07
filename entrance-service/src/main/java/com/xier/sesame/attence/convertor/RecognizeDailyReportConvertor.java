package com.xier.sesame.attence.convertor;

import com.xier.sesame.attence.domain.RecognizeDailyReport;
import com.xier.sesame.common.rpc.dto.PaginationDto;
import com.xier.sesame.attence.dto.RecognizeDailyReportDto;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.base.converter.PaginationConvertor;
import java.util.List;
import java.util.ArrayList;

/**
 * 每日识别汇总数据转换器
 * @author zhutx
 */
public class RecognizeDailyReportConvertor {
	
	
	public static RecognizeDailyReportDto recognizeDailyReportToRecognizeDailyReportDto(RecognizeDailyReport recognizeDailyReport) {
		if (recognizeDailyReport == null)
			return null;
		RecognizeDailyReportDto recognizeDailyReportDto = new RecognizeDailyReportDto();		
		recognizeDailyReportDto.setRecognizeDailyReportId(recognizeDailyReport.getRecognizeDailyReportId());
		recognizeDailyReportDto.setOrgId(recognizeDailyReport.getOrgId());
		recognizeDailyReportDto.setReportDate(recognizeDailyReport.getReportDate());
		recognizeDailyReportDto.setTotalTimes(recognizeDailyReport.getTotalTimes());
		recognizeDailyReportDto.setEmployeeTimes(recognizeDailyReport.getEmployeeTimes());
		recognizeDailyReportDto.setVisitorTimes(recognizeDailyReport.getVisitorTimes());
		recognizeDailyReportDto.setStangerTimes(recognizeDailyReport.getStangerTimes());
		recognizeDailyReportDto.setGmtCreate(recognizeDailyReport.getGmtCreate());
		recognizeDailyReportDto.setGmtModify(recognizeDailyReport.getGmtModify());
		return recognizeDailyReportDto;
	}
	
	public static RecognizeDailyReport recognizeDailyReportDtoToRecognizeDailyReport(RecognizeDailyReportDto recognizeDailyReportDto) {
		if (recognizeDailyReportDto == null)
			return null;
		RecognizeDailyReport recognizeDailyReport = new RecognizeDailyReport();		
		recognizeDailyReport.setRecognizeDailyReportId(recognizeDailyReportDto.getRecognizeDailyReportId());
		recognizeDailyReport.setOrgId(recognizeDailyReportDto.getOrgId());
		recognizeDailyReport.setReportDate(recognizeDailyReportDto.getReportDate());
		recognizeDailyReport.setTotalTimes(recognizeDailyReportDto.getTotalTimes());
		recognizeDailyReport.setEmployeeTimes(recognizeDailyReportDto.getEmployeeTimes());
		recognizeDailyReport.setVisitorTimes(recognizeDailyReportDto.getVisitorTimes());
		recognizeDailyReport.setStangerTimes(recognizeDailyReportDto.getStangerTimes());
		recognizeDailyReport.setGmtCreate(recognizeDailyReportDto.getGmtCreate());
		recognizeDailyReport.setGmtModify(recognizeDailyReportDto.getGmtModify());
		return recognizeDailyReport;
	}	
	
	public static List<RecognizeDailyReportDto> recognizeDailyReportListToRecognizeDailyReportDtoList(List<RecognizeDailyReport> recognizeDailyReportList) {
		if (recognizeDailyReportList == null)
			return null;
		List<RecognizeDailyReportDto> RecognizeDailyReportDtoList = new ArrayList<RecognizeDailyReportDto>();
		for (RecognizeDailyReport recognizeDailyReport : recognizeDailyReportList) {
			RecognizeDailyReportDtoList.add(recognizeDailyReportToRecognizeDailyReportDto(recognizeDailyReport));
		}
		return RecognizeDailyReportDtoList;
	}
	
	public static List<RecognizeDailyReport> recognizeDailyReportDtoListToRecognizeDailyReportList(List<RecognizeDailyReportDto> recognizeDailyReportDtoList) {
		if (recognizeDailyReportDtoList == null)
			return null;
		List<RecognizeDailyReport> RecognizeDailyReportList = new ArrayList<RecognizeDailyReport>();
		for (RecognizeDailyReportDto recognizeDailyReportDto : recognizeDailyReportDtoList) {
			RecognizeDailyReportList.add(recognizeDailyReportDtoToRecognizeDailyReport(recognizeDailyReportDto));
		}
		return RecognizeDailyReportList;
	}
	
	public static PaginationDto<RecognizeDailyReportDto> paginationRecognizeDailyReportToPaginationRecognizeDailyReportDto(Pagination<RecognizeDailyReport> pagination) {
		PaginationDto<RecognizeDailyReportDto> paginationDto = PaginationConvertor.paginationToPaginationDto(pagination, new PaginationDto<RecognizeDailyReportDto>());
		if (paginationDto == null)
			return null;
		paginationDto.setData(recognizeDailyReportListToRecognizeDailyReportDtoList(pagination.getData()));
		return paginationDto;
	}
	
	public static Pagination<RecognizeDailyReport> paginationRecognizeDailyReportDtoToPaginationRecognizeDailyReport(PaginationDto<RecognizeDailyReportDto> paginationDto) {
		Pagination<RecognizeDailyReport> pagination = PaginationConvertor.paginationDtoToPagination(paginationDto, new Pagination<RecognizeDailyReport>());
		if (pagination == null)
			return null;
		pagination.setData(recognizeDailyReportDtoListToRecognizeDailyReportList(paginationDto.getData()));
		return pagination;
	}
	
}