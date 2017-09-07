package com.xier.sesame.attence.enums;

import com.xier.sesame.common.exception.ErrorCode;
import com.xier.sesame.common.exception.ErrorLevel;
import com.xier.sesame.common.exception.ErrorType;
import com.xier.sesame.common.exception.V1ErrorCode;

/**
 * 定义机构错误码类型
 * 
 * @author erxiao
 *
 */
public enum AttenceErrorCode implements ErrorCode { 
	
	IMAGE_SAVE_ERROR(new V1ErrorCode(ErrorType.SERVICE, ErrorLevel.WARN, "015", "0001"), "图片保存异常"),
	IMAGE_SAVE_WRONG(new V1ErrorCode(ErrorType.SERVICE, ErrorLevel.WARN, "015", "0002"), "图片保存失败"),

	/** 本主题正在使用中 */
	SUBJECT_EXIST(new V1ErrorCode(ErrorType.SERVICE, ErrorLevel.WARN, "015", "0002"), "此主题已存在"),
	SUBJECT_INUSE(new V1ErrorCode(ErrorType.SERVICE, ErrorLevel.WARN, "015", "0003"), "本主题正在使用中"),
	DEVICE_NOTEXIST(new V1ErrorCode(ErrorType.SERVICE, ErrorLevel.WARN, "015", "0004"), "设备不存在"),
	DEVICE_WRONG_TYPE(new V1ErrorCode(ErrorType.SERVICE, ErrorLevel.WARN, "015", "0005"), "设备类型有误"),
	DEVICE_GROUP_RELATION_NOTEXIST(new V1ErrorCode(ErrorType.SERVICE, ErrorLevel.WARN, "015", "0006"), "设备相关权限组不存在"),
	
	WRONG_IMAGE_FORMAT(new V1ErrorCode(ErrorType.SERVICE, ErrorLevel.WARN, "015", "0007"), "非法图片"),

	;

	/** 错误码，不能为空 */
	private V1ErrorCode errorCode;

	/** 错误信息，一般情况下不能为空 */
	private String errorMessage;

	AttenceErrorCode(V1ErrorCode errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	@Override
	public String getCode() {
		return this.errorCode.getCode();
	}

	/**
	 * @return the errorMessage
	 */
	public String getMessage() {
		return errorMessage;
	}

}
