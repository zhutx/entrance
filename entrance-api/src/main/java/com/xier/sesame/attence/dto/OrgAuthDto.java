package com.xier.sesame.attence.dto;

import java.util.Date;

import com.xier.sesame.common.rpc.dto.BaseDto;

public class OrgAuthDto extends BaseDto {

	private static final long serialVersionUID = 2478555576467167126L;

	/**
	 * 主键id 
	 */
	private Long authId;
	
	/**
	 * 机构id
	 */
	private Long orgId;
	
	/**
	 * 对接考勤平台
	 */
	private String platformCode;
	
	/**
	 * 机构同步数据唯一标示
	 */
	private String accessId;
	
	/**
	 * 用于签名加密，一个accessid对应一个accesskey
	 */
	private String accessKey;
	
	/**
	 * 状态 
	 */
	private Integer status;
	
	/**
	 * 创建时间 
	 */
	private Date gmtCreate;
	
	/**
	 * 修改时间 
	 */
	private Date gmtModify;

	public Long getAuthId() {
		return authId;
	}

	public void setAuthId(Long authId) {
		this.authId = authId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	public String getAccessId() {
		return accessId;
	}

	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
	
	public static String checkParam(OrgAuthDto orgAuthDto) {
		if(orgAuthDto == null){
			return "orgAuthDto is null";
		}
		if (orgAuthDto.getAuthId() == null) {
			return "authId is null";
		}
		return "";
	}

}
