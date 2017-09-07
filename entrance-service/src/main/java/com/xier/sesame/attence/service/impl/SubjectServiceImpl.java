package com.xier.sesame.attence.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.moredian.bee.common.utils.BeanUtils;
import com.xier.sesame.attence.domain.Subject;
import com.xier.sesame.attence.manager.SubjectManager;
import com.xier.sesame.attence.model.SubjectInfo;
import com.xier.sesame.attence.request.SubjectAddRequest;
import com.xier.sesame.attence.service.SubjectService;
import com.xier.sesame.common.rpc.ServiceResponse;

@Service
public class SubjectServiceImpl implements SubjectService{
	
	@Autowired
	private SubjectManager subjectManager;
	
	@Override
	public ServiceResponse<Long> addSubject(SubjectAddRequest request) {
		Long subjectId = subjectManager.addSubject(request);
		return new ServiceResponse<Long>(true, null, subjectId);
	}
	
	private List<SubjectInfo> subjectListToSubjectInfoList(List<Subject> subjectList) {
		return BeanUtils.copyListProperties(SubjectInfo.class, subjectList);
	}

	@Override
	public List<SubjectInfo> findSubject() {
		List<Subject> subjectList = subjectManager.findSubject();
		return subjectListToSubjectInfoList(subjectList);
	}
	
	private SubjectInfo subjectToSubjectInfo(Subject subject) {
		return BeanUtils.copyProperties(SubjectInfo.class, subject);
	}

	@Override
	public SubjectInfo getSubject(Long subjectId) {
		Subject subject = subjectManager.getSubjectById(subjectId);
		return subjectToSubjectInfo(subject);
	}


}
