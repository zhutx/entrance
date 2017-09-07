package com.xier.sesame.attence.dao;

import java.util.List;

import com.xier.sesame.attence.domain.SubjectConfig;

public interface SubjectConfigDao {
	
	List<Long> findDeviceIdBySubject(Long orgId, Long subjectId);
	
	void addSubjectConfig(SubjectConfig subjectConfig);
	
	int removeOne(Long orgId, Long subjectId, Long deviceId);
	
	Long getSubjectId(Long orgId, Long deviceId);
	
	void removeByDeviceId(Long orgId, Long deviceId);
	
	int updateSubjectId(Long orgId, Long deviceId, Long subjectId);
	
}
