package com.xier.sesame.attence.dao;

import java.util.List;

import com.xier.sesame.attence.domain.OrgAuthDO;

public interface OrgAuthDao {
	
	/**
	 * 新增机构注册授权信息
	 */
	public void insert(OrgAuthDO orgAuthDO);
	
	/**
	 * 修改
	 */
	public int update(OrgAuthDO orgAuthDO);
	
	/**
	 * 根据authId删除
	 */
	public int delete(Long authId);
	
	
	/**
	 * 根据authId查询
	 */
	public OrgAuthDO queryDOById(Long authId);
	
	/**
	 * 根据orgId查询
	 */
	public OrgAuthDO queryDOByOrgId(Long orgId);
	
	/**
	 * 获取所有可用的机构授权信息
	 * status =1
	 */
	public List<OrgAuthDO> queryUsableList();
	
	/**
	 * 获取所有机构授权信息
	 */
	public List<OrgAuthDO> queryAllList();

}
