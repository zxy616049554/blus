/**
 * @Copyright ®2018 gjmctp Co. Ltd. All rights reserved.<br/>
 * 项目名称 : 国金金属商品交易平台
 * 创建日期 : 2018年5月25日
 * 修改历史 : 
 *     1. [2018年5月25日]创建文件 by gjmctp
 */
package com.rk.report;

import java.io.Serializable;

import com.rk.enums.ENUM_RESULT;


/**
 * 
 * 返回报文公共状态信息
 * 
 * @author gjmctp
 * @date 2018年6月20日 下午2:01:12 
 * @version V1.0
 */
public class Status implements Serializable{
    
    /** 
    * @Fields serialVersionUID : TODO
    */
    private static final long serialVersionUID = -3557250584353942589L;
    
    /**状态编码*/
    private String statusCode;
    /**状态描述*/
    private String statusMessage;

    public Status() {
        super();
    }

    public Status(String statusCode, String statusMessage) {
        super();
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @Override
    public String toString() {
        return "Status [statusCode=" + statusCode + ", statusMessage=" + statusMessage + "]";
    }
    
    
    public static Status succ() {
        Status ret = new Status();
        ret.setStatusCode(ENUM_RESULT.SUCCESS.code());
        ret.setStatusMessage(ENUM_RESULT.SUCCESS.desc());
        return ret;
    }
    
    public static Status fail() {
        Status ret = new Status();
        ret.setStatusCode(ENUM_RESULT.FAIL.code());
        ret.setStatusMessage(ENUM_RESULT.FAIL.desc());
        return ret;
    }
    
    public static Status fail(String desc) {
        Status ret = new Status();
        ret.setStatusCode(ENUM_RESULT.FAIL.code());
        ret.setStatusMessage(desc);
        return ret;
    }
    
    public static Status unusual(String desc) {
        Status ret = new Status();
        ret.setStatusCode(ENUM_RESULT.UNUSUAL.code());
        ret.setStatusMessage(desc);
        return ret;
    }

}
