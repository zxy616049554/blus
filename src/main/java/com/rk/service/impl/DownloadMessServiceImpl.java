package com.rk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rk.util.DateUtil;
import com.rk.dao.DownloadMessDao;
import com.rk.entity.DownloadMess;
import com.rk.service.DownloadMessService;

/**
 * 描述： （download_mess） 接口实现
 * 
 * 作者： gjmctp
 * 时间： 2020-07-24 09:49:17
 */
@Service
public class DownloadMessServiceImpl implements DownloadMessService {

	@Autowired
	private DownloadMessDao downloadMessDao;

	public PageInfo<DownloadMess> findPage(int pageNum, int pageSize, Map<String, Object> params) {
		PageHelper.startPage(pageNum, pageSize);
		List<DownloadMess> list = downloadMessDao.findByParams(params);
		PageInfo<DownloadMess> page = new PageInfo<DownloadMess>(list);
		return page;
	}

	public List<DownloadMess> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		return downloadMessDao.findByParams(params);
	}

	public DownloadMess get(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<DownloadMess> list = downloadMessDao.findByParams(params);
		if(list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public int save(DownloadMess downloadMess) {
		downloadMess.setMakeDate(DateUtil.getCurrentStr());
		downloadMess.setMakeTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		downloadMess.setModifyDate(DateUtil.getCurrentStr());
		downloadMess.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return downloadMessDao.save(downloadMess);
	}

	public int update(DownloadMess downloadMess) {
		downloadMess.setModifyDate(DateUtil.getCurrentStr());
		downloadMess.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return downloadMessDao.update(downloadMess);
	}

}

