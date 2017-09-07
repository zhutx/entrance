package com.xier.sesame.attence.service;

import com.xier.sesame.attence.dto.OffLinePasswordDto;
import com.xier.sesame.common.rpc.ServiceResponse;

/**
 * Created by xxu on 2017/4/14.
 */
public interface OffLinePasswordService {

    /**
     * 增加离线密码
     *
     * @param offLinePasswordDto
     * @return
     */
    public ServiceResponse<Long> addOffLinePassword(OffLinePasswordDto offLinePasswordDto);


    /**
     * 修改离线密码
     *
     * @param offLinePasswordDto
     * @return
     */
    public ServiceResponse<Integer> updateOffLinePassword(OffLinePasswordDto offLinePasswordDto);



    /**
     * 修改离线密码
     *
     * @param offLinePasswordDto
     * @return
     */
    public ServiceResponse<Integer> updateOffLinePasswordByOrgId(OffLinePasswordDto offLinePasswordDto);



    /**
     * 根据ID删除离线密码
     *
     * @param id
     * @param orgId 分表字段
     */
    public ServiceResponse<Integer> removeOffLinePasswordById(Long id, Long orgId);


    /**
     * 根据id获取离线密码
     *
     * @param id
     * @param orgId 分表字段
     * @return
     */
    public ServiceResponse<OffLinePasswordDto> getOffLinePasswordById(Long id, Long orgId);


    /**
     * 根据id获取离线密码
     *
     * @param orgId 分表字段
     * @return
     */
    public ServiceResponse<OffLinePasswordDto> getOffLinePasswordByOrgId(Long orgId);


}
