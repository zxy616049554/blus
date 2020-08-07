package com.rk.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.rk.entity.WxGroup;

/**
 * 描述： （wx_group） 接口
 * 
 * 作者： gjmctp
 * 时间： 2020-07-17 11:02:34
 */
public interface WxGroupService {

	public PageInfo<WxGroup> findPage(int pageNum, int pageSize, Map<String, Object> params);

	public List<WxGroup> findAll();

	public WxGroup get(int id);

	public int save(WxGroup wxGroup);

	public int update(WxGroup wxGroup);
	
	public WxGroup getMessByRoomId(String roomId);
	
	public List<WxGroup> getGroupMessByWcId(String wcId);

}