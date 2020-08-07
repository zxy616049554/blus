package com.rk.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.rk.entity.DownloadMess;

/**
 * 描述： 操作 （download_mess）
 * 
 * 作者： gjmctp
 * 时间： 2020-07-24 09:49:17
 */
@Repository
public interface DownloadMessDao {

	public List<DownloadMess> findByParams(Map<String, Object> params);

	public int save(DownloadMess downloadMess);

    public int update(DownloadMess downloadMess);
  
}