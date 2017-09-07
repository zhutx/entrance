package com.xier.sesame.attence.web.controller.resp;

import lombok.Data;

/**
 * Created by xxu on 2017/4/8.
 */
@Data
public class NewSettingData {

    private Integer attenceSwitch = 0;
    private Integer stangerSwitch = 0;
    private String openWeekDays = "";
    private String dayBeginTime = "00:00";
    private String dayEndTime = "23:59";
    private Long switchSettingId = 0L;
    private Long groupId = 0L;
    private String groupName="";
    private Integer groupType;
    private Integer systemDefault;
    private Integer allMemberFlag;
    private Integer memberSize;
}
