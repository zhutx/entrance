package com.xier.sesame.attence.web.controller;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xier.attendflysync.service.DataSyncService;
import com.xier.sesame.attence.dto.AddOrgAuthDto;
import com.xier.sesame.attence.dto.OrgAuthDto;
import com.xier.sesame.attence.service.OrgAuthService;
import com.xier.sesame.attence.util.DozerMapperUtil;
import com.xier.sesame.attence.web.controller.req.QueryAuthByOrgIdModel;
import com.xier.sesame.attence.web.controller.req.SyncUserModel;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.common.utils.JsonUtils;
import com.xier.sesame.common.web.BaseDataResponse;
import com.xier.sesame.common.web.BaseResponse;

@Controller
public class OrgAuthController extends BaseController{
	
	@Reference
	private OrgAuthService orgAuthService;
	@Reference
	private DataSyncService dataSyncService;

	/**
     * 机构注册授权列表信息
     */
    @RequestMapping(value = "/orgAuth/list")
    @ResponseBody
    public BaseResponse list() {
    	BaseDataResponse<List<OrgAuthDto>> br = new BaseDataResponse<List<OrgAuthDto>>();
    	try {
    		ServiceResponse<List<OrgAuthDto>> result = orgAuthService.queryAllList();
			if (!result.isSuccess()) {
				logger.error("授权信息查询失败：" + JsonUtils.toJson(result));
				br.setMessage(result.getErrorContext().getMessage());
				br.setResult("1");
				return br;
			}
			br.setData(result.getData());
		} catch (Throwable e) {
			logger.error("OrgAuthController.list error", e);
			br.setMessage("查询机构注册授权列表信息失败");
			br.setResult("1");
		}
        return br;
    }
    
    /**
     * 更新机构注册授权信息
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/orgAuth/update")
    @ResponseBody
    public BaseResponse update(@RequestBody OrgAuthDto orgAuthDto) {
    	logger.info("update reqestParam = " + JsonUtils.toJson(orgAuthDto));
    	BaseResponse br = new BaseResponse();
    	try {
    		if(orgAuthDto == null){
    			return br;
    		}
    		ServiceResponse result = null;
    		if(orgAuthDto.getAuthId() != null){
    			result = orgAuthService.updateOrgAuth(orgAuthDto);
    		}else{
    			result = orgAuthService.addOrgAuth(DozerMapperUtil.getMapper().map(orgAuthDto, AddOrgAuthDto.class));
    		}
			if (!result.isSuccess()) {
				logger.error("授权信息更新失败：" + JsonUtils.toJson(result));
				br.setMessage(result.getErrorContext().getMessage());
				br.setResult("1");
				return br;
			}
		} catch (Throwable e) {
			logger.error("OrgAuthController.update error", e);
			br.setMessage("更新机构注册授权信息失败");
			br.setResult("1");
		}
        return br;
    }
    
    /**
     * 查询机构授权信息
     */
	@RequestMapping(value = "/orgAuth/queryByOrgId")
    @ResponseBody
    public BaseResponse queryByOrgId(@RequestBody QueryAuthByOrgIdModel req) {
    	logger.info("queryByOrgId reqestParam = " + JsonUtils.toJson(req));
        BaseDataResponse<OrgAuthDto> br = new BaseDataResponse<OrgAuthDto>();
        try {
        	ServiceResponse<OrgAuthDto> result = orgAuthService.queryOrgAuthByOrgId(req.getOrgId());
			if (!result.isSuccess()) {
				logger.error("授权信息查询失败：" + JsonUtils.toJson(result));
				br.setMessage(result.getErrorContext().getMessage());
				br.setResult("1");
				return br;
			}
			br.setData(result.getData());
		} catch (Exception e) {
			logger.error("OrgAuthController.queryByOrgId error, param=" + JsonUtils.toJson(req), e);
			br.setMessage("根据orgId获取机构授权信息失败");
			br.setResult("1");
		}
        return br;
    }
    
    /**
     * 手动触发同步机构下用户信息
     */
    @RequestMapping(value = "/orgAuth/syncAllUser")
    @ResponseBody
    public BaseResponse syncAllUser(@RequestBody SyncUserModel syncUserModel) {
    	logger.info("syncAllUser reqestParam = " + JsonUtils.toJson(syncUserModel));
    	BaseResponse br = new BaseResponse();
    	try {
			ServiceResponse<Boolean> result = dataSyncService.startSyncAllUserInfo(syncUserModel.getOrgId());
			if (!result.isSuccess()) {
				logger.error("调用同步机构用户接口失败：" + JsonUtils.toJson(result));
				br.setMessage(result.getErrorContext().getMessage());
				br.setResult("1");
				return br;
			}
		} catch (Throwable e) {
			logger.error("OrgAuthController.syncAllUser error", e);
			br.setMessage("手动触发同步机构下用户信息");
			br.setResult("1");
		}
        return br;
    }
    
    public static void main(String[] args) {
    	OrgAuthDto orgAuthDto = new OrgAuthDto();
    	orgAuthDto.setOrgId(1548494959005401088L);
    	orgAuthDto.setPlatformCode("FLY");
    	orgAuthDto.setStatus(1);
    	//orgAuthDto.setAuthId(authId);
    	orgAuthDto.setAccessId("HJ22nHFp");
    	orgAuthDto.setAccessKey("D5FB50A1745E416E8C74614C1A0ED103");
    	
    	System.out.println(JsonUtils.toJson(orgAuthDto));
    	
            int count = 1;  
            DecimalFormat countFormat=new DecimalFormat("0000000000");  
            System.out.println(countFormat.format(999));  
	}
	
}
