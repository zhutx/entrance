package com.xier.sesame.attence.manager.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xier.sesame.attence.dao.OffLinePasswordDao;
import com.xier.sesame.attence.domain.OffLinePassword;
import com.xier.sesame.attence.manager.OffLinePasswordManager;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.idgenerator.service.IdgeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xxu on 2017/4/14.
 */

@Service
public class OffLinePasswordManagerImpl implements OffLinePasswordManager{

    @Reference
    private IdgeneratorService idgeneratorService;


    @Autowired
    private OffLinePasswordDao offLinePasswordDao;




    @Override
    public ServiceResponse<Long> addOffLinePassword(OffLinePassword offLinePassword) {
        ServiceResponse<Long> serviceResponse = idgeneratorService.getNextIdByTypeName("com.xier.sesame.attence.domain.OffLinePassword");
        if (serviceResponse.isSuccess()) {
            offLinePassword.setOffLinePasswordId(serviceResponse.getData());
            offLinePasswordDao.addOffLinePassword(offLinePassword);
        }
        return serviceResponse;
    }

    @Override
    public int updateOffLinePassword(OffLinePassword offLinePassword) {
        return offLinePasswordDao.updateOffLinePassword(offLinePassword);
    }

    @Override
    public int updateOffLinePasswordByOrgId(OffLinePassword offLinePassword) {
        return offLinePasswordDao.updateOffLinePasswordByOrgId(offLinePassword);
    }


    @Override
    public int removeOffLinePasswordById(Long id, Long orgId) {
        return offLinePasswordDao.removeOffLinePasswordById(id,orgId);
    }

    @Override
    public OffLinePassword getOffLinePasswordById(Long id, Long orgId) {
        return offLinePasswordDao.getOffLinePasswordById(id,orgId);
    }

    @Override
    public OffLinePassword getOffLinePasswordByOrgId(Long orgId) {
        return offLinePasswordDao.getOffLinePasswordByOrgId(orgId);
    }
}
