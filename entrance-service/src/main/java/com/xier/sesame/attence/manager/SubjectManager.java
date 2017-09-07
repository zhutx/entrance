package com.xier.sesame.attence.manager;

import java.util.List;

import com.xier.sesame.attence.domain.Subject;
import com.xier.sesame.attence.request.SubjectAddRequest;

public interface SubjectManager {
	
	Long addSubject(SubjectAddRequest request);
	
	List<Subject> findSubject();
	
	Subject getSubjectById(Long id);
	
	Subject getDefaultSubject();


}
