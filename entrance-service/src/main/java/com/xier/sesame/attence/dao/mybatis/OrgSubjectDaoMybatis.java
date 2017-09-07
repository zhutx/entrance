package com.xier.sesame.attence.dao.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xier.sesame.attence.dao.OrgSubjectDao;
import com.xier.sesame.attence.domain.OrgSubject;
import com.xier.sesame.common.base.dao.BaseDaoMybatis;

@Repository
public class OrgSubjectDaoMybatis extends BaseDaoMybatis implements OrgSubjectDao {

	@Override
	public OrgSubject getOrgSubject(Long orgId, Long subjectId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("orgId", orgId);
		parameter.put("subjectId", subjectId);
		return (OrgSubject)this.getSqlSession().selectOne("com.xier.sesame.attence.OrgSubject.getOrgSubject", parameter);
	}

	@Override
	public int insertOrUpdate(OrgSubject orgSubject) {
		return this.getSqlSession().insert("com.xier.sesame.attence.OrgSubject.insertOrUpdate", orgSubject);
	}

}
