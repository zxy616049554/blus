package com.rk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rk.dao.WxFriendsDao;
import com.rk.entity.WxFriends;
import com.rk.service.WxFriendsService;
import com.rk.util.DateUtil;
@Service
public class WxFriendsServiceImpl implements WxFriendsService{
	@Autowired
	private WxFriendsDao wxFriendsDao;

	@Override
	public PageInfo<WxFriends> findPage(int pageNum, int pageSize, Map<String, Object> params) {
		PageHelper.startPage(pageNum, pageSize);
		List<WxFriends> list = wxFriendsDao.findByParams(params);
		PageInfo<WxFriends> page = new PageInfo<WxFriends>(list);
		return page;
	}

	public List<WxFriends> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		return wxFriendsDao.findByParams(params);
	}

	public WxFriends get(int id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<WxFriends> list = wxFriendsDao.findByParams(params);
		if(list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public int save(WxFriends wxFriends) {
		wxFriends.setMakeDate(DateUtil.getCurrentStr());
		wxFriends.setMakeTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		wxFriends.setModifyDate(DateUtil.getCurrentStr());
		wxFriends.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return wxFriendsDao.save(wxFriends);
	}

	public int update(WxFriends wxFriends) {
		wxFriends.setModifyDate(DateUtil.getCurrentStr());
		wxFriends.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return wxFriendsDao.update(wxFriends);
	}

	@Override
	public WxFriends getMessBywcId(String wcId) {
		return wxFriendsDao.getMessByWcId(wcId);
	}

	@Override
	public List<WxFriends> getFriendsMessByWcIds(String wcId) {
		// TODO Auto-generated method stub
		return wxFriendsDao.getFriendsMessByWcIds(wcId);
	}

}
