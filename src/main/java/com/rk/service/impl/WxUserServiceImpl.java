package com.rk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rk.dao.WxUserDao;
import com.rk.entity.WxUser;
import com.rk.service.WxUserService;
import com.rk.util.DateUtil;

/**
 * 描述： （wx_user） 接口实现
 * 
 * 作者： gjmctp
 * 时间： 2020-07-17 10:31:40
 */
@Service
public class WxUserServiceImpl implements WxUserService {

	@Autowired
	private WxUserDao wxUserDao;

	public PageInfo<WxUser> findPage(int pageNum, int pageSize, Map<String, Object> params) {
		PageHelper.startPage(pageNum, pageSize);
		List<WxUser> list = wxUserDao.findByParams(params);
		PageInfo<WxUser> page = new PageInfo<WxUser>(list);
		return page;
	}

	public List<WxUser> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		return wxUserDao.findByParams(params);
	}

	public WxUser get(int id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<WxUser> list = wxUserDao.findByParams(params);
		if(list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public int save(WxUser wxUser) {
		wxUser.setMakeDate(DateUtil.getCurrentStr());
		wxUser.setMakeTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		wxUser.setModifyDate(DateUtil.getCurrentStr());
		wxUser.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return wxUserDao.save(wxUser);
	}

	public int update(WxUser wxUser) {
		wxUser.setModifyDate(DateUtil.getCurrentStr());
		wxUser.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return wxUserDao.update(wxUser);
	}

	@Override
	public WxUser getMessByWcId(String WcId) {
		return wxUserDao.getMessByWcId(WcId);
	}

	@Override
	public WxUser getUserMessByWId(String WId) {
		return wxUserDao.getUserMessByWId(WId);
	}

}