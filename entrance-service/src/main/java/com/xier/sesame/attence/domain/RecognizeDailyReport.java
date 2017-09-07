package com.xier.sesame.attence.domain;

import com.xier.sesame.common.base.domain.BaseDomain;
import lombok.Data;

import java.util.Date;

@Data
public class RecognizeDailyReport extends BaseDomain {

	private static final long serialVersionUID = -0L;
	
	//识别日报id 
	private String recognizeDailyReportId;
	
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
	
	// 
	private Date gmtCreate;
	
	// 
	private Date gmtModify;
	

}
