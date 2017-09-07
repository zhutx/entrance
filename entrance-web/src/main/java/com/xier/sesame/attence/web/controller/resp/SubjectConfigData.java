package com.xier.sesame.attence.web.controller.resp;

public class SubjectConfigData {
	
	private Long subjectSettingId;
	private Long subOrgId;
	private String subOrgRemark;
	private String subjectName;
	private Integer holdSeconds;
	private Integer status;
	
	public Long getSubOrgId() {
		return subOrgId;
	}
	public void setSubOrgId(Long subOrgId) {
		this.subOrgId = subOrgId;
	}
	public Long getSubjectSettingId() {
		return subjectSettingId;
	}
	public void setSubjectSettingId(Long subjectSettingId) {
		this.subjectSettingId = subjectSettingId;
	}
	public String getSubOrgRemark() {
		return subOrgRemark;
	}
	public void setSubOrgRemark(String subOrgRemark) {
		this.subOrgRemark = subOrgRemark;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public Integer getHoldSeconds() {
		return holdSeconds;
	}
	public void setHoldSeconds(Integer holdSeconds) {
		this.holdSeconds = holdSeconds;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}
