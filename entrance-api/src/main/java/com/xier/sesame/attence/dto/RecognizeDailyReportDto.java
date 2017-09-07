package com.xier.sesame.attence.dto;

import com.xier.sesame.common.rpc.dto.BaseDto;

import java.util.Date;

public class RecognizeDailyReportDto extends BaseDto {

	private static final long serialVersionUID = -0L;
	
	//识别日报id 
	private String recognizeDailyReportId;
	
	//机构id 
	private Long orgId;
	
	//报告日期 
	private Date reportDate;
	
	//总识别次数 
	private Integer totalTimes;
	
	//员工识别次数 
	private Integer employeeTimes;
	
	//访客识别次数 
	private Integer visitorTimes;
	
	//陌生人识别次数 
	private Integer stangerTimes;
	
	// 
	private Date gmtCreate;
	
	// 
	private Date gmtModify;
	


	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getRecognizeDailyReportId() {
		return recognizeDailyReportId;
	}

	public void setRecognizeDailyReportId(String recognizeDailyReportId) {
		this.recognizeDailyReportId = recognizeDailyReportId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

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
