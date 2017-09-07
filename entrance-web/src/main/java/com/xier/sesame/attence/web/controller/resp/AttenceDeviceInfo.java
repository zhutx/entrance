package com.xier.sesame.attence.web.controller.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xxu on 2017/5/18.
 */
@Data
public class AttenceDeviceInfo implements Serializable {
    // 设备id
    private Long deviceId;
    // 机构id
    private Long orgId;
    // 位置id
    private Integer deviceType;
    // 设备名
    private String deviceName;
    // 设备SN
    private String deviceSn;

    private Integer version;
    // 网络状态
    private Integer netState;

    private Integer status;
    //重启状态
    private Byte progress;
}
