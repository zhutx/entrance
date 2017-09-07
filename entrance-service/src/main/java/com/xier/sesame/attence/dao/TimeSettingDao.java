package com.xier.sesame.attence.dao;

import java.util.List;

import com.xier.sesame.attence.domain.TimeSetting;
import com.xier.sesame.common.base.domain.query.Pagination;

/**
 * 门禁日历配置Dao
 * @author zhutx
 *
 */
public interface TimeSettingDao {
	
	/**
	 * 增加门禁日历配置
	 * @param timeSetting
	 * @return
	 */
	public void addTimeSetting(TimeSetting timeSetting);
	
	
	/**
	 * 修改门禁日历配置
	 * @param timeSetting
	 * @return 修改记录条数
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
	 * @return 删除记录条数
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
	 * 根据id获取门禁日历配置,同时加锁
	 * @param id
	 * @param orgId 分表字段
	 * @return 
	 */
	public TimeSetting getTimeSettingByIdForUpdate(Long id, Long orgId);
		
    
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
