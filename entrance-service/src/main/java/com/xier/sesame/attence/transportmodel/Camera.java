package com.xier.sesame.attence.transportmodel;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class Camera {
	
	@XStreamAsAttribute  
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


}
