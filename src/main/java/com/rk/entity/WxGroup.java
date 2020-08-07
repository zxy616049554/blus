package com.rk.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.rk.dto.ChatRoomMembersDto;

/**
 * 描述： （wx_group）
 * 
 * 作者： gjmctp
 * 时间： 2020-07-17 11:02:34
 */
 public class WxGroup implements Serializable {
 
 	private static final long serialVersionUID = 1L;
 	
		
	/** 群列表id */
	private int gId;
		
	/** 用户wcid */
	private String userwcId;
		
	/** 用户的群id */
	private String usergroupId;
		
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
		
	/** 群成员默认昵称 */
	private String groupNickName;
		
	/** 群主 */
	private String chatRoomOwner;
		
	/** 大头像 */
	private String bigHeadImgUrl;
		
	/** 小头像 */
	private String smallHeadImgUrl;
	
	/** 群v1 */
	private String v1;
		
	/** 群成员数 */
	private String memberCount;
	
	public int getGId() {
		return gId;
	}

	public void setGId(int gId) {
		this.gId = gId;
	}
	
	public String getUserwcId() {
		return userwcId;
	}

	public void setUserwcId(String userwcId) {
		this.userwcId = userwcId;
	}
	
	public String getUsergroupId() {
		return usergroupId;
	}

	public void setUsergroupId(String usergroupId) {
		this.usergroupId = usergroupId;
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

	public int getgId() {
		return gId;
	}

	public void setgId(int gId) {
		this.gId = gId;
	}

	public String getGroupNickName() {
		return groupNickName;
	}

	public void setGroupNickName(String groupNickName) {
		this.groupNickName = groupNickName;
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

	public String getChatRoomOwner() {
		return chatRoomOwner;
	}

	public void setChatRoomOwner(String chatRoomOwner) {
		this.chatRoomOwner = chatRoomOwner;
	}

	public String getV1() {
		return v1;
	}

	public void setV1(String v1) {
		this.v1 = v1;
	}

	public String getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(String memberCount) {
		this.memberCount = memberCount;
	}
	
	
 }