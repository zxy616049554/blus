package com.rk.dto;

public class ChatRoomMembersDto {
	
	/** 群成员微信号 */
	private String userName;
		
	/** 群成员昵称 */
	private String nickName;
		
	/** 邀请人微信号 */
	private String inviterUserName;
	private String wId;
	private String roomId;
	
	private String path;
	
	
	public String getwId() {
		return wId;
	}

	public void setwId(String wId) {
		this.wId = wId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getInviterUserName() {
		return inviterUserName;
	}

	public void setInviterUserName(String inviterUserName) {
		this.inviterUserName = inviterUserName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	

}
