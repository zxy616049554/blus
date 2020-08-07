package com.rk.dto;

import java.util.List;

public class GroupDto {
	
	/** 用户wcid */
	private String userwcId;
		
	/** 用户的群id */
	private String usergroupId;
	
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

	private List<ChatRoomMembersDto> chatRoomMembersDto;
	
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

	public String getGroupNickName() {
		return groupNickName;
	}

	public void setGroupNickName(String groupNickName) {
		this.groupNickName = groupNickName;
	}

	public String getChatRoomOwner() {
		return chatRoomOwner;
	}

	public void setChatRoomOwner(String chatRoomOwner) {
		this.chatRoomOwner = chatRoomOwner;
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

	public List<ChatRoomMembersDto> getChatRoomMembersDto() {
		return chatRoomMembersDto;
	}

	public void setChatRoomMembersDto(List<ChatRoomMembersDto> chatRoomMembersDto) {
		this.chatRoomMembersDto = chatRoomMembersDto;
	}

	
	
	

}
