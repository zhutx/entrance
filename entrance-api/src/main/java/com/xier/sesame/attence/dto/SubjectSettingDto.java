package com.xier.sesame.attence.dto;

import java.util.Date;
import com.xier.sesame.common.rpc.dto.BaseDto;

public class SubjectSettingDto extends BaseDto {

	private static final long serialVersionUID = -0L;
	
	//主题配置 
	private Long subjectSettingId;
	
	//机构id 
	private Long orgId;
	
	//设备挂载子机构id 
	private Long subOrgId;
	
	//位置名称 
	private String subOrgRemark;
	
	//主题id 
	private Long subjectId;
	
	//主题名 
	private String subjectName;
	
	//logo图
	private String logoImg;
	
	//弹窗停留时间 
	private Integer holdSeconds;
	
	//创建时间 
	private Date gmtCreate;
	
	//修改时间 
	private Date gmtModify;
	

	public Long getSubjectSettingId() {
		return subjectSettingId;
	}

	public void setSubjectSettingId(Long subjectSettingId) {
		this.subjectSettingId = subjectSettingId;
	}
	
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	public Long getSubOrgId() {
		return subOrgId;
	}

	public void setSubOrgId(Long subOrgId) {
		this.subOrgId = subOrgId;
	}
	
	public String getSubOrgRemark() {
		return subOrgRemark;
	}

	public void setSubOrgRemark(String subOrgRemark) {
		this.subOrgRemark = subOrgRemark;
	}
	
	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public String getLogoImg() {
		return logoImg;
	}

	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}

	public Integer getHoldSeconds() {
		return holdSeconds;
	}

	public void setHoldSeconds(Integer holdSeconds) {
		this.holdSeconds = holdSeconds;
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
