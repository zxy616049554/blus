package com.rk.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.rk.entity.MessReceiving;

/**
 * 描述： 操作 （mess_receiving）
 * 
 * 作者： gjmctp
 * 时间： 2020-07-23 11:24:13
 */
@Repository
public interface MessReceivingDao {

	public List<MessReceiving> findByParams(Map<String, Object> params);

	public int save(MessReceiving messReceiving);

    public int update(MessReceiving messReceiving);
    
    public List<MessReceiving> getGroupMessage();                          
  
}