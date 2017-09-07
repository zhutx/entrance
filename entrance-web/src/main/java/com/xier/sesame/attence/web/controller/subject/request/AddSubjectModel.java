package com.xier.sesame.attence.web.controller.subject.request;

public class AddSubjectModel {
	
	private String subjectCode;
	private String subjectName;
	private String subjectDesc;
	private Integer defaultFlag;
	private String showImg;
	private String bgImg;
	private String logoImg;
	private Integer holdSeconds;
	
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectDesc() {
		return subjectDesc;
	}
	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}
	public Integer getDefaultFlag() {
		return defaultFlag;
	}
	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
	public String getShowImg() {
		return showImg;
	}
	public void setShowImg(String showImg) {
		this.showImg = showImg;
	}
	public String getBgImg() {
		return bgImg;
	}
	public void setBgImg(String bgImg) {
		this.bgImg = bgImg;
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
