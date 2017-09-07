package com.xier.sesame.attence.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.moredian.fishnet.device.enums.YesNoFlag;
import com.xier.sesame.attence.dao.SubjectDao;
import com.xier.sesame.attence.domain.Subject;
import com.xier.sesame.common.base.dao.BaseDaoMybatis;

@Repository
public class SubjectDaoMybatis extends BaseDaoMybatis implements SubjectDao {
	
	@Override
	public void addSubject(Subject subject) {
		this.getSqlSession().insert("com.xier.sesame.attence.Subject.addSubject", subject);
	}
		
	@Override
	public Subject getExistOneSubject(String subjectCode, String subjectName) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("subjectCode", subjectCode);
		parameter.put("subjectName", subjectName);
		return (Subject)this.getSqlSession().selectOne("com.xier.sesame.attence.Subject.getExistOneSubject", parameter);
	}

	@Override
	public void clearDefault() {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("oldDefaultFlag", YesNoFlag.YES.getValue());
		parameter.put("newDefaultFlag", YesNoFlag.NO.getValue());
		this.getSqlSession().update("com.xier.sesame.attence.Subject.clearDefault", parameter);
	}

	@Override
	public List<Subject> findAll() {
		return this.getSqlSession().selectList("com.xier.sesame.attence.Subject.findAll");
	}

	@Override
	public Subject getSubjectById(Long id) {
		return (Subject)this.getSqlSession().selectOne("com.xier.sesame.attence.Subject.getSubjectById", id);
	}
	
	@Override
	public Subject getDefaultSubject() {
		return this.getSqlSession().selectOne("com.xier.sesame.attence.Subject.getDefaultSubject");
	}


}
