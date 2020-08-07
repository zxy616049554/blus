package com.rk.entity;

import java.util.Date;

public class MusicMessage{
	 private Music Music;
	 private String ToUserName;
	 private String FromUserName;
	 private Date CreateTime;
	 private String MsgType;
	 public Music getMusic() {
	     return Music;
	 }
	 public void setMusic(Music music) {
	     Music = music;
	 }
	 
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public Date getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Date createTime) {
		CreateTime = createTime;
	}
	
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	@Override
	 public String toString() {
	     return "MusicMessage [Music=" + Music + "]";
	 }

}
