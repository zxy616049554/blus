package com.rk.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.rk.entity.WxPlatform;

/**
 * 描述： （wx_platform） 接口
 * 
 * 作者： gjmctp
 * 时间： 2020-07-17 10:37:41
 */
public interface WxPlatformService {

	public PageInfo<WxPlatform> findPage(int pageNum, int pageSize, Map<String, Object> params);

	public List<WxPlatform> findAll();

	public WxPlatform get(int id);

	public int save(WxPlatform wxPlatformDao);

	public int update(WxPlatform wxPlatformDao);

}