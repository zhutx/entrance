package com.xier.sesame.attence.web.model.transport;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

public class DetConfig {
	
	@XStreamAsAttribute  
	private String minbrightness;
	
	@XStreamAsAttribute  
	private String maxbrightness;
	
	@XStreamAsAttribute  
	private String gaussianblur;
	
	@XStreamAsAttribute  
	private String motionblur;
	
	@XStreamAsAttribute  
	private String minfacesize;
	
	@XStreamAsAttribute  
	private Integer recogtype;

	public String getMinbrightness() {
		return minbrightness;
	}

	public void setMinbrightness(String minbrightness) {
		this.minbrightness = minbrightness;
	}

	public String getMaxbrightness() {
		return maxbrightness;
	}

	public void setMaxbrightness(String maxbrightness) {
		this.maxbrightness = maxbrightness;
	}

	public String getGaussianblur() {
		return gaussianblur;
	}

	public void setGaussianblur(String gaussianblur) {
		this.gaussianblur = gaussianblur;
	}

	public String getMotionblur() {
		return motionblur;
	}

	public void setMotionblur(String motionblur) {
		this.motionblur = motionblur;
	}

	public String getMinfacesize() {
		return minfacesize;
	}

	public void setMinfacesize(String minfacesize) {
		this.minfacesize = minfacesize;
	}

	public Integer getRecogtype() {
		return recogtype;
	}

	public void setRecogtype(Integer recogtype) {
		this.recogtype = recogtype;
	}

	

}
