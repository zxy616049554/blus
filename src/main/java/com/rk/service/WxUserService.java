package com.rk.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.rk.entity.WxUser;

/**
 * 描述： （wx_user） 接口
 * 
 * 作者： gjmctp
 * 时间： 2020-07-17 10:31:40
 */
public interface WxUserService {

	public PageInfo<WxUser> findPage(int pageNum, int pageSize, Map<String, Object> params);

	public List<WxUser> findAll();

	public WxUser get(int id);
	
	public WxUser getMessByWcId(String WcId);
	
	public WxUser getUserMessByWId(String WId);

	public int save(WxUser wxUserDao);

	public int update(WxUser wxUserDao);

}