package com.xier.sesame.attence.manager;

import com.xier.sesame.attence.domain.OffLinePassword;
import com.xier.sesame.common.rpc.ServiceResponse;

/**
 * Created by xxu on 2017/4/14.
 */
public interface OffLinePasswordManager {


    /**
     * 增加离线密码
     * @param offLinePassword
     * @return 主键
     */
    public ServiceResponse<Long> addOffLinePassword(OffLinePassword offLinePassword);


    /**
     * 修改离线密码
     * @param offLinePassword
     * @return
     */
    public int updateOffLinePassword(OffLinePassword offLinePassword);

    /**
     * 修改离线密码
     * @param offLinePassword
     * @return
     */
    public int updateOffLinePasswordByOrgId(OffLinePassword offLinePassword);


    /**
     * @param id
     * @param orgId 分表字段
     * @return
     */
    public int removeOffLinePasswordById(Long id, Long orgId);



    /**
     * 根据id获取门禁开关配置
     * @param id
     * @param orgId 分表字段
     * @return
     */
    public OffLinePassword getOffLinePasswordById(Long id, Long orgId);


    public OffLinePassword getOffLinePasswordByOrgId(Long orgId);




}
