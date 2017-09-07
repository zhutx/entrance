package com.xier.sesame.attence.service;

import com.xier.sesame.attence.dto.SwitchSettingDto;
import com.xier.sesame.common.rpc.dto.PaginationDto; 
import com.xier.sesame.common.rpc.ServiceResponse;

/**
 * 门禁开关配置Service
 * @author zhutx
 *
 */
public interface SwitchSettingService {
	
	/**
	 * 增加门禁开关配置
	 * @param switchSetting
	 * @return
	 */
	public ServiceResponse<Long> addSwitchSetting(SwitchSettingDto switchSettingDto);
	
	
	/**
	 * 修改门禁开关配置
	 * @param switchSetting
	 * @return
	 */
	public ServiceResponse<Integer> updateSwitchSetting(SwitchSettingDto switchSettingDto);
	
	/**
	 * 修改门禁开关配置,只修改属性不为null的字段
	 * @param switchSetting
	 * @return
	 */
	public ServiceResponse<Integer> updateSwitchSettingSelective(SwitchSettingDto switchSettingDto);
	
	
	/**
	 * 根据ID删除门禁开关配置
	 * @param id
	 * @param orgId 分表字段
	 */
	public ServiceResponse<Integer> removeSwitchSettingById(Long id, Long orgId);
	
	
	/**
	 * 根据id获取门禁开关配置
	 * @param id
	 * @param orgId 分表字段
	 * @return
	 */
	public ServiceResponse<SwitchSettingDto> getSwitchSettingById(Long id, Long orgId);
	
    
	/**
	 * 分页查询门禁开关配置
	 * @param pagination
	 * @param switchSetting
	 * @return
	 */
	public ServiceResponse<PaginationDto<SwitchSettingDto>> getPaginationSwitchSetting(PaginationDto<SwitchSettingDto> paginationDto,SwitchSettingDto switchSettingDto);
	
	
	/**
	 * 获取门禁开关配置数量
	 * @param switchSetting
	 * @return
	 */
	public ServiceResponse<Integer> getSwitchSettingCount(SwitchSettingDto switchSettingDto);
	
	/**
	 * 获取机构门禁配置信息
	 * @param id
	 * @param orgId
	 * @return
	 */
	public ServiceResponse<SwitchSettingDto> getSwitchSettingByOrgId(Long orgId);


}
