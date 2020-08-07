package com.rk.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.rk.entity.RobotUser;

/**
 * 描述： 操作 （robot_user）
 * 
 * 作者： gjmctp
 * 时间： 2020-07-23 11:19:40
 */
@Repository
public interface RobotUserDao {

	public List<RobotUser> findByParams(Map<String, Object> params);

	public int save(RobotUser robotUser);

    public int update(RobotUser robotUser);
  
}