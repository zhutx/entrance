package com.xier.sesame.attence.manager.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xier.attendflysync.service.DataSyncService;
import com.xier.sesame.attence.dao.NewSwitchSettingDao;
import com.xier.sesame.attence.domain.NewSwitchSetting;
import com.xier.sesame.attence.manager.NewSwitchSettingManager;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.idgenerator.service.IdgeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xxu on 2017/3/31.
 */

@Service
public class NewSwitchSettingManagerImpl implements NewSwitchSettingManager {

    @Reference
    private IdgeneratorService idgeneratorService;



    @Reference
    private DataSyncService dataSyncService;

    @Autowired
    private NewSwitchSettingDao switchSettingDao;

    @Override
    public ServiceResponse<Long> addSwitchSetting(NewSwitchSetting switchSetting) {
        ServiceResponse<Long> serviceResponse = idgeneratorService.getNextIdByTypeName("com.xier.sesame.attence.domain.NewSwitchSetting");
        if (serviceResponse.isSuccess()) {
            switchSetting.setSwitchSettingId(serviceResponse.getData());
            switchSettingDao.addSwitchSetting(switchSetting);
        }
        return serviceResponse;
    }

    @Override
    public int updateSwitchSetting(NewSwitchSetting switchSetting) {
        return switchSettingDao.updateSwitchSetting(switchSetting);
    }

    @Override
    public int updateSwitchSettingByGroupIdAndOrgId(NewSwitchSetting switchSetting) {
        return switchSettingDao.updateSwitchSettingByGroupIdAndOrgId(switchSetting);
    }

    @Override
    public int updateSwitchSettingSelective(NewSwitchSetting switchSetting) {

		/*
		 * if(switchSetting.getAttenceSwitch() != null && switchSetting.getAttenceSwitch() == YesNoFlag.YES.getValue())
		 * { dataSyncService.startSyncAllUserInfo(switchSetting.getOrgId()); }
		 */

        return switchSettingDao.updateSwitchSettingSelective(switchSetting);
    }

    @Override
    public int removeSwitchSettingById(Long id, Long orgId,Long groupId) {
        return switchSettingDao.removeSwitchSettingById(id, orgId,groupId);
    }

    @Override
    public int removeSwitchSettingByOrgIdAndGroupId(Long orgId, Long groupId) {
        return switchSettingDao.removeSwitchSettingByOrgIdAndGroupId(orgId,groupId);
    }

    @Override
    public NewSwitchSetting getSwitchSettingById(Long id, Long orgId,Long groupId) {
        return switchSettingDao.getSwitchSettingById(id, orgId,groupId);
    }

    @Override
    public Pagination<NewSwitchSetting> getPaginationSwitchSetting(Pagination<NewSwitchSetting> pagination, NewSwitchSetting switchSetting) {
        return switchSettingDao.getPaginationSwitchSetting(pagination, switchSetting);
    }

    @Override
    public int getSwitchSettingCount(NewSwitchSetting switchSetting) {
        return switchSettingDao.getSwitchSettingCount(switchSetting);
    }

    @Override
    public NewSwitchSetting getSwitchSettingByOrgId(Long orgId,Long groupId) {
        return switchSettingDao.getSwitchSettingByOrgIdAndGroupId(orgId,groupId);
    }

    @Override
    public List<NewSwitchSetting> getSwitchSettingListByOrgId(Long orgId) {
        return switchSettingDao.getSwitchSettingListByOrgId(orgId);
    }

    @Override
    public List<NewSwitchSetting> getDeviceBindingSwitchSettings(Long orgId, List<Long> groupIdList) {
        return switchSettingDao.getDeviceBindingSwitchSettings(orgId,groupIdList);
    }
}
