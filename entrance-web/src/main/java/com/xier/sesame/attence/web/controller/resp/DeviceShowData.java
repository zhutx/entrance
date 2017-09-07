package com.xier.sesame.attence.web.controller.resp;

public class DeviceShowData {
	
	private Long equipmentId;
	private Long subOrgId;
	private String subOrgRemark;
	private Integer equipmentType;
	private Integer status;
	private Integer catchTimes;
	private Integer warnTimes;
	
	public Long getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Long equipmentId) {
		this.equipmentId = equipmentId;
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
	public Integer getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(Integer equipmentType) {
		this.equipmentType = equipmentType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCatchTimes() {
		return catchTimes;
	}
	public void setCatchTimes(Integer catchTimes) {
		this.catchTimes = catchTimes;
	}
	public Integer getWarnTimes() {
		return warnTimes;
	}
	public void setWarnTimes(Integer warnTimes) {
		this.warnTimes = warnTimes;
	}

}
