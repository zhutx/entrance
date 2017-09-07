package com.xier.sesame.attence.web.controller.req;

public class SetOpenWeekDaysModel {
	
	private Long orgId;
	private String openWeekDays;
	
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getOpenWeekDays() {
		return openWeekDays;
	}
	public void setOpenWeekDays(String openWeekDays) {
		this.openWeekDays = openWeekDays;
	}

}
