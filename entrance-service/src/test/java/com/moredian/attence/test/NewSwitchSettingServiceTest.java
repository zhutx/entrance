package com.moredian.attence.test;

import com.xier.sesame.attence.AppStarter;
import com.xier.sesame.attence.domain.NewSwitchSetting;
import com.xier.sesame.attence.manager.NewSwitchSettingManager;
import com.xier.sesame.common.rpc.ServiceResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xxu on 2017/4/1.
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = AppStarter.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppStarter.class)
public class NewSwitchSettingServiceTest{

    @Autowired
    private NewSwitchSettingManager newSwitchSettingManager;

    @Test
    public void testAddSwitchSettting(){

        NewSwitchSetting switchSettingDto = new NewSwitchSetting();
        switchSettingDto.setGroupId(100L);
        switchSettingDto.setOrgId(10000L);
        switchSettingDto.setAttenceSwitch(1);
        switchSettingDto.setDayBeginTime("08:00");
        switchSettingDto.setDayEndTime("20:00");
        switchSettingDto.setOpenWeekDays("1,2,3");
        switchSettingDto.setStangerSwitch(1);
        switchSettingDto.setSwitchSettingId(123456L);

        ServiceResponse<Long> serviceResponse=newSwitchSettingManager.addSwitchSetting(switchSettingDto);
        Assert.assertTrue(serviceResponse.isSuccess());
        System.out.println(serviceResponse.getData());


    }

}
