package com.xier.sesame.attence.domain;

import java.util.Date;

import com.xier.sesame.common.base.domain.BaseDomain;

public class SubjectConfig extends BaseDomain {

	private static final long serialVersionUID = 8837349384993247701L;

	private Long subjectConfigId;
	
	private Long orgId;
	
	private Long deviceId;
	
	private Long subjectId;
	
	private Date gmtCreate;
	
	private Date gmtModify;

	public Long getSubjectConfigId() {
		return subjectConfigId;
	}

	public void setSubjectConfigId(Long subjectConfigId) {
		this.subjectConfigId = subjectConfigId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
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
	
	
}
