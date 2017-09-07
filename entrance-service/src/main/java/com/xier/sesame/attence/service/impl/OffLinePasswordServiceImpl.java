package com.xier.sesame.attence.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xier.sesame.attence.convertor.OffLinePasswordConvertor;
import com.xier.sesame.attence.domain.OffLinePassword;
import com.xier.sesame.attence.dto.OffLinePasswordDto;
import com.xier.sesame.attence.manager.OffLinePasswordManager;
import com.xier.sesame.attence.service.OffLinePasswordService;
import com.xier.sesame.common.rpc.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**离线密码服务
 * Created by xxu on 2017/4/14.
 */
@Service
public class OffLinePasswordServiceImpl implements OffLinePasswordService {

    @Autowired
    private OffLinePasswordManager offLinePasswordManager;

    @Override
    public ServiceResponse<Long> addOffLinePassword(OffLinePasswordDto offLinePasswordDto) {
        ServiceResponse<Long> serviceResponse =offLinePasswordManager.addOffLinePassword(OffLinePasswordConvertor.offLinePasswordDtoToOffLinePassword(offLinePasswordDto));
        return serviceResponse;
    }

    @Override
    public ServiceResponse<Integer> updateOffLinePassword(OffLinePasswordDto offLinePasswordDto) {
        Integer count = offLinePasswordManager.updateOffLinePassword(OffLinePasswordConvertor.offLinePasswordDtoToOffLinePassword(offLinePasswordDto));
        return new ServiceResponse<Integer>(true, null, count);
    }

    @Override
    public ServiceResponse<Integer> updateOffLinePasswordByOrgId(OffLinePasswordDto offLinePasswordDto) {
        Integer count = offLinePasswordManager.updateOffLinePasswordByOrgId(OffLinePasswordConvertor.offLinePasswordDtoToOffLinePassword(offLinePasswordDto));
        return new ServiceResponse<Integer>(true, null, count);
    }

    @Override
    public ServiceResponse<Integer> removeOffLinePasswordById(Long id, Long orgId) {
        Integer count = offLinePasswordManager.removeOffLinePasswordById(id,orgId);
        return new ServiceResponse<Integer>(true, null, count);
    }

    @Override
    public ServiceResponse<OffLinePasswordDto> getOffLinePasswordById(Long id, Long orgId) {

        OffLinePassword OffLinePassword=offLinePasswordManager.getOffLinePasswordById(id,orgId);
        return new ServiceResponse<OffLinePasswordDto>(true, null, OffLinePasswordConvertor.offLinePasswordTooffLinePasswordDto(OffLinePassword));
    }

    @Override
    public ServiceResponse<OffLinePasswordDto> getOffLinePasswordByOrgId(Long orgId) {
        OffLinePassword OffLinePassword=offLinePasswordManager.getOffLinePasswordByOrgId(orgId);
        return new ServiceResponse<OffLinePasswordDto>(true, null, OffLinePasswordConvertor.offLinePasswordTooffLinePasswordDto(OffLinePassword));
    }

}
