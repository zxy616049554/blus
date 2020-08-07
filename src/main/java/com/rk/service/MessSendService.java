package com.rk.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.rk.entity.MessSend;

/**
 * 描述： （mess_send） 接口
 * 
 * 作者： gjmctp
 * 时间： 2020-07-23 16:39:56
 */
public interface MessSendService {

	public PageInfo<MessSend> findPage(int pageNum, int pageSize, Map<String, Object> params);

	public List<MessSend> findAll();

	public MessSend get(Long id);

	public int save(MessSend messSend);

	public int update(MessSend messSend);

}