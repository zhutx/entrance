package com.xier.sesame.attence.domain;

import java.util.Date;
import com.xier.sesame.common.base.domain.BaseDomain;

public class TimeSetting extends BaseDomain {

	private static final long serialVersionUID = -0L;
	
	//门禁时间设置id 
	private Long timeSettingId;
	
	//机构id 
	private Long orgId;
	
	//门禁禁用日期 
	private String disableDate;
	
	//创建时间 
	private Date gmtCreate;
	
	//修改时间 
	private Date gmtModify;
	

	public Long getTimeSettingId() {
		return timeSettingId;
	}

	public void setTimeSettingId(Long timeSettingId) {
		this.timeSettingId = timeSettingId;
	}
	
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	public String getDisableDate() {
		return disableDate;
	}

	public void setDisableDate(String disableDate) {
		this.disableDate = disableDate;
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
