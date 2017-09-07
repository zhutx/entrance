package com.xier.sesame.attence.manager.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.moredian.fishnet.device.model.DeviceInfo;
import com.moredian.fishnet.device.service.DeviceService;
import com.moredian.fishnet.member.model.MemberInfo;
import com.moredian.fishnet.member.service.DeptMemberRelationService;
import com.moredian.fishnet.member.service.MemberService;
import com.moredian.fishnet.org.enums.YesNoFlag;
import com.moredian.fishnet.org.model.GroupInfo;
import com.moredian.fishnet.org.model.OrgInfo;
import com.moredian.fishnet.org.service.GroupService;
import com.moredian.fishnet.org.service.OrgService;
import com.moredian.fishnet.org.service.PositionService;
import com.xier.sesame.attence.dao.RecognizeRecordDao;
import com.xier.sesame.attence.domain.DeviceRecognizeCountToday;
import com.xier.sesame.attence.domain.RecognizeRecord;
import com.xier.sesame.attence.domain.RecognizeRecordToday;
import com.xier.sesame.attence.domain.RecognizeRecordView;
import com.xier.sesame.attence.dto.SyncRecordDto;
import com.xier.sesame.attence.dto.SyncRecordResultDto;
import com.xier.sesame.attence.enums.OrgUserGroup;
import com.xier.sesame.attence.enums.RecognizeUserType;
import com.xier.sesame.attence.manager.RecognizeRecordManager;
import com.xier.sesame.cloudeye.dto.RecognizeLogDto;
import com.xier.sesame.cloudeye.enums.RecognizeType;
import com.xier.sesame.common.base.domain.query.Pagination;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.common.uploadfile.manager.ImageFileManager;
import com.xier.sesame.common.utils.JsonUtils;
import com.xier.sesame.idgenerator.service.IdgeneratorService;
import com.xier.sesame.rabbit.log.Logger;
import com.xier.sesame.rabbit.log.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 识别记录Manager
 * @author zhutx
 */
@Service
public class RecognizeRecordManagerImpl implements RecognizeRecordManager{
	
	private static Logger logger = LoggerFactory.getLogger(RecognizeRecordManagerImpl.class);

	@Reference
	private IdgeneratorService idgeneratorService;
	
	@Autowired
	private RecognizeRecordDao recognizeRecordDao;
	
	@Reference
	private DeviceService deviceService;
	@Reference
	private OrgService orgService;
	@Reference
	private DeptMemberRelationService deptMemberRelationService;
	@Autowired
	private ImageFileManager imageFileManager;
	@Reference
	private PositionService positionService;
	@Reference
	private GroupService groupService;
	@Reference
	private MemberService memberService;

	public RecognizeRecordManagerImpl() {
	}


	@Override
	public void receiveRecognizeLog(String message) {
		
		RecognizeLogDto logDto = JsonUtils.fromJson(RecognizeLogDto.class, message);
		
		int recognizeType = logDto.getRecognizeType();
		if(!this.isOne2One(recognizeType)) { // 1:n
			RecognizeRecord recognizeRecord = this.buildRecognizeRecord(logDto);
			if(recognizeRecord == null) return;
			this.addRecognizeRecord(recognizeRecord);
		} 

	}

	@Override
	public void receiveRecognizeLogDto(RecognizeLogDto logDto) {
		int recognizeType = logDto.getRecognizeType();
		if (!this.isOne2One(recognizeType)) { // 1:n
			RecognizeRecord recognizeRecord = this.buildRecognizeRecord(logDto);
			if (recognizeRecord == null) return;
			this.addRecognizeRecord(recognizeRecord);
		}
	}

	/**
	 * get the department name according moredian user id.
	 * This method could be changed if more properties will needed from Dingding
	 * @param orgId
	 * @param userId
	 * @return
	 */
	private String getDepartmentFromUser(Long orgId,Long userId){
		StringBuilder department=new StringBuilder("");
//		UserSearchForVisitorRequest searchBean=new UserSearchForVisitorRequest();
//		searchBean.setOrgId(orgId);
//		searchBean.setUserId(userId);

		List<String> deptList=deptMemberRelationService.findDeptName(orgId,userId);
		for(String s:deptList){
			department.append(s).append(",");
		}
//		ServiceResponse<UserForDingResponse> sr=userSearchService.getVisibleDdUser(searchBean);

		if(department.toString().length()>1){
			return department.toString().substring(0,department.toString().length()-1);

		}
		return "";

	}



	private RecognizeRecord buildRecognizeRecord(RecognizeLogDto logDto) {
		RecognizeRecord recognizeRecord = null;
		
		try {
			recognizeRecord = new RecognizeRecord();
			
			OrgInfo org = orgService.getOrgInfo(logDto.getRelationOrgId());
			
			recognizeRecord.setOrgId(org.getOrgId());
			recognizeRecord.setOrgName(org.getOrgName());
			recognizeRecord.setCatchImageUrl(logDto.getImageUrl());
			recognizeRecord.setPersonId(logDto.getPersonId());
			recognizeRecord.setUserId(logDto.getRelationUserId());
			recognizeRecord.setFacePicId(logDto.getTargetFacePicId());
			recognizeRecord.setUniqueNumber(logDto.getDeviceId());
			recognizeRecord.setRecognizeTime(logDto.getRecognizeTime());
			
			GroupInfo group = groupService.getGroupByCode(org.getOrgId(), logDto.getRecognizeSubOrgId());
			
			if (group == null) {
				group = groupService.getGroupInfo(org.getOrgId(), Long.valueOf(logDto.getRecognizeSubOrgId()));
			}
			recognizeRecord.setSubOrgId(group.getGroupId());
			recognizeRecord.setSubOrgCode(group.getGroupCode());

			
			DeviceInfo device = deviceService.getDeviceBySn(logDto.getDeviceId());
			if(device == null) {
				logger.info("\n"+org.getOrgName()+"["+org.getOrgId()+"]记录1:n识别日志失败：未找到设备"+logDto.getDeviceId());
				return null;
			}
			
			recognizeRecord.setDeviceName(device.getDeviceName());
			recognizeRecord.setQuality((double)logDto.getQuality());
			recognizeRecord.setConfidence((double)logDto.getConfidence());
			recognizeRecord.setRecognizeType(logDto.getRecognizeType());
			
			if(RecognizeType.DYNAMIC_VERIFY_PASS.intValue() == logDto.getRecognizeType() || RecognizeType.STATIC_VERIFY.intValue() == logDto.getRecognizeType()){
				
				int relationUserGroup = logDto.getRelationUserGroup();
				if(relationUserGroup == 0 || OrgUserGroup.isOrgUser(relationUserGroup)){ // 成员
					
					recognizeRecord.setRecognizeUserType(RecognizeUserType.MEMBER.getValue());
					
					MemberInfo member = memberService.getMemberInfo(recognizeRecord.getOrgId(), recognizeRecord.getUserId());
					if(member == null) {
						logger.info("\n"+org.getOrgName()+"["+org.getOrgId()+"]记录1:n识别日志失败：未找到组["+relationUserGroup+"]的用户"+recognizeRecord.getUserId());
						return null;
					}
					
					recognizeRecord.setRealName(member.getMemberName());
					recognizeRecord.setMobile(member.getMobile());
					recognizeRecord.setMatchImageUrl(imageFileManager.getRelativePath(member.getVerifyFaceUrl()));
					recognizeRecord.setDepartment(getDepartmentFromUser(org.getOrgId(),recognizeRecord.getUserId()));
					recognizeRecord.setStatus(YesNoFlag.YES.getValue());
					
			    } else if(OrgUserGroup.isVisitor(relationUserGroup)){ // 访客
			    	
			    	recognizeRecord.setRecognizeUserType(RecognizeUserType.VISITOR.getValue());
					recognizeRecord.setRealName(logDto.getUserName());
					recognizeRecord.setMatchImageUrl(logDto.getMatchImageUrl());
					
					recognizeRecord.setStatus(YesNoFlag.YES.getValue());
					
			    } else {
			    	logger.info("/n------无法确定用户组,realtionUserGroup"+relationUserGroup);
			    	return null;
			    }
				
			} else {
				
				recognizeRecord.setRecognizeUserType(RecognizeUserType.STANGER.getValue());
				recognizeRecord.setStatus(YesNoFlag.NO.getValue());
			}
		} catch (Exception e) {
			recognizeRecord = null;
			 e.printStackTrace();
			logger.info("/n----------应用系统记录1比n识别日志失败--------", e);
		}
		
		return recognizeRecord;
	}
	
	/**
	 * 判断是否1:1
	 * @param recognizeType
	 * @return
	 */
	private boolean isOne2One(int recognizeType){
		boolean b = false;
		
		if(RecognizeType.DYNAMIC_MATCH.intValue() == recognizeType) {
			b = true;
		} else if(RecognizeType.DYNAMIC_NOTMATCH.intValue() == recognizeType) {
			b = true;
		} else if(RecognizeType.STATIC_MATCH.intValue() == recognizeType) {
			b = true;
		} else if(RecognizeType.STATIC_NOTMATCH.intValue() == recognizeType) {
			b = true;
		}
		return b;
	}
	
	@Override
	public ServiceResponse<Long> addRecognizeRecord(RecognizeRecord recognizeRecord) {
		
		ServiceResponse<Long> serviceResponse = idgeneratorService.getNextIdByTypeName("com.xier.sesame.attence.RecognizeRecord");
		if (serviceResponse.isSuccess()) {
			recognizeRecord.setRecognizeRecordId(serviceResponse.getData());
			recognizeRecordDao.addRecognizeRecord(recognizeRecord);
		}
		return serviceResponse;
	}
	
	@Override
	public int updateRecognizeRecord(RecognizeRecord recognizeRecord) {
		return recognizeRecordDao.updateRecognizeRecord(recognizeRecord);
	}
	
	@Override
	public int updateRecognizeRecordSelective(RecognizeRecord recognizeRecord) {
		return recognizeRecordDao.updateRecognizeRecordSelective(recognizeRecord);
	}
	
	@Override
	public int removeRecognizeRecordById(Long id, Long orgId) {
		return recognizeRecordDao.removeRecognizeRecordById(id, orgId);
	}
	
	@Override
	public RecognizeRecord getRecognizeRecordById(Long id, Long orgId) {
		return recognizeRecordDao.getRecognizeRecordById(id, orgId);
	}
	  
	@Override
	public Pagination<RecognizeRecord> getPaginationRecognizeRecord(Pagination<RecognizeRecord> pagination,RecognizeRecord recognizeRecord) {
		return recognizeRecordDao.getPaginationRecognizeRecord(pagination, recognizeRecord);
	}
	
	@Override
	public Pagination<RecognizeRecordView> getPaginationRecognizeRecordView(Pagination<RecognizeRecordView> pagination,
			RecognizeRecordView recognizeRecordView) {
		return recognizeRecordDao.getPaginationRecognizeRecordView(pagination, recognizeRecordView);
	}

	@Override
	public Pagination<RecognizeRecordView> getH5CompanyPaginationRecognizeRecordView(Pagination<RecognizeRecordView> pagination,
																			RecognizeRecordView recognizeRecordView) {
		return recognizeRecordDao.getH5CompanyPaginationRecognizeRecordView(pagination, recognizeRecordView);
	}

	@Override
	public int getRecognizeRecordCount(RecognizeRecord recognizeRecord) {
		return recognizeRecordDao.getRecognizeRecordCount(recognizeRecord);
	}

	@Override
	public List<RecognizeRecordToday> getRecognizeRecordToday(Long orgId) {
		return recognizeRecordDao.getRecognizeRecordToday(orgId);
	}
	
	@Override
	public List<DeviceRecognizeCountToday> getDeviceRecognizeCountToday(Long orgId, Integer recognizeUserType) {
		return recognizeRecordDao.getDeviceRecognizeCountToday(orgId, recognizeUserType);
	}

	@Override
	public List<SyncRecordResultDto> getSyncRecognizeRecord(SyncRecordDto syncRecordDto) {
		return recognizeRecordDao.getSyncRecognizeRecord(syncRecordDto);
	}

}
