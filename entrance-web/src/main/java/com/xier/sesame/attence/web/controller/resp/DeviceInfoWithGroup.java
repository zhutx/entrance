package com.xier.sesame.attence.web.controller.resp;

import com.moredian.fishnet.device.model.CameraDeviceInfoVo;
import com.moredian.fishnet.device.model.DeviceInfo;
import com.moredian.fishnet.device.model.GroupInfo;
import lombok.Data;

import java.util.List;

/**
 * Created by xxu on 2017/5/15.
 */
@Data
public class DeviceInfoWithGroup {

    private DeviceInfo deviceInfo;

    private List<GroupInfo> groupInfos;

    private CameraDeviceInfoVo camera;

}
