package com.xier.sesame.attence.web.controller.req;

import lombok.Data;

/**
 * Created by xxu on 2017/4/14.
 */
@Data
public class OffLinePasswordModel {

    //Offline password id
    private Long offLinePasswordId;

    //org id
    private Long orgId;

    //password
    private String password;

    //cell phone
    private String cellPhone;


}
