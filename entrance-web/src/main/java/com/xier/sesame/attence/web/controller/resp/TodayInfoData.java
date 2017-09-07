package com.xier.sesame.attence.web.controller.resp;

public class TodayInfoData {
	
	private Integer totalTimes = 0;
	private Integer employeeTimes = 0;
	private Integer visitorTimes = 0;
	private Integer stangerTimes = 0;
	
	public Integer getTotalTimes() {
		return totalTimes;
	}
	public void setTotalTimes(Integer totalTimes) {
		this.totalTimes = totalTimes;
	}
	public Integer getEmployeeTimes() {
		return employeeTimes;
	}
	public void setEmployeeTimes(Integer employeeTimes) {
		this.employeeTimes = employeeTimes;
	}
	public Integer getVisitorTimes() {
		return visitorTimes;
	}
	public void setVisitorTimes(Integer visitorTimes) {
		this.visitorTimes = visitorTimes;
	}
	public Integer getStangerTimes() {
		return stangerTimes;
	}
	public void setStangerTimes(Integer stangerTimes) {
		this.stangerTimes = stangerTimes;
	}

}
