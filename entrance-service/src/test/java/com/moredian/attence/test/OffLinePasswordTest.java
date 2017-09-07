package com.moredian.attence.test;

import com.xier.sesame.attence.AppStarter;
import com.xier.sesame.attence.domain.OffLinePassword;
import com.xier.sesame.attence.manager.OffLinePasswordManager;
import com.xier.sesame.common.rpc.ServiceResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xxu on 2017/4/14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppStarter.class)
public class OffLinePasswordTest {

    @Autowired
    private OffLinePasswordManager offLinePasswordManager;

    @Test
    public void testAddOffLinePassword(){

        OffLinePassword offLinePassword = new OffLinePassword();
        offLinePassword.setPassword("teste");
        offLinePassword.setOrgId(10000L);
        offLinePassword.setOffLinePasswordId(87459696L);

        ServiceResponse<Long> serviceResponse=offLinePasswordManager.addOffLinePassword(offLinePassword);
        Assert.assertTrue(serviceResponse.isSuccess());
        System.out.println(serviceResponse.getData());


    }

}
