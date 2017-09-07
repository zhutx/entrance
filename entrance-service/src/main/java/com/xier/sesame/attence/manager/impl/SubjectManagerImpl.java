package com.xier.sesame.attence.manager.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Reference;
import com.moredian.bee.common.exception.BizAssert;
import com.moredian.bee.common.utils.BeanUtils;
import com.moredian.bee.common.utils.RandomUtil;
import com.xier.sesame.attence.dao.SubjectDao;
import com.xier.sesame.attence.domain.Subject;
import com.xier.sesame.attence.enums.AttenceErrorCode;
import com.xier.sesame.attence.enums.YesNoFlag;
import com.xier.sesame.attence.manager.SubjectManager;
import com.xier.sesame.attence.request.SubjectAddRequest;
import com.xier.sesame.common.uploadfile.enums.FilePathType;
import com.xier.sesame.common.uploadfile.manager.ImageFileManager;
import com.xier.sesame.common.utils.ExceptionUtils;
import com.xier.sesame.idgenerator.service.IdgeneratorService;

@Service
public class SubjectManagerImpl implements SubjectManager{

	@Reference
	private IdgeneratorService idgeneratorService;
	@Autowired
	private SubjectDao subjectDao;
	@Autowired
	private ImageFileManager imageFileManager;
	
	private Long genSubjectId() {
		return idgeneratorService.getNextIdByTypeName("com.xier.sesame.attence.Subject").getData();
	}
	
	private String storeImage(byte[] image, FilePathType filePathType) {
		
		Map<String, Object> map = null;
		String fileName = RandomUtil.getUUID() + ".jpg";
		try {
			map = imageFileManager.saveImage(image, filePathType, fileName);
		} catch (Exception e) {
			ExceptionUtils.throwException(AttenceErrorCode.IMAGE_SAVE_ERROR, AttenceErrorCode.IMAGE_SAVE_ERROR.getMessage());
		}
		if(!"0".equals(map.get("result"))) {
			ExceptionUtils.throwException(AttenceErrorCode.IMAGE_SAVE_WRONG, AttenceErrorCode.IMAGE_SAVE_WRONG.getMessage());
		}
		
		return (String)map.get("url");
		
	}
	
	private Subject buildSubject(SubjectAddRequest request) {
		Subject subject = BeanUtils.copyProperties(Subject.class, request);
		subject.setSubjectId(this.genSubjectId());
		
		byte[] showImage = Base64.decodeBase64(request.getShowImg());
		byte[] bgImage = Base64.decodeBase64(request.getBgImg());
		byte[] logoImage = Base64.decodeBase64(request.getLogoImg());
		
    	subject.setShowImg(this.storeImage(showImage, FilePathType.TYPE_SUBJECT));
    	subject.setBgImg(this.storeImage(bgImage, FilePathType.TYPE_SUBJECT));
    	subject.setShowImg(this.storeImage(logoImage, FilePathType.TYPE_SUBJECT));
		
		return subject;
	}
	
	synchronized private static boolean isImageFromBase64String(String base64String) {  
        boolean flag = false;  
        try {  
            BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(Base64.decodeBase64(base64String)));  
            if (null == bufImg) {  
                return flag;  
            }  
            flag = true;  
        } catch (Exception e) {  
            return flag;
        }  
        return flag;  
    } 
	
	@Override
	@Transactional
	public Long addSubject(SubjectAddRequest request) {
		BizAssert.notBlank(request.getSubjectCode());
		BizAssert.notBlank(request.getSubjectName());
		BizAssert.notNull(request.getDefaultFlag());
		BizAssert.notBlank(request.getShowImg());
		BizAssert.notBlank(request.getBgImg());
		BizAssert.notBlank(request.getLogoImg());
		BizAssert.notNull(request.getHoldSeconds());
		BizAssert.isTrue(isImageFromBase64String(request.getShowImg()));
		BizAssert.isTrue(isImageFromBase64String(request.getBgImg()));
		BizAssert.isTrue(isImageFromBase64String(request.getLogoImg()));
		
		Subject existSubject = subjectDao.getExistOneSubject(request.getSubjectCode(), request.getSubjectName());
		if(existSubject != null) ExceptionUtils.throwException(AttenceErrorCode.SUBJECT_EXIST, AttenceErrorCode.SUBJECT_EXIST.getMessage());
		
		if(YesNoFlag.YES.getValue() == request.getDefaultFlag()) {
			subjectDao.clearDefault();
		}
		
		Subject subject = this.buildSubject(request);
		subjectDao.addSubject(subject);
		
		return subject.getSubjectId();
	}
	
	@Override
	public List<Subject> findSubject() {
		return subjectDao.findAll();
	}
	
	@Override
	public Subject getSubjectById(Long id) {
		return subjectDao.getSubjectById(id);
	}
	
	@Override
	public Subject getDefaultSubject() {
		return subjectDao.getDefaultSubject();
	}

}
