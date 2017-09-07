package com.xier.sesame.attence.web.controller.req;

import java.util.Calendar;
import java.util.Date;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.xier.sesame.common.utils.TimeUtil;

public class SearchRecognizeModel {
	
	private Long orgId;
	private String mobile;
	private String identityNo;
	private Integer recognizeUserType;
	private String realName;
	private Long subOrgId;
	private Integer status;
	private Date searchBeginDate;
	private Date searchEndDate;
	private Integer pageSize;
	private Integer pageNo;
	
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		if(!StringUtils.isBlank(mobile)) {
			this.mobile = mobile;
		} 
	}
	public String getIdentityNo() {
		return identityNo;
	}
	public void setIdentityNo(String identityNo) {
		if(!StringUtils.isBlank(identityNo)) {
			this.identityNo = identityNo;
		}
	}
	public Integer getRecognizeUserType() {
		return recognizeUserType;
	}
	public void setRecognizeUserType(Integer recognizeUserType) {
		this.recognizeUserType = recognizeUserType;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		if(!StringUtils.isBlank(realName)) {
			this.realName = realName;
		}
	}
	public Long getSubOrgId() {
		return subOrgId;
	}
	public void setSubOrgId(Long subOrgId) {
		this.subOrgId = subOrgId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getSearchBeginDate() {
		if(searchBeginDate != null) return searchBeginDate;
		// 没传值，默认从一个星期前开始查
		Calendar cal = Calendar.getInstance();
		cal.setTime(TimeUtil.getCurrentDate());
		cal.add(Calendar.DATE, -7);
		return cal.getTime();
	}
	public void setSearchBeginDate(Date searchBeginDate) {
		this.searchBeginDate = searchBeginDate;
	}
	public Date getSearchEndDate() {
		if(searchEndDate != null){
			// 将传来的日期从00:00调整为23:59分
			Calendar cal = Calendar.getInstance();
			cal.setTime(searchEndDate);
			cal.set(Calendar.HOUR_OF_DAY, 24);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.MILLISECOND, -1);
			return cal.getTime();
		} else {
			// 未传值，则查询截止日期为今日23:59
			Calendar cal = Calendar.getInstance();
			cal.setTime(TimeUtil.getCurrentEndDate());
			return cal.getTime();
		}
	}
	public void setSearchEndDate(Date searchEndDate) {
		this.searchEndDate = searchEndDate;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

}
