package com.xier.sesame.attence.convertor;

import com.xier.sesame.attence.domain.SwitchSetting;
import com.xier.sesame.common.rpc.dto.PaginationDto;
import com.xier.sesame.attence.dto.SwitchSettingDto;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.base.converter.PaginationConvertor;
import java.util.List;
import java.util.ArrayList;

/**
 * 门禁开关配置数据转换器
 * @author zhutx
 */
public class SwitchSettingConvertor {
	
	
	public static SwitchSettingDto switchSettingToSwitchSettingDto(SwitchSetting switchSetting) {
		if (switchSetting == null)
			return null;
		SwitchSettingDto switchSettingDto = new SwitchSettingDto();		
		switchSettingDto.setSwitchSettingId(switchSetting.getSwitchSettingId());
		switchSettingDto.setOrgId(switchSetting.getOrgId());
		switchSettingDto.setAttenceSwitch(switchSetting.getAttenceSwitch());
		switchSettingDto.setStangerSwitch(switchSetting.getStangerSwitch());
		switchSettingDto.setOpenWeekDays(switchSetting.getOpenWeekDays());
		switchSettingDto.setDayBeginTime(switchSetting.getDayBeginTime());
		switchSettingDto.setDayEndTime(switchSetting.getDayEndTime());
		switchSettingDto.setGmtCreate(switchSetting.getGmtCreate());
		switchSettingDto.setGmtModify(switchSetting.getGmtModify());
		return switchSettingDto;
	}
	
	public static SwitchSetting switchSettingDtoToSwitchSetting(SwitchSettingDto switchSettingDto) {
		if (switchSettingDto == null)
			return null;
		SwitchSetting switchSetting = new SwitchSetting();		
		switchSetting.setSwitchSettingId(switchSettingDto.getSwitchSettingId());
		switchSetting.setOrgId(switchSettingDto.getOrgId());
		switchSetting.setAttenceSwitch(switchSettingDto.getAttenceSwitch());
		switchSetting.setStangerSwitch(switchSettingDto.getStangerSwitch());
		switchSetting.setOpenWeekDays(switchSettingDto.getOpenWeekDays());
		switchSetting.setDayBeginTime(switchSettingDto.getDayBeginTime());
		switchSetting.setDayEndTime(switchSettingDto.getDayEndTime());
		switchSetting.setGmtCreate(switchSettingDto.getGmtCreate());
		switchSetting.setGmtModify(switchSettingDto.getGmtModify());
		return switchSetting;
	}	
	
	public static List<SwitchSettingDto> switchSettingListToSwitchSettingDtoList(List<SwitchSetting> switchSettingList) {
		if (switchSettingList == null)
			return null;
		List<SwitchSettingDto> SwitchSettingDtoList = new ArrayList<SwitchSettingDto>();
		for (SwitchSetting switchSetting : switchSettingList) {
			SwitchSettingDtoList.add(switchSettingToSwitchSettingDto(switchSetting));
		}
		return SwitchSettingDtoList;
	}
	
	public static List<SwitchSetting> switchSettingDtoListToSwitchSettingList(List<SwitchSettingDto> switchSettingDtoList) {
		if (switchSettingDtoList == null)
			return null;
		List<SwitchSetting> SwitchSettingList = new ArrayList<SwitchSetting>();
		for (SwitchSettingDto switchSettingDto : switchSettingDtoList) {
			SwitchSettingList.add(switchSettingDtoToSwitchSetting(switchSettingDto));
		}
		return SwitchSettingList;
	}
	
	public static PaginationDto<SwitchSettingDto> paginationSwitchSettingToPaginationSwitchSettingDto(Pagination<SwitchSetting> pagination) {
		PaginationDto<SwitchSettingDto> paginationDto = PaginationConvertor.paginationToPaginationDto(pagination, new PaginationDto<SwitchSettingDto>());
		if (paginationDto == null)
			return null;
		paginationDto.setData(switchSettingListToSwitchSettingDtoList(pagination.getData()));
		return paginationDto;
	}
	
	public static Pagination<SwitchSetting> paginationSwitchSettingDtoToPaginationSwitchSetting(PaginationDto<SwitchSettingDto> paginationDto) {
		Pagination<SwitchSetting> pagination = PaginationConvertor.paginationDtoToPagination(paginationDto, new Pagination<SwitchSetting>());
		if (pagination == null)
			return null;
		pagination.setData(switchSettingDtoListToSwitchSettingList(paginationDto.getData()));
		return pagination;
	}
	
}