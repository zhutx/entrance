package com.xier.sesame.attence.transportmodel;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("moredian")
public class Seeiner {
	
	@XStreamAlias("bizversion")  
	private String bizVersion;
	
	@XStreamAlias("cardreader")  
	private CardReader cardReader;
	
	@XStreamAlias("facedetected")  
	private CloudFaceDetected cloudFaceDetected;
	
	@XStreamAlias("facedetected")  
	private LocalFaceDetected localFaceDetected;
	
	private Camera camera;

	@XStreamAlias("admin")
	private Admin admin;
	
	private Gpio gpio;
	
	@XStreamAlias("opentime")  
	private OpenTime openTime;
	
	private Resource resource;

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getBizVersion() {
		return bizVersion;
	}
	public void setBizVersion(String bizVersion) {
		this.bizVersion = bizVersion;
	}
	public CardReader getCardReader() {
		return cardReader;
	}
	public void setCardReader(CardReader cardReader) {
		this.cardReader = cardReader;
	}
	public CloudFaceDetected getCloudFaceDetected() {
		return cloudFaceDetected;
	}
	public void setCloudFaceDetected(CloudFaceDetected cloudFaceDetected) {
		this.cloudFaceDetected = cloudFaceDetected;
	}
	public Camera getCamera() {
		return camera;
	}
	public void setCamera(Camera camera) {
		this.camera = camera;
	}
	public Gpio getGpio() {
		return gpio;
	}
	public void setGpio(Gpio gpio) {
		this.gpio = gpio;
	}
	public OpenTime getOpenTime() {
		return openTime;
	}
	public void setOpenTime(OpenTime openTime) {
		this.openTime = openTime;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public LocalFaceDetected getLocalFaceDetected() {
		return localFaceDetected;
	}
	public void setLocalFaceDetected(LocalFaceDetected localFaceDetected) {
		this.localFaceDetected = localFaceDetected;
	}
	
	

}
