package com.xier.sesame.attence.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.xier.sesame.attence.dto.SwitchSettingDto;
import com.xier.sesame.attence.dto.TimeSettingDto;
import com.xier.sesame.attence.service.SwitchSettingService;
import com.xier.sesame.attence.service.TimeSettingService;
import com.xier.sesame.common.rpc.ServiceResponse;

public class DisableDaysUtil {
	
	public static String getDisableDays(Long orgId, int year, SwitchSettingService switchSettingService, TimeSettingService timeSettingService){
		String openWeekDays = ""; 
		ServiceResponse<SwitchSettingDto> sr_switchSettingDto = switchSettingService.getSwitchSettingByOrgId(orgId);
		if(sr_switchSettingDto.isSuccess() && sr_switchSettingDto.isExistData()) {
			openWeekDays = sr_switchSettingDto.getData().getOpenWeekDays();
		}
		
		// 承载年度禁用日期
		StringBuffer data = new StringBuffer();
		
		List<String> calendarDisableDayList = new ArrayList<>();
		ServiceResponse<List<TimeSettingDto>> sr_timeSettingDtoList = timeSettingService.getTimeSetting(buildTimeSettingDto(orgId));
		if(sr_timeSettingDtoList.isSuccess() && CollectionUtils.isNotEmpty(sr_timeSettingDtoList.getData())) {
			calendarDisableDayList = buildCalendarDisabledDayList(sr_timeSettingDtoList.getData());
		}
		
		// 依据星期中的门禁开放日配置 --> 提取本年度12个月中每个月的禁用日
		List<Integer> openWeekDayList = buildOpenWeekDayList(openWeekDays);
		for(int month=0;month<12;month++){ 
			List<String> monthDisableDaysList = getDisableDaysOfYear(openWeekDayList, year, month, calendarDisableDayList); // 提取单个月的禁用日
			for(String str : monthDisableDaysList) {
				if(data.length() == 0){
					data.append(str);
				} else {
					data.append("," + str);
				}
			}
		}
		
		return data.toString();
	}
	
	private static List<String> buildCalendarDisabledDayList(List<TimeSettingDto> timeSettingDtoList) {
		List<String> list = new ArrayList<>();
		for(TimeSettingDto dto : timeSettingDtoList) {
			list.add(dto.getDisableDate());
		}
		return list;
	}
	
	private static TimeSettingDto buildTimeSettingDto(Long orgId) {
		TimeSettingDto dto = new TimeSettingDto();
		dto.setOrgId(orgId);
		return dto;
	}
	
	private static List<Integer> buildOpenWeekDayList(String openWeekDays) {
		List<Integer> openWeekDayList = new ArrayList<>();
		if(StringUtils.isNotBlank(openWeekDays)) {
			String[] arr = openWeekDays.split(",");
			for(String str : arr){
				openWeekDayList.add(Integer.parseInt(str));
			}
		}
		return openWeekDayList;
	}
	
	/**
	 * 依据星期中的门禁开放日配置 --> 提取某年某月的禁用日
	 * @param openWeekDays
	 * @return
	 */
	private static List<String> getDisableDaysOfYear(List<Integer> openWeekDayList, int year, int month, List<String> calendarDisableDayList){
		List<String> list = new ArrayList<>();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		while (calendar.get(Calendar.MONTH) == month) {
			
			String date = format.format(calendar.getTime());
			if(calendarDisableDayList.contains(date)){
				list.add(date);
			} else {
				if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
	                if(!openWeekDayList.contains(1)){
	                	list.add(date);
	                }
	            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
	            	if(!openWeekDayList.contains(2)){
	                	list.add(date);
	                }
	            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
	            	if(!openWeekDayList.contains(3)){
	                	list.add(date);
	                }
	            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
	            	if(!openWeekDayList.contains(4)){
	                	list.add(date);
	                }
	            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
	            	if(!openWeekDayList.contains(5)){
	                	list.add(date);
	                }
	            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
	            	if(!openWeekDayList.contains(6)){
	                	list.add(date);
	                }
	            } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
	            	if(!openWeekDayList.contains(7)){
	                	list.add(date);
	                }
	            }
			}
			
            calendar.add(Calendar.DATE, 1);
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		
		String openWeekDays = "1,2,3,5"; // 星期中的门禁开放日
		List<Integer> openWeekDayList = buildOpenWeekDayList(openWeekDays);
		int year = 2016; // 年份
		
		List<String> calendarDisableDayList = new ArrayList<>(); // 日历中的门禁禁用日
		calendarDisableDayList.add("2016-01-04");
		calendarDisableDayList.add("2016-01-05");
		
		StringBuffer data = new StringBuffer();
		for(int month=0;month<12;month++){
			List<String> monthDisableDaysList = getDisableDaysOfYear(openWeekDayList, year, month, calendarDisableDayList);
			for(String str : monthDisableDaysList) {
				if(data.length() == 0){
					data.append(str);
				} else {
					data.append("," + str);
				}
			}
		}
		
		System.out.println(data.toString());
		
	}

}
