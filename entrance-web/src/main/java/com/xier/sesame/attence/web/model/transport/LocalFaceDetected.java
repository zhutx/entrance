package com.xier.sesame.attence.web.model.transport;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class LocalFaceDetected {
	
	private Service service;
	
	@XStreamAlias("detconfig")  
	private DetConfig detConfig;
	
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public DetConfig getDetConfig() {
		return detConfig;
	}
	public void setDetConfig(DetConfig detConfig) {
		this.detConfig = detConfig;
	}

}
