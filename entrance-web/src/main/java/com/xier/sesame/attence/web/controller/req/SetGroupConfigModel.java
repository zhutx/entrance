package com.xier.sesame.attence.web.controller.req;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SetGroupConfigModel {
	
	private Long orgId;


	private Long groupId;

	//每日门禁开放起始时间
	private String dayBeginTime;

	//每日门禁开放截止时间
	private String dayEndTime;


	//open time for week
	//format like 1,2,3,4,5,6,7 ,1==Sunday,2==Monday,3==Tuesday,4==Wednesday,5==Thursday,6==Friday,7==Saturday
	private String weekdays;

	/** 通行成员id  */
	private List<Long> members = new ArrayList<>(); //optional

	/** 群组关联部门 */
	private List<Long> depts = new ArrayList<>(); //optional

	/**
	 * need to reset member.Set it to ture means reset member and dept
	 */
	private Boolean resetMemberDept;


	/**
	 * group name
	 */
	private String groupName;

	/** 是否指定全员 */
	private Boolean isAllMember; //optional


}
