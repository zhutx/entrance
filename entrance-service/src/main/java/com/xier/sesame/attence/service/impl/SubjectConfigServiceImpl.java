package com.xier.sesame.attence.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.xier.sesame.attence.manager.SubjectConfigManager;
import com.xier.sesame.attence.request.SubjectConfigRequest;
import com.xier.sesame.attence.service.SubjectConfigService;
import com.xier.sesame.common.rpc.ServiceResponse;

@Service
public class SubjectConfigServiceImpl implements SubjectConfigService{
	
	@Autowired
	private SubjectConfigManager subjectConfigManager;
	
	@Override
	public List<Long> findDeviceIdBySubject(Long orgId, Long subjectId) {
		return subjectConfigManager.findDeviceIdBySubject(orgId, subjectId);
	}
	
	@Override
	public ServiceResponse<Boolean> subjectConfig(SubjectConfigRequest request) {
		boolean result = subjectConfigManager.subjectConfig(request);
		return new ServiceResponse<Boolean>(true, null, result);
	}

	@Override
	public Long getSubjectIdByDevice(Long orgId, Long deviceId) {
		return subjectConfigManager.getSubjectIdByDevice(orgId, deviceId);
	}

	@Override
	public ServiceResponse<Boolean> toggleSubjectOfDevice(Long orgId, Long deviceId, Long subjectId) {
		boolean result = subjectConfigManager.toggleSubjectOfDevice(orgId, deviceId, subjectId);
		return new ServiceResponse<Boolean>(true, null, result);
	}

}
