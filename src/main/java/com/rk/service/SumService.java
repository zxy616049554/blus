package com.rk.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.rk.entity.Sum;

/**
 * 描述： （sum） 接口
 * 
 * 作者： gjmctp
 * 时间： 2020-08-05 14:01:29
 */
public interface SumService {

	public PageInfo<Sum> findPage(int pageNum, int pageSize, Map<String, Object> params);

	public List<Sum> findAll();

	public Sum get(int id);

	public int save(Sum sum);

	public int update(Sum sum);

	public Sum getMessByid(int sum);
}