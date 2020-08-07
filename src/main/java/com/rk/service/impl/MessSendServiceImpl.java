package com.rk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rk.util.DateUtil;
import com.rk.entity.MessSend;
import com.rk.dao.MessSendDao;
import com.rk.service.MessSendService;

/**
 * 描述： （mess_send） 接口实现
 * 
 * 作者： gjmctp
 * 时间： 2020-07-23 16:39:56
 */
@Service
public class MessSendServiceImpl implements MessSendService {

	@Autowired
	private MessSendDao messSendDao;

	public PageInfo<MessSend> findPage(int pageNum, int pageSize, Map<String, Object> params) {
		PageHelper.startPage(pageNum, pageSize);
		List<MessSend> list = messSendDao.findByParams(params);
		PageInfo<MessSend> page = new PageInfo<MessSend>(list);
		return page;
	}

	public List<MessSend> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		return messSendDao.findByParams(params);
	}

	public MessSend get(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<MessSend> list = messSendDao.findByParams(params);
		if(list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public int save(MessSend messSend) {
		messSend.setMakeDate(DateUtil.getCurrentStr());
		messSend.setMakeTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		messSend.setModifyDate(DateUtil.getCurrentStr());
		messSend.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return messSendDao.save(messSend);
	}

	public int update(MessSend messSend) {
		messSend.setModifyDate(DateUtil.getCurrentStr());
		messSend.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return messSendDao.update(messSend);
	}

}

