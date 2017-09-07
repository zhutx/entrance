package com.xier.sesame.attence.manager.impl;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xier.sesame.attence.dao.OrgAuthDao;
import com.xier.sesame.attence.domain.OrgAuthDO;
import com.xier.sesame.attence.dto.OrgAuthDto;
import com.xier.sesame.attence.manager.OrgAuthManager;
import com.xier.sesame.attence.util.DozerMapperUtil;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.idgenerator.service.IdgeneratorService;

@Service
public class OrgAuthManagerImpl implements OrgAuthManager {
	
	@Reference
	private IdgeneratorService idgeneratorService;
	@Resource
	private OrgAuthDao orgAuthDao;

	@Override
	public Long insert(OrgAuthDO orgAuthDO) {
		ServiceResponse<Long> sr = idgeneratorService.getNextIdByTypeName("com.xier.sesame.attence.domain.OrgAuthDO");
        if(!sr.isSuccess() || sr.getData() == null){
        	throw new RuntimeException("id获取异常，");
        }
        Long authId = sr.getData();
        orgAuthDO.setAuthId(authId);
        orgAuthDao.insert(orgAuthDO);
        return authId;

	}

	@Override
	public int update(OrgAuthDO orgAuthDO) {
		return orgAuthDao.update(orgAuthDO);
	}

	@Override
	public int delete(Long authId) {
		return orgAuthDao.delete(authId);
	}

	@Override
	public OrgAuthDto queryDOById(Long authId) {
		OrgAuthDO authDO = orgAuthDao.queryDOById(authId);
		if(authDO == null){
			return null;
		}
		return DozerMapperUtil.getMapper().map(authDO, OrgAuthDto.class);
	}

	@Override
	public List<OrgAuthDto> queryUsableList() {
		List<OrgAuthDO> authDOs = orgAuthDao.queryUsableList();
		if(CollectionUtils.isEmpty(authDOs)){
			return Collections.emptyList();
		}
		return DozerMapperUtil.mapList(authDOs, OrgAuthDto.class);
	}

	@Override
	public OrgAuthDto queryDOByOrgId(Long orgId) {
		OrgAuthDO authDO = orgAuthDao.queryDOByOrgId(orgId);
		if(authDO == null){
			return null;
		}
		return DozerMapperUtil.getMapper().map(authDO, OrgAuthDto.class);
	}

	@Override
	public List<OrgAuthDto> queryAllList() {
		List<OrgAuthDO> authDOs = orgAuthDao.queryAllList();
		if(CollectionUtils.isEmpty(authDOs)){
			return Collections.emptyList();
		}
		return DozerMapperUtil.mapList(authDOs, OrgAuthDto.class);
	}

}
