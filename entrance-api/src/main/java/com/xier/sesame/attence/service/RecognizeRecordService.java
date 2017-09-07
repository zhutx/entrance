package com.xier.sesame.attence.service;

import com.xier.sesame.attence.dto.*;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.common.rpc.dto.PaginationDto;

import java.util.List;

/**
 * 识别记录Service
 * @author zhutx
 *
 */
public interface RecognizeRecordService {
	
	void receiveRecognizeLog(String message);
	
	/**
	 * 增加识别记录
	 * @param recognizeRecordDto
	 * @return
	 */
	ServiceResponse<Long> addRecognizeRecord(RecognizeRecordDto recognizeRecordDto);
	
	
	/**
	 * 修改识别记录
	 * @param recognizeRecordDto
	 * @return
	 */
	ServiceResponse<Integer> updateRecognizeRecord(RecognizeRecordDto recognizeRecordDto);
	
	/**
	 * 修改识别记录,只修改属性不为null的字段
	 * @param recognizeRecordDto
	 * @return
	 */
	ServiceResponse<Integer> updateRecognizeRecordSelective(RecognizeRecordDto recognizeRecordDto);
	
	
	/**
	 * 根据ID删除识别记录
	 * @param id
	 * @param orgId 分表字段
	 */
	ServiceResponse<Integer> removeRecognizeRecordById(Long id, Long orgId);
	
	
	/**
	 * 根据id获取识别记录
	 * @param id
	 * @param orgId 分表字段
	 * @return
	 */
	ServiceResponse<RecognizeRecordDto> getRecognizeRecordById(Long id, Long orgId);


	/**
	 * 分页查询识别记录
	 * @param paginationDto
	 * @param recognizeRecordDto
	 * @return
	 */
	 ServiceResponse<PaginationDto<RecognizeRecordDto>> getPaginationRecognizeRecord(PaginationDto<RecognizeRecordDto> paginationDto,RecognizeRecordDto recognizeRecordDto);

	 ServiceResponse<PaginationDto<RecognizeRecordViewDto>> getPaginationRecognizeRecordView(PaginationDto<RecognizeRecordViewDto> paginationDto,RecognizeRecordViewDto recognizeRecordViewDto);

	ServiceResponse<PaginationDto<RecognizeRecordViewDto>> getH5CompanyPaginationRecognizeRecordView(PaginationDto<RecognizeRecordViewDto> paginationDto,RecognizeRecordViewDto recognizeRecordViewDto);


	/**
	 * 获取识别记录数量
	 * @param recognizeRecordDto
	 * @return
	 */
	 ServiceResponse<Integer> getRecognizeRecordCount(RecognizeRecordDto recognizeRecordDto);
	

	 ServiceResponse<List<RecognizeRecordTodayDto>> getRecognizeRecordToday(Long orgId);
	
	 ServiceResponse<List<DeviceRecognizeCountTodayDto>> getDeviceRecognizeCountToday(Long orgId, Integer recognizeUserType);

	/**
	 * 抓取待同步的考勤数据
	 */
	 ServiceResponse<List<SyncRecordResultDto>> getSyncRecognizeRecord(SyncRecordDto syncRecordDto);
}
