package com.xier.sesame.attence.web.controller.req;

public class ApplySubjectSettingModel {
	
	private Long orgId;
	private Long subjectSettingId;
	private Long subjectId;
	private String logoImg;
	private Integer holdSeconds;
	
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public Long getSubjectSettingId() {
		return subjectSettingId;
	}
	public void setSubjectSettingId(Long subjectSettingId) {
		this.subjectSettingId = subjectSettingId;
	}
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	public String getLogoImg() {
		return logoImg;
	}
	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}
	public Integer getHoldSeconds() {
		return holdSeconds;
	}
	public void setHoldSeconds(Integer holdSeconds) {
		this.holdSeconds = holdSeconds;
	}
	
}
