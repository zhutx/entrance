package com.xier.sesame.attence.manager.impl;

import com.xier.sesame.attence.domain.RecognizeDailyReport;
import org.springframework.beans.factory.annotation.Autowired;
import com.xier.sesame.attence.manager.RecognizeDailyReportManager;
import com.xier.sesame.attence.dao.RecognizeDailyReportDao;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.idgenerator.service.IdgeneratorService;
import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 每日识别汇总Manager
 * @author zhutx
 */
@Service
public class RecognizeDailyReportManagerImpl implements RecognizeDailyReportManager{

	@Reference
	private IdgeneratorService idgeneratorService;
	
	@Autowired
	private RecognizeDailyReportDao recognizeDailyReportDao;
	
	@Override
	public ServiceResponse<Long> addRecognizeDailyReport(RecognizeDailyReport recognizeDailyReport) {
		ServiceResponse<Long> serviceResponse = idgeneratorService.getNextIdByTypeName("com.xier.sesame.attence.RecognizeDailyReport");
		if (serviceResponse.isSuccess()) {
			recognizeDailyReport.setRecognizeDailyReportId(serviceResponse.getData().toString());
			recognizeDailyReportDao.addRecognizeDailyReport(recognizeDailyReport);
		}
		return serviceResponse;
	}
	
	@Override
	public int updateRecognizeDailyReport(RecognizeDailyReport recognizeDailyReport) {
		return recognizeDailyReportDao.updateRecognizeDailyReport(recognizeDailyReport);
	}
	
	@Override
	public int updateRecognizeDailyReportSelective(RecognizeDailyReport recognizeDailyReport) {
		return recognizeDailyReportDao.updateRecognizeDailyReportSelective(recognizeDailyReport);
	}
	
	@Override
	public int removeRecognizeDailyReportById(String id, Long orgId) {
		return recognizeDailyReportDao.removeRecognizeDailyReportById(id, orgId);
	}
	
	@Override
	public RecognizeDailyReport getRecognizeDailyReportById(String id, Long orgId) {
		return recognizeDailyReportDao.getRecognizeDailyReportById(id, orgId);
	}
	  
	@Override
	public Pagination<RecognizeDailyReport> getPaginationRecognizeDailyReport(Pagination<RecognizeDailyReport> pagination,RecognizeDailyReport recognizeDailyReport) {
		return recognizeDailyReportDao.getPaginationRecognizeDailyReport(pagination, recognizeDailyReport);
	}
	
	@Override
	public int getRecognizeDailyReportCount(RecognizeDailyReport recognizeDailyReport) {
		return recognizeDailyReportDao.getRecognizeDailyReportCount(recognizeDailyReport);
	}


	@Override
	public RecognizeDailyReport getRecordCountByTimeRangeAndOrgId(String startTime, String endTime, Long orgId) {
		return recognizeDailyReportDao.getRecordCountByTimeRangeAndOrgId(startTime,endTime,orgId);
	}

	@Override
	public RecognizeDailyReport getSourceRecordCountByTimeRangeAndOrgId(Long startTime, Long endTime, Long orgId) {
		return recognizeDailyReportDao.getSourceRecordCountByTimeRangeAndOrgId(startTime,endTime,orgId);
	}
}
