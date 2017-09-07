package com.xier.sesame.attence.dao.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xier.sesame.attence.dao.SubjectConfigDao;
import com.xier.sesame.attence.domain.SubjectConfig;
import com.xier.sesame.common.base.dao.BaseDaoMybatis;

@Repository
public class SubjectConfigDaoMybatis extends BaseDaoMybatis implements SubjectConfigDao {
	
	@Override
	public List<Long> findDeviceIdBySubject(Long orgId, Long subjectId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("orgId", orgId);
		parameter.put("subjectId", subjectId);
		return this.getSqlSession().selectList("com.xier.sesame.attence.SubjectConfig.findDeviceIdBySubject", parameter);
	}

	@Override
	public int removeOne(Long orgId, Long subjectId, Long deviceId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("orgId", orgId);
		parameter.put("subjectId", subjectId);
		parameter.put("deviceId", deviceId);
		return this.getSqlSession().delete("com.xier.sesame.attence.SubjectConfig.deleteOne", parameter);
	}

	@Override
	public void addSubjectConfig(SubjectConfig subjectSetting) {
		this.getSqlSession().insert("com.xier.sesame.attence.SubjectConfig.addSubjectConfig", subjectSetting);
	}
		
	@Override
	public Long getSubjectId(Long orgId, Long deviceId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("orgId", orgId);
		parameter.put("deviceId", deviceId);
		return this.getSqlSession().selectOne("com.xier.sesame.attence.SubjectConfig.getSubjectId", parameter);
	}

	@Override
	public void removeByDeviceId(Long orgId, Long deviceId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("orgId", orgId);
		parameter.put("deviceId", deviceId);
		this.getSqlSession().delete("com.xier.sesame.attence.SubjectConfig.deleteByDeviceId", parameter);
	}

	@Override
	public int updateSubjectId(Long orgId, Long deviceId, Long subjectId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("orgId", orgId);
		parameter.put("deviceId", deviceId);
		parameter.put("subjectId", subjectId);
		return this.getSqlSession().update("com.xier.sesame.attence.SubjectConfig.updateSubjectId", parameter);
	}


}
