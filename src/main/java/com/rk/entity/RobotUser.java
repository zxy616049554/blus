package com.rk.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述： （robot_user）
 * 
 * 作者： gjmctp
 * 时间： 2020-07-23 11:19:40
 */
 public class RobotUser implements Serializable {
 
 	private static final long serialVersionUID = 1L;
 	
		
	/** 自增列表 */
	private int rId;
		
	/** 用户名称 */
	private String rUsername;
		
	/** 用户密码 */
	private String rPassword;
		
	/** 用户手机号 */
	private String rPhone;
		
	/** 用户类型 */
	private int rUsertype;
		
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
		
	
	public int getRId() {
		return rId;
	}

	public void setRId(int rId) {
		this.rId = rId;
	}
	
	public String getRUsername() {
		return rUsername;
	}

	public void setRUsername(String rUsername) {
		this.rUsername = rUsername;
	}
	
	public String getRPassword() {
		return rPassword;
	}

	public void setRPassword(String rPassword) {
		this.rPassword = rPassword;
	}
	
	public String getRPhone() {
		return rPhone;
	}

	public void setRPhone(String rPhone) {
		this.rPhone = rPhone;
	}
	
	public int getRUsertype() {
		return rUsertype;
	}

	public void setRUsertype(int rUsertype) {
		this.rUsertype = rUsertype;
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