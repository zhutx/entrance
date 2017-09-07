package com.xier.sesame.attence.manager;

import java.util.List;

import com.xier.sesame.attence.convertor.TimeSettingConvertor;
import com.xier.sesame.attence.domain.TimeSetting;
import com.xier.sesame.attence.dto.TimeSettingDto;
import com.xier.sesame.common.base.domain.query.Pagination; 
import com.xier.sesame.common.rpc.ServiceResponse;

/**
 * 门禁日历配置Manager
 * @author zhutx
 *
 */
public interface TimeSettingManager {
	
	/**
	 * 增加门禁日历配置
	 * @param timeSetting
	 * @return 主键
	 */
	public ServiceResponse<Long> addTimeSetting(TimeSetting timeSetting);
	
	
	/**
	 * 修改门禁日历配置
	 * @param timeSetting
	 * @return
	 */
	public int updateTimeSetting(TimeSetting timeSetting);
	
	/**
	 * 修改门禁日历配置,只修改属性不为null的字段
	 * @param timeSetting
	 * @return 修改记录条数
	 */
	public int updateTimeSettingSelective(TimeSetting timeSetting);
		
	/**
	 * 根据ID删除门禁日历配置
	 * @param id
	 * @param orgId 分表字段
	 * @return
	 */
	public int removeTimeSettingById(Long id, Long orgId);
	
	
	/**
	 * 根据id获取门禁日历配置
	 * @param id
	 * @param orgId 分表字段
	 * @return
	 */
	public TimeSetting getTimeSettingById(Long id, Long orgId);
	
    
	/**
	 * 分页查询门禁日历配置
	 * @param pagination
	 * @param timeSetting
	 * @return
	 */
	public Pagination<TimeSetting> getPaginationTimeSetting(Pagination<TimeSetting> pagination,TimeSetting timeSetting);
	
	
	/**
	 * 获取门禁日历配置数量
	 * @param timeSetting
	 * @return
	 */
	public int getTimeSettingCount(TimeSetting timeSetting);
	
	public List<TimeSetting> getTimeSetting(TimeSetting timeSetting);

	public TimeSetting getOneTimeSetting(TimeSetting timeSetting);


}
