package com.xier.sesame.attence.web.controller.req;

import lombok.Data;

import java.util.List;

/**
 * Created by xxu on 2017/3/24.
 */
@Data

public class DeviceAndGroupBindingModel {
    private Long deviceId;
    private Long orgId;
    private List<Long> groupIdList;
    private String deviceName;
}
