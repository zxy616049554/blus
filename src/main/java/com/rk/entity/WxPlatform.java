package com.rk.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述： （wx_platform）
 * 
 * 作者： gjmctp
 * 时间： 2020-07-17 10:37:41
 */
 public class WxPlatform implements Serializable {
 
 	private static final long serialVersionUID = 1L;
 	
		
	/** 平台id */
	private int platformId;
		
	/** 平台账号 */
	private String platformAccount;
		
	/** 平台密码 */
	private String platformPassword;
		
	/** 平台key */
	private String platformKey;
		
	/** 平台地址 */
	private String platformUrl;
		
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
		
	
	public int getPlatformId() {
		return platformId;
	}

	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}
	
	public String getPlatformAccount() {
		return platformAccount;
	}

	public void setPlatformAccount(String platformAccount) {
		this.platformAccount = platformAccount;
	}
	
	public String getPlatformPassword() {
		return platformPassword;
	}

	public void setPlatformPassword(String platformPassword) {
		this.platformPassword = platformPassword;
	}
	
	public String getPlatformKey() {
		return platformKey;
	}

	public void setPlatformKey(String platformKey) {
		this.platformKey = platformKey;
	}
	
	public String getPlatformUrl() {
		return platformUrl;
	}

	public void setPlatformUrl(String platformUrl) {
		this.platformUrl = platformUrl;
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

	public WxPlatform() {
		
	}
	
	public WxPlatform(int platformId, String platformAccount, String platformPassword, String platformKey,
			String platformUrl, String standby1, String standby2, Date makeDate, String makeTime, Date modifyDate,
			String modifyTime) {
		super();
		this.platformId = platformId;
		this.platformAccount = platformAccount;
		this.platformPassword = platformPassword;
		this.platformKey = platformKey;
		this.platformUrl = platformUrl;
		this.standby1 = standby1;
		this.standby2 = standby2;
		this.makeDate = makeDate;
		this.makeTime = makeTime;
		this.modifyDate = modifyDate;
		this.modifyTime = modifyTime;
	}
		
	
 }