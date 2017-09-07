package com.xier.sesame.attence.manager;

import java.util.List;

import com.xier.sesame.attence.request.SubjectConfigRequest;

public interface SubjectConfigManager {
	
	List<Long> findDeviceIdBySubject(Long orgId, Long subjectId);
	
	boolean subjectConfig(SubjectConfigRequest request);
	
	Long getSubjectId(Long orgId, Long deviceId);
	
	Long getSubjectIdByDevice(Long orgId, Long deviceId);
	
	boolean toggleSubjectOfDevice(Long orgId, Long deviceId, Long subjectId);
	

}
