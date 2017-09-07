package com.xier.sesame.attence.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Service;
import com.xier.sesame.attence.domain.OrgAuthDO;
import com.xier.sesame.attence.dto.AddOrgAuthDto;
import com.xier.sesame.attence.dto.OrgAuthDto;
import com.xier.sesame.attence.manager.OrgAuthManager;
import com.xier.sesame.attence.service.OrgAuthService;
import com.xier.sesame.attence.util.DozerMapperUtil;
import com.xier.sesame.common.exception.CommonErrorCode;
import com.xier.sesame.common.exception.ErrorContext;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.common.utils.JsonUtils;
import com.xier.sesame.common.utils.StringUtil;

@Service
public class OrgAuthServiceImpl implements OrgAuthService{
	
	public static final Logger logger = LoggerFactory.getLogger(OrgAuthServiceImpl.class);

	@Resource
	private OrgAuthManager orgAuthManager;
	
	@Override
	public ServiceResponse<Long> addOrgAuth(AddOrgAuthDto dto) {
		ServiceResponse<Long> sr = new ServiceResponse<Long>(true, null);
		try {
			Long authId = orgAuthManager.insert(DozerMapperUtil.getMapper().map(dto, OrgAuthDO.class));
			sr.setData(authId);
		} catch (Exception e) {
			logger.error(String.format("OrgAuthServiceImpl.addOrgAuth error, param=%s", JsonUtils.toJson(dto)), e);
			sr.setErrorContext(new ErrorContext(CommonErrorCode.UNKONWN, "新增机构授权信息异常：" + e.getMessage()));
			sr.setSuccess(false);
		}
		return sr;
	}

	@Override
	public ServiceResponse<Boolean> updateOrgAuth(OrgAuthDto orgAuthDto) {
		ServiceResponse<Boolean> sr = new ServiceResponse<Boolean>(true, null);
		try {
			String checkMsg = OrgAuthDto.checkParam(orgAuthDto);
			if(StringUtil.isNotBlank(checkMsg)){
				logger.error("{},param-error={},param={}", "OrgAuthServiceImpl.updateOrgAuth",
						JsonUtils.toJson(checkMsg), JsonUtils.toJson(orgAuthDto));
				sr.setErrorContext(new ErrorContext(CommonErrorCode.UNKONWN, "参数错误：" + checkMsg));
				sr.setSuccess(false);
				return sr;
			}
			orgAuthManager.update(DozerMapperUtil.getMapper().map(orgAuthDto, OrgAuthDO.class));
			sr.setData(true);
		} catch (Exception e) {
			logger.error(String.format("OrgAuthServiceImpl.updateOrgAuth error, param=%s", JsonUtils.toJson(orgAuthDto)), e);
			sr.setErrorContext(new ErrorContext(CommonErrorCode.UNKONWN, "更新机构授权信息异常：" + e.getMessage()));
			sr.setSuccess(false);
		}
		return sr;
	}

	@Override
	public ServiceResponse<Boolean> deleteOrgAuth(Long authId) {
		ServiceResponse<Boolean> sr = new ServiceResponse<Boolean>(true, null);
		try {
			if(authId == null){
				logger.error("OrgAuthServiceImpl.deleteOrgAuth, param-error: authId is null");
				sr.setErrorContext(new ErrorContext(CommonErrorCode.UNKONWN, "参数错误：authId is null"));
				sr.setSuccess(false);
				return sr;
			}
			orgAuthManager.delete(authId);
			sr.setData(true);
		} catch (Exception e) {
			logger.error(String.format("OrgAuthServiceImpl.deleteOrgAuth error, param=%s", JsonUtils.toJson(authId)), e);
			sr.setErrorContext(new ErrorContext(CommonErrorCode.UNKONWN, "删除机构授权信息异常：" + e.getMessage()));
			sr.setSuccess(false);
		}
		return sr;
	}

	@Override
	public ServiceResponse<OrgAuthDto> queryOrgAuthById(Long authId) {
		ServiceResponse<OrgAuthDto> sr = new ServiceResponse<OrgAuthDto>(true, null);
		try {
			if(authId == null){
				logger.error("OrgAuthServiceImpl.queryOrgAuthById, param-error: authId is null");
				sr.setErrorContext(new ErrorContext(CommonErrorCode.UNKONWN, "参数错误：authId is null"));
				sr.setSuccess(false);
				return sr;
			}
			sr.setData(orgAuthManager.queryDOById(authId));
		} catch (Exception e) {
			logger.error(String.format("OrgAuthServiceImpl.queryOrgAuthById error, param=%s", JsonUtils.toJson(authId)), e);
			sr.setErrorContext(new ErrorContext(CommonErrorCode.UNKONWN, "查询机构授权信息异常：" + e.getMessage()));
			sr.setSuccess(false);
		}
		return sr;
	}

	@Override
	public ServiceResponse<List<OrgAuthDto>> queryUsableList() {
		ServiceResponse<List<OrgAuthDto>> sr = new ServiceResponse<List<OrgAuthDto>>(true, null);
		try {
			sr.setData(orgAuthManager.queryUsableList());
		} catch (Exception e) {
			logger.error("OrgAuthServiceImpl.queryUsableList error,", e);
			sr.setErrorContext(new ErrorContext(CommonErrorCode.UNKONWN, "查询所有可用机构授权信息异常：" + e.getMessage()));
			sr.setSuccess(false);
		}
		return sr;
	}

	@Override
	public ServiceResponse<OrgAuthDto> queryOrgAuthByOrgId(Long orgId) {
		ServiceResponse<OrgAuthDto> sr = new ServiceResponse<OrgAuthDto>(true, null);
		try {
			if(orgId == null){
				logger.error("OrgAuthServiceImpl.queryOrgAuthByOrgId, param-error: orgId is null");
				sr.setErrorContext(new ErrorContext(CommonErrorCode.UNKONWN, "参数错误：orgId is null"));
				sr.setSuccess(false);
				return sr;
			}
			sr.setData(orgAuthManager.queryDOByOrgId(orgId));
		} catch (Exception e) {
			logger.error(String.format("OrgAuthServiceImpl.queryOrgAuthByOrgId error, param=%s", JsonUtils.toJson(orgId)), e);
			sr.setErrorContext(new ErrorContext(CommonErrorCode.UNKONWN, "查询机构授权信息异常：" + e.getMessage()));
			sr.setSuccess(false);
		}
		return sr;
	}

	@Override
	public ServiceResponse<List<OrgAuthDto>> queryAllList() {
		ServiceResponse<List<OrgAuthDto>> sr = new ServiceResponse<List<OrgAuthDto>>(true, null);
		try {
			sr.setData(orgAuthManager.queryAllList());
		} catch (Exception e) {
			logger.error("OrgAuthServiceImpl.queryAllList error,", e);
			sr.setErrorContext(new ErrorContext(CommonErrorCode.UNKONWN, "查询所有机构授权信息异常：" + e.getMessage()));
			sr.setSuccess(false);
		}
		return sr;
	}
	
}
