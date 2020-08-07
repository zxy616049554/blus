package com.rk.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 描述： （chat_roommembers）
 * 
 * 作者： gjmctp
 * 时间： 2020-07-22 13:14:04
 */
 public class ChatRoommembers implements Serializable {
 
 	private static final long serialVersionUID = 1L;
 	
		
	/** 自增id */
	private int chatId;
		
	/** 群id */
	private String chatRoomId;
		
	/** 群成员微信号 */
	private String chatUserName;
		
	/** 群成员昵称 */
	private String chatNickName;
	
	/** 修改后的昵称 */
	private String displayName;
		
	/** 邀请人微信号 */
	private String inviterUserName;
	
	/** 大头像 */
	private String bigHeadImgUrl;
	
	/** 小头像 */
	private String smallHeadImgUrl;
	
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
		
	
	public int getChatId() {
		return chatId;
	}

	public void setChatId(int chatId) {
		this.chatId = chatId;
	}
	
	public String getChatRoomId() {
		return chatRoomId;
	}

	public void setChatRoomId(String chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	
	public String getChatUserName() {
		return chatUserName;
	}

	public void setChatUserName(String chatUserName) {
		this.chatUserName = chatUserName;
	}
	
	public String getChatNickName() {
		return chatNickName;
	}

	public void setChatNickName(String chatNickName) {
		this.chatNickName = chatNickName;
	}
	
	public String getInviterUserName() {
		return inviterUserName;
	}

	public void setInviterUserName(String inviterUserName) {
		this.inviterUserName = inviterUserName;
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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getBigHeadImgUrl() {
		return bigHeadImgUrl;
	}

	public void setBigHeadImgUrl(String bigHeadImgUrl) {
		this.bigHeadImgUrl = bigHeadImgUrl;
	}

	public String getSmallHeadImgUrl() {
		return smallHeadImgUrl;
	}

	public void setSmallHeadImgUrl(String smallHeadImgUrl) {
		this.smallHeadImgUrl = smallHeadImgUrl;
	}
	
	
	
 }