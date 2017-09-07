package com.xier.sesame.attence.service;

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

import com.xier.sesame.attence.AppStarter;
import com.xier.sesame.attence.model.SubjectInfo;
import com.xier.sesame.common.utils.JsonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppStarter.class)
@Transactional
public class SubjectServiceTest {
	
	public static final Logger logger = LoggerFactory.getLogger(SubjectServiceTest.class);
	
	@Autowired
	private SubjectService subjectService;
	
	@Test
    public void testFindSubject() {
		List<SubjectInfo> subjects = subjectService.findSubject();
		Assert.notEmpty(subjects);
		
		logger.info(JsonUtils.toJson(subjects));
    }
	
	@Test
    public void testGetSubject() {
		Long subjectId = 1566910601974775808L;
		SubjectInfo subject = subjectService.getSubject(subjectId);
		Assert.notNull(subject);
		
		logger.info(JsonUtils.toJson(subject));
    }

}
