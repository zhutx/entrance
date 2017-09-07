package com.xier.sesame.attence.dao.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xier.sesame.attence.dao.OrgAuthDao;
import com.xier.sesame.attence.domain.OrgAuthDO;
import com.xier.sesame.common.base.dao.BaseDaoMybatis;

@Repository
public class OrgAuhtDaoMybatis extends BaseDaoMybatis implements OrgAuthDao {

	@Override
	public void insert(OrgAuthDO orgAuthDO) {
		this.getSqlSession().insert("OrgAuth.insert", orgAuthDO);
	}

	@Override
	public int update(OrgAuthDO orgAuthDO) {
		return this.getSqlSession().update("OrgAuth.update", orgAuthDO);
	}

	@Override
	public int delete(Long authId) {
		return this.getSqlSession().delete("OrgAuth.deleteById", authId);
	}

	@Override
	public OrgAuthDO queryDOById(Long authId) {
		return this.getSqlSession().selectOne("OrgAuth.queryDOById", authId);
	}

	@Override
	public List<OrgAuthDO> queryUsableList() {
		return this.getSqlSession().selectList("OrgAuth.queryUsableList");
	}

	@Override
	public OrgAuthDO queryDOByOrgId(Long orgId) {
		return this.getSqlSession().selectOne("OrgAuth.queryDOByOrgId", orgId);
	}

	@Override
	public List<OrgAuthDO> queryAllList() {
		return this.getSqlSession().selectList("OrgAuth.queryAllList");
	}
	
}