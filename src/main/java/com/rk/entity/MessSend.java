package com.rk.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 描述： （mess_send）
 * 
 * 作者： gjmctp
 * 时间： 2020-07-23 16:39:55
 */
 public class MessSend implements Serializable {
 
 	private static final long serialVersionUID = 1L;
 	
		
	/** 自增id */
	private int sendId;
		
	/** 微信号实例Id */
	private String wId;
		
	/** 微信号/群号 */
	private String wcId;
		
	/** 文本内容 */
	private String content;
		
	/** 艾特 */
	private String at;
		
	/** 文件url */
	private String path;
		
	/** 文件名 */
	private String fileName;
		
	/** 视频封面url链接，可自定义 */
	private String thumbPath;
		
	/** 语音时长 */
	private int length;
		
	/** 标题 */
	private String title;
		
	/** 链接 */
	private String url;
		
	/** 描述 */
	private String description;
		
	/** 图标url */
	private String thumbUrl;
		
	/** 要发送的名片微信号 */
	private String nameCardId;
		
	/** 取回调中xml中md5字段值 */
	private String imageMd5;
		
	/** 取回调中xml中len字段值 */
	private String imgSize;
	
	/** 类型*/
	private int messSendType;
		
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
	
	public int getSendId() {
		return sendId;
	}

	public void setSendId(int sendId) {
		this.sendId = sendId;
	}
	
	public String getWId() {
		return wId;
	}

	public void setWId(String wId) {
		this.wId = wId;
	}
	
	public String getWcId() {
		return wcId;
	}

	public void setWcId(String wcId) {
		this.wcId = wcId;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getAt() {
		return at;
	}

	public void setAt(String at) {
		this.at = at;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getThumbPath() {
		return thumbPath;
	}

	public void setThumbPath(String thumbPath) {
		this.thumbPath = thumbPath;
	}
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getThumbUrl() {
		return thumbUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
	
	public String getNameCardId() {
		return nameCardId;
	}

	public void setNameCardId(String nameCardId) {
		this.nameCardId = nameCardId;
	}
	
	public String getImageMd5() {
		return imageMd5;
	}

	public void setImageMd5(String imageMd5) {
		this.imageMd5 = imageMd5;
	}
	
	public String getImgSize() {
		return imgSize;
	}

	public void setImgSize(String imgSize) {
		this.imgSize = imgSize;
	}

	public String getwId() {
		return wId;
	}

	public void setwId(String wId) {
		this.wId = wId;
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

	public int getMessSendType() {
		return messSendType;
	}

	public void setMessSendType(int messSendType) {
		this.messSendType = messSendType;
	}
		
	
 }