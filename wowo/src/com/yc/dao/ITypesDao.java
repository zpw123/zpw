package com.yc.dao;

import java.util.List;

import com.yc.bean.Goodstype;

public interface ITypesDao {
	public Integer add(String tname,String des,Integer status);
	
	public Integer del(String tid);
	
	public Integer update(String tname,String des,Integer status);
	
	public List<Goodstype> find(Integer pageNo,Integer pageSize);

	public List<Goodstype> find();
}
