package com.xier.sesame.attence.dao;

import com.xier.sesame.attence.domain.DeviceRecognizeCountToday;
import com.xier.sesame.attence.domain.RecognizeRecord;
import com.xier.sesame.attence.domain.RecognizeRecordToday;
import com.xier.sesame.attence.domain.RecognizeRecordView;
import com.xier.sesame.attence.dto.SyncRecordDto;
import com.xier.sesame.attence.dto.SyncRecordResultDto;
import com.xier.sesame.common.base.domain.query.Pagination;

import java.util.List;

/**
 * 识别记录Dao
 * @author zhutx
 *
 */
public interface RecognizeRecordDao {
	
	/**
	 * 增加识别记录
	 * @param recognizeRecord
	 * @return
	 */
	public void addRecognizeRecord(RecognizeRecord recognizeRecord);
	
	
	/**
	 * 修改识别记录
	 * @param recognizeRecord
	 * @return 修改记录条数
	 */
	public int updateRecognizeRecord(RecognizeRecord recognizeRecord);
	
	/**
	 * 修改识别记录,只修改属性不为null的字段
	 * @param recognizeRecord
	 * @return 修改记录条数
	 */
	public int updateRecognizeRecordSelective(RecognizeRecord recognizeRecord);
	
	
	/**
	 * 根据ID删除识别记录
	 * @param id
	 * @param orgId 分表字段
	 * @return 删除记录条数
	 */
	public int removeRecognizeRecordById(Long id, Long orgId);
	
	
	/**
	 * 根据id获取识别记录
	 * @param id
	 * @param orgId 分表字段
	 * @return 
	 */
	public RecognizeRecord getRecognizeRecordById(Long id, Long orgId);
	
	/**
	 * 根据id获取识别记录,同时加锁
	 * @param id
	 * @param orgId 分表字段
	 * @return 
	 */
	public RecognizeRecord getRecognizeRecordByIdForUpdate(Long id, Long orgId);
		
    
	/**
	 * 分页查询识别记录
	 * @param pagination
	 * @param recognizeRecord
	 * @return
	 */
	public Pagination<RecognizeRecord> getPaginationRecognizeRecord(Pagination<RecognizeRecord> pagination,RecognizeRecord recognizeRecord);
	
	public Pagination<RecognizeRecordView> getPaginationRecognizeRecordView(Pagination<RecognizeRecordView> pagination,RecognizeRecordView recognizeRecordView);

	public Pagination<RecognizeRecordView> getH5CompanyPaginationRecognizeRecordView(Pagination<RecognizeRecordView> pagination,RecognizeRecordView recognizeRecordView);


	/**
	 * 获取识别记录数量
	 * @param recognizeRecord
	 * @return
	 */
	public int getRecognizeRecordCount(RecognizeRecord recognizeRecord);
	

	public List<RecognizeRecordToday> getRecognizeRecordToday(Long orgId);
	
	public List<DeviceRecognizeCountToday> getDeviceRecognizeCountToday(Long orgId, Integer recognizeUserType);
	
	/**
	 * 抓取待同步的考勤数据
	 * @param syncRecordDto
	 * @return
	 */
	public List<SyncRecordResultDto> getSyncRecognizeRecord(SyncRecordDto syncRecordDto);



}
