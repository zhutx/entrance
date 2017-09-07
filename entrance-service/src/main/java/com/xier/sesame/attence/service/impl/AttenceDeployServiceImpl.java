package com.xier.sesame.attence.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xier.sesame.attence.dto.AttenceDeployDto;
import com.xier.sesame.attence.manager.AttenceDeployServiceManager;
import com.xier.sesame.attence.service.AttenceDeployService;
import com.xier.sesame.common.rpc.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 提供门禁布控功能
 * Created by xxu on 2017/4/8.
 */

@Service
public class AttenceDeployServiceImpl implements AttenceDeployService {

    @Autowired
    private AttenceDeployServiceManager attenceDeployServiceManager;

    @Override
    public ServiceResponse<Boolean> addDeviceConfig(AttenceDeployDto attenceDeployDto) {
        return attenceDeployServiceManager.addDeviceConfig(attenceDeployDto);
    }
}
