package com.rk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rk.dao.WxPlatformDao;
import com.rk.entity.WxPlatform;
import com.rk.service.WxPlatformService;
import com.rk.util.DateUtil;

/**
 * 描述： （wx_platform） 接口实现
 * 
 * 作者： gjmctp
 * 时间： 2020-07-17 10:37:41
 */
@Service
public class WxPlatformServiceImpl implements WxPlatformService {

	@Autowired
	private WxPlatformDao wxPlatformDao;

	
	public PageInfo<WxPlatform> findPage(int pageNum, int pageSize, Map<String, Object> params) {
		PageHelper.startPage(pageNum, pageSize);
		List<WxPlatform> list = wxPlatformDao.findByParams(params);
		PageInfo<WxPlatform> page = new PageInfo<WxPlatform>(list);
		return page;
	}

	public List<WxPlatform> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		return wxPlatformDao.findByParams(params);
	}

	public WxPlatform get(int id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<WxPlatform> list = wxPlatformDao.findByParams(params);
		if(list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public int save(WxPlatform wxPlatform) {
		wxPlatform.setMakeDate(DateUtil.getCurrentStr());
		wxPlatform.setMakeTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		wxPlatform.setModifyDate(DateUtil.getCurrentStr());
		wxPlatform.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return wxPlatformDao.save(wxPlatform);
	}

	public int update(WxPlatform wxPlatform) {
		wxPlatform.setModifyDate(DateUtil.getCurrentStr());
		wxPlatform.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return wxPlatformDao.update(wxPlatform);
	}

}

