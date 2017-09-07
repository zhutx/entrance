package com.xier.sesame.attence.dao.mybatis;

import com.xier.sesame.attence.dao.NewSwitchSettingDao;
import com.xier.sesame.attence.domain.NewSwitchSetting;
import com.xier.sesame.common.base.dao.BaseDaoMybatis;
import com.xier.sesame.common.base.domain.query.Pagination;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xxu on 2017/3/31.
 */
@Repository
public class NewSwitchSettingDaoMybatis extends BaseDaoMybatis implements NewSwitchSettingDao {



    @Override
    public void addSwitchSetting(NewSwitchSetting switchSetting) {
        this.getSqlSession().insert("com.xier.sesame.attence.domain.NewSwitchSetting.addSwitchSetting", switchSetting);
    }

    @Override
    public int updateSwitchSetting(NewSwitchSetting switchSetting) {
        return this.getSqlSession().update("com.xier.sesame.attence.domain.NewSwitchSetting.updateSwitchSetting", switchSetting);
    }

    @Override
    public int updateSwitchSettingByGroupIdAndOrgId(NewSwitchSetting switchSetting) {
        return this.getSqlSession().update("com.xier.sesame.attence.domain.NewSwitchSetting.updateSwitchSettingByGroupIdAndOrgId", switchSetting);

    }

    @Override
    public int updateSwitchSettingSelective(NewSwitchSetting switchSetting) {
        return this.getSqlSession().update("com.xier.sesame.attence.domain.NewSwitchSetting.updateSwitchSettingSelective", switchSetting);
    }

    @Override
    public int removeSwitchSettingById(Long id, Long orgId,Long groupId) {
        NewSwitchSetting switchSetting = new NewSwitchSetting();
        switchSetting.setSwitchSettingId(id);
        switchSetting.setOrgId(orgId);
        switchSetting.setGroupId(groupId);
        return this.getSqlSession().delete("com.xier.sesame.attence.domain.NewSwitchSetting.removeSwitchSettingById", switchSetting);
    }

    @Override
    public int removeSwitchSettingByOrgIdAndGroupId(Long orgId, Long groupId) {
        NewSwitchSetting switchSetting = new NewSwitchSetting();
        switchSetting.setOrgId(orgId);
        switchSetting.setGroupId(groupId);
        return this.getSqlSession().delete("com.xier.sesame.attence.domain.NewSwitchSetting.removeSwitchSettingByOrgIdAndGroupId", switchSetting);

    }

    @Override
    public NewSwitchSetting getSwitchSettingById(Long id, Long orgId,Long groupId) {
        NewSwitchSetting switchSetting = new NewSwitchSetting();
        switchSetting.setSwitchSettingId(id);
        switchSetting.setOrgId(orgId);
        switchSetting.setGroupId(groupId);
        return (NewSwitchSetting)this.getSqlSession().selectOne("com.xier.sesame.attence.domain.NewSwitchSetting.getSwitchSettingById", switchSetting);
    }

    @Override
    public NewSwitchSetting getSwitchSettingByIdForUpdate(Long id, Long orgId,Long groupId) {
        NewSwitchSetting switchSetting = new NewSwitchSetting();
        switchSetting.setSwitchSettingId(id);
        switchSetting.setOrgId(orgId);
        switchSetting.setGroupId(groupId);
        return (NewSwitchSetting)this.getSqlSession().selectOne("com.xier.sesame.attence.domain.NewSwitchSetting.getSwitchSettingByIdForUpdate", switchSetting);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pagination<NewSwitchSetting> getPaginationSwitchSetting(Pagination<NewSwitchSetting> pagination, NewSwitchSetting switchSetting) {
        return this.getPagination(pagination,switchSetting, "com.xier.sesame.attence.domain.NewSwitchSetting.getSwitchSettingCount", "com.xier.sesame.attence.domain.NewSwitchSetting.getPaginationSwitchSetting");
    }

    @Override
    public int getSwitchSettingCount(NewSwitchSetting switchSetting) {
        return this.getSqlSession().selectOne("com.xier.sesame.attence.domain.NewSwitchSetting.getSwitchSettingCount", switchSetting);
    }

    @Override
    public NewSwitchSetting getSwitchSettingByOrgIdAndGroupId(Long orgId,Long groupId) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("orgId", orgId);
        parameter.put("groupId", groupId);
        return this.getSqlSession().selectOne("com.xier.sesame.attence.domain.NewSwitchSetting.getSwitchSettingByOrgId", parameter);
    }

    @Override
    public List<NewSwitchSetting> getSwitchSettingListByOrgId(Long orgId){
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("orgId", orgId);
        return this.getSqlSession().selectList("com.xier.sesame.attence.domain.NewSwitchSetting.getSwitchSettingListByOrgId", parameter);


    }


    @Override
    public List<NewSwitchSetting> getDeviceBindingSwitchSettings(Long orgId, List<Long> groupIdList) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("orgId", orgId);
        parameter.put("groupIdList",groupIdList);
        return this.getSqlSession().selectList("com.xier.sesame.attence.domain.NewSwitchSetting.getDeviceBindingSwitchSettings", parameter);

    }
}

