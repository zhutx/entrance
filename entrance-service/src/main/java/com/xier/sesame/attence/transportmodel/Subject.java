package com.xier.sesame.attence.transportmodel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("subject")
public class Subject {
	
	@XStreamAsAttribute  
	@XStreamAlias("id")
	private String subjectCode;
	
	@XStreamAsAttribute  
	@XStreamAlias("logo")
	private String logo;
	
	@XStreamAsAttribute  
	@XStreamAlias("bgimg")
	private String bgImg;
	
	@XStreamAsAttribute  
	@XStreamAlias("holdseconds")
	private Integer holdSeconds;

	
	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getBgImg() {
		return bgImg;
	}

	public void setBgImg(String bgImg) {
		this.bgImg = bgImg;
	}

	public Integer getHoldSeconds() {
		return holdSeconds;
	}

	public void setHoldSeconds(Integer holdSeconds) {
		this.holdSeconds = holdSeconds;
	}

}
