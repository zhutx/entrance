package com.xier.sesame.attence.manager;

import java.util.List;

import com.xier.sesame.attence.domain.OrgAuthDO;
import com.xier.sesame.attence.dto.OrgAuthDto;

public interface OrgAuthManager {
	
	/**
	 * 新增机构注册授权信息
	 */
	public Long insert(OrgAuthDO orgAuthDO);
	
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
	public OrgAuthDto queryDOById(Long authId);
	
	/**
	 * 根据orgId查询
	 */
	public OrgAuthDto queryDOByOrgId(Long orgId);
	
	/**
	 * 获取所有可用的机构授权信息
	 */
	public List<OrgAuthDto> queryUsableList();
	
	/**
	 * 获取所有机构授权信息
	 */
	public List<OrgAuthDto> queryAllList();

}
