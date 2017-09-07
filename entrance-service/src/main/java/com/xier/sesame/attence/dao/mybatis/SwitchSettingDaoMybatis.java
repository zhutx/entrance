package com.xier.sesame.attence.dao.mybatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xier.sesame.attence.dao.SwitchSettingDao;
import com.xier.sesame.attence.domain.SwitchSetting;
import com.xier.sesame.common.base.dao.BaseDaoMybatis;
import com.xier.sesame.common.base.domain.query.Pagination;

/**
 * 门禁开关配置Dao
 * @author zhutx
 *
 */
@Repository
public class SwitchSettingDaoMybatis extends BaseDaoMybatis implements SwitchSettingDao {
	
	@Override
	public void addSwitchSetting(SwitchSetting switchSetting) {
		this.getSqlSession().insert("com.xier.sesame.attence.SwitchSetting.addSwitchSetting", switchSetting);
	}
		
	@Override
	public int updateSwitchSetting(SwitchSetting switchSetting) {
		return this.getSqlSession().update("com.xier.sesame.attence.SwitchSetting.updateSwitchSetting", switchSetting);
	}
	
	@Override
	public int updateSwitchSettingSelective(SwitchSetting switchSetting) {
		return this.getSqlSession().update("com.xier.sesame.attence.SwitchSetting.updateSwitchSettingSelective", switchSetting);
	}
	
	@Override
	public int removeSwitchSettingById(Long id, Long orgId) {
		SwitchSetting switchSetting = new SwitchSetting();
		switchSetting.setSwitchSettingId(id);
		switchSetting.setOrgId(orgId);
		return this.getSqlSession().delete("com.xier.sesame.attence.SwitchSetting.removeSwitchSettingById", switchSetting);
	}
	
	@Override
	public SwitchSetting getSwitchSettingById(Long id, Long orgId) {
		SwitchSetting switchSetting = new SwitchSetting();
		switchSetting.setSwitchSettingId(id);
		switchSetting.setOrgId(orgId);
		return (SwitchSetting)this.getSqlSession().selectOne("com.xier.sesame.attence.SwitchSetting.getSwitchSettingById", switchSetting);
	}
	
	@Override
	public SwitchSetting getSwitchSettingByIdForUpdate(Long id, Long orgId) {
		SwitchSetting switchSetting = new SwitchSetting();
		switchSetting.setSwitchSettingId(id);
		switchSetting.setOrgId(orgId);
		return (SwitchSetting)this.getSqlSession().selectOne("com.xier.sesame.attence.SwitchSetting.getSwitchSettingByIdForUpdate", switchSetting);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pagination<SwitchSetting> getPaginationSwitchSetting(Pagination<SwitchSetting> pagination,SwitchSetting switchSetting) {
		return this.getPagination(pagination,switchSetting, "com.xier.sesame.attence.SwitchSetting.getSwitchSettingCount", "com.xier.sesame.attence.SwitchSetting.getPaginationSwitchSetting");
	}	
	
	@Override
	public int getSwitchSettingCount(SwitchSetting switchSetting) {
		return this.getSqlSession().selectOne("com.xier.sesame.attence.SwitchSetting.getSwitchSettingCount", switchSetting);
	}

	@Override
	public SwitchSetting getSwitchSettingByOrgId(Long orgId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("orgId", orgId);
		return this.getSqlSession().selectOne("com.xier.sesame.attence.SwitchSetting.getSwitchSettingByOrgId", parameter);
	}
	



}
