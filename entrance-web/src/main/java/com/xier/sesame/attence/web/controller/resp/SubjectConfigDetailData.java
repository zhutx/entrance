package com.xier.sesame.attence.web.controller.resp;

public class SubjectConfigDetailData {
	
	private Long subOrgId;
	private String subOrgRemark;
	private Long subjectId;
	private String subjectName;
	private String showImg;
	private String logoImg;
	private Integer holdSeconds;
	
	public Long getSubOrgId() {
		return subOrgId;
	}
	public void setSubOrgId(Long subOrgId) {
		this.subOrgId = subOrgId;
	}
	public Long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
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
	public String getShowImg() {
		return showImg;
	}
	public void setShowImg(String showImg) {
		this.showImg = showImg;
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
