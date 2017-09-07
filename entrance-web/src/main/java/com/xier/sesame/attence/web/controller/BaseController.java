package com.xier.sesame.attence.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.xier.sesame.common.exception.SesameRuntimeException;
import com.xier.sesame.common.rpc.ServiceResponse;
import com.xier.sesame.common.rpc.dto.PaginationDto;
import com.xier.sesame.common.utils.JsonUtils;
import com.xier.sesame.common.web.BaseResponse;

@Controller
abstract public class BaseController {
	protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@ExceptionHandler()
	public String handleException(Exception exception, HttpServletRequest request, HttpServletResponse response) {
		logger.error(exception.getMessage(), exception);

		String result = "1";
		String message = null;
		if(exception instanceof CannotGetJdbcConnectionException) {
			message = "不能获取数据库连接，请联系管理员！";
		}else if(exception instanceof SesameRuntimeException){
			SesameRuntimeException sesameRuntimeException = (SesameRuntimeException)exception;
			message = sesameRuntimeException.getErrorContext().getMessage();
		}else{
			message = "未知错误";
		}
		
		try {
			PrintWriter writer = response.getWriter();
			BaseResponse baseResponse = new BaseResponse(result, message);
			writer.write(JsonUtils.toJson(baseResponse));
			writer.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
				
	}
	
	public <T> void modelAddPageData(ModelMap modelMap, PaginationDto<T> paginationDto) {
		
		modelAddPageData(modelMap, paginationDto, "dataList");
	}
	
	public <T> void modelAddPageData(ModelMap modelMap, PaginationDto<T> paginationDto, String propertyName) {
        if(CollectionUtils.isNotEmpty(paginationDto.getData())){
            modelMap.addAttribute(propertyName, paginationDto.getData());
            modelMap.addAttribute("totalPages", paginationDto.getTotalPage());
            modelMap.addAttribute("currentPage", paginationDto.getPageNo());
        }else{
            modelMap.addAttribute(propertyName, paginationDto.getData());
            modelMap.addAttribute("totalPages",1);
            modelMap.addAttribute("currentPage", paginationDto.getPageNo());
        }
		
	}
	
	public <T> void modelAddPageData(ModelMap modelMap, ServiceResponse<PaginationDto<T>> serviceResponse, String propertyName) {
        
		if (serviceResponse.isSuccess()) {
			PaginationDto<T> paginationDto = serviceResponse.getData();
			modelAddPageData(modelMap, paginationDto, propertyName);
		}
		else {
			throw new SesameRuntimeException(serviceResponse.getErrorContext());
		}
		
	}
	
	public <T> void modelAddPageData(ModelMap modelMap, ServiceResponse<PaginationDto<T>> serviceResponse) {        
		modelAddPageData(modelMap, serviceResponse, "dataList");
	}
	
}
