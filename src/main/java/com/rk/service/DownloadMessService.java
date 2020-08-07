package com.rk.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.rk.entity.DownloadMess;

/**
 * 描述： （download_mess） 接口
 * 
 * 作者： gjmctp
 * 时间： 2020-07-24 09:49:17
 */
public interface DownloadMessService {

	public PageInfo<DownloadMess> findPage(int pageNum, int pageSize, Map<String, Object> params);

	public List<DownloadMess> findAll();

	public DownloadMess get(Long id);

	public int save(DownloadMess downloadMess);

	public int update(DownloadMess downloadMess);

}