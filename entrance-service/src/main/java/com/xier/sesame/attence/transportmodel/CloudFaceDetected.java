package com.xier.sesame.attence.transportmodel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class CloudFaceDetected {
	
	private Integer service;
	@XStreamAlias("detconfig")  
	private DetConfig detConfig;
	
	public Integer getService() {
		return service;
	}
	public void setService(Integer service) {
		this.service = service;
	}
	public DetConfig getDetConfig() {
		return detConfig;
	}
	public void setDetConfig(DetConfig detConfig) {
		this.detConfig = detConfig;
	}

}
