package com.rk.dto;

public class FriendsDto {

	/** 用户wcid */
	private String userwcId;
		
	/** 好友的wcid */
	private String friendwcId;
	
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
