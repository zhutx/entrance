package com.xier.sesame.attence.service;

import java.util.List;

import com.xier.sesame.attence.dto.TimeSettingDto;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.common.rpc.dto.PaginationDto;

/**
 * 门禁日历配置Service
 * @author zhutx
 *
 */
public interface TimeSettingService {
	
	/**
	 * 增加门禁日历配置
	 * @param timeSetting
	 * @return
	 */
	public ServiceResponse<Long> addTimeSetting(TimeSettingDto timeSettingDto);
	
	
	/**
	 * 修改门禁日历配置
	 * @param timeSetting
	 * @return
	 */
	public ServiceResponse<Integer> updateTimeSetting(TimeSettingDto timeSettingDto);
	
	/**
	 * 修改门禁日历配置,只修改属性不为null的字段
	 * @param timeSetting
	 * @return
	 */
	public ServiceResponse<Integer> updateTimeSettingSelective(TimeSettingDto timeSettingDto);
	
	
	/**
	 * 根据ID删除门禁日历配置
	 * @param id
	 * @param orgId 分表字段
	 */
	public ServiceResponse<Integer> removeTimeSettingById(Long id, Long orgId);
	
	
	/**
	 * 根据id获取门禁日历配置
	 * @param id
	 * @param orgId 分表字段
	 * @return
	 */
	public ServiceResponse<TimeSettingDto> getTimeSettingById(Long id, Long orgId);
	
    
	/**
	 * 分页查询门禁日历配置
	 * @param pagination
	 * @param timeSetting
	 * @return
	 */
	public ServiceResponse<PaginationDto<TimeSettingDto>> getPaginationTimeSetting(PaginationDto<TimeSettingDto> paginationDto,TimeSettingDto timeSettingDto);
	
	
	/**
	 * 获取门禁日历配置数量
	 * @param timeSetting
	 * @return
	 */
	public ServiceResponse<Integer> getTimeSettingCount(TimeSettingDto timeSettingDto);
	
	public ServiceResponse<List<TimeSettingDto>> getTimeSetting(TimeSettingDto timeSettingDto);
	
	public ServiceResponse<TimeSettingDto> getOneTimeSetting(TimeSettingDto timeSettingDto);


}
