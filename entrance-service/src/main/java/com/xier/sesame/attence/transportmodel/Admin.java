package com.xier.sesame.attence.transportmodel;

import lombok.Data;

/**
 * Created by xxu on 2017/4/14.
 */
@Data
public class Admin {

    private String offlinepassword;

    //temp value, used to enable or disable screen saver
    private Boolean enableScreenSaver;
}
