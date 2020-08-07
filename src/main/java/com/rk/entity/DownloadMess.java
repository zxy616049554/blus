package com.rk.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述： （download_mess）
 * 
 * 作者： gjmctp
 * 时间： 2020-07-24 09:49:16
 */
 public class DownloadMess implements Serializable {
 
 	private static final long serialVersionUID = 1L;
 	
		
	/** 自增id */
	private int dId;
		
	/** 下载的文件 */
	private String dUrl;
		
	/** 类型 */
	private int dType;
		
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
		
	
	public int getDId() {
		return dId;
	}

	public void setDId(int dId) {
		this.dId = dId;
	}
	
	public String getDUrl() {
		return dUrl;
	}

	public void setDUrl(String dUrl) {
		this.dUrl = dUrl;
	}
	
	public int getDType() {
		return dType;
	}

	public void setDType(int dType) {
		this.dType = dType;
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