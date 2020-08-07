package com.rk.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.PageInfo;
import com.rk.entity.WxFriends;

/**
 * 描述： （wx_friends） 接口
 * 
 * 作者： gjmctp
 * 时间： 2020-07-17 10:53:01
 */
public interface WxFriendsService {

	public PageInfo<WxFriends> findPage(int pageNum, int pageSize, Map<String, Object> params);

	public List<WxFriends> findAll();

	public WxFriends get(int id);
	
	public WxFriends getMessBywcId(String wcId);

	public int save(WxFriends wxFriendsDao);

	public int update(WxFriends wxFriendsDao);
	
	public List<WxFriends> getFriendsMessByWcIds(String wcId);

}