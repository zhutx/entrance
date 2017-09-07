package com.xier.sesame.attence.dao;

import java.util.List;

import com.xier.sesame.attence.domain.Subject;

public interface SubjectDao {
	
	Subject getExistOneSubject(String subjectCode, String subjectName);
	
	void clearDefault();
	
	void addSubject(Subject subject);
	
	List<Subject> findAll();
	
	public Subject getSubjectById(Long id);
	
	public Subject getDefaultSubject();

}
