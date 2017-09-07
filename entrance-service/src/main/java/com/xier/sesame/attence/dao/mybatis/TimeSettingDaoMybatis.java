package com.xier.sesame.attence.dao.mybatis;

import com.xier.sesame.attence.dao.TimeSettingDao;
import com.xier.sesame.attence.domain.TimeSetting;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.base.dao.BaseDaoMybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * 门禁日历配置Dao
 * @author zhutx
 *
 */
@Repository
public class TimeSettingDaoMybatis extends BaseDaoMybatis implements TimeSettingDao {
	
	@Override
	public void addTimeSetting(TimeSetting timeSetting) {
		this.getSqlSession().insert("com.xier.sesame.attence.TimeSetting.addTimeSetting", timeSetting);
	}
		
	@Override
	public int updateTimeSetting(TimeSetting timeSetting) {
		return this.getSqlSession().update("com.xier.sesame.attence.TimeSetting.updateTimeSetting", timeSetting);
	}
	
	@Override
	public int updateTimeSettingSelective(TimeSetting timeSetting) {
		return this.getSqlSession().update("com.xier.sesame.attence.TimeSetting.updateTimeSettingSelective", timeSetting);
	}
	
	@Override
	public int removeTimeSettingById(Long id, Long orgId) {
		TimeSetting timeSetting = new TimeSetting();
		timeSetting.setTimeSettingId(id);
		timeSetting.setOrgId(orgId);
		return this.getSqlSession().delete("com.xier.sesame.attence.TimeSetting.removeTimeSettingById", timeSetting);
	}
	
	@Override
	public TimeSetting getTimeSettingById(Long id, Long orgId) {
		TimeSetting timeSetting = new TimeSetting();
		timeSetting.setTimeSettingId(id);
		timeSetting.setOrgId(orgId);
		return (TimeSetting)this.getSqlSession().selectOne("com.xier.sesame.attence.TimeSetting.getTimeSettingById", timeSetting);
	}
	
	@Override
	public TimeSetting getTimeSettingByIdForUpdate(Long id, Long orgId) {
		TimeSetting timeSetting = new TimeSetting();
		timeSetting.setTimeSettingId(id);
		timeSetting.setOrgId(orgId);
		return (TimeSetting)this.getSqlSession().selectOne("com.xier.sesame.attence.TimeSetting.getTimeSettingByIdForUpdate", timeSetting);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pagination<TimeSetting> getPaginationTimeSetting(Pagination<TimeSetting> pagination,TimeSetting timeSetting) {
		return this.getPagination(pagination,timeSetting, "com.xier.sesame.attence.TimeSetting.getTimeSettingCount", "com.xier.sesame.attence.TimeSetting.getPaginationTimeSetting");
	}	
	
	@Override
	public int getTimeSettingCount(TimeSetting timeSetting) {
		return this.getSqlSession().selectOne("com.xier.sesame.attence.TimeSetting.getTimeSettingCount", timeSetting);
	}

	@Override
	public List<TimeSetting> getTimeSetting(TimeSetting timeSetting) {
		return this.getSqlSession().selectList("com.xier.sesame.attence.TimeSetting.getTimeSetting", timeSetting);
	}

	@Override
	public TimeSetting getOneTimeSetting(TimeSetting timeSetting) {
		return this.getSqlSession().selectOne("com.xier.sesame.attence.TimeSetting.getTimeSetting", timeSetting);
	}
	



}
