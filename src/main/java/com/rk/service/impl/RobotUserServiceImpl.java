package com.rk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rk.util.DateUtil;
import com.rk.entity.RobotUser;
import com.rk.dao.RobotUserDao;
import com.rk.service.RobotUserService;

/**
 * 描述： （robot_user） 接口实现
 * 
 * 作者： gjmctp
 * 时间： 2020-07-23 11:19:40
 */
@Service
public class RobotUserServiceImpl implements RobotUserService {

	@Autowired
	private RobotUserDao robotUserDao;

	public PageInfo<RobotUser> findPage(int pageNum, int pageSize, Map<String, Object> params) {
		PageHelper.startPage(pageNum, pageSize);
		List<RobotUser> list = robotUserDao.findByParams(params);
		PageInfo<RobotUser> page = new PageInfo<RobotUser>(list);
		return page;
	}

	public List<RobotUser> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		return robotUserDao.findByParams(params);
	}

	public RobotUser get(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<RobotUser> list = robotUserDao.findByParams(params);
		if(list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public int save(RobotUser robotUser) {
		robotUser.setMakeDate(DateUtil.getCurrentStr());
		robotUser.setMakeTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		robotUser.setModifyDate(DateUtil.getCurrentStr());
		robotUser.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return robotUserDao.save(robotUser);
	}

	public int update(RobotUser robotUser) {
		robotUser.setModifyDate(DateUtil.getCurrentStr());
		robotUser.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return robotUserDao.update(robotUser);
	}

}

