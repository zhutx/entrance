package com.xier.sesame.attence.test;

import com.xier.sesame.attence.AppStarter;
import com.xier.sesame.attence.dto.AttenceDeployDto;
import com.xier.sesame.attence.dto.NewSwitchSettingDto;
import com.xier.sesame.attence.manager.AttenceDeployServiceManager;
import com.xier.sesame.attence.service.DeviceConfigService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxu on 2017/4/8.
        */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppStarter.class)
public class DeployTest {

    @Autowired
    private AttenceDeployServiceManager attenceDeployServiceManager;

    @Autowired
    private DeviceConfigService deviceConfigService;

//    @Test
//    public void testXml(){
//        List<Long> a=new ArrayList<>();
//        a.add(1564277391885336576L);
//
//        deviceConfigService.notifyXmlServer(1564269111188389888L,a);
//    }


    @Test
    public void deployGroupIdlist(){

        AttenceDeployDto attenceDeployDto=new AttenceDeployDto();
        attenceDeployDto.setOrgId(1565074529225539584L);
        attenceDeployDto.setDeviceId(1565161665522040832L);
        List<NewSwitchSettingDto> gList=new ArrayList<NewSwitchSettingDto>();
        NewSwitchSettingDto newSwitchSettingDto=new NewSwitchSettingDto();
        newSwitchSettingDto.setGroupCode("000000070100000000000000000000000000");
        newSwitchSettingDto.setOpenWeekDays("1,2,3,4,6");
        newSwitchSettingDto.setDayBeginTime("06:00");
        newSwitchSettingDto.setDayEndTime("23:15");


        gList.add(newSwitchSettingDto);
        attenceDeployDto.setGroupSettingList(gList);
        attenceDeployServiceManager.addDeviceConfig(attenceDeployDto);

        try {
            Thread.sleep(1000*10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
