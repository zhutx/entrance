package com.xier.sesame.attence.dto;

import com.xier.sesame.common.rpc.dto.BaseDto;

public class DeviceRecognizeCountTodayDto extends BaseDto {

	private static final long serialVersionUID = -0L;
	
	//唯一码
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
