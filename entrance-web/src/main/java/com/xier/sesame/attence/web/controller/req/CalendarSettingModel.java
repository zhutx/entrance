package com.xier.sesame.attence.web.controller.req;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarSettingModel {
	
	private Long orgId;
	private String date;
	private Integer disableFlag;
	
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.date = sdf.format(d);
	}
	public Integer getDisableFlag() {
		return disableFlag;
	}
	public void setDisableFlag(Integer disableFlag) {
		this.disableFlag = disableFlag;
	}
	
}
