package com.rk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rk.util.DateUtil;
import com.rk.dao.ReplyMessageDao;
import com.rk.entity.MessReceiving;
import com.rk.entity.ReplyMessage;
import com.rk.service.ReplyMessageService;

/**
 * 描述： （reply_message） 接口实现
 * 
 * 作者： gjmctp
 * 时间： 2020-07-23 17:01:51
 */
@Service
public class ReplyMessageServiceImpl implements ReplyMessageService {

	@Autowired
	private ReplyMessageDao replyMessageDao;

	public PageInfo<ReplyMessage> findPage(int pageNum, int pageSize, Map<String, Object> params) {
		PageHelper.startPage(pageNum, pageSize);
		List<ReplyMessage> list = replyMessageDao.findByParams(params);
		PageInfo<ReplyMessage> page = new PageInfo<ReplyMessage>(list);
		return page;
	}

	public List<ReplyMessage> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		return replyMessageDao.findByParams(params);
	}

	public ReplyMessage get(int id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<ReplyMessage> list = replyMessageDao.findByParams(params);
		if(list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public int save(ReplyMessage replyMessage) {
		replyMessage.setMakeDate(DateUtil.getCurrentStr());
		replyMessage.setMakeTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		replyMessage.setModifyDate(DateUtil.getCurrentStr());
		replyMessage.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return replyMessageDao.save(replyMessage);
	}

	public int update(ReplyMessage replyMessage) {
		replyMessage.setModifyDate(DateUtil.getCurrentStr());
		replyMessage.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return replyMessageDao.update(replyMessage);
	}

	@Override
	public List<ReplyMessage> getMessByReceiveMess(String receiveMess) {
		// TODO Auto-generated method stub
		return replyMessageDao.getMessByReceiveMess(receiveMess);
	}

	@Override
	public ReplyMessage getMessByReceive(String receiveMess) {
		// TODO Auto-generated method stub
		return replyMessageDao.getMessByReceive(receiveMess);
	}

	@Override
	public int delete(int replyId) {
		// TODO Auto-generated method stub
		return replyMessageDao.delete(replyId);
	}


}

