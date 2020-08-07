package com.rk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.rk.entity.ChatRoommembers;
/**
 * 描述： 操作 （chat_roommembers）
 * 
 * 作者： gjmctp
 * 时间： 2020-07-22 13:14:04
 */
@Repository
public interface ChatRoommembersDao {

	public List<ChatRoommembers> findByParams(Map<String, Object> params);

	public int save(ChatRoommembers chatRoommembers);

    public int update(ChatRoommembers chatRoommembers);
    
    public List<ChatRoommembers> getMessChatRooms(@Param("roomId")String roomId);
    
    public ChatRoommembers getMessChatRoomsByUserId(@Param("userId")String userId);
    
    public ChatRoommembers getMesschatRoomsByDisplayName(@Param("displayName")String displayName);
  
}