package com.xier.sesame.attence.web.model.transport;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class OpenTime {
	
	@XStreamAlias("weekdays")  
	private String weekDays;
	
	@XStreamAlias("begintime")  
	private String beginTime;
	
	@XStreamAlias("endtime")  
	private String endTime;

	public String getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(String weekDays) {
		this.weekDays = weekDays;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	

}
