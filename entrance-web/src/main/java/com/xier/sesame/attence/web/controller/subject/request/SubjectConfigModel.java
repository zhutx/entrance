package com.xier.sesame.attence.web.controller.subject.request;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class SubjectConfigModel {
	
	private Long orgId;
	private Long subjectId;
	private String logoImg;
	private String bgImg;
	private Integer holdSeconds;
	private List<Long> devices = new ArrayList<>();
	
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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
		if(StringUtils.isBlank(logoImg)){
			this.logoImg = null;
		} else {
			this.logoImg = logoImg;
		}
	}
	public String getBgImg() {
		return bgImg;
	}
	public void setBgImg(String bgImg) {
		if(StringUtils.isBlank(bgImg)){
			this.bgImg = null;
		} else {
			this.bgImg = bgImg;
		}
	}
	public Integer getHoldSeconds() {
		return holdSeconds;
	}
	public void setHoldSeconds(Integer holdSeconds) {
		this.holdSeconds = holdSeconds;
	}
	public List<Long> getDevices() {
		return devices;
	}
	public void setDevices(List<Long> devices) {
		this.devices = devices;
	}

}
