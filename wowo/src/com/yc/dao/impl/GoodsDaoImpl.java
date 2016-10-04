package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.bean.Goods;
import com.yc.dao.DBHelper;
import com.yc.dao.IGoodsDao;

public class GoodsDaoImpl implements IGoodsDao{
	public int add(String gname, String des, String pic, int status, int aid,int spid) {
		DBHelper db=new DBHelper();
		String sql="insert into goods values(seq_goods_gid.nextval,?,?,?,?,0,?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(gname);
		params.add(des);
		params.add(status);
		params.add(pic);
		params.add(aid);
		params.add(spid);
		return db.doUpdate(sql, params);
	}

	@Override
	public int del(String gid) {
		DBHelper db=new DBHelper();
		String sql="";
		List<Object> params=new ArrayList<Object>();
		if(gid.contains(",")){
			sql="delete from goods where gid in("+gid+")";
		}else{
			sql="delete from goods where gid=?";
			params.add(gid);
		}
		return db.doUpdate(sql, params);
	}

	@Override
	public int update(int gid, int status,String gname, String des, String pic) {
		DBHelper db=new DBHelper();
		String sql="update goods set status=?,gname=?,des=?,pic=? where gid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(status);
		params.add(gname);
		params.add(des);
		params.add(pic);
		params.add(gid);
		return db.doUpdate(sql, params);
	}

	@Override
	public List<Goods> find(int aid, int pageNo, int pageSize) {
		DBHelper db=new DBHelper();
		String sql="select *from (select a.*,rownum as rn from (select *from goodss where aid=? order by gid asc) a where rownum<=?) where rn>?";
		List<Object> params=new ArrayList<Object>();
		params.add(aid);
		params.add(pageNo*pageSize);
		params.add((pageNo-1)*pageSize);
		return db.find(sql, params, Goods.class);
	}

	public List<Goods> find(int gid) {
		DBHelper db=new DBHelper();
		String sql="select *from goodss where gid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(gid);
		return db.find(sql, params, Goods.class);
	}
	
	@Override
	public List<Goods> find(Map<String, String> map, int pageNo, int pageSize) {
		return null;
	}

	@Override
	public int getTotal(Integer aid) {
		DBHelper db=new DBHelper();
		String sql=null;
		List<Object> params=new ArrayList<Object>();
		if(aid==null){
			sql="select count(gid) from goodss";
		}else{
			sql="select count(gid) from goodss where aid=?";
			params.add(aid);
		}
		return db.findByOne(sql, params);
	}

	@Override
	public List<Goods> find() {
		DBHelper db=new DBHelper();
		String sql="select *from goodss order by gid asc";
		List<Object> params=new ArrayList<Object>();
		return db.find(sql, params, Goods.class);
	}

	@Override
	public int getIndex(){
		DBHelper db=new DBHelper();
		String sql="select *from goodss";
		return db.getIndex(sql, null);
	}

	
}
