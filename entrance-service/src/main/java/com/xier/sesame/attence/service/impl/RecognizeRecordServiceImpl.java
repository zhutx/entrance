package com.xier.sesame.attence.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xier.sesame.attence.convertor.DeviceRecognizeCountTodayConvertor;
import com.xier.sesame.attence.convertor.RecognizeRecordConvertor;
import com.xier.sesame.attence.convertor.RecognizeRecordTodayConvertor;
import com.xier.sesame.attence.convertor.RecognizeRecordViewConvertor;
import com.xier.sesame.attence.domain.DeviceRecognizeCountToday;
import com.xier.sesame.attence.domain.RecognizeRecord;
import com.xier.sesame.attence.domain.RecognizeRecordToday;
import com.xier.sesame.attence.domain.RecognizeRecordView;
import com.xier.sesame.attence.dto.*;
import com.xier.sesame.attence.manager.OrgAuthManager;
import com.xier.sesame.attence.manager.RecognizeRecordManager;
import com.xier.sesame.attence.service.RecognizeRecordService;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.exception.CommonErrorCode;
import com.xier.sesame.common.exception.ErrorContext;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.common.rpc.dto.PaginationDto;
import com.xier.sesame.common.utils.JsonUtils;
import com.xier.sesame.common.utils.StringUtil;
import com.xier.sesame.rabbit.log.Logger;
import com.xier.sesame.rabbit.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 识别记录Service
 * @author zhutx
 */
@Service
public class RecognizeRecordServiceImpl implements RecognizeRecordService{
	
	private static Logger logger = LoggerFactory.getLogger(RecognizeRecordServiceImpl.class);
	
	@Autowired
	private RecognizeRecordManager recognizeRecordManager;
	
	
	@Autowired
	private OrgAuthManager orgAuthManager;
	
	@Override
	public void receiveRecognizeLog(String message) {
		
		recognizeRecordManager.receiveRecognizeLog(message);

	}


	
	@Override
	public ServiceResponse<Long> addRecognizeRecord(RecognizeRecordDto recognizeRecordDto) {
		ServiceResponse<Long> serviceResponse = recognizeRecordManager.addRecognizeRecord(RecognizeRecordConvertor.recognizeRecordDtoToRecognizeRecord(recognizeRecordDto));
		return serviceResponse;
	}
	
	@Override
	public ServiceResponse<Integer> updateRecognizeRecord(RecognizeRecordDto recognizeRecordDto) {
		Integer count = recognizeRecordManager.updateRecognizeRecord(RecognizeRecordConvertor.recognizeRecordDtoToRecognizeRecord(recognizeRecordDto)); 
		return new ServiceResponse<Integer>(true, null, count);
	}
	
	@Override
	public ServiceResponse<Integer> updateRecognizeRecordSelective(RecognizeRecordDto recognizeRecordDto) {
		Integer count = recognizeRecordManager.updateRecognizeRecordSelective(RecognizeRecordConvertor.recognizeRecordDtoToRecognizeRecord(recognizeRecordDto)); 
		return new ServiceResponse<Integer>(true, null, count);
	}
	
	@Override
	public ServiceResponse<Integer> removeRecognizeRecordById(Long id, Long orgId) {
		Integer count = recognizeRecordManager.removeRecognizeRecordById(id, orgId);
		return new ServiceResponse<Integer>(true, null, count);
	}
	
	@Override
	public ServiceResponse<RecognizeRecordDto> getRecognizeRecordById(Long id, Long orgId) {
		RecognizeRecord recognizeRecord = recognizeRecordManager.getRecognizeRecordById(id, orgId);
		return new ServiceResponse<RecognizeRecordDto>(true, null, RecognizeRecordConvertor.recognizeRecordToRecognizeRecordDto(recognizeRecord));
	}
	  
	@Override
	public ServiceResponse<PaginationDto<RecognizeRecordDto>> getPaginationRecognizeRecord(PaginationDto<RecognizeRecordDto> paginationDto,RecognizeRecordDto recognizeRecordDto) {
		Pagination<RecognizeRecord> pagination = recognizeRecordManager.getPaginationRecognizeRecord(RecognizeRecordConvertor.paginationRecognizeRecordDtoToPaginationRecognizeRecord(paginationDto) , RecognizeRecordConvertor.recognizeRecordDtoToRecognizeRecord(recognizeRecordDto));
		return new ServiceResponse<PaginationDto<RecognizeRecordDto>>(true, null, RecognizeRecordConvertor.paginationRecognizeRecordToPaginationRecognizeRecordDto(pagination));
	}
	
	@Override
	public ServiceResponse<PaginationDto<RecognizeRecordViewDto>> getPaginationRecognizeRecordView(
			PaginationDto<RecognizeRecordViewDto> paginationDto, RecognizeRecordViewDto recognizeRecordViewDto) {
		Pagination<RecognizeRecordView> pagination = recognizeRecordManager.getPaginationRecognizeRecordView(RecognizeRecordViewConvertor.paginationRecognizeRecordViewDtoToPaginationRecognizeRecordView(paginationDto) , RecognizeRecordViewConvertor.recognizeRecordViewDtoToRecognizeRecordView(recognizeRecordViewDto));
		return new ServiceResponse<PaginationDto<RecognizeRecordViewDto>>(true, null, RecognizeRecordViewConvertor.paginationRecognizeRecordViewToPaginationRecognizeRecordViewDto(pagination));
	}

	@Override
	public ServiceResponse<PaginationDto<RecognizeRecordViewDto>> getH5CompanyPaginationRecognizeRecordView(
			PaginationDto<RecognizeRecordViewDto> paginationDto, RecognizeRecordViewDto recognizeRecordViewDto) {
		Pagination<RecognizeRecordView> pagination = recognizeRecordManager.getH5CompanyPaginationRecognizeRecordView(RecognizeRecordViewConvertor.paginationRecognizeRecordViewDtoToPaginationRecognizeRecordView(paginationDto) , RecognizeRecordViewConvertor.recognizeRecordViewDtoToRecognizeRecordView(recognizeRecordViewDto));
		return new ServiceResponse<PaginationDto<RecognizeRecordViewDto>>(true, null, RecognizeRecordViewConvertor.paginationRecognizeRecordViewToPaginationRecognizeRecordViewDto(pagination));
	}


	@Override
	public ServiceResponse<Integer> getRecognizeRecordCount(RecognizeRecordDto recognizeRecordDto) {
		Integer count = recognizeRecordManager.getRecognizeRecordCount(RecognizeRecordConvertor.recognizeRecordDtoToRecognizeRecord(recognizeRecordDto));
		return new ServiceResponse<Integer>(true, null, count);
	}

	@Override
	public ServiceResponse<List<RecognizeRecordTodayDto>> getRecognizeRecordToday(Long orgId) {
		List<RecognizeRecordToday> recognizeRecordCountList = recognizeRecordManager.getRecognizeRecordToday(orgId);
		return new ServiceResponse<List<RecognizeRecordTodayDto>>(true, null, RecognizeRecordTodayConvertor.recognizeRecordTodayListToRecognizeRecordTodayDtoList(recognizeRecordCountList));
	}

	@Override
	public ServiceResponse<List<DeviceRecognizeCountTodayDto>> getDeviceRecognizeCountToday(Long orgId, Integer recognizeUserType) {
		List<DeviceRecognizeCountToday> recognizeCountList = recognizeRecordManager.getDeviceRecognizeCountToday(orgId, recognizeUserType);
		return new ServiceResponse<List<DeviceRecognizeCountTodayDto>>(true, null, DeviceRecognizeCountTodayConvertor.deviceRecognizeCountTodayListToDeviceRecognizeCountTodayDtoList(recognizeCountList));
	}

	@Override
	public ServiceResponse<List<SyncRecordResultDto>> getSyncRecognizeRecord(SyncRecordDto syncRecordDto) {
		ServiceResponse<List<SyncRecordResultDto>> sr = new ServiceResponse<List<SyncRecordResultDto>>(true, null);
		try {
			String checkMsg = SyncRecordDto.checkParam(syncRecordDto);
			if(StringUtil.isNotBlank(checkMsg)){
				logger.error("{},param-error={},param={}", "RecognizeRecordServiceImpl.getSyncRecognizeRecord",
						JsonUtils.toJson(checkMsg), JsonUtils.toJson(syncRecordDto));
				sr.setErrorContext(new ErrorContext(CommonErrorCode.UNKONWN, "参数错误：" + checkMsg));
				sr.setSuccess(false);
				return sr;
			}
			
			OrgAuthDto orgAuthDto = orgAuthManager.queryDOByOrgId(syncRecordDto.getOrgId());
			
			if (orgAuthDto == null) {
				sr.setErrorContext(new ErrorContext(CommonErrorCode.UNKONWN, "已经停止考勤同步"));
				sr.setSuccess(false);				
			}
			else {
				
				//先以修改时间位起始时间。
				syncRecordDto.setStartTime(orgAuthDto.getGmtModify());
				sr.setData(recognizeRecordManager.getSyncRecognizeRecord(syncRecordDto));				
			}
		} catch (Exception e) {
			logger.error(String.format("RecognizeRecordServiceImpl.getSyncRecognizeRecord error, param=%s", 
					JsonUtils.toJson(syncRecordDto)), e);
			sr.setErrorContext(new ErrorContext(CommonErrorCode.UNKONWN, "获取考勤同步记录异常：" + e.getMessage()));
			sr.setSuccess(false);
		}
		return sr;
	}




}
