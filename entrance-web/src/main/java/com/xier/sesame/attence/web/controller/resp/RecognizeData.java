package com.xier.sesame.attence.web.controller.resp;

public class RecognizeData {
	
	private Long recoId;
	private String faceImg;
	private String catchImg;
	private String subOrgRemark;
	private Integer recognizeUserType;
	private String realName;
	private Double quality;
	private Double confidence;
	private Integer status;
	private Long recoTime;
	
	public Long getRecoId() {
		return recoId;
	}
	public void setRecoId(Long recoId) {
		this.recoId = recoId;
	}
	public String getFaceImg() {
		return faceImg;
	}
	public void setFaceImg(String faceImg) {
		this.faceImg = faceImg;
	}
	public String getCatchImg() {
		return catchImg;
	}
	public void setCatchImg(String catchImg) {
		this.catchImg = catchImg;
	}
	public String getSubOrgRemark() {
		return subOrgRemark;
	}
	public void setSubOrgRemark(String subOrgRemark) {
		this.subOrgRemark = subOrgRemark;
	}
	public Integer getRecognizeUserType() {
		return recognizeUserType;
	}
	public void setRecognizeUserType(Integer recognizeUserType) {
		this.recognizeUserType = recognizeUserType;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getRecoTime() {
		return recoTime;
	}
	public void setRecoTime(Long recoTime) {
		this.recoTime = recoTime;
	}

}
