package com.xier.sesame.attence.domain;

import com.xier.sesame.common.base.domain.BaseDomain;

public class DeviceConfig extends BaseDomain {

	private static final long serialVersionUID = -0L;
	
	// 
	private Long deviceConfigId;
	
	//机构id 
	private Long orgId;
	
	//版本号 
	private String bizVersion;
	
	//读卡器类型(0-身份证,1-IC卡) 
	private Integer cardReaderType;
	
	//读卡器厂家 
	private String cardReaderVender;
	
	//服务类型(0-云端,1-本地) 
	private Integer faceDetectedService;
	
	//本地识别服务器地址 
	private String faceDetectedServiceUrl;
	
	//识别阈值 
	private String faceDetectedServiceThreshold;
	
	// 
	private String faceDetectedMinbrightness;
	
	//最大亮度 
	private String faceDetectedMaxbrightness;
	
	//高斯模糊 
	private String faceDetectedGaussianblur;
	
	//运动模糊 
	private String faceDetectedMotionblur;
	
	//人脸框最小值 
	private String faceDetectedMinfacesize;
	
	//识别方式(0-1:N, 1-1:1) 
	private Integer faceDetectedRecogtype;
	
	//摄像头类型(0-USB,1-IPcamera) 
	private Integer cameraType;
	
	//开门类型(0-三全视,1-联智通达) 
	private Integer gpioType;
	
	//gpio口的开关量 
	private String gpioValue;
	

	public Long getDeviceConfigId() {
		return deviceConfigId;
	}

	public void setDeviceConfigId(Long deviceConfigId) {
		this.deviceConfigId = deviceConfigId;
	}
	
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	public String getBizVersion() {
		return bizVersion;
	}

	public void setBizVersion(String bizVersion) {
		this.bizVersion = bizVersion;
	}
	
	public Integer getCardReaderType() {
		return cardReaderType;
	}

	public void setCardReaderType(Integer cardReaderType) {
		this.cardReaderType = cardReaderType;
	}
	
	public String getCardReaderVender() {
		return cardReaderVender;
	}

	public void setCardReaderVender(String cardReaderVender) {
		this.cardReaderVender = cardReaderVender;
	}
	
	public Integer getFaceDetectedService() {
		return faceDetectedService;
	}

	public void setFaceDetectedService(Integer faceDetectedService) {
		this.faceDetectedService = faceDetectedService;
	}
	
	public String getFaceDetectedServiceUrl() {
		return faceDetectedServiceUrl;
	}

	public void setFaceDetectedServiceUrl(String faceDetectedServiceUrl) {
		this.faceDetectedServiceUrl = faceDetectedServiceUrl;
	}
	
	public String getFaceDetectedServiceThreshold() {
		return faceDetectedServiceThreshold;
	}

	public void setFaceDetectedServiceThreshold(String faceDetectedServiceThreshold) {
		this.faceDetectedServiceThreshold = faceDetectedServiceThreshold;
	}
	
	public String getFaceDetectedMinbrightness() {
		return faceDetectedMinbrightness;
	}

	public void setFaceDetectedMinbrightness(String faceDetectedMinbrightness) {
		this.faceDetectedMinbrightness = faceDetectedMinbrightness;
	}
	
	public String getFaceDetectedMaxbrightness() {
		return faceDetectedMaxbrightness;
	}

	public void setFaceDetectedMaxbrightness(String faceDetectedMaxbrightness) {
		this.faceDetectedMaxbrightness = faceDetectedMaxbrightness;
	}
	
	public String getFaceDetectedGaussianblur() {
		return faceDetectedGaussianblur;
	}

	public void setFaceDetectedGaussianblur(String faceDetectedGaussianblur) {
		this.faceDetectedGaussianblur = faceDetectedGaussianblur;
	}
	
	public String getFaceDetectedMotionblur() {
		return faceDetectedMotionblur;
	}

	public void setFaceDetectedMotionblur(String faceDetectedMotionblur) {
		this.faceDetectedMotionblur = faceDetectedMotionblur;
	}
	
	public String getFaceDetectedMinfacesize() {
		return faceDetectedMinfacesize;
	}

	public void setFaceDetectedMinfacesize(String faceDetectedMinfacesize) {
		this.faceDetectedMinfacesize = faceDetectedMinfacesize;
	}
	
	public Integer getFaceDetectedRecogtype() {
		return faceDetectedRecogtype;
	}

	public void setFaceDetectedRecogtype(Integer faceDetectedRecogtype) {
		this.faceDetectedRecogtype = faceDetectedRecogtype;
	}
	
	public Integer getCameraType() {
		return cameraType;
	}

	public void setCameraType(Integer cameraType) {
		this.cameraType = cameraType;
	}
	
	public Integer getGpioType() {
		return gpioType;
	}

	public void setGpioType(Integer gpioType) {
		this.gpioType = gpioType;
	}
	
	public String getGpioValue() {
		return gpioValue;
	}

	public void setGpioValue(String gpioValue) {
		this.gpioValue = gpioValue;
	}
	
}
