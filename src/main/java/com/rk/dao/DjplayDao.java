package com.rk.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rk.entity.Djplay;

/**
 * 描述： 操作 （djplay）
 * 
 * 作者： gjmctp
 * 时间： 2020-08-06 10:26:06
 */
@Repository
public interface DjplayDao {

	public List<Djplay> findByParams(Map<String, Object> params);

	public int save(Djplay djplayDao);

    public int update(Djplay djplayDao);
  
    public Djplay getUserRoomCount(Djplay djplay);
}