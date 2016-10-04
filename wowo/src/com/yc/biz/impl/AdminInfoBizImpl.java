package com.yc.biz.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.bean.AdminInfo;
import com.yc.biz.IAdminInfoBiz;
import com.yc.dao.DBHelper;
import com.yc.dao.IAdminInfoDao;
import com.yc.dao.impl.AdminInfoDaoImpl;

public class AdminInfoBizImpl implements IAdminInfoBiz{

	@Override
	public AdminInfo login(String name, String pwd,String rid) {
		if(name==null||"".equals(name)){
			return null;
		}
		if(pwd==null||"".equals(pwd)){
			return null;
		}
		IAdminInfoDao adminInfoDao=new AdminInfoDaoImpl();
		return adminInfoDao.login(name, pwd, rid);
	}
	
	public List<AdminInfo> find() {
		IAdminInfoDao adminInfoDao=new AdminInfoDaoImpl();
		return adminInfoDao.find();
	}


	@Override
	public AdminInfo find(Integer aid) {
		DBHelper db=new DBHelper();
		List<Object> params=new ArrayList<Object>();
		params.add(aid);
		return db.findByOne("select *from adminInfos where aid=? and status=2", params, AdminInfo.class);
	}

	@Override
	public List<AdminInfo> find(Integer pageNo, Integer pageSize) {
		DBHelper db=new DBHelper();
		List<Object> params=new ArrayList<Object>();
		params.add(pageNo*pageSize);
		params.add((pageNo-1)*pageSize);
		String sql="select * from(select a.*,rownum as rn from (select * from adminInfos where status=2 order by aid desc)a where rownum<=?) where rn>?";
		return db.find(sql, params, AdminInfo.class);
	}

	@Override
	public List<AdminInfo> find(Integer rid, Integer pageNo, Integer pageSize) {
		DBHelper db=new DBHelper();
		String sql=null;
		List<Object> params=new ArrayList<Object>();
		if(rid==null && pageNo==null){
			sql="select *from adminInfos where  status=2 order by aid desc";
		}else if(rid!=null && pageNo==null){
			sql="select *from adminInfos where rid=? and status=2 order by aid desc"; 
		}else if(rid!=null && pageNo!=null){
			sql="select * from(select a.*,rownum as rn from (select * from adminInfos where  status=2 and rid=? order by aid desc)a where rownum<=?) where rn>?";
			params.add(rid);
			params.add(pageNo*pageSize);
			params.add((pageNo-1)*pageSize);
		}else if(rid==null && pageNo!=null){
			sql="select * from(select a.*,rownum as rn from (select * from adminInfos where  status=2 order by aid desc)a where rownum<=?) where rn>?";
			params.add(pageNo*pageSize);
			params.add((pageNo-1)*pageSize);
		}
		return db.find(sql,params,AdminInfo.class);
	}

	@Override
	public Integer update(String aid, String status, String mark) {
		DBHelper db=new DBHelper();
		String sql=null;
		List<Object> params=new ArrayList<Object>();
		if(aid.contains(",") && !aid.contains(" or")){
			sql="update adminInfo set status=?,mark=? where aid in("+aid+")";
			params.add(status);
			params.add(mark);
		}else{
			sql="update adminInfo set status=?,mark=? where aid=?";
			params.add(status);
			params.add(mark);
			params.add(aid);
		}
		return db.doUpdate(sql, params);
	}

	@Override
	public Integer update(Integer aid, String photo) {
		DBHelper db=new DBHelper();
		String sql="updae adminInfo set photo=? where aid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(photo);
		params.add(aid);
		return db.doUpdate(sql,params);
	}

	@Override
	public Integer update(String aname, String pwd) {
		IAdminInfoDao adminInfoDao=new AdminInfoDaoImpl();
		return adminInfoDao.update(aname,pwd);
	}

	@Override
	public Integer update(String aid) {
		DBHelper db=new DBHelper();
		String sql=null;
		List<Object> params=new ArrayList<Object>();
		if(aid.contains(",") && !aid.contains(" or")){
			sql="update adminInfo set pwd='aaaaaa' where aid in("+aid+")";
		}else{
			sql="update adminInfo set pwd='aaaaaa' where aid=?";
		}
		params.add(aid);
		return db.doUpdate(sql, params);
	}

	@Override
	public Integer update(String aname, String rid, String tel, String photo,String aid) {
		DBHelper db=new DBHelper();
		String sql="update adminInfo set aid=aid";
		List<Object> params=new ArrayList<Object>();
		if(aname!=null){
			sql+=",aname=?";
			params.add(aname);
		}
		
		if(rid!=null){
			sql+=",rid=?";
			params.add(rid);
		}
		
		if(tel!=null){
			sql+=",tel=?";
			params.add(tel);
		}
		
		if(photo!=null){
			sql+=",photo=?";
			params.add(photo);
		}
		sql+=" where aid=?";
		params.add(aid);
		return db.doUpdate(sql, params);
	}

	@Override
	public Integer add(String aname, String pwd, String rid, String email,
			String tel) {
		DBHelper db=new DBHelper();
		String sql=null;
		List<Object> params=new ArrayList<Object>();
		sql="insert into adminInfo values(seq_adminInfo_aid.nextval,?,?,?,?,?,'',2,'')";
		params.add(aname);
		params.add(pwd);
		params.add(rid);
		params.add(email);
		params.add(tel);
		
		return db.doUpdate(sql, params);
	}

	@Override
	public Integer del(String aid) {
		DBHelper db=new DBHelper();
		String sql=null;
		List<Object> params=new ArrayList<Object>();
		if(aid.contains(",") && !aid.contains(" or")){
			sql="update adminInfo set status=3 where aid in("+aid+")";
		}else{
			sql="update adminInfo set status=3 where aid=?";
			params.add(aid);
		}
		return db.doUpdate(sql, params);
	}

	@Override
	public Integer getTotal(Integer rid) {
		IAdminInfoDao adminInfoDao=new AdminInfoDaoImpl();
		return adminInfoDao.getTotal(rid);
	}

	@Override
	public Integer add(String aname, String pwd, String rid, String email,
			String tel, String photo) {
		DBHelper db=new DBHelper();
		String sql=null;
		List<Object> params=new ArrayList<Object>();
		sql="insert into adminInfo values(seq_adminInfo_aid.nextval,?,?,?,?,?,?,2,'')";
		params.add(aname);
		params.add(pwd);
		params.add(rid);
		params.add(email);
		params.add(tel);
		params.add(photo);
		
		return db.doUpdate(sql, params);
	}

	@Override
	public List<AdminInfo> find(Map<String, String> map, Integer pageNo,	Integer pageSize) {
		IAdminInfoDao adminInfoDao=new AdminInfoDaoImpl();
		return adminInfoDao.find(map, pageNo, pageSize);
	}
}
