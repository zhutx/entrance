package com.xier.sesame.attence.transportmodel;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.xier.sesame.attence.dto.DeviceConfigDto;
import com.xier.sesame.attence.dto.NewSwitchSettingDto;
import com.xier.sesame.attence.model.OrgSubjectInfo;

/**
 * 门禁设备同步
 * @author xux
 *
 */
public class DeviceConfigControlUtil {
	
	public static String buildDeviceConfigXml(DeviceConfigDto deviceConfigDto, List<NewSwitchSettingDto> switchSettingDto, OrgSubjectInfo orgSubjectInfo,String offlinePassword){
		
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
		seeiner.setAdmin(buildAdmin(offlinePassword));
		seeiner.setResource(buildResource(orgSubjectInfo));
		
		XStream xStream = new XStream();  
        xStream.autodetectAnnotations(true);  
        return xStream.toXML(seeiner);  
		
	}
	
	private static Resource buildResource(OrgSubjectInfo orgSubjectData) {
		Resource resource = new Resource();
		Subject subject = new Subject();
		subject.setSubjectCode(orgSubjectData.getSubjectCode());
		subject.setLogo(orgSubjectData.getLogoImg());
		subject.setBgImg(orgSubjectData.getBgImg());
		subject.setHoldSeconds(orgSubjectData.getHoldSeconds());
		resource.setSubject(subject);
		return resource;
	}
	
	private static OpenTime buildOpenTime(List<NewSwitchSettingDto> switchSettingDtoList) {
		OpenTime openTime = new OpenTime();
		//In version 0.0.1,no need to send weekdays
//		openTime.setWeekDays("1,2,3,4,5,6,7");
		List<Group> groups=new ArrayList<>();
		if(switchSettingDtoList!=null) {
			for (NewSwitchSettingDto dto : switchSettingDtoList) {
				Group g = new Group();
				g.setGroupName("");
				g.setGroupId(dto.getGroupId());
				g.setBeginTime(dto.getDayBeginTime());
				g.setEndTime(dto.getDayEndTime());
				g.setGroupCode(dto.getGroupCode());
				g.setWeekdays(dto.getOpenWeekDays());


				groups.add(g);
			}
		}
		openTime.setGroupList(groups);
//		openTime.setBeginTime(switchSettingDto.getDayBeginTime());
//		openTime.setEndTime(switchSettingDto.getDayEndTime());
		return openTime;
	}

	private static Admin buildAdmin(String offlinepassword) {
		Admin admin=new Admin();
		admin.setOfflinepassword(offlinepassword);
		//TODO remove it after infraed machine

		admin.setEnableScreenSaver(true);
		return admin;
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
