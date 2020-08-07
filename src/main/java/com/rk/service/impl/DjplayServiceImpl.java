package com.rk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rk.dao.DjplayDao;
import com.rk.entity.Djplay;
import com.rk.service.DjplayService;
import com.rk.util.DateUtil;

/**
 * 描述： （djplay） 接口实现
 * 
 * 作者： gjmctp
 * 时间： 2020-08-06 10:26:06
 */
@Service
public class DjplayServiceImpl implements DjplayService {

	@Autowired
	private DjplayDao djplayMapper;

	public PageInfo<Djplay> findPage(int pageNum, int pageSize, Map<String, Object> params) {
		PageHelper.startPage(pageNum, pageSize);
		List<Djplay> list = djplayMapper.findByParams(params);
		PageInfo<Djplay> page = new PageInfo<Djplay>(list);
		return page;
	}

	public List<Djplay> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		return djplayMapper.findByParams(params);
	}

	public Djplay get(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<Djplay> list = djplayMapper.findByParams(params);
		if(list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public int save(Djplay djplay) {
		djplay.setMakeDate(DateUtil.getCurrentStr());
		djplay.setMakeTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		djplay.setModifyDate(DateUtil.getCurrentStr());
		djplay.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return djplayMapper.save(djplay);
	}

	public int update(Djplay djplay) {
		djplay.setModifyDate(DateUtil.getCurrentStr());
		djplay.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return djplayMapper.update(djplay);
	}

	@Override
	public Djplay getUserRoomCount(Djplay djplay) {
		// TODO Auto-generated method stub
		return djplayMapper.getUserRoomCount(djplay);
	}

}

