package com.rk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rk.dao.WxGroupDao;
import com.rk.entity.WxGroup;
import com.rk.service.WxGroupService;
import com.rk.util.DateUtil;

/**
 * 描述： （wx_group） 接口实现
 * 
 * 作者： gjmctp
 * 时间： 2020-07-17 11:02:34
 */
@Service
public class WxGroupServiceImpl implements WxGroupService {

	@Autowired
	private WxGroupDao wxGroupDao;

	public PageInfo<WxGroup> findPage(int pageNum, int pageSize, Map<String, Object> params) {
		PageHelper.startPage(pageNum, pageSize);
		List<WxGroup> list = wxGroupDao.findByParams(params);
		PageInfo<WxGroup> page = new PageInfo<WxGroup>(list);
		return page;
	}

	public List<WxGroup> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		return wxGroupDao.findByParams(params);
	}

	public WxGroup get(int id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<WxGroup> list = wxGroupDao.findByParams(params);
		if(list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public int save(WxGroup wxGroup) {
		wxGroup.setMakeDate(DateUtil.getCurrentStr());
		wxGroup.setMakeTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		wxGroup.setModifyDate(DateUtil.getCurrentStr());
		wxGroup.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return wxGroupDao.save(wxGroup);
	}

	public int update(WxGroup wxGroup) {
		wxGroup.setModifyDate(DateUtil.getCurrentStr());
		wxGroup.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return wxGroupDao.update(wxGroup);
	}

	@Override
	public WxGroup getMessByRoomId(String roomId) {
		// TODO Auto-generated method stub
		return wxGroupDao.getMessByRoomId(roomId);
	}

	@Override
	public List<WxGroup> getGroupMessByWcId(String wcId) {
		// TODO Auto-generated method stub
		return wxGroupDao.getGroupMessByWcId(wcId);
	}

}

