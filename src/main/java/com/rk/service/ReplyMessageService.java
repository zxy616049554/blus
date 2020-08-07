package com.rk.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.rk.entity.MessReceiving;
import com.rk.entity.ReplyMessage;

/**
 * 描述： （reply_message） 接口
 * 
 * 作者： gjmctp
 * 时间： 2020-07-23 17:01:51
 */
public interface ReplyMessageService {

	public PageInfo<ReplyMessage> findPage(int pageNum, int pageSize, Map<String, Object> params);

	public List<ReplyMessage> findAll();

	public ReplyMessage get(int id);

	public int save(ReplyMessage replyMessage);

	public int update(ReplyMessage replyMessage);
	
	public List<ReplyMessage> getMessByReceiveMess(String receiveMess);
	
	public ReplyMessage getMessByReceive(String receiveMess);

	public int delete(int replyId);
}