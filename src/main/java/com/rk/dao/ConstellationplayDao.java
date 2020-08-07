package com.rk.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rk.entity.Constellationplay;

/**
 * 描述： 操作 （constellationplay）
 * 
 * 作者： gjmctp
 * 时间： 2020-08-06 17:33:44
 */
@Repository
public interface ConstellationplayDao {

	public List<Constellationplay> findByParams(Map<String, Object> params);

	public int save(Constellationplay constellationplayDao);

    public int update(Constellationplay constellationplayDao);
    public Constellationplay getConsetUserRoomCount(Constellationplay constellationplay);
}