package com.rk.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述： （reply_message）
 * 
 * 作者： gjmctp
 * 时间： 2020-07-23 17:01:51
 */
 public class ReplyMessage implements Serializable {
 
 	private static final long serialVersionUID = 1L;
 	
		
	/** 自增id */
	private int replyId;
		
	/** 接收的消息 */
	private String receiveMess;
		
	/** 回复的消息 */
	private String replyMess;
		
	/** 备用字段1 */
	private String standby1;
		
	/** 备用字段2 */
	private String standby2;
		
	/** 创建日期 */
	private Date makeDate;
		
	/** 创建时间 */
	private String makeTime;
		
	/** 修改日期 */
	private Date modifyDate;
		
	/** 修改时间 */
	private String modifyTime;
		
	
	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	
	public String getReceiveMess() {
		return receiveMess;
	}

	public void setReceiveMess(String receiveMess) {
		this.receiveMess = receiveMess;
	}
	
	public String getReplyMess() {
		return replyMess;
	}

	public void setReplyMess(String replyMess) {
		this.replyMess = replyMess;
	}
	
	public String getStandby1() {
		return standby1;
	}

	public void setStandby1(String standby1) {
		this.standby1 = standby1;
	}
	
	public String getStandby2() {
		return standby2;
	}

	public void setStandby2(String standby2) {
		this.standby2 = standby2;
	}
	
	public Date getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}
	
	public String getMakeTime() {
		return makeTime;
	}

	public void setMakeTime(String makeTime) {
		this.makeTime = makeTime;
	}
	
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
		
 }