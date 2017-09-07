package com.xier.sesame.attence.service;

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
import com.xier.sesame.attence.model.OrgSubjectInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppStarter.class)
@Transactional
public class OrgSubjectServiceTest {
	
	public static final Logger logger = LoggerFactory.getLogger(OrgSubjectServiceTest.class);
	
	@Autowired
	private OrgSubjectService orgSubjectService;
	
	@Test
    public void testGetOrgSubject(){
		
		Long orgId = 1565799871892422656L;
		Long subjectId = 1566910601974775808L;
		
		OrgSubjectInfo orgSubjectInfo = orgSubjectService.getOrgSubject(orgId, subjectId);
		Assert.notNull(orgSubjectInfo);
		
		logger.info(JsonUtils.toJson(orgSubjectInfo));
    }

}
