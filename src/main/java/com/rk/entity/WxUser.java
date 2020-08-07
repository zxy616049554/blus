package com.rk.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述： （wx_user）
 * 
 * 作者： gjmctp
 * 时间： 2020-07-17 10:31:40
 */
 public class WxUser implements Serializable {
 
 	private static final long serialVersionUID = 1L;
 		
	/** 用户id */
 	private int userId;
		
	/** 用户wcid */
	private String userwcId;
		
	/** 用户wid */
	private String userwid;
		
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
	
	/** 用户账户*/
	private String userAccount;	
	
	/** 用户国家*/
	private String userCountry;
	
	/** 用户城市*/
	private String userCity;
	
	/** 用户个性签名*/
	private String userSignature;
	
	/** 用户昵称*/
	private String userNickName;
	
	/** 性别*/
	private int userSex;
	
	/** 标题url*/
	private String userHeadUrl;
	
	/** 小标题图片url*/
	private String smallHeadImgUrl;
	
	/** 状态*/
	private int status;
		
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserwcId() {
		return userwcId;
	}

	public void setUserwcId(String userwcId) {
		this.userwcId = userwcId;
	}
	
	public String getUserwid() {
		return userwid;
	}

	public void setUserwid(String userwid) {
		this.userwid = userwid;
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

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserCountry() {
		return userCountry;
	}

	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserSignature() {
		return userSignature;
	}

	public void setUserSignature(String userSignature) {
		this.userSignature = userSignature;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public int getUserSex() {
		return userSex;
	}

	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}

	public String getUserHeadUrl() {
		return userHeadUrl;
	}

	public void setUserHeadUrl(String userHeadUrl) {
		this.userHeadUrl = userHeadUrl;
	}

	public String getSmallHeadImgUrl() {
		return smallHeadImgUrl;
	}

	public void setSmallHeadImgUrl(String smallHeadImgUrl) {
		this.smallHeadImgUrl = smallHeadImgUrl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
		
	
 }