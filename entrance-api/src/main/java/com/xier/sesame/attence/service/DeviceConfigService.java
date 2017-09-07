package com.xier.sesame.attence.service;

import com.xier.sesame.attence.dto.DeviceConfigDto;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.common.rpc.dto.PaginationDto;

import java.util.List;
import java.util.Map;

/**
 * 设备配置Service
 * @author zhutx
 *
 */
public interface DeviceConfigService {
	
	/**
	 * 增加设备配置
	 * @param deviceConfigDto
	 * @return
	 */
	public ServiceResponse<Long> addDeviceConfig(DeviceConfigDto deviceConfigDto);
	
	
	/**
	 * 修改设备配置
	 * @param deviceConfigDto
	 * @return
	 */
	public ServiceResponse<Integer> updateDeviceConfig(DeviceConfigDto deviceConfigDto);
	
	/**
	 * 修改设备配置,只修改属性不为null的字段
	 * @param deviceConfigDto
	 * @return
	 */
	public ServiceResponse<Integer> updateDeviceConfigSelective(DeviceConfigDto deviceConfigDto);
	
	
	/**
	 * 根据ID删除设备配置
	 * @param id
	 */
	public ServiceResponse<Integer> removeDeviceConfigById(Long id);
	
	
	/**
	 * 根据id获取设备配置
	 * @param id
	 * @return
	 */
	public ServiceResponse<DeviceConfigDto> getDeviceConfigById(Long id);
	
    
	/**
	 * 分页查询设备配置
	 * @param paginationDto
	 * @param deviceConfigDto
	 * @return
	 */
	public ServiceResponse<PaginationDto<DeviceConfigDto>> getPaginationDeviceConfig(PaginationDto<DeviceConfigDto> paginationDto,DeviceConfigDto deviceConfigDto);
	
	public ServiceResponse<DeviceConfigDto> getOneDeviceConfig(DeviceConfigDto deviceConfigDto);
	
	
	/**
	 * 获取设备配置数量
	 * @param deviceConfigDto
	 * @return
	 */
	public ServiceResponse<Integer> getDeviceConfigCount(DeviceConfigDto deviceConfigDto);


	/**
	 * 获取设备的配置信息XML格式
	 * @param orgId
	 * @param deviceId
	 * @return
	 */
	public ServiceResponse<Map<String,String>> getXmlConfigForDevice(Long orgId, Long deviceId);


	/**
	 * 通知服务去生成XML配置文件，上传给服务端
	 */
	public ServiceResponse<List<Long>> notifyXmlServer(Long orgId, List<Long> deviceIds);


	public ServiceResponse<List<Long>> deployGroupInfo(Long orgId,List<Long> deviceIds);









}
