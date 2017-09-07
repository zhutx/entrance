package com.xier.sesame.attence.web.controller.resp;

import lombok.Data;

/**
 * Created by xxu on 2017/4/18.
 */
@Data
public class EmployeeRecognizeRecord {

    private Long recoId;
//    private String faceImg;
    private String catchImg;
//    private String subOrgRemark;
    private String deviceName;
//    private Integer recognizeUserType;
//    private Integer status;
    private Long recoTime;
}
