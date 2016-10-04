package com.yc.biz.impl;

import java.util.List;
import java.util.Map;

import com.yc.bean.Goods;
import com.yc.dao.IGoodsDao;
import com.yc.dao.impl.GoodsDaoImpl;

public class GoodsBizImpl implements IGoodsBiz{
	public int add(String gname, String des, String pic, int status, int aid,int spid) {
		IGoodsDao goodsDao=new GoodsDaoImpl();
		return goodsDao.add(gname, des, pic, status, aid, spid);
	}

	@Override
	public int del(String gid) {
		IGoodsDao goodsDao=new GoodsDaoImpl();
		return goodsDao.del(gid);
	}

	@Override
	public int update(int gid,int status,String gname, String des, String pic) {
		IGoodsDao goodsDao=new GoodsDaoImpl();
		return goodsDao.update(gid,status,gname, des, pic);
	}

	@Override
	public List<Goods> find(int aid, int pageNo, int pageSize) {
		IGoodsDao goodsDao=new GoodsDaoImpl();
		return goodsDao.find(aid, pageNo, pageSize);
	}

	@Override
	public List<Goods> find(Map<String, String> map, int pageNo, int pageSize) {
		IGoodsDao goodsDao=new GoodsDaoImpl();
		return goodsDao.find(map, pageNo, pageSize);
	}

	@Override
	public int getTotal(Integer aid) {
		IGoodsDao goodsDao=new GoodsDaoImpl();
		return goodsDao.getTotal(aid);
	}

	@Override
	public List<Goods> find() {
		IGoodsDao goodsDao=new GoodsDaoImpl();
		return goodsDao.find();
	}

	@Override
	public List<Goods> find(int gid) {
		IGoodsDao goodsDao=new GoodsDaoImpl();
		return goodsDao.find(gid);
	}

	@Override
	public int getIndex() {
		IGoodsDao goodsDao=new GoodsDaoImpl();
		return goodsDao.getIndex();
	}
}
