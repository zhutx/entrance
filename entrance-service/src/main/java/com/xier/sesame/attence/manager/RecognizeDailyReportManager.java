package com.xier.sesame.attence.manager;

import com.xier.sesame.attence.domain.RecognizeDailyReport;
import com.xier.sesame.common.base.domain.query.Pagination; 
import com.xier.sesame.common.rpc.ServiceResponse;

/**
 * 每日识别汇总Manager
 * @author zhutx
 *
 */
public interface RecognizeDailyReportManager {
	
	/**
	 * 增加每日识别汇总
	 * @param recognizeDailyReport
	 * @return 主键
	 */
	public ServiceResponse<Long> addRecognizeDailyReport(RecognizeDailyReport recognizeDailyReport);
	
	
	/**
	 * 修改每日识别汇总
	 * @param recognizeDailyReport
	 * @return
	 */
	public int updateRecognizeDailyReport(RecognizeDailyReport recognizeDailyReport);
	
	/**
	 * 修改每日识别汇总,只修改属性不为null的字段
	 * @param recognizeDailyReport
	 * @return 修改记录条数
	 */
	public int updateRecognizeDailyReportSelective(RecognizeDailyReport recognizeDailyReport);
		
	/**
	 * 根据ID删除每日识别汇总
	 * @param id
	 * @param orgId 分表字段
	 * @return
	 */
	public int removeRecognizeDailyReportById(String id, Long orgId);
	
	
	/**
	 * 根据id获取每日识别汇总
	 * @param id
	 * @param orgId 分表字段
	 * @return
	 */
	public RecognizeDailyReport getRecognizeDailyReportById(String id, Long orgId);
	
    
	/**
	 * 分页查询每日识别汇总
	 * @param pagination
	 * @param recognizeDailyReport
	 * @return
	 */
	public Pagination<RecognizeDailyReport> getPaginationRecognizeDailyReport(Pagination<RecognizeDailyReport> pagination,RecognizeDailyReport recognizeDailyReport);
	
	
	/**
	 * 获取每日识别汇总数量
	 * @param recognizeDailyReport
	 * @return
	 */
	public int getRecognizeDailyReportCount(RecognizeDailyReport recognizeDailyReport);


	/**
	 * 根据指定日期范围、orgid，返回在该时间段内识别日志的统计数目
	 * @param startTime
	 * @param endTime
	 * @param orgId
	 * @return
	 */
	public RecognizeDailyReport getRecordCountByTimeRangeAndOrgId(String startTime, String endTime, Long orgId);



	/**
	 * 根据指定日期范围、orgid，返回在该时间段内识别日志的统计数目
	 * @param startTime
	 * @param endTime
	 * @param orgId
	 * @return
	 */
	public RecognizeDailyReport getSourceRecordCountByTimeRangeAndOrgId(Long startTime, Long endTime, Long orgId);


}
