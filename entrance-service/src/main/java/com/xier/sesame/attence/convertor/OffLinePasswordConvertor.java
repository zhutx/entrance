package com.xier.sesame.attence.convertor;

import com.xier.sesame.attence.domain.OffLinePassword;
import com.xier.sesame.attence.dto.OffLinePasswordDto;

/**
 * Created by xxu on 2017/4/14.
 */
public class OffLinePasswordConvertor {

    public static OffLinePasswordDto offLinePasswordTooffLinePasswordDto(OffLinePassword offLinePassword) {
        if (offLinePassword == null)
            return null;
        OffLinePasswordDto offLinePasswordDto = new OffLinePasswordDto();
        offLinePasswordDto.setOffLinePasswordId(offLinePassword.getOffLinePasswordId());
        offLinePasswordDto.setOrgId(offLinePassword.getOrgId());
        offLinePasswordDto.setPassword(offLinePassword.getPassword());
        offLinePasswordDto.setGmtCreate(offLinePassword.getGmtCreate());
        offLinePasswordDto.setGmtModify(offLinePassword.getGmtModify());
        return offLinePasswordDto;
    }

    public static OffLinePassword offLinePasswordDtoToOffLinePassword(OffLinePasswordDto offLinePasswordDto) {
        if (offLinePasswordDto == null)
            return null;
        OffLinePassword offLinePassword = new OffLinePassword();
        offLinePassword.setOffLinePasswordId(offLinePasswordDto.getOffLinePasswordId());
        offLinePassword.setOrgId(offLinePasswordDto.getOrgId());
        offLinePassword.setPassword(offLinePasswordDto.getPassword());
        offLinePassword.setGmtCreate(offLinePasswordDto.getGmtCreate());
        offLinePassword.setGmtModify(offLinePasswordDto.getGmtModify());
        return offLinePassword;
    }

}
