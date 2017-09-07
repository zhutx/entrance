package com.moredian.attence.test;

import com.xier.sesame.attence.AppStarter;
import com.xier.sesame.attence.service.DeviceConfigService;
import com.xier.sesame.common.rpc.ServiceResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xxu on 2017/3/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppStarter.class)
public class GenerateXmlTest {

    @Autowired
    private DeviceConfigService deviceConfigService;


    @Test
    public void testGenerateXml(){
        ServiceResponse<Map<String,String>> xmlMap=deviceConfigService.getXmlConfigForDevice(1568915106161491968L,1568999261750689792L);
        Assert.assertTrue(xmlMap.isSuccess());
        System.out.println(xmlMap.getData().get("xml"));

    }
}
