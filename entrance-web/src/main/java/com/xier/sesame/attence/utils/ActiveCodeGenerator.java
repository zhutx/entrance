package com.xier.sesame.attence.utils;

import com.alibaba.dubbo.common.utils.StringUtils;

/**
 * Created by alpinist on 16-3-8.
 */
public abstract class ActiveCodeGenerator {
    public static final boolean TRUE = true;
    public static final boolean FALSE = false;

    //激活码生成算法
    public static String generateActiveCode(String deviceCode){
        return StringUtils.isBlank(deviceCode)?""+(int)((Math.random()*9+1)*100000000):deviceCode.substring(0,2)+(int)((Math.random()*9+1)*100000000);
    }
}
