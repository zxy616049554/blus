package com.rk.exception;

import java.io.Serializable;

import com.github.pagehelper.util.StringUtil;
import com.rk.enums.ENUM_EXCEPTION_TYPE;



/**
 * 基本异常处理
 * 
 * @author wanglf
 * @date 2019年1月18日 上午9:10:30
 * @version V1.0
 */
public class BaseException extends RuntimeException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4038660098003807161L;

	/**
	 * 错误码
	 */
	private String errCode;

	/**
	 * 错误消息
	 */
	private String errMsg;

	/**
	 * 错误码对应的参数
	 */
	private Object[] args;

	public BaseException(Throwable cause) {
		super(cause);
	}

	/**
	 * 支持传入code,message和动态参数
	 * */
	public BaseException(String errCode, String errMsg, Object[] args) {
		this.errCode = errCode;
		String message = null;
		if (!StringUtil.isEmpty(errCode)) {
			message = getMessage(errCode, args);
		}
		if (message == null) {
			message = errMsg;
		}
		this.errMsg = message;
	}

	/**
	 * 支持传入code和动态参数，根据code从统一异常枚举获取message
	 * */
	public BaseException(String errCode, Object[] args) {
		this(errCode, null, args);
	}

	/**
	 * 支持传入code和errMsg
	 * */
	public BaseException(String errCode, String errMsg) {
		super(errMsg);
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	/**
	 * 支持传入异常枚举和动态参数
	 * */
	public BaseException(ENUM_EXCEPTION_TYPE statusEnum, Object[] args) {
		this(statusEnum.getCode(), args);
	}
	
	/**
	 * 支持传入异常枚举
	 * */
	public BaseException(ENUM_EXCEPTION_TYPE statusEnum) {
		super(statusEnum.getMessage());
		this.errCode = statusEnum.getCode();
		this.errMsg = statusEnum.getMessage();
	}

	public BaseException(String string) {
		// TODO Auto-generated constructor stub
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public String getMessage(String errCode, Object[] args) {
		String errMsg = ENUM_EXCEPTION_TYPE.findStatus(errCode).getMessage();
		errMsg = String.format(errMsg, args);
		return errMsg;
	}
	
}
