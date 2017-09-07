package com.xier.sesame.attence.convertor;

import com.xier.sesame.attence.domain.RecognizeRecord;
import com.xier.sesame.common.rpc.dto.PaginationDto;
import com.xier.sesame.attence.dto.RecognizeRecordDto;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.base.converter.PaginationConvertor;
import java.util.List;
import java.util.ArrayList;

/**
 * 识别记录数据转换器
 * @author zhutx
 */
public class RecognizeRecordConvertor {
	
	
	public static RecognizeRecordDto recognizeRecordToRecognizeRecordDto(RecognizeRecord recognizeRecord) {
		if (recognizeRecord == null)
			return null;
		RecognizeRecordDto recognizeRecordDto = new RecognizeRecordDto();		
		recognizeRecordDto.setRecognizeRecordId(recognizeRecord.getRecognizeRecordId());
		recognizeRecordDto.setPersonId(recognizeRecord.getPersonId());
		recognizeRecordDto.setUserId(recognizeRecord.getUserId());
		recognizeRecordDto.setFacePicId(recognizeRecord.getFacePicId());
		recognizeRecordDto.setOrgId(recognizeRecord.getOrgId());
		recognizeRecordDto.setCatchImageUrl(recognizeRecord.getCatchImageUrl());
		recognizeRecordDto.setMatchImageUrl(recognizeRecord.getMatchImageUrl());
		recognizeRecordDto.setUniqueNumber(recognizeRecord.getUniqueNumber());
		recognizeRecordDto.setDeviceName(recognizeRecord.getDeviceName());
		recognizeRecordDto.setOrgName(recognizeRecord.getOrgName());
		recognizeRecordDto.setRecognizeTime(recognizeRecord.getRecognizeTime());
		recognizeRecordDto.setSubOrgId(recognizeRecord.getSubOrgId());
		recognizeRecordDto.setSubOrgCode(recognizeRecord.getSubOrgCode());
		recognizeRecordDto.setSubOrgRemark(recognizeRecord.getSubOrgRemark());
		recognizeRecordDto.setRelationSubOrgRemark(recognizeRecord.getRelationSubOrgRemark());
		recognizeRecordDto.setDepartment(recognizeRecord.getDepartment());
		recognizeRecordDto.setQuality(recognizeRecord.getQuality());
		recognizeRecordDto.setConfidence(recognizeRecord.getConfidence());
		recognizeRecordDto.setRecognizeType(recognizeRecord.getRecognizeType());
		recognizeRecordDto.setRealName(recognizeRecord.getRealName());
		recognizeRecordDto.setRecognizeUserType(recognizeRecord.getRecognizeUserType());
		recognizeRecordDto.setStatus(recognizeRecord.getStatus());
		recognizeRecordDto.setGmtCreate(recognizeRecord.getGmtCreate());
		recognizeRecordDto.setGmtModify(recognizeRecord.getGmtModify());
		return recognizeRecordDto;
	}
	
	public static RecognizeRecord recognizeRecordDtoToRecognizeRecord(RecognizeRecordDto recognizeRecordDto) {
		if (recognizeRecordDto == null)
			return null;
		RecognizeRecord recognizeRecord = new RecognizeRecord();		
		recognizeRecord.setRecognizeRecordId(recognizeRecordDto.getRecognizeRecordId());
		recognizeRecord.setPersonId(recognizeRecordDto.getPersonId());
		recognizeRecord.setUserId(recognizeRecordDto.getUserId());
		recognizeRecord.setFacePicId(recognizeRecordDto.getFacePicId());
		recognizeRecord.setOrgId(recognizeRecordDto.getOrgId());
		recognizeRecord.setCatchImageUrl(recognizeRecordDto.getCatchImageUrl());
		recognizeRecord.setMatchImageUrl(recognizeRecordDto.getMatchImageUrl());
		recognizeRecord.setUniqueNumber(recognizeRecordDto.getUniqueNumber());
		recognizeRecord.setDeviceName(recognizeRecordDto.getDeviceName());
		recognizeRecord.setOrgName(recognizeRecordDto.getOrgName());
		recognizeRecord.setRecognizeTime(recognizeRecordDto.getRecognizeTime());
		recognizeRecord.setSubOrgId(recognizeRecordDto.getSubOrgId());
		recognizeRecord.setSubOrgCode(recognizeRecordDto.getSubOrgCode());
		recognizeRecord.setSubOrgRemark(recognizeRecordDto.getSubOrgRemark());
		recognizeRecord.setRelationSubOrgRemark(recognizeRecordDto.getRelationSubOrgRemark());
		recognizeRecord.setDepartment(recognizeRecordDto.getDepartment());
		recognizeRecord.setQuality(recognizeRecordDto.getQuality());
		recognizeRecord.setConfidence(recognizeRecordDto.getConfidence());
		recognizeRecord.setRecognizeType(recognizeRecordDto.getRecognizeType());
		recognizeRecord.setRealName(recognizeRecordDto.getRealName());
		recognizeRecord.setRecognizeUserType(recognizeRecordDto.getRecognizeUserType());
		recognizeRecord.setStatus(recognizeRecordDto.getStatus());
		recognizeRecord.setSyncStatus(recognizeRecordDto.getSyncStatus());
		recognizeRecord.setRetryCount(recognizeRecordDto.getRetryCount());
		recognizeRecord.setNextRetryTime(recognizeRecordDto.getNextRetryTime());
		recognizeRecord.setGmtCreate(recognizeRecordDto.getGmtCreate());
		recognizeRecord.setGmtModify(recognizeRecordDto.getGmtModify());
		return recognizeRecord;
	}	
	
	public static List<RecognizeRecordDto> recognizeRecordListToRecognizeRecordDtoList(List<RecognizeRecord> recognizeRecordList) {
		if (recognizeRecordList == null)
			return null;
		List<RecognizeRecordDto> RecognizeRecordDtoList = new ArrayList<RecognizeRecordDto>();
		for (RecognizeRecord recognizeRecord : recognizeRecordList) {
			RecognizeRecordDtoList.add(recognizeRecordToRecognizeRecordDto(recognizeRecord));
		}
		return RecognizeRecordDtoList;
	}
	
	public static List<RecognizeRecord> recognizeRecordDtoListToRecognizeRecordList(List<RecognizeRecordDto> recognizeRecordDtoList) {
		if (recognizeRecordDtoList == null)
			return null;
		List<RecognizeRecord> RecognizeRecordList = new ArrayList<RecognizeRecord>();
		for (RecognizeRecordDto recognizeRecordDto : recognizeRecordDtoList) {
			RecognizeRecordList.add(recognizeRecordDtoToRecognizeRecord(recognizeRecordDto));
		}
		return RecognizeRecordList;
	}
	
	public static PaginationDto<RecognizeRecordDto> paginationRecognizeRecordToPaginationRecognizeRecordDto(Pagination<RecognizeRecord> pagination) {
		PaginationDto<RecognizeRecordDto> paginationDto = PaginationConvertor.paginationToPaginationDto(pagination, new PaginationDto<RecognizeRecordDto>());
		if (paginationDto == null)
			return null;
		paginationDto.setData(recognizeRecordListToRecognizeRecordDtoList(pagination.getData()));
		return paginationDto;
	}
	
	public static Pagination<RecognizeRecord> paginationRecognizeRecordDtoToPaginationRecognizeRecord(PaginationDto<RecognizeRecordDto> paginationDto) {
		Pagination<RecognizeRecord> pagination = PaginationConvertor.paginationDtoToPagination(paginationDto, new Pagination<RecognizeRecord>());
		if (pagination == null)
			return null;
		pagination.setData(recognizeRecordDtoListToRecognizeRecordList(paginationDto.getData()));
		return pagination;
	}
	
}