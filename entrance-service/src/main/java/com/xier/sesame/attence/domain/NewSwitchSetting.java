package com.xier.sesame.attence.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xxu on 2017/3/31.
 */
@Data
public class NewSwitchSetting implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 7014810336417270990L;

    //开关配置id
    private Long switchSettingId;

    //人员组的id
    private Long groupId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    //机构id
    private Long orgId;

    //子机构码 【zipcode 6】【机构类型 2 】【主机构编号6】【子3】【子3】【子3】【子3】【子2】【子2】【子2】【子2】【子2】
    private String groupCode;

    //考勤统计开关
    private Integer attenceSwitch;

    //陌生人告警开关
    private Integer stangerSwitch;

    //一周中开放日期
    //format is 2,3,4,5,6 means Monday~friday is working. 1 means Sunday,7 means saturday
    private String openWeekDays;

    //每日门禁开放起始时间
    private String dayBeginTime;

    //每日门禁开放截止时间
    private String dayEndTime;

    //创建时间
    private Date gmtCreate;

    //修改时间
    private Date gmtModify;


}
