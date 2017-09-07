package com.xier.sesame.attence.service;

import java.util.List;

import com.xier.sesame.attence.model.SubjectInfo;
import com.xier.sesame.attence.request.SubjectAddRequest;
import com.xier.sesame.common.rpc.ServiceResponse;

/**
 * 主题服务
 * @author zhutx
 *
 */
public interface SubjectService {
	
	ServiceResponse<Long> addSubject(SubjectAddRequest request);
	
	List<SubjectInfo> findSubject();
	
	SubjectInfo getSubject(Long subjectId);


}
