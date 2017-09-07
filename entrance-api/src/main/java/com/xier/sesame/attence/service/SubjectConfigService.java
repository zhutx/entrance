package com.xier.sesame.attence.service;

import java.util.List;

import com.xier.sesame.attence.request.SubjectConfigRequest;
import com.xier.sesame.common.rpc.ServiceResponse;

/**
 * 设备主题配置服务
 * @author zhutx
 *
 */
public interface SubjectConfigService {
	
	List<Long> findDeviceIdBySubject(Long orgId, Long subjectId);
	
	ServiceResponse<Boolean> subjectConfig(SubjectConfigRequest request);
	
	Long getSubjectIdByDevice(Long orgId, Long deviceId);
	
	ServiceResponse<Boolean> toggleSubjectOfDevice(Long orgId, Long deviceId, Long subjectId);
	
}
