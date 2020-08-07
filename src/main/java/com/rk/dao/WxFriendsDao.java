package com.rk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.rk.entity.WxFriends;

/**
 * 描述： 操作 （wx_friends）
 * 
 * 作者： gjmctp
 * 时间： 2020-07-17 10:53:01
 */
@Repository
public interface WxFriendsDao {

	public List<WxFriends> findByParams(Map<String, Object> params);

	public int save(WxFriends wxFriendsDao);

    public int update(WxFriends wxFriendsDao);
    
    public WxFriends getMessByWcId(@Param("wcid")String wcid);
  
    public List<WxFriends> getFriendsMessByWcIds(@Param("wcId")String wcId);
}