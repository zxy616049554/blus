package com.rk.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.rk.entity.WxPlatform;

/**
 * 描述： 操作 （wx_platform）
 * 
 * 作者： gjmctp
 * 时间： 2020-07-17 10:37:41
 */
@Repository
public interface WxPlatformDao {

	public List<WxPlatform> findByParams(Map<String, Object> params);

	public int save(WxPlatform wxPlatform);

    public int update(WxPlatform wxPlatform);
  
}