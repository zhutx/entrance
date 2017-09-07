package com.moredian.attence.test;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.moredian.bee.rmq.EventBus;
import com.moredian.fishnet.common.model.msg.ConfigGroupAtcDataMsg;
import com.moredian.fishnet.device.model.TransferMessageInfo;
import com.moredian.fishnet.device.request.TransferRequest;
import com.xier.sesame.attence.AppStarter;
import com.xier.sesame.attence.message.AttenceMessageListener;
import com.xier.sesame.attence.service.DeviceConfigService;

/**
 * Created by xxu on 2017/3/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppStarter.class)
public class SendMessageTest {

    @Autowired
    AttenceMessageListener deviceInnerService;

    @Autowired
    DeviceConfigService deviceConfigService;


    @Test
    public void sendMQMessage(){
        ConfigGroupAtcDataMsg noticeGroupMsg=new ConfigGroupAtcDataMsg();
        noticeGroupMsg.setOrgId(12000L);
        noticeGroupMsg.setGroupName("gourName");
        noticeGroupMsg.setGroupType(1);
        noticeGroupMsg.setGroupId(100L);
        EventBus.publish(noticeGroupMsg);




    }
//
//    @Test
//    public void sendMqttMessage(){
//
//        Gson gson=new Gson();
//        String deviceSn="AUK0BCVAH1-0c63fc003dcb";
//        TransferMessageInfo transferMessageInfo =buildTransferMessage();
//        String jsonStr=gson.toJson(transferMessageInfo);
//        System.out.println(jsonStr);
//        String base64Message= Base64.encodeBase64String(jsonStr.getBytes());
//        DeviceStateInfo info=deviceInnerService.sendMessage(buildTransferRequest(deviceSn,base64Message));
//        System.out.println(base64Message);
//        System.out.println(info);
//
//
//    }

//    @Test
//    public void sendMqttMessage(){
//
////        String deviceSn="AUK0BCVAH1";
////        deviceConfigService.notifyXmlServer(100L, ids);
//
//
//    }



    private TransferRequest buildTransferRequest(String sn, String message) {

        TransferRequest transferRequest=new TransferRequest();
        transferRequest.setSerialNumber(sn);
        transferRequest.setBody(message);
        //It's better to delay
        transferRequest.setDelaySeconds(5);
        return transferRequest;

    }

    private TransferMessageInfo buildTransferMessage() {
        TransferMessageInfo transferMessageInfo = new TransferMessageInfo();
        transferMessageInfo.setEventType(101);
        transferMessageInfo.setSeverity(5);
        transferMessageInfo.setSeqId(UUID.randomUUID().toString());
        transferMessageInfo.setMessage("Download config file");

        return transferMessageInfo;
//		JSONUtils.valueToString()
//		transferRequest.setBody(transferMessageInfo.to);
//		Base64.encodeBase64String(transferMessageInfo());

    }


//    @Test
//    public void sendMessage(){
//        OrgInfo orgInfo =new OrgInfo();
//        orgInfo.setOrgId(10L);
//        orgInfo.setAddress("fefef");
//        orgInfo.setCityId(998);
//        orgInfo.setContact("mingtian");
//        int result = EventBus.publish(orgInfo);
//        System.out.println("testaaaa");
//        System.out.println(result);
//    }
}
