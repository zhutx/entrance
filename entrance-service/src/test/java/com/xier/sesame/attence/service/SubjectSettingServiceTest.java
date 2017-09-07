package com.xier.sesame.attence.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.moredian.bee.common.utils.JsonUtils;
import com.xier.sesame.attence.AppStarter;
import com.xier.sesame.attence.request.SubjectConfigRequest;
import com.xier.sesame.common.rpc.ServiceResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppStarter.class)
@Transactional
public class SubjectSettingServiceTest {
	
	public static final Logger logger = LoggerFactory.getLogger(SubjectSettingServiceTest.class);
	
	@Autowired
	private SubjectConfigService subjectSettingService;
	
	@Test
    public void testFindDeviceIdBySubject() {
        
		Long orgId = 1565799871892422656L;
		Long subjectId = 1566910601974775808L;
		List<Long> deviceIds = subjectSettingService.findDeviceIdBySubject(orgId, subjectId);
		Assert.notEmpty(deviceIds);
		logger.info(JsonUtils.toJson(deviceIds));
    }
	
	@Test
	public void testSubjectConfig() {
		SubjectConfigRequest request = new SubjectConfigRequest();
		request.setOrgId(1565799871892422656L);
		request.setSubjectId(1566910601974775808L);
		request.setLogoImg(null);
		request.setBgImg(null);
		request.setHoldSeconds(5);
		List<Long> devices = new ArrayList<>();
		request.setDevices(devices);
		ServiceResponse<Boolean> sr = subjectSettingService.subjectConfig(request);
		Assert.isTrue(sr.isSuccess());
	}

}
