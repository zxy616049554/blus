package com.rk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rk.util.DateUtil;
import com.rk.dao.SumDao;
import com.rk.entity.Sum;
import com.rk.service.SumService;

/**
 * 描述： （sum） 接口实现
 * 
 * 作者： gjmctp
 * 时间： 2020-08-05 14:01:29
 */
@Service
public class SumServiceImpl implements SumService {

	@Autowired
	private SumDao sumDao;

	public PageInfo<Sum> findPage(int pageNum, int pageSize, Map<String, Object> params) {
		PageHelper.startPage(pageNum, pageSize);
		List<Sum> list = sumDao.findByParams(params);
		PageInfo<Sum> page = new PageInfo<Sum>(list);
		return page;
	}

	public List<Sum> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		return sumDao.findByParams(params);
	}

	public Sum get(int id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<Sum> list = sumDao.findByParams(params);
		if(list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public int save(Sum sum) {
		sum.setMakeDate(DateUtil.getCurrentStr());
		sum.setMakeTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		sum.setModifyDate(DateUtil.getCurrentStr());
		sum.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return sumDao.save(sum);
	}

	public int update(Sum sum) {
		sum.setModifyDate(DateUtil.getCurrentStr());
		sum.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return sumDao.update(sum);
	}

	@Override
	public Sum getMessByid(int sum) {
		// TODO Auto-generated method stub
		return sumDao.getMessByid(sum);
	}

}

