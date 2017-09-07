package com.xier.sesame.attence.transportmodel;

import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Data;

import java.util.List;

@Data
public class OpenTime {
	
//	@XStreamAlias("weekdays")
//	private String weekDays;
	
//	@XStreamAlias("begintime")
//	private String beginTime;
//
//	@XStreamAlias("endtime")
//	private String endTime;

	@XStreamImplicit(itemFieldName="group")
	private List<Group> groupList;

//	public String getWeekDays() {
//		return weekDays;
//	}
//
//	public void setWeekDays(String weekDays) {
//		this.weekDays = weekDays;
//	}
//
//	public String getBeginTime() {
//		return beginTime;
//	}
//
//	public void setBeginTime(String beginTime) {
//		this.beginTime = beginTime;
//	}
//
//	public String getEndTime() {
//		return endTime;
//	}
//
//	public void setEndTime(String endTime) {
//		this.endTime = endTime;
//	}
	

}
