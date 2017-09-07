package com.xier.sesame.attence.web.controller.req;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxu on 2017/3/23.
 */
@Data
public class UserGroupModel {

    /** 机构id */
    private Long orgId; //required
    /** 群组名 */
    private String groupName; //required
    /** 是否指定全员 */
    private Boolean isAllMember; //required
    /** 通行成员id  */
    private List<Long> members = new ArrayList<>(); //optional

    /** 群组关联部门 */
    private List<Long> depts = new ArrayList<>(); //optional

    /**
     * need to reset member.Set it to ture means reset member and dept
     */
    private Boolean resetMemberDept;

    /**
     * 门禁每周开放时间
     */
    private String weekdays;

    //每日门禁开放起始时间
    private String dayBeginTime;

    //每日门禁开放截止时间
    private String dayEndTime;




}
