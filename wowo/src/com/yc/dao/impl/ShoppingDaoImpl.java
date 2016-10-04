package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.Shopping;
import com.yc.dao.DBHelper;
import com.yc.dao.IShoppingDao;

public class ShoppingDaoImpl implements IShoppingDao{

	@Override
	public int add(String sname, int aid, int tid, String prov, String city,
			String area, String points, String tel,String info) {
		DBHelper db=new DBHelper();
		String sql="insert into shopping values(seq_shopping_spid.nextval,?,?,?,?,?,?,?,?,?,0)";
		List<Object> params=new ArrayList<Object>();
		params.add(sname);
		params.add(aid);
		params.add(tid);
		params.add(prov);
		params.add(city);
		params.add(area);
		params.add(points);
		params.add(tel);
		params.add(info);
		return db.doUpdate(sql, params);
	}

	@Override
	public int del(String spid) {
		DBHelper db=new DBHelper();
		String sql="";
		List<Object> params=new ArrayList<Object>();
		if(spid.equals(",")){
			sql="delete from shopping where spid in("+spid+")";
		}else{
			sql="delete from shopping where spid=?";
			params.add(spid);
		}
		return db.doUpdate(sql, params);
	}

	@Override
	public int update(int spid,String sname,int tid, String prov, String city, String area,String points, String tel,String info) {
		DBHelper db=new DBHelper();
		String sql="update shoppings set sname=?,tid=?,prov=?,city=?,area=?,points=?,tel=?,info=? where spid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(sname);
		params.add(tid);
		params.add(prov);
		params.add(city);
		params.add(area);
		params.add(points);
		params.add(tel);
		params.add(info);
		params.add(spid);
		return db.doUpdate(sql, params);
	}

	@Override
	public List<Shopping> find(int aid, int pageNo, int pageSize) {
		DBHelper db=new DBHelper();
		String sql="select * from (select a.*,rownum as rn from (select *from shoppings where aid=? order by spid asc) a where rownum<=?) where rn>?";
		List<Object> params=new ArrayList<Object>();
		params.add(aid);
		params.add(pageNo*pageSize);
		params.add((pageNo-1)*pageSize);
		return db.find(sql, params, Shopping.class);
	}

	@Override
	public List<Shopping> find(int pageNo, int pageSize) {
		DBHelper db=new DBHelper();
		String sql="select * from (select a.*,rownum as rn from (select *from shoppings order by spid asc) a where rownum<=?) where rn>?";
		List<Object> params=new ArrayList<Object>();
		params.add(pageNo*pageSize);
		params.add((pageNo-1)*pageSize);
		return db.find(sql, params, Shopping.class);
	}
	
	public List<Shopping> find(String status,int pageNo, int pageSize) {
		DBHelper db=new DBHelper();
		String sql="select * from (select a.*,rownum as rn from (select *from shoppings where status=0 order by spid asc) a where rownum<=?) where rn>?";
		List<Object> params=new ArrayList<Object>();
		params.add(pageNo*pageSize);
		params.add((pageNo-1)*pageSize);
		return db.find(sql, params, Shopping.class);
	}

	@Override
	public List<Shopping> find(int aid) {
		DBHelper db=new DBHelper();
		String sql="select * from shoppings where aid=? order by spid asc";
		List<Object> params=new ArrayList<Object>();
		params.add(aid);
		return db.find(sql, params, Shopping.class);
	}

	@Override
	public int getTotal(int aid) {
		DBHelper db=new DBHelper();
		String sql=null;
		List<Object> params=new ArrayList<Object>();
		if(aid==0){
			sql="select count(spid) from shoppings";
		}else{
			sql="select count(spid) from shoppings where aid=?";
			params.add(aid);
		}
		return db.findByOne(sql, params);
	}

	@Override
	public int update(int spid, int status) {
		DBHelper db=new DBHelper();
		String sql="update shopping set status=? where spid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(status);
		params.add(spid);
		return db.doUpdate(sql, params);
	}
	
}
