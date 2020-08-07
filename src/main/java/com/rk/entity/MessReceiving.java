package com.rk.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述： （mess_receiving）
 * 
 * 作者： gjmctp
 * 时间： 2020-07-23 11:24:13
 */
 public class MessReceiving implements Serializable {
 
 	private static final long serialVersionUID = 1L;
 	
		
	/** 自增id */
	private int messId;
		
	/** 微信号 */
	private String messWcId;
		
	/** 账号 */
	private String account;
		
	/** 消息类型 */
	private int messageType;
		
	/** 发送微信号 */
	private String dataFromUser;
		
	/** 发送群号 */
	private String dataFromGroup;
		
	/** 接收微信号 */
	private String dataToUser;
		
	/** 消息msgId */
	private String dataMsgId;
		
	/** 消息newMsgId */
	private String dataNewMsgId;
		
	/** 其他消息具体类型 */
	private int dataMsgType;
		
	/** 时间 */
	private String dataTimestamp;
		
	/** 消息体 */
	private String dataContent;
		
	/** 是否是自己发送的消息 */
	private int dataSelf;
		
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
		
	
	public int getMessId() {
		return messId;
	}

	public void setMessId(int messId) {
		this.messId = messId;
	}
	
	public String getMessWcId() {
		return messWcId;
	}

	public void setMessWcId(String messWcId) {
		this.messWcId = messWcId;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	
	public String getDataFromUser() {
		return dataFromUser;
	}

	public void setDataFromUser(String dataFromUser) {
		this.dataFromUser = dataFromUser;
	}
	
	public String getDataFromGroup() {
		return dataFromGroup;
	}

	public void setDataFromGroup(String dataFromGroup) {
		this.dataFromGroup = dataFromGroup;
	}
	
	public String getDataToUser() {
		return dataToUser;
	}

	public void setDataToUser(String dataToUser) {
		this.dataToUser = dataToUser;
	}
	
	public String getDataMsgId() {
		return dataMsgId;
	}

	public void setDataMsgId(String dataMsgId) {
		this.dataMsgId = dataMsgId;
	}
	
	public String getDataNewMsgId() {
		return dataNewMsgId;
	}

	public void setDataNewMsgId(String dataNewMsgId) {
		this.dataNewMsgId = dataNewMsgId;
	}
	
	public int getDataMsgType() {
		return dataMsgType;
	}

	public void setDataMsgType(int dataMsgType) {
		this.dataMsgType = dataMsgType;
	}
	
	public String getDataTimestamp() {
		return dataTimestamp;
	}

	public void setDataTimestamp(String dataTimestamp) {
		this.dataTimestamp = dataTimestamp;
	}
	
	public String getDataContent() {
		return dataContent;
	}

	public void setDataContent(String dataContent) {
		this.dataContent = dataContent;
	}
	
	public int getDataSelf() {
		return dataSelf;
	}

	public void setDataSelf(int dataSelf) {
		this.dataSelf = dataSelf;
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