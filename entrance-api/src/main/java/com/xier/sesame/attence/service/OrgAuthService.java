package com.xier.sesame.attence.service;

import java.util.List;

import com.xier.sesame.attence.dto.AddOrgAuthDto;
import com.xier.sesame.attence.dto.OrgAuthDto;
import com.xier.sesame.common.rpc.ServiceResponse;

/**
 * 机构注册授权信息存储查询服务
 */
public interface OrgAuthService {
	
	/**
	 * 增加机构授权信息
	 */
	public ServiceResponse<Long> addOrgAuth(AddOrgAuthDto dto);
	
	
	/**
	 * 修改机构授权信息
	 */
	public ServiceResponse<Boolean> updateOrgAuth(OrgAuthDto orgAuthDto);
	
	/**
	 * 根据ID删除机构授权信息
	 */
	public ServiceResponse<Boolean> deleteOrgAuth(Long authId);
	
	/**
	 * 根据orgId获取机构授权信息
	 */
	public ServiceResponse<OrgAuthDto> queryOrgAuthByOrgId(Long orgId);
	
	/**
	 * 根据id获取机构授权信息
	 */
	public ServiceResponse<OrgAuthDto> queryOrgAuthById(Long authId);
	
    /**
	 * 获取所有可用的机构授权信息
     */
	public ServiceResponse<List<OrgAuthDto>> queryUsableList();
	
    /**
	 * 获取所有机构授权信息
     */
	public ServiceResponse<List<OrgAuthDto>> queryAllList();


}
