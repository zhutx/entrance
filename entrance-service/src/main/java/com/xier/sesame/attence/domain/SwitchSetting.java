package com.xier.sesame.attence.domain;

import java.util.Date;
import com.xier.sesame.common.base.domain.BaseDomain;

public class SwitchSetting extends BaseDomain {

	private static final long serialVersionUID = -0L;
	
	//开关配置id 
	private Long switchSettingId;
	
	//机构id 
	private Long orgId;
	
	//考勤统计开关 
	private Integer attenceSwitch;
	
	//陌生人告警开关 
	private Integer stangerSwitch;
	
	//一周中开放日期 
	private String openWeekDays;
	
	//每日门禁开放起始时间 
	private String dayBeginTime;
	
	//每日门禁开放截止时间 
	private String dayEndTime;
	
	//创建时间 
	private Date gmtCreate;
	
	//修改时间 
	private Date gmtModify;
	

	public Long getSwitchSettingId() {
		return switchSettingId;
	}

	public void setSwitchSettingId(Long switchSettingId) {
		this.switchSettingId = switchSettingId;
	}
	
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
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
