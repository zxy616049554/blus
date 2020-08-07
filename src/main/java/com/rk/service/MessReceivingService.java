package com.rk.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.rk.entity.MessReceiving;

/**
 * 描述： （mess_receiving） 接口
 * 
 * 作者： gjmctp
 * 时间： 2020-07-23 11:24:13
 */
public interface MessReceivingService {

	public PageInfo<MessReceiving> findPage(int pageNum, int pageSize, Map<String, Object> params);

	public List<MessReceiving> findAll();

	public MessReceiving get(Long id);

	public int save(MessReceiving messReceiving);

	public int update(MessReceiving messReceiving);

	public List<MessReceiving> getGroupMessage();
}