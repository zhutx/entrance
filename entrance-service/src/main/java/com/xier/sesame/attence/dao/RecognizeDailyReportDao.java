package com.xier.sesame.attence.dao;

import com.xier.sesame.attence.domain.RecognizeDailyReport;
import com.xier.sesame.common.base.domain.query.Pagination;

/**
 * 每日识别汇总Dao
 * @author zhutx
 *
 */
public interface RecognizeDailyReportDao {
	
	/**
	 * 增加每日识别汇总
	 * @param recognizeDailyReport
	 * @return
	 */
	public void addRecognizeDailyReport(RecognizeDailyReport recognizeDailyReport);
	
	
	/**
	 * 修改每日识别汇总
	 * @param recognizeDailyReport
	 * @return 修改记录条数
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
	 * @return 删除记录条数
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
	 * 根据id获取每日识别汇总,同时加锁
	 * @param id
	 * @param orgId 分表字段
	 * @return 
	 */
	public RecognizeDailyReport getRecognizeDailyReportByIdForUpdate(String id, Long orgId);
		
    
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
	 * 根据orgid,时间范围获取该时间段内识别日志的统计数据
	 * @param startTime
	 * @param endTime
	 * @param orgId 分表字段
	 * @return
	 */
	public RecognizeDailyReport getRecordCountByTimeRangeAndOrgId(String startTime,String endTime, Long orgId);



	/**
	 * 根据orgid,时间范围获取该时间段内识别日志的统计数据
	 * @param startTime
	 * @param endTime
	 * @param orgId 分表字段
	 * @return
	 */
	public RecognizeDailyReport getSourceRecordCountByTimeRangeAndOrgId(Long startTime,Long endTime, Long orgId);




}
