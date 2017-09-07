package com.xier.sesame.attence.domain;

import com.xier.sesame.common.base.domain.BaseDomain;

public class DeviceRecognizeCountToday extends BaseDomain {
	
	private static final long serialVersionUID = 5219883939620080843L;

	//设备唯一码
	private String uniqueNumber;
	
	//识别记录数
	private Integer recognizeCount;


	public String getUniqueNumber() {
		return uniqueNumber;
	}

	public void setUniqueNumber(String uniqueNumber) {
		this.uniqueNumber = uniqueNumber;
	}

	public Integer getRecognizeCount() {
		return recognizeCount;
	}

	public void setRecognizeCount(Integer recognizeCount) {
		this.recognizeCount = recognizeCount;
	}

}
