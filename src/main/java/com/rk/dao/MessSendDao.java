package com.rk.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.rk.entity.MessSend;

/**
 * 描述： 操作 （mess_send）
 * 
 * 作者： gjmctp
 * 时间： 2020-07-23 16:39:55
 */
@Repository
public interface MessSendDao {

	public List<MessSend> findByParams(Map<String, Object> params);

	public int save(MessSend messSend);

    public int update(MessSend messSend);
  
}