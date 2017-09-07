package com.xier.sesame.attence.dao.mybatis;

import com.xier.sesame.attence.dao.RecognizeRecordDao;
import com.xier.sesame.attence.domain.DeviceRecognizeCountToday;
import com.xier.sesame.attence.domain.RecognizeRecord;
import com.xier.sesame.attence.domain.RecognizeRecordToday;
import com.xier.sesame.attence.domain.RecognizeRecordView;
import com.xier.sesame.attence.dto.SyncRecordDto;
import com.xier.sesame.attence.dto.SyncRecordResultDto;
import com.xier.sesame.common.base.dao.BaseDaoMybatis;
import com.xier.sesame.common.base.domain.query.Pagination;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 识别记录Dao
 * @author zhutx
 *
 */
@Repository
public class RecognizeRecordDaoMybatis extends BaseDaoMybatis implements RecognizeRecordDao {
	
	@Override
	public void addRecognizeRecord(RecognizeRecord recognizeRecord) {
		this.getSqlSession().insert("com.xier.sesame.attence.RecognizeRecord.addRecognizeRecord", recognizeRecord);
	}
		
	@Override
	public int updateRecognizeRecord(RecognizeRecord recognizeRecord) {
		return this.getSqlSession().update("com.xier.sesame.attence.RecognizeRecord.updateRecognizeRecord", recognizeRecord);
	}
	
	@Override
	public int updateRecognizeRecordSelective(RecognizeRecord recognizeRecord) {
		return this.getSqlSession().update("com.xier.sesame.attence.RecognizeRecord.updateRecognizeRecordSelective", recognizeRecord);
	}
	
	@Override
	public int removeRecognizeRecordById(Long id, Long orgId) {
		RecognizeRecord recognizeRecord = new RecognizeRecord();
		recognizeRecord.setRecognizeRecordId(id);
		recognizeRecord.setOrgId(orgId);
		return this.getSqlSession().delete("com.xier.sesame.attence.RecognizeRecord.removeRecognizeRecordById", recognizeRecord);
	}
	
	@Override
	public RecognizeRecord getRecognizeRecordById(Long id, Long orgId) {
		RecognizeRecord recognizeRecord = new RecognizeRecord();
		recognizeRecord.setRecognizeRecordId(id);
		recognizeRecord.setOrgId(orgId);
		return (RecognizeRecord)this.getSqlSession().selectOne("com.xier.sesame.attence.RecognizeRecord.getRecognizeRecordById", recognizeRecord);
	}
	
	@Override
	public RecognizeRecord getRecognizeRecordByIdForUpdate(Long id, Long orgId) {
		RecognizeRecord recognizeRecord = new RecognizeRecord();
		recognizeRecord.setRecognizeRecordId(id);
		recognizeRecord.setOrgId(orgId);
		return (RecognizeRecord)this.getSqlSession().selectOne("com.xier.sesame.attence.RecognizeRecord.getRecognizeRecordByIdForUpdate", recognizeRecord);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pagination<RecognizeRecord> getPaginationRecognizeRecord(Pagination<RecognizeRecord> pagination,RecognizeRecord recognizeRecord) {
		return this.getPagination(pagination,recognizeRecord, "com.xier.sesame.attence.RecognizeRecord.getRecognizeRecordCount", "com.xier.sesame.attence.RecognizeRecord.getPaginationRecognizeRecord");
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public Pagination<RecognizeRecordView> getPaginationRecognizeRecordView(Pagination<RecognizeRecordView> pagination,
			RecognizeRecordView recognizeRecordView) {
		return this.getPagination(pagination,recognizeRecordView, "com.xier.sesame.attence.RecognizeRecord.getRecognizeRecordViewCount", "com.xier.sesame.attence.RecognizeRecord.getPaginationRecognizeRecordView");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pagination<RecognizeRecordView> getH5CompanyPaginationRecognizeRecordView(Pagination<RecognizeRecordView> pagination,
																			RecognizeRecordView recognizeRecordView) {
		return this.getPagination(pagination,recognizeRecordView, "com.xier.sesame.attence.RecognizeRecord.getH5CompanyRecognizeRecordViewCount", "com.xier.sesame.attence.RecognizeRecord.getH5CompanyPaginationRecognizeRecordView");
	}


	@Override
	public int getRecognizeRecordCount(RecognizeRecord recognizeRecord) {
		return this.getSqlSession().selectOne("com.xier.sesame.attence.RecognizeRecord.getRecognizeRecordCount", recognizeRecord);
	}

	@Override
	public List<RecognizeRecordToday> getRecognizeRecordToday(Long orgId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("orgId", orgId);
		return this.getSqlSession().selectList("com.xier.sesame.attence.RecognizeRecord.getRecognizeRecordToday", parameter);
	}
	
	@Override
	public List<DeviceRecognizeCountToday> getDeviceRecognizeCountToday(Long orgId, Integer recognizeUserType) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("orgId", orgId);
		parameter.put("recognizeUserType", recognizeUserType);
		return this.getSqlSession().selectList("com.xier.sesame.attence.RecognizeRecord.getDeviceRecognizeCountToday", parameter);
	}

	@Override
	public List<SyncRecordResultDto> getSyncRecognizeRecord(SyncRecordDto syncRecordDto) {
		return this.getSqlSession().selectList("com.xier.sesame.attence.RecognizeRecord.getSyncRecognizeRecord", syncRecordDto);
	}

}
