package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yc.bean.UserInfo;
import com.yc.dao.DBHelper;
import com.yc.dao.IUserInfoDao;

public class UserInfoDaoImpl implements IUserInfoDao{
	public UserInfo findLogin(String uname, String pwd) {
		DBHelper db=new DBHelper();
		String sql="select *from userInfo where uname=? and pwd=?";
		List<Object> params=new ArrayList<Object>(); 
		params.add(uname);
		params.add(pwd);
		return db.findByOne(sql, params, UserInfo.class);
	}

	@Override
	public Integer adduser(String email, String uname, String tel, String pwd,String prov, String city, String area) {
		DBHelper db=new DBHelper();
		String sql="insert into userInfo values(seq_userInfo_usid.nextval,?,'',?,?,'',?,?,?,?,0,2)";
		List<Object> params=new ArrayList<Object>(); 
		params.add(uname);
		params.add(pwd);
		params.add(tel);
		params.add(email);
		params.add(prov);
		params.add(city);
		params.add(area);
		
		return db.doUpdate(sql, params);
	}

	@Override
	public List<UserInfo> find(Integer pageNo, Integer pageSize) {
		DBHelper db=new DBHelper();
		String sql="select * from (select u.*,rownum as rn from (select *from userInfo where status=2 order by uid asc) u where rownum<=?) where rn>?";
		List<Object> params=new ArrayList<Object>(); 
		
		params.add(pageSize*pageNo);
		params.add((pageNo-1)*pageSize);
		
		return db.find(sql, params, UserInfo.class);
	}

	@Override
	public List<UserInfo> find(String  uname, Integer pageNo, Integer pageSize) {
		DBHelper db=new DBHelper();
		String sql="";
		List<Object> params=new ArrayList<Object>(); 
		if(uname==null||"".equals(uname)){
			sql="select * from (select u.*,rownum as rn from (select *from userInfo where order by uid asc) u where rownum<=?) where rn>?";
		}else{
			sql="select * from (select u.*,rownum as rn from (select *from userInfo where and uname=? order by uid asc) u where rownum<=?) where rn>?";
			params.add(uname);
		}
		if(pageNo!=null){
			params.add(pageSize*pageNo);
			params.add((pageNo-1)*pageSize);
		}
		return db.find(sql, params, UserInfo.class);
	}

	@Override
	public List<UserInfo> find(Map<String, String> map, Integer pageNo,Integer pageSize) {
		DBHelper db=new DBHelper();
		String sql="select * from userInfo";
		List<Object> params=new ArrayList<Object>(); 
		
		if(map!=null&&map.size()>0){
			Set<String> keys=map.keySet();
			sql+=" where 1=1";
			for(String key:keys){
				sql+=" and "+key+"?";
				params.add(map.get(key));
			}
		}
		sql+=" order by usid asc";
		if(pageNo!=null){
			sql="select * from (select u.*,rownum as rn from ("+sql+") u where rownum<=?) where rn>?";
			params.add(pageSize*pageNo);
			params.add((pageNo-1)*pageSize);
		}
		return db.find(sql, params, UserInfo.class);
	}

	@Override
	public Integer update(String uname, String relname, String tel,String photo, String email) {
		return null;
	}

	@Override
	public Integer del(String uid) {
		DBHelper db=new DBHelper();
		List<Object> params=new ArrayList<Object>();
		String sql="";
		if(uid.contains(",")){
			sql="delete from userInfo where usid in("+uid+")";
		}else{
			sql="delete from userInfo where usid=?";
			params.add(uid);
		}
		return db.doUpdate(sql, params);
	}

	@Override
	public Integer getTotal(Integer uid) {
		DBHelper db=new DBHelper();
		List<Object> params=new ArrayList<Object>();
		String sql="select count(uid) from adminInfo";
		return db.findByOne(sql, params);
	}
	
}
