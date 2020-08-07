package com.rk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.rk.entity.WxGroup;

/**
 * 描述： 操作 （wx_group）
 * 
 * 作者： gjmctp
 * 时间： 2020-07-17 11:02:34
 */
@Repository
public interface WxGroupDao {

	public List<WxGroup> findByParams(Map<String, Object> params);

	public int save(WxGroup wxGroupDao);

    public int update(WxGroup wxGroupDao);
    
    public WxGroup getMessByRoomId(@Param("roomId")String roomId);
    
    public List<WxGroup> getGroupMessByWcId(@Param("wcId")String wcId);
}