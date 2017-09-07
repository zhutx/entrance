package com.xier.sesame.attence.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.google.gson.Gson;
import com.moredian.bee.common.utils.HttpInvoker;
import com.moredian.bee.common.utils.HttpInvokerResponse;
import com.moredian.fishnet.device.enums.DeviceType;
import com.moredian.fishnet.device.model.DeviceInfo;
import com.moredian.fishnet.device.model.TransferMessageInfo;
import com.moredian.fishnet.device.request.TransferRequest;
import com.moredian.fishnet.device.service.DeviceGroupRelationService;
import com.moredian.fishnet.device.service.DeviceService;
import com.moredian.fishnet.org.service.GroupService;
import com.xier.sesame.attence.convertor.DeviceConfigConvertor;
import com.xier.sesame.attence.domain.DeviceConfig;
import com.xier.sesame.attence.domain.OffLinePassword;
import com.xier.sesame.attence.domain.OrgSubject;
import com.xier.sesame.attence.domain.Subject;
import com.xier.sesame.attence.dto.AttenceDeployDto;
import com.xier.sesame.attence.dto.DeviceConfigDto;
import com.xier.sesame.attence.dto.NewSwitchSettingDto;
import com.xier.sesame.attence.dto.SubjectSettingDto;
import com.xier.sesame.attence.manager.*;
import com.xier.sesame.attence.model.OrgSubjectInfo;
import com.xier.sesame.attence.service.*;
import com.xier.sesame.attence.transportmodel.DeviceConfigControlUtil;
import com.xier.sesame.attence.utils.StringEncoderUtil;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.common.rpc.dto.PaginationDto;
import com.xier.sesame.common.uploadfile.manager.ImageFileManager;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * 设备配置Service
 * @author zhutx
 */
@Service
public class DeviceConfigServiceImpl implements DeviceConfigService {

	public static final Logger logger = LoggerFactory.getLogger(DeviceConfigServiceImpl.class);


	@Value("${spider.web.address}")
	private String spiderWebAddress;

	final public static String DEVICE_MGMT_PATH = "/services/device/management";

	private String urlOfDeviceMgmtService() {
		return this.spiderWebAddress + DEVICE_MGMT_PATH;
	}


	@Autowired
	private DeviceConfigManager deviceConfigManager;

	@Autowired
	private OffLinePasswordManager offLinePasswordManager;

	@Reference
	private DeviceService deviceService;


	@Reference
	private NewSwitchSettingService switchSettingService;

	@Reference
	private SubjectConfigService subjectSettingService;

	@Reference
	private SubjectService subjectService;

	@Reference
	private GroupService groupService;

	@Reference
	private AttenceDeployService attenceDeployService;

	@Autowired
	private SubjectConfigManager subjectSettingManager;
	@Autowired
	private SubjectManager subjectManager;
	@Autowired
	private OrgSubjectManager orgSubjectManager;
	@Autowired
	private ImageFileManager imageFileManager;
	@Reference
	private DeviceGroupRelationService deviceGroupRelationService;

	@Override
	public ServiceResponse<Long> addDeviceConfig(DeviceConfigDto deviceConfigDto) {
		ServiceResponse<Long> serviceResponse = deviceConfigManager.addDeviceConfig(DeviceConfigConvertor.deviceConfigDtoToDeviceConfig(deviceConfigDto));
		return serviceResponse;
	}

	@Override
	public ServiceResponse<Integer> updateDeviceConfig(DeviceConfigDto deviceConfigDto) {
		Integer count = deviceConfigManager.updateDeviceConfig(DeviceConfigConvertor.deviceConfigDtoToDeviceConfig(deviceConfigDto));
		return new ServiceResponse<Integer>(true, null, count);
	}

	@Override
	public ServiceResponse<Integer> updateDeviceConfigSelective(DeviceConfigDto deviceConfigDto) {
		Integer count = deviceConfigManager.updateDeviceConfigSelective(DeviceConfigConvertor.deviceConfigDtoToDeviceConfig(deviceConfigDto));
		return new ServiceResponse<Integer>(true, null, count);
	}

	@Override
	public ServiceResponse<Integer> removeDeviceConfigById(Long id) {
		Integer count = deviceConfigManager.removeDeviceConfigById(id);
		return new ServiceResponse<Integer>(true, null, count);
	}

	@Override
	public ServiceResponse<DeviceConfigDto> getDeviceConfigById(Long id) {
		DeviceConfig deviceConfig = deviceConfigManager.getDeviceConfigById(id);
		return new ServiceResponse<DeviceConfigDto>(true, null, DeviceConfigConvertor.deviceConfigToDeviceConfigDto(deviceConfig));
	}

	@Override
	public ServiceResponse<PaginationDto<DeviceConfigDto>> getPaginationDeviceConfig(PaginationDto<DeviceConfigDto> paginationDto, DeviceConfigDto deviceConfigDto) {
		Pagination<DeviceConfig> pagination = deviceConfigManager.getPaginationDeviceConfig(DeviceConfigConvertor.paginationDeviceConfigDtoToPaginationDeviceConfig(paginationDto), DeviceConfigConvertor.deviceConfigDtoToDeviceConfig(deviceConfigDto));
		return new ServiceResponse<PaginationDto<DeviceConfigDto>>(true, null, DeviceConfigConvertor.paginationDeviceConfigToPaginationDeviceConfigDto(pagination));
	}

	@Override
	public ServiceResponse<Integer> getDeviceConfigCount(DeviceConfigDto deviceConfigDto) {
		Integer count = deviceConfigManager.getDeviceConfigCount(DeviceConfigConvertor.deviceConfigDtoToDeviceConfig(deviceConfigDto));
		return new ServiceResponse<Integer>(true, null, count);
	}


	@Override
	public ServiceResponse<List<Long>> notifyXmlServer(Long orgId, List<Long> deviceIds) {
		//first, get deviceId list
//
		Gson gson = new Gson();

		List<Long> failedDeviceIds = new ArrayList<>();
		//对所有设备生成xml，并推送到xml 文件server
		for (Long deviceId : deviceIds) {
			//generate xml to fishnet
			logger.info("DeviceConfigServiceImpl start to generate xml");
			ServiceResponse<Map<String, String>> xmlMap = getXmlConfigForDevice(orgId, deviceId);
			//if generate xml failed, record the failed deviceId and continue
			if (null == xmlMap || MapUtils.isEmpty(xmlMap.getData())) {
				failedDeviceIds.add(deviceId);
				logger.error("DeviceConfigServiceImpl generate xml failed");
				continue;
			}
			String xml = xmlMap.getData().get("xml");
			logger.info(xml);
			com.moredian.bee.common.rpc.ServiceResponse<Boolean> updateResult = deviceService.updateXmlConfig(orgId, deviceId, xml);

			if (!updateResult.isSuccess() || BooleanUtils.isNotTrue(updateResult.getData())) {
				logger.error(String.format("Update xml Device Id %d is failed", deviceId));
				failedDeviceIds.add(deviceId);
			} else {
				logger.info(String.format("Update xml Device Id  %d is successfully", deviceId));
				//发送MQTT给对应设备，让设备来更新配置文件
				String deviceSn = xmlMap.getData().get("sn");
				TransferMessageInfo transferMessageInfo = buildTransferMessage(xml);
				String jsonStr = gson.toJson(transferMessageInfo);
				String base64Message = Base64.encodeBase64String(jsonStr.getBytes());
				logger.info(String.format("Start to transfer Device Device Id is %d ", deviceId));
				transferDevice(buildTransferRequest(deviceSn, base64Message));
//				deviceService.transferDevice(buildTransferRequest(deviceSn, base64Message));
				logger.info(String.format("Transfer Device completed ,Device Id is%d ", deviceId));
			}
		}

		if (CollectionUtils.isNotEmpty(failedDeviceIds)) {
			return new ServiceResponse<List<Long>>(false, null, failedDeviceIds);
		} else {
			return new ServiceResponse<List<Long>>(true, null, failedDeviceIds);

		}
	}


	@Override
	public ServiceResponse<List<Long>> deployGroupInfo(Long orgId, List<Long> deviceIds) {
		//开始布控
		List<Long> failedDeviceList = new ArrayList<>();
		for (Long deviceId : deviceIds) {
			List<NewSwitchSettingDto> settingDtoList = new ArrayList<>();

			DeviceInfo device=deviceService.getDeviceById(orgId,deviceId);

			List<Long> groupIdList = deviceGroupRelationService.findGroupIdByDeviceId(orgId, deviceId);

			for (Long groupId : groupIdList) {
				ServiceResponse<NewSwitchSettingDto> settingDto = switchSettingService.getSwitchSettingByOrgIdAndGroupId(orgId, groupId);
//                ServiceResponse<SubOrgDto> subOrgDto = groupService.getSubOrgById(groupId, deviceAndGroupBindingModel.getOrgId());
				if (settingDto.isSuccess() && settingDto.isExistData()) {
					settingDtoList.add(settingDto.getData());
				}
			}

			AttenceDeployDto attenceDeployDto = new AttenceDeployDto();
			attenceDeployDto.setOrgId(orgId);
			attenceDeployDto.setDeviceId(deviceId);
			attenceDeployDto.setGroupSettingList(settingDtoList);

			try {
//				attenceDeployService.
				attenceDeployService.addDeviceConfig(attenceDeployDto);
			} catch (Exception e) {
				logger.error("布控失败，机构ID是" + orgId + "设备ID是" + deviceId);
				failedDeviceList.add(deviceId);
			}
		}

		return new ServiceResponse<List<Long>>(true, null, failedDeviceList);

	}

	@Override
	public ServiceResponse<DeviceConfigDto> getOneDeviceConfig(DeviceConfigDto deviceConfigDto) {
		DeviceConfig deviceConfig = deviceConfigManager.getOneDeviceConfig(DeviceConfigConvertor.deviceConfigDtoToDeviceConfig(deviceConfigDto));
		return new ServiceResponse<DeviceConfigDto>(true, null, DeviceConfigConvertor.deviceConfigToDeviceConfigDto(deviceConfig));
	}

	@Override
	public ServiceResponse<Map<String, String>> getXmlConfigForDevice(Long orgId, Long deviceId) {
		Map<String, String> xmlConfingMap = this.syncDeviceConfig(orgId, deviceId);
		if (MapUtils.isEmpty(xmlConfingMap)) {
			return new ServiceResponse<Map<String, String>>(false, null, xmlConfingMap);
		} else {
			return new ServiceResponse<Map<String, String>>(true, null, xmlConfingMap);
		}
	}

	private OrgSubjectInfo getOrgSubjectInfo(Long orgId, Long deviceId) {

		Long subjectId = subjectSettingManager.getSubjectId(orgId, deviceId); // 获取设备主题id
		Subject subject=null;
		OrgSubject orgSubject=null;
		OrgSubjectInfo data = new OrgSubjectInfo();
		if(subjectId!=null) {
			subject = subjectManager.getSubjectById(subjectId); // 获取主题数据
			orgSubject = orgSubjectManager.getOrgSubject(orgId, subjectId); // 获取机构主题定制数据
			data.setSubjectId(subject.getSubjectId());
			data.setSubjectCode(subject.getSubjectCode());
			data.setSubjectName(subject.getSubjectName());
			data.setSubjectDesc(subject.getSubjectDesc());

		}


		data.setOrgId(orgId);


		if (orgSubject != null) {
			data.setShowImg(imageFileManager.getImageUrl(orgSubject.getShowImg()));
			data.setBgImg(imageFileManager.getImageUrl(orgSubject.getBgImg()));
			data.setLogoImg(imageFileManager.getImageUrl(orgSubject.getLogoImg()));
			data.setHoldSeconds(orgSubject.getHoldSeconds());
		} else {
			if(subject!=null) {
				data.setShowImg(imageFileManager.getImageUrl(subject.getShowImg()));
				data.setBgImg(imageFileManager.getImageUrl(subject.getBgImg()));
				data.setLogoImg(imageFileManager.getImageUrl(subject.getLogoImg()));
				data.setHoldSeconds(subject.getHoldSeconds());
			}
		}

		return data;
	}

	//create device xml
	private Map<String, String> syncDeviceConfig(Long orgId, Long deviceId) {

		DeviceInfo device = deviceService.getDeviceById(orgId, deviceId);

		if (device.getDeviceType() != DeviceType.BOARD_ATTENDANCE.getValue()
				&& device.getDeviceType()!=DeviceType.BOARD_BOX.getValue()
				&& device.getDeviceType()!=DeviceType.BOARD_ATTENDANCE_DUALEYE.getValue()) return null;

		List<Long> groupIdList = deviceGroupRelationService.findGroupIdByDeviceId(orgId, deviceId);

		//subOrgDeviceRelationDtoList不应该为空，为空说明设备和机构未和任何组绑定，数据异常
		ServiceResponse<List<NewSwitchSettingDto>> sr_switchSettingDto = new ServiceResponse(false, null, null);
		sr_switchSettingDto.setData(null);
		if (CollectionUtils.isEmpty(groupIdList)) {
//			ExceptionUtils.throwException(AttenceErrorCode.DEVICE_GROUP_RELATION_NOTEXIST, AttenceErrorCode.DEVICE_GROUP_RELATION_NOTEXIST.getMessage());
			logger.error("设备和组没有关系绑定");
		} else {
			sr_switchSettingDto = switchSettingService.getDeviceBindingSwitchSettings(orgId, groupIdList);

		}
		//TODO subjectSetting don't need to get.Use default one.
		ServiceResponse<DeviceConfigDto> sr_deviceConfigDto = getOneDeviceConfig(this.buildDeviceConfigDto(orgId));
		ServiceResponse<SubjectSettingDto> sr_subjectSettingDto = null;//subjectSettingService.getOneSubjectSetting(this.buildSubjectSettingDto(orgId));
		//no subject , use default subject

		// 获取设备主题数据
		OrgSubjectInfo orgSubjectInfo = this.getOrgSubjectInfo(orgId, deviceId);


//		ServiceResponse<SubjectDto> sr_subjectDto = subjectService.getSubjectById(sr_subjectSettingDto.getData().getSubjectId());
		DeviceConfigDto deviceConfigDto = sr_deviceConfigDto.getData();
		if (deviceConfigDto == null) {
			deviceConfigDto = new DeviceConfigDto();
			deviceConfigDto.setBizVersion("1");
			deviceConfigDto.setCardReaderType(1);
			deviceConfigDto.setCardReaderVender("shensi");
			deviceConfigDto.setFaceDetectedService(0);
			deviceConfigDto.setFaceDetectedMaxbrightness("255");
			deviceConfigDto.setFaceDetectedMinbrightness("0");
			deviceConfigDto.setFaceDetectedGaussianblur("0.16");
			deviceConfigDto.setFaceDetectedMotionblur("0.4");
			deviceConfigDto.setFaceDetectedMinfacesize("100");
			deviceConfigDto.setFaceDetectedRecogtype(0);
			//camera
			deviceConfigDto.setCameraType(0);
			//gpio
			deviceConfigDto.setGpioType(2);
			deviceConfigDto.setGpioValue("118");

		}

		//get offline password
		String offPassword = "";
		try {
			offPassword = StringEncoderUtil.EncoderByMd5("201704");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		OffLinePassword offLinePassword = offLinePasswordManager.getOffLinePasswordByOrgId(orgId);
		if (offLinePassword != null && offLinePassword.getPassword() != null) {
			offPassword = offLinePassword.getPassword();
		}


		//TODO just set hard-code value 0
		if(device.getDeviceType()==DeviceType.BOARD_BOX.getValue()){
			//moredian box camera type should be 1
			deviceConfigDto.setCameraType(1);
		}else{
			deviceConfigDto.setCameraType(0);
		}

		String xml = DeviceConfigControlUtil.buildDeviceConfigXml(deviceConfigDto, sr_switchSettingDto.getData(), orgSubjectInfo, offPassword);
		logger.info("------门禁设备下载了XML配置信息:-------");
		logger.info("\n" + xml);

		Map map = new HashMap<>();
		map.put("xml", xml);
		map.put("sn", device.getDeviceSn());

		return map;
	}


	//keep it until codes come back
//	private List<Long> getGroupIdList(DeviceInfo deviceInfo){
//		List<Long> groupIds=new ArrayList<>();
//		List<GroupInfo>   groupInfoList=deviceInfo.getGroups();
//		for(GroupInfo groupInfo:groupInfoList){
//			groupIds.add(groupInfo.getGroupId());
//
//		}
//
//		return groupIds;
//	}

	private SubjectSettingDto buildSubjectSettingDto(Long orgId) {
		SubjectSettingDto dto = new SubjectSettingDto();
		dto.setOrgId(orgId);
		return dto;
	}

	private DeviceConfigDto buildDeviceConfigDto(Long orgId) {
		DeviceConfigDto dto = new DeviceConfigDto();
		dto.setOrgId(orgId);
		return dto;
	}


	private TransferRequest buildTransferRequest(String sn, String message) {

		TransferRequest transferRequest = new TransferRequest();
		transferRequest.setSerialNumber(sn);
		transferRequest.setBody(message);
		//It's better to delay
		transferRequest.setDelaySeconds(5);
		return transferRequest;

	}

	private TransferMessageInfo<String> buildTransferMessage(String configXml) {
		TransferMessageInfo<String> transferMessageInfo = new TransferMessageInfo<>();
		transferMessageInfo.setEventType(101);
		transferMessageInfo.setSeverity(5);
		transferMessageInfo.setSeqId(UUID.randomUUID().toString());
		transferMessageInfo.setMessage("Download config file");
		transferMessageInfo.setData(configXml);

		return transferMessageInfo;

	}

	public boolean transferDevice(TransferRequest request) {
		String url = urlOfDeviceMgmtService();
		url = url + "/config?sn=" + request.getSerialNumber() + "&body=" + request.getBody() + "&delaySeconds=" + request.getDelaySeconds();

		logger.info("TransferDevice with uri:" + url + ";sn:" + request.getSerialNumber());
		performRequest(url);
		return true;
	}


	private boolean performRequest(String invokerUrl) {
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		headerMap.put("Accept", "text/plain, application/json, application/*+json, */*");
		Map<String, String> urlVaMap = new HashMap<String, String>();
		HttpInvokerResponse httpResponse = HttpInvoker.invokerPost(invokerUrl, headerMap, urlVaMap, "");
		if (httpResponse == null) {
			logger.debug("Failed to send activate info,url is {}.", invokerUrl);
		}
		if (httpResponse.getResponseCode() == 200) {
			return true;
		}
		return false;
	}





}
