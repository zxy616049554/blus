package com.rk.dto;

public class MessReceiveDto {
	
	/** 自增id */
	private int messId;
	
	/** 微信号实例Id */
	private String wId;
		
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
	
	/** 语音长度*/
	private String length;
	
	/** xml中返回的bufId字段值 */
	private String bufId;

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

	public String getwId() {
		return wId;
	}

	public void setwId(String wId) {
		this.wId = wId;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getBufId() {
		return bufId;
	}

	public void setBufId(String bufId) {
		this.bufId = bufId;
	}
	
	

}
