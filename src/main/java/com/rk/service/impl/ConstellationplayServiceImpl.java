package com.rk.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rk.dao.ConstellationplayDao;
import com.rk.entity.Constellationplay;
import com.rk.service.ConstellationplayService;
import com.rk.util.DateUtil;

/**
 * 描述： （constellationplay） 接口实现
 * 
 * 作者： gjmctp
 * 时间： 2020-08-06 17:33:44
 */
@Service
public class ConstellationplayServiceImpl implements ConstellationplayService {

	@Autowired
	private ConstellationplayDao constellationplayMapper;

	public PageInfo<Constellationplay> findPage(int pageNum, int pageSize, Map<String, Object> params) {
		PageHelper.startPage(pageNum, pageSize);
		List<Constellationplay> list = constellationplayMapper.findByParams(params);
		PageInfo<Constellationplay> page = new PageInfo<Constellationplay>(list);
		return page;
	}

	public List<Constellationplay> findAll() {
		Map<String, Object> params = new HashMap<String, Object>();
		return constellationplayMapper.findByParams(params);
	}

	public Constellationplay get(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<Constellationplay> list = constellationplayMapper.findByParams(params);
		if(list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public int save(Constellationplay constellationplayDao) {
		constellationplayDao.setMakeDate(DateUtil.getCurrentStr());
		constellationplayDao.setMakeTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		constellationplayDao.setModifyDate(DateUtil.getCurrentStr());
		constellationplayDao.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return constellationplayMapper.save(constellationplayDao);
	}

	public int update(Constellationplay constellationplayDao) {
		constellationplayDao.setModifyDate(DateUtil.getCurrentStr());
		constellationplayDao.setModifyTime(DateUtil.getDateCurrent(DateUtil.FORMAT_DATE_TIME));
		return constellationplayMapper.update(constellationplayDao);
	}

	@Override
	public Constellationplay getConsetUserRoomCount(Constellationplay constellationplay) {
		// TODO Auto-generated method stub
		return constellationplayMapper.getConsetUserRoomCount(constellationplay);
	}

}

