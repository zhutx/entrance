package com.xier.sesame.attence.dto;

import com.xier.sesame.common.rpc.dto.BaseDto;

public class RecognizeRecordTodayDto extends BaseDto {

	private static final long serialVersionUID = -0L;
	
	//用户类型 
	private Integer recognizeUserType;
	
	//识别记录数
	private Integer recognizeCount;


	public Integer getRecognizeUserType() {
		return recognizeUserType;
	}

	public void setRecognizeUserType(Integer recognizeUserType) {
		this.recognizeUserType = recognizeUserType;
	}

	public Integer getRecognizeCount() {
		return recognizeCount;
	}

	public void setRecognizeCount(Integer recognizeCount) {
		this.recognizeCount = recognizeCount;
	}
	
	
	
}
