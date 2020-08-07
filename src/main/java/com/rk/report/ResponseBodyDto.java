/**
 * @Copyright ®2018 gjmetal Co. Ltd. All rights reserved.<br/>
 * 项目名称 : 国金大宗商品交易平台
 * 创建日期 : 2018年9月26日
 * 修改历史 : 
 *     1. [2018年9月26日]创建文件 by gjmetal
 */
package com.rk.report;

import java.io.Serializable;

/**  
 * 【报文返回body类】
 * @author 王利飞
 * @date 2018年9月26日 上午9:46:36 
 * @version V1.0  
*/
public class ResponseBodyDto implements Serializable{
    
    /** 
    * @Fields serialVersionUID : TODO
    */
    private static final long serialVersionUID = 2350755533325990222L;
    
    /**返回的数据*/
	private Object  data;
	
    /**返回报错提示节点*/
    private  Status status;
    
    
    
    
    public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
	public String toString() {
		return "ResponseBodyDto [data=" + data + ", status=" + status + "]";
	}
    
}
