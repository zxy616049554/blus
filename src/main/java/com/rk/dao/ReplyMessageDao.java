package com.rk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.rk.entity.ReplyMessage;

/**
 * 描述： 操作 （reply_message）
 * 
 * 作者： gjmctp
 * 时间： 2020-07-23 17:01:51
 */
@Repository
public interface ReplyMessageDao {

	public List<ReplyMessage> findByParams(Map<String, Object> params);

	public int save(ReplyMessage replyMessage);

    public int update(ReplyMessage replyMessage);
    
    public List<ReplyMessage> getMessByReceiveMess(@Param("receiveMess")String receiveMess);
 
    public ReplyMessage getMessByReceive(@Param("receiveMess")String receiveMess);
    
    public int delete(int replyId);
}