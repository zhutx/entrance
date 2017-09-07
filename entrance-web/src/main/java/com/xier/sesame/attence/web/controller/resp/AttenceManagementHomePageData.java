package com.xier.sesame.attence.web.controller.resp;

import lombok.Data;

import java.util.Date;

/**
 * Created by xxu on 2017/5/4.
 */
@Data
public class AttenceManagementHomePageData {

    private static final long serialVersionUID = -0L;


    //机构id
    private Long orgId;

    //报告日期
    private Date reportDate;

    //总识别次数
    private Integer totalTimes;

    //员工识别次数
    private Integer employeeTimes;

    //访客识别次数
    private Integer visitorTimes;

    //陌生人识别次数
    private Integer stangerTimes;

    //机构下员工总数
    private Integer memberNumber;

    //机构下部门总数
    private Integer departmentNumber;

    //机构名称
    private String orgName;


    //机构LOGO
    private String orgLogo;




}
