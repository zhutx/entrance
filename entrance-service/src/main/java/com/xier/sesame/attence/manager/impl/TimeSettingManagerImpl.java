package com.xier.sesame.attence.manager.impl;

import com.xier.sesame.attence.domain.TimeSetting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.xier.sesame.attence.manager.TimeSettingManager;
import com.xier.sesame.attence.dao.TimeSettingDao;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.idgenerator.service.IdgeneratorService;
import org.springframework.stereotype.Service;
import com.alibaba.dubbo.config.annotation.Reference;

/**
 * 门禁日历配置Manager
 * @author zhutx
 */
@Service
public class TimeSettingManagerImpl implements TimeSettingManager{

	@Reference
	private IdgeneratorService idgeneratorService;
	
	@Autowired
	private TimeSettingDao timeSettingDao;
	
	@Override
	public ServiceResponse<Long> addTimeSetting(TimeSetting timeSetting) {
		ServiceResponse<Long> serviceResponse = idgeneratorService.getNextIdByTypeName("com.xier.sesame.attence.TimeSetting");
		if (serviceResponse.isSuccess()) {
			timeSetting.setTimeSettingId(serviceResponse.getData());
			timeSettingDao.addTimeSetting(timeSetting);
		}
		return serviceResponse;
	}
	
	@Override
	public int updateTimeSetting(TimeSetting timeSetting) {
		return timeSettingDao.updateTimeSetting(timeSetting);
	}
	
	@Override
	public int updateTimeSettingSelective(TimeSetting timeSetting) {
		return timeSettingDao.updateTimeSettingSelective(timeSetting);
	}
	
	@Override
	public int removeTimeSettingById(Long id, Long orgId) {
		return timeSettingDao.removeTimeSettingById(id, orgId);
	}
	
	@Override
	public TimeSetting getTimeSettingById(Long id, Long orgId) {
		return timeSettingDao.getTimeSettingById(id, orgId);
	}
	  
	@Override
	public Pagination<TimeSetting> getPaginationTimeSetting(Pagination<TimeSetting> pagination,TimeSetting timeSetting) {
		return timeSettingDao.getPaginationTimeSetting(pagination, timeSetting);
	}
	
	@Override
	public int getTimeSettingCount(TimeSetting timeSetting) {
		return timeSettingDao.getTimeSettingCount(timeSetting);
	}

	@Override
	public List<TimeSetting> getTimeSetting(TimeSetting timeSetting) {
		return timeSettingDao.getTimeSetting(timeSetting);
	}

	@Override
	public TimeSetting getOneTimeSetting(TimeSetting timeSetting) {
		return timeSettingDao.getOneTimeSetting(timeSetting);
	}



}
