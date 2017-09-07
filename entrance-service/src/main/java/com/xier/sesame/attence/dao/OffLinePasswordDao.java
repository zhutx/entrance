package com.xier.sesame.attence.dao;

import com.xier.sesame.attence.domain.OffLinePassword;

/**
 * Created by xxu on 2017/4/14.
 */
public interface OffLinePasswordDao{


        /**
         * 增加离线密码
         * @param offLinePassword
         * @return
         */
        public void addOffLinePassword(OffLinePassword offLinePassword);


        /**
         * 修改离线密码
         * @param offLinePassword
         * @return 修改记录条数
         */
        public int updateOffLinePassword(OffLinePassword offLinePassword);


        /**
         * 修改离线密码
         * @param offLinePassword
         * @return 修改记录条数
         */
        public int updateOffLinePasswordByOrgId(OffLinePassword offLinePassword);


        /**
         * 根据ID删除离线密码
         * @param id
         * @param orgId 分表字段
         * @return 删除记录条数
         */
        public int removeOffLinePasswordById(Long id, Long orgId);

        /**
         * 根据id获取门禁开关配置
         * @param id
         * @param orgId 分表字段
         * @return
         */
        public OffLinePassword getOffLinePasswordById(Long id, Long orgId);


        /**
         * 根据id获取门禁开关配置
         * @param orgId
         * @param orgId 分表字段
         * @return
         */
        public OffLinePassword getOffLinePasswordByOrgId(Long orgId);

}
