package com.rk.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.rk.entity.Djplay;

/**
 * 描述： （djplay） 接口
 * 
 * 作者： gjmctp
 * 时间： 2020-08-06 10:26:06
 */
public interface DjplayService {

	public PageInfo<Djplay> findPage(int pageNum, int pageSize, Map<String, Object> params);

	public List<Djplay> findAll();

	public Djplay get(Long id);

	public int save(Djplay djplayDao);

	public int update(Djplay djplayDao);
	
	public Djplay getUserRoomCount(Djplay djplay);

}