package com.rk.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述： （wx_friends）
 * 
 * 作者： gjmctp
 * 时间： 2020-07-17 10:53:01
 */
 public class WxFriends implements Serializable {
 
 	private static final long serialVersionUID = 1L;
 	
		
	/** 好友列表id */
	private int fId;
		
	/** 用户wcid */
	private String userwcId;
		
	/** 好友的wcid */
	private String friendwcId;
		
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
	
	/** 好友昵称 */
	private String friendNickName;
	
	/** 好友备注 */
	private String friendRemark;
	
	/** 好友签名 */
	private String friendSignature;
	
	/** 好友性别 */
	private int friendSex;
	
	/** 好友别名 */
	private String aliasName;
	
	/** 国家 */
	private String country;
	
	/** 大头像 */
	private String bigHead;
	
	/** 小头像 */
	private String smallHead;
	
	/** 标签列表 */
	private String labelList;
	
	/** 用户的WxiD */
	private String v1;
		
	
	public int getFId() {
		return fId;
	}

	public void setFId(int fId) {
		this.fId = fId;
	}
	
	public String getUserwcId() {
		return userwcId;
	}

	public void setUserwcId(String userwcId) {
		this.userwcId = userwcId;
	}
	
	public String getFriendwcId() {
		return friendwcId;
	}

	public void setFriendwcId(String friendwcId) {
		this.friendwcId = friendwcId;
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

	public int getfId() {
		return fId;
	}

	public void setfId(int fId) {
		this.fId = fId;
	}

	public String getFriendNickName() {
		return friendNickName;
	}

	public void setFriendNickName(String friendNickName) {
		this.friendNickName = friendNickName;
	}

	public String getFriendRemark() {
		return friendRemark;
	}

	public void setFriendRemark(String friendRemark) {
		this.friendRemark = friendRemark;
	}

	public String getFriendSignature() {
		return friendSignature;
	}

	public void setFriendSignature(String friendSignature) {
		this.friendSignature = friendSignature;
	}

	public int getFriendSex() {
		return friendSex;
	}

	public void setFriendSex(int friendSex) {
		this.friendSex = friendSex;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBigHead() {
		return bigHead;
	}

	public void setBigHead(String bigHead) {
		this.bigHead = bigHead;
	}

	public String getSmallHead() {
		return smallHead;
	}

	public void setSmallHead(String smallHead) {
		this.smallHead = smallHead;
	}

	public String getLabelList() {
		return labelList;
	}

	public void setLabelList(String labelList) {
		this.labelList = labelList;
	}

	public String getV1() {
		return v1;
	}

	public void setV1(String v1) {
		this.v1 = v1;
	}		
	
 }