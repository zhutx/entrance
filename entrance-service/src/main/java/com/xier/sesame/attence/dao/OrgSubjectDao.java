package com.xier.sesame.attence.dao;

import com.xier.sesame.attence.domain.OrgSubject;

public interface OrgSubjectDao {
	
	OrgSubject getOrgSubject(Long orgId, Long subjectId);
	
	int insertOrUpdate(OrgSubject orgSubject);

}
