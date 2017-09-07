package com.xier.sesame.attence.web.controller.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * Created by xxu on 2017/3/24.
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class GroupInfoWithSetting {

    private Integer groupType;
    private String groupCode;
    private Integer allMemberFlag;
    private Long groupId;
    private String groupName;
    private Long orgId;
    private Integer systemDefault;
    private Integer memberSize;
    private Date gmtCreate;
    private Date gmtModify;
    private List<String> devices;
    private List<String> members;
    //每日门禁开放起始时间
    private String dayBeginTime;

    //每日门禁开放截止时间
    private String dayEndTime;


    //一周中开放日期
    //format is 2,3,4,5,6 means Monday~friday is working. 1 means Sunday,7 means saturday
    private String openWeekDays;

}
