package com.xier.sesame.attence.dto;

import java.util.Date;

import com.xier.sesame.attence.constant.AttenceConstant;
import com.xier.sesame.common.rpc.dto.BaseDto;

public class SyncRecordDto extends BaseDto {

	private static final long serialVersionUID = 2478555576467167126L;

	/**
	 * 机构id
	 */
	private Long orgId;
	

	/**
	 * 开始时间
	 */
	private Date startTime;
	
	/**
	 * 同步状态
	 */
	private int syncStatus = AttenceConstant.RecordSyncStatus.INIT;
	
	/**
	 * 重试时间
	 */
	private Date nextRetryTime;
	
	public SyncRecordDto() {
		super();
	}

	public SyncRecordDto(Long orgId, Date nextRetryTime) {
		if(orgId == null || nextRetryTime == null){
			throw new IllegalArgumentException("all param cannot null");
		}
		this.orgId = orgId;
		this.nextRetryTime = nextRetryTime;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public int getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(int syncStatus) {
		this.syncStatus = syncStatus;
	}

	public Date getNextRetryTime() {
		return nextRetryTime;
	}

	public void setNextRetryTime(Date nextRetryTime) {
		this.nextRetryTime = nextRetryTime;
	}

	public static String checkParam(SyncRecordDto syncRecordDto) {
		if(syncRecordDto == null){
			return "syncRecordDto is null";
		}
		if (syncRecordDto.getOrgId() == null || syncRecordDto.getNextRetryTime() == null) {
			return "orgId or nextRetryTime is null";
		}
		return "";
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
}
