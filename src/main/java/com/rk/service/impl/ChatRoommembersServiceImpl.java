package com.rk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rk.util.DateUtil;
import com.rk.dao.ChatRoommembersDao;
import com.rk.entity.ChatRoommembers;
import com.rk.service.ChatRoommembersService;

/**
 * 描述： （chat_roommembers） 接口实现
 * 
 * 作者： gjmctp
 * 时间： 2020-07-22 13:14:04
 */
@Service
public class ChatRoommembersServiceImpl implements ChatRoommembersService {

	@Autowired
	private ChatRoommembersDao chatRoommembersDao;

	public PageInfo<ChatRoommembers> findPage(int pageNum, int pageSize, Map<String, Object> params) {
		PageHelper.startPage(pageNum, pageSize);
		List<ChatRoommembers> list = chatRoommembersDao.findByParams(params);
		PageInfo<ChatRoommembers> page = new PageInfo<ChatRoommembers>(list);
		return page;
	}

	public List<ChatRoommembers> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		return chatRoommembersDao.findByParams(params);
	}

	public ChatRoommembers get(int id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<ChatRoommembers> list = chatRoommembersDao.findByParams(params);
		if(list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public int save(ChatRoommembers chatRoommembers) {
		chatRoommembers.setMakeDate(DateUtil.getCurrentStr());
		chatRoommembers.setMakeTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		chatRoommembers.setModifyDate(DateUtil.getCurrentStr());
		chatRoommembers.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return chatRoommembersDao.save(chatRoommembers);
	}

	public int update(ChatRoommembers chatRoommembers) {
		chatRoommembers.setModifyDate(DateUtil.getCurrentStr());
		chatRoommembers.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return chatRoommembersDao.update(chatRoommembers);
	}

	@Override
	public List<ChatRoommembers> getMessChatRooms(String roomId) {
		return chatRoommembersDao.getMessChatRooms(roomId);
	}

	@Override
	public ChatRoommembers getMessChatRoomsByUserId(String userId) {
		return chatRoommembersDao.getMessChatRoomsByUserId(userId);
	}

	@Override
	public ChatRoommembers getMesschatRoomsByDisplayName(String displayName) {
		// TODO Auto-generated method stub
		return chatRoommembersDao.getMesschatRoomsByDisplayName(displayName);
	}

}

