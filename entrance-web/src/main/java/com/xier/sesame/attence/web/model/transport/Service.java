package com.xier.sesame.attence.web.model.transport;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class Service {
	
	@XStreamAsAttribute  
	private String url;
	
	@XStreamAsAttribute  
	private String threshold;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}
	
}
