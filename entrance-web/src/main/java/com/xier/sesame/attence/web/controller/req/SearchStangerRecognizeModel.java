package com.xier.sesame.attence.web.controller.req;

import java.util.Date;

public class SearchStangerRecognizeModel {
	
	private Long orgId;
	private Integer pageSize;
	private Integer pageNo;
	
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
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
