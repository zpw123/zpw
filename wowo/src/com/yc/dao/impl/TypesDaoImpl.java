package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.Goodstype;
import com.yc.dao.DBHelper;
import com.yc.dao.ITypesDao;

public class TypesDaoImpl implements ITypesDao{

	@Override
	public Integer add(String tname, String des, Integer status) {
		DBHelper db=new DBHelper();
		String sql="";
		List<Object> params=new ArrayList<Object>();
		sql="insert into goodstype values(seq_goodstype_tid.nextval,?,?,?)";
		params.add(tname);
		params.add(des);
		params.add(status);
		return db.doUpdate(sql, params);
		
	}

	@Override
	public Integer del(String tid) {
		DBHelper db=new DBHelper();
		String sql="";
		List<Object> params=new ArrayList<Object>();
		if(tid.contains(",")){
			sql="delete from goodstype where tid in("+tid+")";
		}else{
			sql="delete from goodstype where tid=?";
			params.add(tid);
		}
		return db.doUpdate(sql,params);
	}

	@Override
	public Integer update(String tname, String des, Integer status) {
		DBHelper db=new DBHelper();
		String sql="update goodstype set tid=tid";
		List<Object> params=new ArrayList<Object>();
		if(tname!=null){
			sql+=",tname=?";
			params.add(tname);
		}
		
		if(des!=null){
			sql+=",des=?";
			params.add(des);
		}
		
		if(status!=null){
			sql+=",status=?";
			params.add(status);
		}
		System.out.println(sql);
		return db.doUpdate(sql, params);
	}

	@Override
	public List<Goodstype> find(Integer pageNo, Integer pageSize) {
		DBHelper db=new DBHelper();
		String sql="select * from (select t.*,rownum as rn from (select * from goodstype order by tid asc) t where rownum<=?) where rn>?";
		List<Object> params=new ArrayList<Object>();
		params.add(pageNo*pageSize);
		params.add((pageNo-1)*pageSize);
		
		return db.find(sql, params, Goodstype.class);
	}

	@Override
	public List<Goodstype> find() {
		DBHelper db=new DBHelper();
		String sql="select * from goodstype order by tid asc";
		return db.find(sql, null, Goodstype.class);
	}

}
