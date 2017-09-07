package com.xier.sesame.attence.domain;

import com.xier.sesame.common.base.domain.BaseDomain;

import java.util.Date;

public class RecognizeRecord extends BaseDomain {

	private static final long serialVersionUID = -0L;
	
	// 
	private Long recognizeRecordId;
	
	//底库用户ID 
	private Long personId;
	
	//关联用户ID 
	private Long userId;
	
	//目标人脸图标id
	private Long facePicId;
	
	//关联用户所在机构 
	private Long orgId;
	
	//抓拍图片 
	private String catchImageUrl;
	
	//对比图片 
	private String matchImageUrl;
	
	//识别记录的设备ID 
	private String uniqueNumber;

	//识别记录的设备名，用于显示给用户
	private String deviceName;
	
	//识别机构名 
	private String orgName;
	
	//识别记录的时间 
	private Long recognizeTime;
	
	private Long subOrgId;
	
	private String subOrgCode;
	
	private String subOrgRemark;
	
	private String relationSubOrgRemark;
	
	private String department;

	private String departmentId;
	
	//用于识别的图片质量 
	private Double quality;
	
	//识别出结果的可信度 
	private Double confidence;
	
	//识别类型 
	private Integer recognizeType;
	
	//姓名 
	private String realName;
	
	private String mobile;
	
	private String identityNo;
	
	//识别用户类型(1-员工,2-访客,3-黑名单) 
	private Integer recognizeUserType;
	
	//状态 
	private Integer status;
	
	/**
	 * 同步数据状态
	 */
	private Integer syncStatus;
	
	/**
	 * 重试次数
	 */
	private Integer retryCount;
	
	/**
	 * 下一次重试时间
	 */
	private Date nextRetryTime;
	
	//创建时间 
	private Date gmtCreate;
	
	//修改时间 
	private Date gmtModify;
	

	public Long getRecognizeRecordId() {
		return recognizeRecordId;
	}

	public void setRecognizeRecordId(Long recognizeRecordId) {
		this.recognizeRecordId = recognizeRecordId;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getMatchImageUrl() {
		return matchImageUrl;
	}

	public void setMatchImageUrl(String matchImageUrl) {
		this.matchImageUrl = matchImageUrl;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getRecognizeTime() {
		return recognizeTime;
	}

	public void setRecognizeTime(Long recognizeTime) {
		this.recognizeTime = recognizeTime;
	}

	public Long getSubOrgId() {
		return subOrgId;
	}

	public void setSubOrgId(Long subOrgId) {
		this.subOrgId = subOrgId;
	}

	public String getSubOrgRemark() {
		return subOrgRemark;
	}

	public void setSubOrgRemark(String subOrgRemark) {
		this.subOrgRemark = subOrgRemark;
	}

	public Double getQuality() {
		return quality;
	}

	public void setQuality(Double quality) {
		this.quality = quality;
	}

	public Double getConfidence() {
		return confidence;
	}

	public void setConfidence(Double confidence) {
		this.confidence = confidence;
	}

	public Integer getRecognizeType() {
		return recognizeType;
	}

	public void setRecognizeType(Integer recognizeType) {
		this.recognizeType = recognizeType;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getRecognizeUserType() {
		return recognizeUserType;
	}

	public void setRecognizeUserType(Integer recognizeUserType) {
		this.recognizeUserType = recognizeUserType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModify() {
		return gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFacePicId() {
		return facePicId;
	}

	public void setFacePicId(Long facePicId) {
		this.facePicId = facePicId;
	}

	public String getCatchImageUrl() {
		return catchImageUrl;
	}

	public void setCatchImageUrl(String catchImageUrl) {
		this.catchImageUrl = catchImageUrl;
	}

	public String getUniqueNumber() {
		return uniqueNumber;
	}

	public void setUniqueNumber(String uniqueNumber) {
		this.uniqueNumber = uniqueNumber;
	}

	public String getRelationSubOrgRemark() {
		return relationSubOrgRemark;
	}

	public void setRelationSubOrgRemark(String relationSubOrgRemark) {
		this.relationSubOrgRemark = relationSubOrgRemark;
	}

	public String getSubOrgCode() {
		return subOrgCode;
	}

	public void setSubOrgCode(String subOrgCode) {
		this.subOrgCode = subOrgCode;
	}

	public String getDepartment() {
		return department;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public void setDepartment(String department) {
		this.department = department;
	}



	public Integer getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(Integer syncStatus) {
		this.syncStatus = syncStatus;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}

	public Date getNextRetryTime() {
		return nextRetryTime;
	}

	public void setNextRetryTime(Date nextRetryTime) {
		this.nextRetryTime = nextRetryTime;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
}
