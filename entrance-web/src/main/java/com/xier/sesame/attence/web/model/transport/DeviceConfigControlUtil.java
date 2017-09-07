package com.xier.sesame.attence.web.model.transport;

import com.thoughtworks.xstream.XStream;
import com.xier.sesame.attence.dto.DeviceConfigDto;
import com.xier.sesame.attence.dto.SubjectSettingDto;
import com.xier.sesame.attence.dto.SwitchSettingDto;
import com.xier.sesame.attence.model.SubjectInfo;

/**
 * 门禁设备同步
 * @author zhutx
 *
 */
public class DeviceConfigControlUtil {
	
	public static String buildDeviceConfigXml(DeviceConfigDto deviceConfigDto, SwitchSettingDto switchSettingDto, SubjectInfo subjectInfo){
		
		Seeiner seeiner = new Seeiner();
		seeiner.setBizVersion(deviceConfigDto.getBizVersion());
		seeiner.setCardReader(buildCardReader(deviceConfigDto));
		
		if(deviceConfigDto.getFaceDetectedService() == 0){
			seeiner.setCloudFaceDetected(buildCloudFaceDetected(deviceConfigDto));
		} else {
			seeiner.setLocalFaceDetected(buildLocalFaceDetected(deviceConfigDto));
		}
		
		seeiner.setCamera(buildCamera(deviceConfigDto));
		seeiner.setGpio(buildGpio(deviceConfigDto));
		seeiner.setOpenTime(buildOpenTime(switchSettingDto));
		seeiner.setResource(buildResource(subjectInfo));
		
		XStream xStream = new XStream();  
        xStream.autodetectAnnotations(true);  
        return xStream.toXML(seeiner);  
		
	}
	
	private static Resource buildResource(SubjectInfo subjectInfo) {
		Resource resource = new Resource();
		Subject subject = new Subject();
		subject.setSubjectCode(subjectInfo.getSubjectCode());
		subject.setLogo(subjectInfo.getLogoImg());
		subject.setBgImg(subjectInfo.getBgImg());
		subject.setHoldSeconds(subjectInfo.getHoldSeconds());
		resource.setSubject(subject);
		return resource;
	}
	
	private static OpenTime buildOpenTime(SwitchSettingDto switchSettingDto) {
		OpenTime openTime = new OpenTime();
		openTime.setWeekDays(switchSettingDto.getOpenWeekDays());
		openTime.setBeginTime(switchSettingDto.getDayBeginTime());
		openTime.setEndTime(switchSettingDto.getDayEndTime());
		return openTime;
	}
	
	private static Gpio buildGpio(DeviceConfigDto deviceConfigDto) {
		Gpio gpio = new Gpio();
		gpio.setType(deviceConfigDto.getGpioType());
		gpio.setValue(deviceConfigDto.getGpioValue());
		return gpio;
	}
	
	private static Camera buildCamera(DeviceConfigDto deviceConfigDto) {
		Camera camera = new Camera();
		camera.setType(deviceConfigDto.getCameraType());
		return camera;
	}
	
	private static LocalFaceDetected buildLocalFaceDetected(DeviceConfigDto deviceConfigDto) {
		LocalFaceDetected faceDetected = new LocalFaceDetected();
		
		Service service = new Service();
		service.setUrl(deviceConfigDto.getFaceDetectedServiceUrl());
		service.setThreshold(deviceConfigDto.getFaceDetectedServiceThreshold());
		faceDetected.setService(service);
		
		faceDetected.setDetConfig(buildDetConfig(deviceConfigDto));
		
		return faceDetected;
	}
	
	private static CloudFaceDetected buildCloudFaceDetected(DeviceConfigDto deviceConfigDto) {
		CloudFaceDetected faceDetected = new CloudFaceDetected();
		
		faceDetected.setService(deviceConfigDto.getFaceDetectedService());
		faceDetected.setDetConfig(buildDetConfig(deviceConfigDto));
		
		return faceDetected;
	}
	
	private static CardReader buildCardReader(DeviceConfigDto deviceConfigDto) {
		CardReader cardReader = new CardReader();
		cardReader.setType(deviceConfigDto.getCardReaderType());
		cardReader.setVender(deviceConfigDto.getCardReaderVender());
		return cardReader;
	}
	
	private static DetConfig buildDetConfig(DeviceConfigDto deviceConfigDto){
		DetConfig detConfig = new DetConfig();
		detConfig.setMinbrightness(deviceConfigDto.getFaceDetectedMinbrightness());
		detConfig.setMaxbrightness(deviceConfigDto.getFaceDetectedMaxbrightness());
		detConfig.setGaussianblur(deviceConfigDto.getFaceDetectedGaussianblur());
		detConfig.setMotionblur(deviceConfigDto.getFaceDetectedMotionblur());
		detConfig.setMinfacesize(deviceConfigDto.getFaceDetectedMinfacesize());
		detConfig.setRecogtype(deviceConfigDto.getFaceDetectedRecogtype());
		return detConfig;
	}

}
