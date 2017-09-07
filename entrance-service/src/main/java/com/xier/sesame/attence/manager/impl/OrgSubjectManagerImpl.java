package com.xier.sesame.attence.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xier.sesame.attence.dao.OrgSubjectDao;
import com.xier.sesame.attence.domain.OrgSubject;
import com.xier.sesame.attence.manager.OrgSubjectManager;
import com.xier.sesame.common.exception.BizAssert;

@Service
public class OrgSubjectManagerImpl implements OrgSubjectManager {
	
	@Autowired
	private OrgSubjectDao orgSubjectDao;

	@Override
	public OrgSubject getOrgSubject(Long orgId, Long subjectId) {
		BizAssert.notNull(orgId);
		BizAssert.notNull(subjectId);
		return orgSubjectDao.getOrgSubject(orgId, subjectId);
	}

}
