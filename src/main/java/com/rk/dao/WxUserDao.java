package com.rk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.rk.entity.WxUser;

/**
 * 描述： 操作 （wx_user）
 * 
 * 作者： gjmctp
 * 时间： 2020-07-17 10:31:40
 */
@Repository
public interface WxUserDao{

	public List<WxUser> findByParams(Map<String, Object> params);

	public int save(WxUser wxUserDao);

    public int update(WxUser wxUserDao);
    
    public WxUser getMessByWcId(@Param("WcId") String WcId);
	
	public WxUser getUserMessByWId(@Param("WId") String WId);
}