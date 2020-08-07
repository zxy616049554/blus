package com.rk.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.rk.entity.Sum;

/**
 * 描述： 操作 （sum）
 * 
 * 作者： gjmctp
 * 时间： 2020-08-05 14:01:29
 */
@Repository
public interface SumDao {

	public List<Sum> findByParams(Map<String, Object> params);

	public int save(Sum sum);

    public int update(Sum sum);
    
    public Sum getMessByid(@Param("sum")int sum);
  
}