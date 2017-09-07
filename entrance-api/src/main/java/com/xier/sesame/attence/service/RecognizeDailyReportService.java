package com.xier.sesame.attence.service;

import com.xier.sesame.attence.dto.RecognizeDailyReportDto;
import com.xier.sesame.common.rpc.dto.PaginationDto;
import com.xier.sesame.common.rpc.ServiceResponse;

/**
 * 每日识别汇总Service
 * @author zhutx
 *
 */
public interface RecognizeDailyReportService {
	
	/**
	 * 增加每日识别汇总
	 * @param recognizeDailyReportDto
	 * @return
	 */
	ServiceResponse<Long> addRecognizeDailyReport(RecognizeDailyReportDto recognizeDailyReportDto);
	
	
	/**
	 * 修改每日识别汇总
	 * @param recognizeDailyReportDto
	 * @return
	 */
	ServiceResponse<Integer> updateRecognizeDailyReport(RecognizeDailyReportDto recognizeDailyReportDto);
	
	/**
	 * 修改每日识别汇总,只修改属性不为null的字段
	 * @param recognizeDailyReportDto
	 * @return
	 */
	ServiceResponse<Integer> updateRecognizeDailyReportSelective(RecognizeDailyReportDto recognizeDailyReportDto);
	
	
	/**
	 * 根据ID删除每日识别汇总
	 * @param id
	 * @param orgId 分表字段
	 */
	ServiceResponse<Integer> removeRecognizeDailyReportById(String id, Long orgId);
	
	
	/**
	 * 根据id获取每日识别汇总
	 * @param id
	 * @param orgId 分表字段
	 * @return
	 */
	ServiceResponse<RecognizeDailyReportDto> getRecognizeDailyReportById(String id, Long orgId);


	/**
	 * 分页查询每日识别汇总
	 * @param paginationDto
	 * @param recognizeDailyReportDto
	 * @return
	 */
	ServiceResponse<PaginationDto<RecognizeDailyReportDto>> getPaginationRecognizeDailyReport(PaginationDto<RecognizeDailyReportDto> paginationDto,RecognizeDailyReportDto recognizeDailyReportDto);


	/**
	 * 获取每日识别汇总数量
	 * @param recognizeDailyReportDto
	 * @return
	 */
	ServiceResponse<Integer> getRecognizeDailyReportCount(RecognizeDailyReportDto recognizeDailyReportDto);


	/**
	 * 根据指定日期范围、orgid，返回在该时间段内识别日志的统计数目
	 * 返回记录是已归档的每日记录的总和
	 * @param startTime
	 * @param endTime
	 * @param orgId
	 * @return
	 */
	ServiceResponse<RecognizeDailyReportDto> getRecordCountByTimeRangeAndOrgId(String startTime, String endTime, Long orgId);


	/**
	 * 根据指定日期范围、orgid，返回在该时间段内识别日志的统计数目
	 * 查询方式是从原数据表查询
	 * @param startTime
	 * @param endTime
	 * @param orgId
	 * @return
	 */
	ServiceResponse<RecognizeDailyReportDto> getSourceRecordCountByTimeRangeAndOrgId(Long startTime, Long endTime, Long orgId);




}
