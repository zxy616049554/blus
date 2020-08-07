package com.rk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rk.util.DateUtil;
import com.rk.dao.MessReceivingDao;
import com.rk.entity.MessReceiving;
import com.rk.service.MessReceivingService;

/**
 * 描述： （mess_receiving） 接口实现
 * 
 * 作者： gjmctp
 * 时间： 2020-07-23 11:24:13
 */
@Service
public class MessReceivingServiceImpl implements MessReceivingService {

	@Autowired
	private MessReceivingDao messReceivingDao;

	public PageInfo<MessReceiving> findPage(int pageNum, int pageSize, Map<String, Object> params) {
		PageHelper.startPage(pageNum, pageSize);
		List<MessReceiving> list = messReceivingDao.findByParams(params);
		PageInfo<MessReceiving> page = new PageInfo<MessReceiving>(list);
		return page;
	}

	public List<MessReceiving> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		return messReceivingDao.findByParams(params);
	}

	public MessReceiving get(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<MessReceiving> list = messReceivingDao.findByParams(params);
		if(list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public int save(MessReceiving messReceiving) {
		messReceiving.setMakeDate(DateUtil.getCurrentStr());
		messReceiving.setMakeTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		messReceiving.setModifyDate(DateUtil.getCurrentStr());
		messReceiving.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return messReceivingDao.save(messReceiving);
	}

	public int update(MessReceiving messReceiving) {
		messReceiving.setModifyDate(DateUtil.getCurrentStr());
		messReceiving.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return messReceivingDao.update(messReceiving);
	}

	@Override
	public List<MessReceiving> getGroupMessage() {
		// TODO Auto-generated method stub
		return messReceivingDao.getGroupMessage();
	}

}

