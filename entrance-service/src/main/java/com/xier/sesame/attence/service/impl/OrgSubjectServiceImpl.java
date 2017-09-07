package com.xier.sesame.attence.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.moredian.bee.common.utils.BeanUtils;
import com.xier.sesame.attence.domain.OrgSubject;
import com.xier.sesame.attence.manager.OrgSubjectManager;
import com.xier.sesame.attence.model.OrgSubjectInfo;
import com.xier.sesame.attence.service.OrgSubjectService;

@Service
public class OrgSubjectServiceImpl implements OrgSubjectService {
	
	@Autowired
	private OrgSubjectManager orgSubjectManager;
	
	private OrgSubjectInfo orgSubjectToOrgSubjectInfo(OrgSubject orgSubject) {
		if(orgSubject == null) return null;
		return BeanUtils.copyProperties(OrgSubjectInfo.class, orgSubject);
	}

	@Override
	public OrgSubjectInfo getOrgSubject(Long orgId, Long subjectId) {
		OrgSubject orgSubject = orgSubjectManager.getOrgSubject(orgId, subjectId);
		return orgSubjectToOrgSubjectInfo(orgSubject);
	}

}
