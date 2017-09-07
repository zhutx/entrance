package com.xier.sesame.attence.domain;

import java.util.Date;
import com.xier.sesame.common.base.domain.BaseDomain;

public class Subject extends BaseDomain {

	private static final long serialVersionUID = -0L;
	
	//主题id 
	private Long subjectId;
	
	//主题编号 
	private String subjectCode;
	
	//主题名 
	private String subjectName;
	
	private String subjectDesc;
	
	//展示图
	private String showImg;
	
	private String bgImg;
	
	private String logoImg;
	
	private Integer holdSeconds;
	
	//默认标识
	private Integer defaultFlag;
	
	//状态 
	private Integer status;
	
	//创建时间 
	private Date gmtCreate;
	
	//修改时间 
	private Date gmtModify;
	

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}
	
	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public String getSubjectDesc() {
		return subjectDesc;
	}

	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}

	public String getShowImg() {
		return showImg;
	}

	public void setShowImg(String showImg) {
		this.showImg = showImg;
	}

	public String getBgImg() {
		return bgImg;
	}

	public void setBgImg(String bgImg) {
		this.bgImg = bgImg;
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

	public Integer getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
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
	
}
