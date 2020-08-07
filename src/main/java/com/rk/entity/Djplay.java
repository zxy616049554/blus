package com.rk.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述： （djplay）
 * 
 * 作者： gjmctp
 * 时间： 2020-08-06 10:26:06
 */
 public class Djplay implements Serializable {
 
 	private static final long serialVersionUID = 1L;
 	
		
	/**  */
	private int id;
		
	/**  */
	private String chatRoomId;
		
	/**  */
	private String chatUserWx;
		
	/**  */
	private int count;
		
	/**  */
	private Date makeDate;
		
	/**  */
	private String makeTime;
		
	/**  */
	private Date modifyDate;
		
	/**  */
	private String modifyTime;
		
	/**  */
	private String standby1;
		
	/**  */
	private String standby2;
		
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getChatRoomId() {
		return chatRoomId;
	}

	public void setChatRoomId(String chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	
	public String getChatUserWx() {
		return chatUserWx;
	}

	public void setChatUserWx(String chatUserWx) {
		this.chatUserWx = chatUserWx;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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
		
 }