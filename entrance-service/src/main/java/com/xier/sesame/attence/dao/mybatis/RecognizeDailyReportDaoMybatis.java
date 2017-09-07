package com.xier.sesame.attence.dao.mybatis;

import com.xier.sesame.attence.dao.RecognizeDailyReportDao;
import com.xier.sesame.attence.domain.RecognizeDailyReport;
import com.xier.sesame.common.base.dao.BaseDaoMybatis;
import com.xier.sesame.common.base.domain.query.Pagination;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * 每日识别汇总Dao
 * @author zhutx
 *
 */
@Repository
public class RecognizeDailyReportDaoMybatis extends BaseDaoMybatis implements RecognizeDailyReportDao {
	
	@Override
	public void addRecognizeDailyReport(RecognizeDailyReport recognizeDailyReport) {
		this.getSqlSession().insert("com.xier.sesame.attence.RecognizeDailyReport.addRecognizeDailyReport", recognizeDailyReport);
	}
		
	@Override
	public int updateRecognizeDailyReport(RecognizeDailyReport recognizeDailyReport) {
		return this.getSqlSession().update("com.xier.sesame.attence.RecognizeDailyReport.updateRecognizeDailyReport", recognizeDailyReport);
	}
	
	@Override
	public int updateRecognizeDailyReportSelective(RecognizeDailyReport recognizeDailyReport) {
		return this.getSqlSession().update("com.xier.sesame.attence.RecognizeDailyReport.updateRecognizeDailyReportSelective", recognizeDailyReport);
	}
	
	@Override
	public int removeRecognizeDailyReportById(String id, Long orgId) {
		RecognizeDailyReport recognizeDailyReport = new RecognizeDailyReport();
		recognizeDailyReport.setRecognizeDailyReportId(id);
		recognizeDailyReport.setOrgId(orgId);
		return this.getSqlSession().delete("com.xier.sesame.attence.RecognizeDailyReport.removeRecognizeDailyReportById", recognizeDailyReport);
	}
	
	@Override
	public RecognizeDailyReport getRecognizeDailyReportById(String id, Long orgId) {
		RecognizeDailyReport recognizeDailyReport = new RecognizeDailyReport();
		recognizeDailyReport.setRecognizeDailyReportId(id);
		recognizeDailyReport.setOrgId(orgId);
		return (RecognizeDailyReport)this.getSqlSession().selectOne("com.xier.sesame.attence.RecognizeDailyReport.getRecognizeDailyReportById", recognizeDailyReport);
	}
	
	@Override
	public RecognizeDailyReport getRecognizeDailyReportByIdForUpdate(String id, Long orgId) {
		RecognizeDailyReport recognizeDailyReport = new RecognizeDailyReport();
		recognizeDailyReport.setRecognizeDailyReportId(id);
		recognizeDailyReport.setOrgId(orgId);
		return (RecognizeDailyReport)this.getSqlSession().selectOne("com.xier.sesame.attence.RecognizeDailyReport.getRecognizeDailyReportByIdForUpdate", recognizeDailyReport);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pagination<RecognizeDailyReport> getPaginationRecognizeDailyReport(Pagination<RecognizeDailyReport> pagination,RecognizeDailyReport recognizeDailyReport) {
		return this.getPagination(pagination,recognizeDailyReport, "com.xier.sesame.attence.RecognizeDailyReport.getRecognizeDailyReportCount", "com.xier.sesame.attence.RecognizeDailyReport.getPaginationRecognizeDailyReport");
	}	
	
	@Override
	public int getRecognizeDailyReportCount(RecognizeDailyReport recognizeDailyReport) {
		return this.getSqlSession().selectOne("com.xier.sesame.attence.RecognizeDailyReport.getRecognizeDailyReportCount", recognizeDailyReport);
	}

	@Override
	public RecognizeDailyReport getRecordCountByTimeRangeAndOrgId(String startTime, String endTime, Long orgId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("orgId", orgId);
		parameter.put("startTime",startTime);
		parameter.put("endTime",endTime);
		return this.getSqlSession().selectOne("com.xier.sesame.attence.RecognizeDailyReport.getRecordCountByTimeRangeAndOrgId", parameter);

	}

	@Override
	public RecognizeDailyReport getSourceRecordCountByTimeRangeAndOrgId(Long startTime, Long endTime, Long orgId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("orgId", orgId);
		parameter.put("startTime",startTime);
		parameter.put("endTime",endTime);
		return this.getSqlSession().selectOne("com.xier.sesame.attence.RecognizeDailyReport.getSourceRecordCountByTimeRangeAndOrgId", parameter);

	}
}
