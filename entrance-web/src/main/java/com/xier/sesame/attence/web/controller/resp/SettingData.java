package com.xier.sesame.attence.web.controller.resp;

public class SettingData {
	
	private Integer attenceSwitch = 0;
	private Integer stangerSwitch = 0;
	private String openWeekDays = "";
	private String dayBeginTime = "00:00";
	private String dayEndTime = "23:59";
	
	public Integer getAttenceSwitch() {
		return attenceSwitch;
	}
	public void setAttenceSwitch(Integer attenceSwitch) {
		this.attenceSwitch = attenceSwitch;
	}
	public Integer getStangerSwitch() {
		return stangerSwitch;
	}
	public void setStangerSwitch(Integer stangerSwitch) {
		this.stangerSwitch = stangerSwitch;
	}
	public String getOpenWeekDays() {
		return openWeekDays;
	}
	public void setOpenWeekDays(String openWeekDays) {
		this.openWeekDays = openWeekDays;
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
