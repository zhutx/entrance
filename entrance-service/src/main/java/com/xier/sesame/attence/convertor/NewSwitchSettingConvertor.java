package com.xier.sesame.attence.convertor;

import com.xier.sesame.attence.domain.NewSwitchSetting;
import com.xier.sesame.attence.dto.NewSwitchSettingDto;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.base.converter.PaginationConvertor;
import com.xier.sesame.common.rpc.dto.PaginationDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxu on 2017/3/31.
 */
public class NewSwitchSettingConvertor{

        public static NewSwitchSettingDto switchSettingToSwitchSettingDto(NewSwitchSetting switchSetting) {
            if (switchSetting == null)
                return null;
            NewSwitchSettingDto switchSettingDto = new NewSwitchSettingDto();
            switchSettingDto.setSwitchSettingId(switchSetting.getSwitchSettingId());
            switchSettingDto.setOrgId(switchSetting.getOrgId());
            switchSettingDto.setAttenceSwitch(switchSetting.getAttenceSwitch());
            switchSettingDto.setStangerSwitch(switchSetting.getStangerSwitch());
            switchSettingDto.setOpenWeekDays(switchSetting.getOpenWeekDays());
            switchSettingDto.setGroupId(switchSetting.getGroupId());
            switchSettingDto.setDayBeginTime(switchSetting.getDayBeginTime());
            switchSettingDto.setDayEndTime(switchSetting.getDayEndTime());
            switchSettingDto.setGmtCreate(switchSetting.getGmtCreate());
            switchSettingDto.setGmtModify(switchSetting.getGmtModify());
            switchSettingDto.setGroupCode(switchSetting.getGroupCode());
            return switchSettingDto;
        }

        public static NewSwitchSetting switchSettingDtoToSwitchSetting(NewSwitchSettingDto switchSettingDto) {
            if (switchSettingDto == null)
                return null;
            NewSwitchSetting switchSetting = new NewSwitchSetting();
            switchSetting.setSwitchSettingId(switchSettingDto.getSwitchSettingId());
            switchSetting.setOrgId(switchSettingDto.getOrgId());
            switchSetting.setAttenceSwitch(switchSettingDto.getAttenceSwitch());
            switchSetting.setStangerSwitch(switchSettingDto.getStangerSwitch());
            switchSetting.setOpenWeekDays(switchSettingDto.getOpenWeekDays());
            switchSetting.setGroupId(switchSettingDto.getGroupId());
            switchSetting.setDayBeginTime(switchSettingDto.getDayBeginTime());
            switchSetting.setDayEndTime(switchSettingDto.getDayEndTime());
            switchSetting.setGmtCreate(switchSettingDto.getGmtCreate());
            switchSetting.setGmtModify(switchSettingDto.getGmtModify());
            switchSetting.setGroupCode(switchSettingDto.getGroupCode());
            return switchSetting;
        }

        public static List<NewSwitchSettingDto> switchSettingListToSwitchSettingDtoList(List<NewSwitchSetting> switchSettingList) {
            if (switchSettingList == null)
                return null;
            List<NewSwitchSettingDto> SwitchSettingDtoList = new ArrayList<NewSwitchSettingDto>();
            for (NewSwitchSetting switchSetting : switchSettingList) {
                SwitchSettingDtoList.add(switchSettingToSwitchSettingDto(switchSetting));
            }
            return SwitchSettingDtoList;
        }

        public static List<NewSwitchSetting> switchSettingDtoListToSwitchSettingList(List<NewSwitchSettingDto> switchSettingDtoList) {
            if (switchSettingDtoList == null)
                return null;
            List<NewSwitchSetting> SwitchSettingList = new ArrayList<NewSwitchSetting>();
            for (NewSwitchSettingDto switchSettingDto : switchSettingDtoList) {
                SwitchSettingList.add(switchSettingDtoToSwitchSetting(switchSettingDto));
            }
            return SwitchSettingList;
        }

        public static PaginationDto<NewSwitchSettingDto> paginationSwitchSettingToPaginationSwitchSettingDto(Pagination<NewSwitchSetting> pagination) {
            PaginationDto<NewSwitchSettingDto> paginationDto = PaginationConvertor.paginationToPaginationDto(pagination, new PaginationDto<NewSwitchSettingDto>());
            if (paginationDto == null)
                return null;
            paginationDto.setData(switchSettingListToSwitchSettingDtoList(pagination.getData()));
            return paginationDto;
        }

        public static Pagination<NewSwitchSetting> paginationSwitchSettingDtoToPaginationSwitchSetting(PaginationDto<NewSwitchSettingDto> paginationDto) {
            Pagination<NewSwitchSetting> pagination = PaginationConvertor.paginationDtoToPagination(paginationDto, new Pagination<NewSwitchSetting>());
            if (pagination == null)
                return null;
            pagination.setData(switchSettingDtoListToSwitchSettingList(paginationDto.getData()));
            return pagination;
        }

}