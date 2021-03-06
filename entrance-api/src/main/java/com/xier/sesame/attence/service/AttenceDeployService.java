package com.xier.sesame.attence.service;

import com.xier.sesame.attence.dto.AttenceDeployDto;
import com.xier.sesame.common.rpc.ServiceResponse;

/**
 * Created by xxu on 2017/4/8.
 */
public interface AttenceDeployService {

    /**
     * 布控功能，为门禁系统服务
     * @param attenceDeployDto
     * @return
     */
    public ServiceResponse<Boolean> addDeviceConfig(AttenceDeployDto attenceDeployDto);
}
