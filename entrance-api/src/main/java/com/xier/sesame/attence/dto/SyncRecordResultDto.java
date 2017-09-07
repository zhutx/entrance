package com.xier.sesame.attence.dto;

import com.xier.sesame.common.rpc.dto.BaseDto;

public class SyncRecordResultDto extends BaseDto {

	private static final long serialVersionUID = -6153118504664697839L;

	/**
	 * 主键id
	 */
	private Long recognizeRecordId;
	/**
	 * 机构id
	 */
	private Long orgId;
	
	/**
	 * 关联用户ID 
	 */
	private Long userId;
	
	/**
	 * 识别记录的设备序号
	 */
	private String uniqueNumber;
	
	/**
	 * 识别记录的时间 
	 */
	private Long recognizeTime;
	
	/**
	 * 重试次数
	 */
	private Integer retryCount;
	
	
	//抓拍图片 
	private String catchImageUrl;

	public Long getRecognizeRecordId() {
		return recognizeRecordId;
	}

	public void setRecognizeRecordId(Long recognizeRecordId) {
		this.recognizeRecordId = recognizeRecordId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUniqueNumber() {
		return uniqueNumber;
	}

	public void setUniqueNumber(String uniqueNumber) {
		this.uniqueNumber = uniqueNumber;
	}

	public Long getRecognizeTime() {
		return recognizeTime;
	}

	public void setRecognizeTime(Long recognizeTime) {
		this.recognizeTime = recognizeTime;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}

	public String getCatchImageUrl() {
		return catchImageUrl;
	}

	public void setCatchImageUrl(String catchImageUrl) {
		this.catchImageUrl = catchImageUrl;
	}
}
