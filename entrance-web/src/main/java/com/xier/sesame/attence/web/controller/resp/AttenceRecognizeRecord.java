package com.xier.sesame.attence.web.controller.resp;

import lombok.Data;

/**
 * Created by xxu on 2017/5/5.
 */
@Data
public class AttenceRecognizeRecord {

    private Long recoId;
    //抓拍头像
    private String catchImg;
    //开门头像
    private String openDoorImag;
    //设备名称
    private String deviceName;

    //识别时间
    private Long recoTime;
    //部门名称
    private String department;
    //真实姓名
    private String realName;

    //识别用户类型(1-员工,2-访客,3-黑名单)
    private Integer recognizeUserType;

    //状态
    private Integer status;

}
