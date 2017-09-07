package com.xier.sesame.attence.web.controller.req;

public class SetDayOpenTimeModel {
	
	private Long orgId;
	private String dayBeginTime;
	private String dayEndTime;
	
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getDayBeginTime() {
		return dayBeginTime;
	}
	public void setDayBeginTime(String dayBeginTime) {
		this.dayBeginTime = dayBeginTime;
	}
	public String getDayEndTime() {
		return dayEndTime;
	}
	public void setDayEndTime(String dayEndTime) {
		this.dayEndTime = dayEndTime;
	}

}
