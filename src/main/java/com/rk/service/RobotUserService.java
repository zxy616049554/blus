package com.rk.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.rk.entity.RobotUser;

/**
 * 描述： （robot_user） 接口
 * 
 * 作者： gjmctp
 * 时间： 2020-07-23 11:19:40
 */
public interface RobotUserService {

	public PageInfo<RobotUser> findPage(int pageNum, int pageSize, Map<String, Object> params);

	public List<RobotUser> findAll();

	public RobotUser get(Long id);

	public int save(RobotUser robotUser);

	public int update(RobotUser robotUser);

}