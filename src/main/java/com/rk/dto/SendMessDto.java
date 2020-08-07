package com.rk.dto;

public class SendMessDto {

	private String wId;//微信实例id
	private String wcId;//微信号/群号
	private String content;//文本内容消息
	private String at;//艾特
	private String path;//文件url链接
	private String fileName;//文件名
	private String thumbPath;//视频封面url链接，可自定义
	private int length;//语音时长
	private String title;//标题
	private String url; //链接
	private String description;//描述
	private String thumbUrl;//图标url
	private String nameCardId;//要发送的名片微信号
	private String imageMd5;// 取回调中xml中md5字段值
	private String imgSize;// 取回调中xml中len字段值
	public String getwId() {
		return wId;
	}
	public void setwId(String wId) {
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
	
	
}
