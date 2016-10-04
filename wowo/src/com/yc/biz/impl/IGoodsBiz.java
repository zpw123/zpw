package com.yc.biz.impl;

import java.util.List;
import java.util.Map;

import com.yc.bean.Goods;

public interface IGoodsBiz {
	public int add(String gname, String des, String pic, int status, int aid,int spid);
	
	public int del(String gid);
	
	public int update(int gid,int status,String gname,String des,String pic);
	
	public List<Goods> find(int aid,int pageNo,int pageSize);
	
	public List<Goods> find(Map<String,String> map,int pageNo,int pageSize);
	
	public int getTotal(Integer aid);

	public List<Goods> find();
	
	public List<Goods> find(int gid);

	public int getIndex();
}
