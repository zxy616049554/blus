package com.rk.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.rk.entity.ChatRoommembers;

/**
 * 描述： （chat_roommembers） 接口
 * 
 * 作者： gjmctp
 * 时间： 2020-07-22 13:14:04
 */
public interface ChatRoommembersService {

	public PageInfo<ChatRoommembers> findPage(int pageNum, int pageSize, Map<String, Object> params);

	public List<ChatRoommembers> findAll();

	public ChatRoommembers get(int id);
	 
	public List<ChatRoommembers> getMessChatRooms(String roomId);

	public int save(ChatRoommembers chatRoommembers);

	public int update(ChatRoommembers chatRoommembers);

    public ChatRoommembers getMessChatRoomsByUserId(String userId);
    
    public ChatRoommembers getMesschatRoomsByDisplayName(String displayName);
}