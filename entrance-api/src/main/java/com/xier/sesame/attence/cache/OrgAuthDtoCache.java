package com.xier.sesame.attence.cache;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xier.sesame.attence.dto.OrgAuthDto;
import com.xier.sesame.attence.service.OrgAuthService;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.common.utils.JsonUtils;
import com.xier.sesame.rabbit.log.Logger;
import com.xier.sesame.rabbit.log.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 机构授权信息缓存
 */
public class OrgAuthDtoCache {
	
	public static final Logger logger = LoggerFactory.getLogger(OrgAuthDtoCache.class);
	
	public static final ScheduledExecutorService CACHE_SERVICE = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
		@Override
		public Thread newThread(Runnable r) {
			 return new Thread(r, "cache-refresh-ScheduledThread");
		}
	});
	
	private static final ConcurrentMap<Long/** orgId */, OrgAuthDto> cache = new ConcurrentSkipListMap<Long, OrgAuthDto>();
	
	@Reference
	private OrgAuthService orgAuthService;
	
	public void init(){
		initUpdateTiming();
	}
	
	public OrgAuthDto get(Long orgId) throws RuntimeException {
		if(orgId == null) return null;
		OrgAuthDto dto = cache.get(orgId);
		if(dto != null){
			return dto;
		}
		ServiceResponse<OrgAuthDto> sr = orgAuthService.queryOrgAuthByOrgId(orgId);
		if(!sr.isSuccess()){
			String msg = String.format("orgAuthService.queryOrgAuthByOrgId error, errorMsg=%s, param=%s", 
					JsonUtils.toJson(sr), JsonUtils.toJson(orgId));
			logger.error(msg);
			throw new RuntimeException(msg);
		}
		dto = sr.getData();
		/**
		 * 数据库中不存在该机构授权信息，则该机构无需同步考勤数据
		 * 放入空属性的对象，防止一直走DB
		 */
		if(dto == null){
			OrgAuthDto newDto = new OrgAuthDto();
			newDto.setOrgId(orgId);
			dto = newDto;
		}
		cache.put(orgId, dto);
		return dto;
	}

	public Collection<OrgAuthDto> getAll() {
		Collection<OrgAuthDto> dtos = cache.values();
		if(CollectionUtils.isEmpty(dtos)){
			loadAll();
		}
		return dtos;
	}
	
	private void initUpdateTiming() {

		CACHE_SERVICE.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					loadAll();
				} catch (Throwable e) {
					logger.error("initUpdateTiming, error", e);
				}
			}
		}, 60, 60, TimeUnit.SECONDS);
	}
	
	private void loadAll(){
		if(orgAuthService == null) return;
		ServiceResponse<List<OrgAuthDto>> sr = orgAuthService.queryUsableList();
		if(!sr.isSuccess()){
			String msg = String.format("orgAuthService.queryUsableList error, errorMsg=%s", 
					JsonUtils.toJson(sr));
			logger.error(msg);
			throw new RuntimeException(msg);
		}
		List<OrgAuthDto> list = sr.getData();
		if(CollectionUtils.isEmpty(list)) return;
		Map<Long, OrgAuthDto> map = new HashMap<Long, OrgAuthDto>();
		for (OrgAuthDto dto : list) {
			if(null != dto){
				map.put(dto.getOrgId(), dto);
			}
		}
		synchronized (this) {
			cache.clear();
			cache.putAll(map);
		}
	}

}
