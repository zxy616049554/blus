package com.rk.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.rk.entity.Constellationplay;

/**
 * 描述： （constellationplay） 接口
 * 
 * 作者： gjmctp
 * 时间： 2020-08-06 17:33:44
 */
public interface ConstellationplayService {

	public PageInfo<Constellationplay> findPage(int pageNum, int pageSize, Map<String, Object> params);

	public List<Constellationplay> findAll();

	public Constellationplay get(Long id);

	public int save(Constellationplay constellationplayDao);

	public int update(Constellationplay constellationplayDao);
	public Constellationplay getConsetUserRoomCount(Constellationplay constellationplay);
}