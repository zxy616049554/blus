/**
 * @Copyright ®2018 gjmctp Co. Ltd. All rights reserved.<br/>
 * 项目名称 : 国金金属商品交易平台
 * 创建日期 : 2018年5月25日
 * 修改历史 : 
 *     1. [2018年5月25日]创建文件 by gjmctp
 */
package com.rk.report;

import java.io.Serializable;

/**
 * 
 * 【接口请求报文类】
 * @author gjmctp
 * @date 2018年5月19日 下午2:12:07 
 * @version V1.0
 */
public class Head implements Serializable{
    /** 
    * @Fields serialVersionUID : TODO
    */
    private static final long serialVersionUID = 5897554746124799905L;
    
    /**请求流水号*/
    private String transId;
    /**操作用户的id*/
    private String token;
    
    private String verson;
    

	public String getVerson() {
		return verson;
	}

	public void setVerson(String verson) {
		this.verson = verson;
	}

	public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }
    
    
    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
    public String toString() {
       return "Head [transId=" + transId + ", token=" + token +"]";
    }


}
