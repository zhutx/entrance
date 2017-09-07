package com.xier.sesame.attence.dto;

import com.xier.sesame.common.rpc.dto.BaseDto;

public class AddOrgAuthDto extends BaseDto {

	private static final long serialVersionUID = 2478555576467167126L;

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
	private Integer status = 1;
	
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

}
