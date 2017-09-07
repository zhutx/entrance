package com.xier.sesame.attence.manager.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.moredian.bee.common.exception.BizAssert;
import com.moredian.cloudeye.core.api.conf.huber.*;
import com.moredian.cloudeye.core.api.conf.huber.auth.AcType;
import com.moredian.cloudeye.core.api.conf.huber.auth.AuthConfig;
import com.moredian.fishnet.device.model.DeviceInfo;
import com.moredian.fishnet.device.service.DeviceService;
import com.xier.sesame.attence.dto.AttenceDeployDto;
import com.xier.sesame.attence.dto.NewSwitchSettingDto;
import com.xier.sesame.attence.manager.AttenceDeployServiceManager;
import com.xier.sesame.attence.service.impl.DeviceConfigServiceImpl;
import com.xier.sesame.common.rpc.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxu on 2017/4/8.
 */
@Service
public class AttenceDeployServiceManagerImpl implements AttenceDeployServiceManager {

    public static final Logger logger = LoggerFactory.getLogger(DeviceConfigServiceImpl.class);


    @Reference
    private DeviceService deviceService;

    @Reference
    private OHuberConfigProvider oHuberConfigProvider;


    @Override
    public ServiceResponse<Boolean> addDeviceConfig(AttenceDeployDto attenceDeployDto) {


        OHuberConfig oHuberConfig=oHuberConfigProvider.loadOhuberConfig(attenceDeployDto.getOrgId());
        DeviceInfo device = deviceService.getDeviceById(attenceDeployDto.getOrgId(), attenceDeployDto.getDeviceId());
        String deviceSn = device.getDeviceSn();
        OHuberConfigNode node= oHuberConfig.findNodeByCode(deviceSn);
        if(node==null){
            node = new OHuberConfigNode();
            node.setCode(deviceSn);
            node.setNodeType(OHCNodeType.TURNIP_VERIFY.name());
            node.setSimilarNum(1);
            node.setChainItems(buildChainItemList(attenceDeployDto.getGroupSettingList()));
        }else{
            node.setChainItems(buildChainItemList(attenceDeployDto.getGroupSettingList()));
        }
        


        oHuberConfigProvider.configNode(attenceDeployDto.getOrgId(), null, node);


        logger.info("=========结束: 成功布控，设备是" + attenceDeployDto.getDeviceId());

        return new ServiceResponse<Boolean>(true);
    }


    private AuthConfig buildAuthConfig(NewSwitchSettingDto newSwitchSettingDto) {
        AuthConfig authConfig = new AuthConfig();
        authConfig.setAcType(AcType.WEEK_CYC);
        //星期周期授权方式，格式为：0111110,080000-170000,表示周一至周五早上八点到下午五点为授权时间，从周日开始
        newSwitchSettingDto.getDayBeginTime();
        String dayOpenTime = timeStrFormat(newSwitchSettingDto.getDayBeginTime()) + "-" + timeStrFormat(newSwitchSettingDto.getDayEndTime());
        String weekdayTime = weekStrFormat(newSwitchSettingDto.getOpenWeekDays());
        String authDetail = weekdayTime + "," + dayOpenTime;
        authConfig.setDetails(authDetail);
        logger.info(String.format("AuthConfig for Group is generated.GroupId is %d,GroupCode is %s,Deploy time is %s", newSwitchSettingDto.getGroupId(), newSwitchSettingDto.getGroupCode(),authDetail));
        return authConfig;

    }


    private String weekStrFormat(String weekday) {
        //weekday format should be 1,2,3,4,5 means Saturday and sunday is disable
        //return forat should be 0111110,means Saturday and sunday is disable
        if (StringUtils.isNotEmpty(weekday)) {

            String[] strArray = weekday.split(",");
            StringBuilder result = new StringBuilder("0000000");
            for (String s : strArray) {
                int week = Integer.parseInt(s);
                result.replace(week-1, week, "1");
            }
            return result.toString();
        } else {
            //that's exception. no day
            return "0000000";
        }


    }

    private String timeStrFormat(String time) {
        if (StringUtils.isNotEmpty(time)) {
            String[] strArray = time.split(":");
            BizAssert.equals(2, strArray.length, "格式错误，正确格式为：08:30");
            StringBuilder sb = new StringBuilder();
            return sb.append(strArray[0]).append(strArray[1]).append("00").toString();
        } else {
            return "000000";
        }
    }

    private List<OHCRecognizeChainItem> buildChainItemList(List<NewSwitchSettingDto> groupIdList) {
        List<OHCRecognizeChainItem> chainItems = new ArrayList<>();
        int index = 0;
        for (NewSwitchSettingDto switchSettingDto : groupIdList) {
            OHCRecognizeChainItem item = new OHCRecognizeChainItem();
            item.setIndex(index);
            item.setTree(false);
            item.setType(1);
            item.setValue(switchSettingDto.getGroupCode());
            item.setAuthConfig(buildAuthConfig(switchSettingDto));
            chainItems.add(item);
            index++;
        }
        return chainItems;
    }
}
