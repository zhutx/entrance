package com.xier.sesame.attence.dao.mybatis;

import com.xier.sesame.attence.dao.OffLinePasswordDao;
import com.xier.sesame.attence.domain.OffLinePassword;
import com.xier.sesame.common.base.dao.BaseDaoMybatis;
import org.springframework.stereotype.Repository;

/**
 * Created by xxu on 2017/4/14.
 */
@Repository
public class OffLinePasswordMybatis extends BaseDaoMybatis implements OffLinePasswordDao {
    @Override
    public void addOffLinePassword(OffLinePassword offLinePassword) {
        this.getSqlSession().insert("com.xier.sesame.attence.domain.OffLinePassword.addOffLinePassword", offLinePassword);

    }

    @Override
    public int updateOffLinePassword(OffLinePassword offLinePassword) {
        return this.getSqlSession().update("com.xier.sesame.attence.domain.OffLinePassword.updateOffLinePassword", offLinePassword);

    }

    @Override
    public int updateOffLinePasswordByOrgId(OffLinePassword offLinePassword) {
        return this.getSqlSession().update("com.xier.sesame.attence.domain.OffLinePassword.updateOffLinePasswordByOrgId", offLinePassword);

    }

    @Override
    public int removeOffLinePasswordById(Long id, Long orgId) {
        OffLinePassword offLinePassword = new OffLinePassword();
        offLinePassword.setOrgId(orgId);
        offLinePassword.setOffLinePasswordId(id);
        return this.getSqlSession().delete("com.xier.sesame.attence.domain.OffLinePassword.removeOffLinePasswordById", offLinePassword);
    }

    @Override
    public OffLinePassword getOffLinePasswordById(Long id, Long orgId) {
        OffLinePassword offLinePassword = new OffLinePassword();
        offLinePassword.setOrgId(orgId);
        offLinePassword.setOffLinePasswordId(id);
        return this.getSqlSession().selectOne("com.xier.sesame.attence.domain.OffLinePassword.getOffLinePasswordById", offLinePassword);

    }

    @Override
    public OffLinePassword getOffLinePasswordByOrgId(Long orgId) {
        OffLinePassword offLinePassword = new OffLinePassword();
        offLinePassword.setOrgId(orgId);
        return this.getSqlSession().selectOne("com.xier.sesame.attence.domain.OffLinePassword.getOffLinePasswordByOrgId", offLinePassword);

    }
}
