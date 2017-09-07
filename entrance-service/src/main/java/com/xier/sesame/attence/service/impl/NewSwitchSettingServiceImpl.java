package com.xier.sesame.attence.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xier.sesame.attence.convertor.NewSwitchSettingConvertor;
import com.xier.sesame.attence.domain.NewSwitchSetting;
import com.xier.sesame.attence.dto.NewSwitchSettingDto;
import com.xier.sesame.attence.manager.NewSwitchSettingManager;
import com.xier.sesame.attence.service.NewSwitchSettingService;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.rpc.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import com.xier.sesame.common.rpc.dto.PaginationDto;

import java.util.List;


/**
 * Created by xxu on 2017/3/31.
 */
@Service
public class NewSwitchSettingServiceImpl implements NewSwitchSettingService {

    @Autowired
    private NewSwitchSettingManager switchSettingManager;

    @Override
    public ServiceResponse<Long> addSwitchSetting(NewSwitchSettingDto switchSettingDto) {
        ServiceResponse<Long> serviceResponse = switchSettingManager.addSwitchSetting(NewSwitchSettingConvertor.switchSettingDtoToSwitchSetting(switchSettingDto));
        return serviceResponse;
    }

    @Override
    public ServiceResponse<Integer> updateSwitchSetting(NewSwitchSettingDto switchSettingDto) {
        Integer count = switchSettingManager.updateSwitchSettingByGroupIdAndOrgId(NewSwitchSettingConvertor.switchSettingDtoToSwitchSetting(switchSettingDto));
        return new ServiceResponse<Integer>(true, null, count);
    }

    @Override
    public ServiceResponse<Integer> updateSwitchSettingSelective(NewSwitchSettingDto switchSettingDto) {
        Integer count = switchSettingManager.updateSwitchSettingSelective(NewSwitchSettingConvertor.switchSettingDtoToSwitchSetting(switchSettingDto));
        return new ServiceResponse<Integer>(true, null, count);
    }

    @Override
    public ServiceResponse<Integer> removeSwitchSettingById(Long id, Long orgId,Long groupId) {
        Integer count = switchSettingManager.removeSwitchSettingById(id, orgId,groupId);
        return new ServiceResponse<Integer>(true, null, count);
    }

    @Override
    public ServiceResponse<NewSwitchSettingDto> getSwitchSettingById(Long id, Long orgId,Long groupId) {
        NewSwitchSetting switchSetting = switchSettingManager.getSwitchSettingById(id, orgId,groupId);
        return new ServiceResponse<NewSwitchSettingDto>(true, null, NewSwitchSettingConvertor.switchSettingToSwitchSettingDto(switchSetting));
    }
//
    @Override
    public ServiceResponse<PaginationDto<NewSwitchSettingDto>> getPaginationSwitchSetting(PaginationDto<NewSwitchSettingDto> paginationDto, NewSwitchSettingDto switchSettingDto) {
        Pagination<NewSwitchSetting> pagination = switchSettingManager.getPaginationSwitchSetting(NewSwitchSettingConvertor.paginationSwitchSettingDtoToPaginationSwitchSetting(paginationDto) , NewSwitchSettingConvertor.switchSettingDtoToSwitchSetting(switchSettingDto));
        PaginationDto<NewSwitchSettingDto> newSwitchPaginationDto=NewSwitchSettingConvertor.paginationSwitchSettingToPaginationSwitchSettingDto(pagination);
        return new ServiceResponse<PaginationDto<NewSwitchSettingDto>>(true, null,newSwitchPaginationDto );
    }

    @Override
    public ServiceResponse<Integer> getSwitchSettingCount(NewSwitchSettingDto switchSettingDto) {
        Integer count = switchSettingManager.getSwitchSettingCount(NewSwitchSettingConvertor.switchSettingDtoToSwitchSetting(switchSettingDto));
        return new ServiceResponse<Integer>(true, null, count);
    }

    @Override
    public ServiceResponse<NewSwitchSettingDto> getSwitchSettingByOrgIdAndGroupId(Long orgId,Long groupId) {
        NewSwitchSetting switchSetting = switchSettingManager.getSwitchSettingByOrgId(orgId,groupId);
        return new ServiceResponse<NewSwitchSettingDto>(true, null, NewSwitchSettingConvertor.switchSettingToSwitchSettingDto(switchSetting));
    }

    @Override
    public ServiceResponse<Integer> removeSwitchSettingByOrgIdAndGroupId(Long orgId, Long groupId) {
        Integer count = switchSettingManager.removeSwitchSettingByOrgIdAndGroupId(orgId,groupId);
        return new ServiceResponse<Integer>(true, null, count);
    }

    @Override
    public ServiceResponse<List<NewSwitchSettingDto>> getSwitchSettingListByOrgId(Long orgId) {
        List<NewSwitchSetting> switchSettingList=switchSettingManager.getSwitchSettingListByOrgId(orgId);
        return new ServiceResponse<List<NewSwitchSettingDto>>(true,null,NewSwitchSettingConvertor.switchSettingListToSwitchSettingDtoList(switchSettingList));
    }

    @Override
    public ServiceResponse<List<NewSwitchSettingDto>> getDeviceBindingSwitchSettings(Long orgId, List<Long> groupIdList) {
        List<NewSwitchSetting> switchSettingList=switchSettingManager.getDeviceBindingSwitchSettings(orgId,groupIdList);
        return new ServiceResponse<List<NewSwitchSettingDto>>(true,null,NewSwitchSettingConvertor.switchSettingListToSwitchSettingDtoList(switchSettingList));

    }
}