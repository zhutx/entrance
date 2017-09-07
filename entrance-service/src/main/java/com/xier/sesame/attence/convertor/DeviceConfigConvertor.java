package com.xier.sesame.attence.convertor;

import com.xier.sesame.attence.domain.DeviceConfig;
import com.xier.sesame.common.rpc.dto.PaginationDto;
import com.xier.sesame.attence.dto.DeviceConfigDto;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.base.converter.PaginationConvertor;
import java.util.List;
import java.util.ArrayList;

/**
 * 设备配置数据转换器
 * @author zhutx
 */
public class DeviceConfigConvertor {
	
	
	public static DeviceConfigDto deviceConfigToDeviceConfigDto(DeviceConfig deviceConfig) {
		if (deviceConfig == null)
			return null;
		DeviceConfigDto deviceConfigDto = new DeviceConfigDto();		
		deviceConfigDto.setDeviceConfigId(deviceConfig.getDeviceConfigId());
		deviceConfigDto.setOrgId(deviceConfig.getOrgId());
		deviceConfigDto.setBizVersion(deviceConfig.getBizVersion());
		deviceConfigDto.setCardReaderType(deviceConfig.getCardReaderType());
		deviceConfigDto.setCardReaderVender(deviceConfig.getCardReaderVender());
		deviceConfigDto.setFaceDetectedService(deviceConfig.getFaceDetectedService());
		deviceConfigDto.setFaceDetectedServiceUrl(deviceConfig.getFaceDetectedServiceUrl());
		deviceConfigDto.setFaceDetectedServiceThreshold(deviceConfig.getFaceDetectedServiceThreshold());
		deviceConfigDto.setFaceDetectedMinbrightness(deviceConfig.getFaceDetectedMinbrightness());
		deviceConfigDto.setFaceDetectedMaxbrightness(deviceConfig.getFaceDetectedMaxbrightness());
		deviceConfigDto.setFaceDetectedGaussianblur(deviceConfig.getFaceDetectedGaussianblur());
		deviceConfigDto.setFaceDetectedMotionblur(deviceConfig.getFaceDetectedMotionblur());
		deviceConfigDto.setFaceDetectedMinfacesize(deviceConfig.getFaceDetectedMinfacesize());
		deviceConfigDto.setFaceDetectedRecogtype(deviceConfig.getFaceDetectedRecogtype());
		deviceConfigDto.setCameraType(deviceConfig.getCameraType());
		deviceConfigDto.setGpioType(deviceConfig.getGpioType());
		deviceConfigDto.setGpioValue(deviceConfig.getGpioValue());
		return deviceConfigDto;
	}
	
	public static DeviceConfig deviceConfigDtoToDeviceConfig(DeviceConfigDto deviceConfigDto) {
		if (deviceConfigDto == null)
			return null;
		DeviceConfig deviceConfig = new DeviceConfig();		
		deviceConfig.setDeviceConfigId(deviceConfigDto.getDeviceConfigId());
		deviceConfig.setOrgId(deviceConfigDto.getOrgId());
		deviceConfig.setBizVersion(deviceConfigDto.getBizVersion());
		deviceConfig.setCardReaderType(deviceConfigDto.getCardReaderType());
		deviceConfig.setCardReaderVender(deviceConfigDto.getCardReaderVender());
		deviceConfig.setFaceDetectedService(deviceConfigDto.getFaceDetectedService());
		deviceConfig.setFaceDetectedServiceUrl(deviceConfigDto.getFaceDetectedServiceUrl());
		deviceConfig.setFaceDetectedServiceThreshold(deviceConfigDto.getFaceDetectedServiceThreshold());
		deviceConfig.setFaceDetectedMinbrightness(deviceConfigDto.getFaceDetectedMinbrightness());
		deviceConfig.setFaceDetectedMaxbrightness(deviceConfigDto.getFaceDetectedMaxbrightness());
		deviceConfig.setFaceDetectedGaussianblur(deviceConfigDto.getFaceDetectedGaussianblur());
		deviceConfig.setFaceDetectedMotionblur(deviceConfigDto.getFaceDetectedMotionblur());
		deviceConfig.setFaceDetectedMinfacesize(deviceConfigDto.getFaceDetectedMinfacesize());
		deviceConfig.setFaceDetectedRecogtype(deviceConfigDto.getFaceDetectedRecogtype());
		deviceConfig.setCameraType(deviceConfigDto.getCameraType());
		deviceConfig.setGpioType(deviceConfigDto.getGpioType());
		deviceConfig.setGpioValue(deviceConfigDto.getGpioValue());
		return deviceConfig;
	}	
	
	public static List<DeviceConfigDto> deviceConfigListToDeviceConfigDtoList(List<DeviceConfig> deviceConfigList) {
		if (deviceConfigList == null)
			return null;
		List<DeviceConfigDto> DeviceConfigDtoList = new ArrayList<DeviceConfigDto>();
		for (DeviceConfig deviceConfig : deviceConfigList) {
			DeviceConfigDtoList.add(deviceConfigToDeviceConfigDto(deviceConfig));
		}
		return DeviceConfigDtoList;
	}
	
	public static List<DeviceConfig> deviceConfigDtoListToDeviceConfigList(List<DeviceConfigDto> deviceConfigDtoList) {
		if (deviceConfigDtoList == null)
			return null;
		List<DeviceConfig> DeviceConfigList = new ArrayList<DeviceConfig>();
		for (DeviceConfigDto deviceConfigDto : deviceConfigDtoList) {
			DeviceConfigList.add(deviceConfigDtoToDeviceConfig(deviceConfigDto));
		}
		return DeviceConfigList;
	}
	
	public static PaginationDto<DeviceConfigDto> paginationDeviceConfigToPaginationDeviceConfigDto(Pagination<DeviceConfig> pagination) {
		PaginationDto<DeviceConfigDto> paginationDto = PaginationConvertor.paginationToPaginationDto(pagination, new PaginationDto<DeviceConfigDto>());
		if (paginationDto == null)
			return null;
		paginationDto.setData(deviceConfigListToDeviceConfigDtoList(pagination.getData()));
		return paginationDto;
	}
	
	public static Pagination<DeviceConfig> paginationDeviceConfigDtoToPaginationDeviceConfig(PaginationDto<DeviceConfigDto> paginationDto) {
		Pagination<DeviceConfig> pagination = PaginationConvertor.paginationDtoToPagination(paginationDto, new Pagination<DeviceConfig>());
		if (pagination == null)
			return null;
		pagination.setData(deviceConfigDtoListToDeviceConfigList(paginationDto.getData()));
		return pagination;
	}
	
}