package com.xier.sesame.attence.web.controller.resp;

import lombok.Data;

/**
 * Created by xxu on 2017/4/18.
 */
@Data
public class CompanyRecognizeRecord {
    private Long recoId;
    private String catchImg;
    private String deviceName;
    private Long recoTime;
    private String department;
    private String realName;
    private String openDoorImg;
    private Integer userType;
}
