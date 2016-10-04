package com.yc.biz.impl;

import java.util.List;
import java.util.Map;

import com.yc.bean.UserInfo;
import com.yc.biz.IUserInfoBiz;
import com.yc.dao.IUserInfoDao;
import com.yc.dao.impl.UserInfoDaoImpl;

public class UserInfoBizImpl implements IUserInfoBiz{
	public UserInfo findLogin(String uname, String pwd) {
		IUserInfoDao userInfo=new UserInfoDaoImpl();
		return userInfo.findLogin(uname, pwd);
	}

	@Override
	public Integer adduser(String email, String uname, String tel, String pwd,String prov, String city, String area) {
		IUserInfoDao userInfo=new UserInfoDaoImpl();
		return userInfo.adduser(email, uname, tel, pwd, prov, city, area);
	}

	@Override
	public List<UserInfo> find(Integer pageNo, Integer pageSize) {
		if(pageNo<=0){
			pageNo=1;
		}
		if(pageSize<=0){
			pageSize=10;
		}
		IUserInfoDao userInfo=new UserInfoDaoImpl();
		return userInfo.find(pageNo, pageSize);
	}

	public List<UserInfo> find(String uname, Integer pageNo, Integer pageSize) {
		if(pageNo<=0){
			pageNo=1;
		}
		if(pageSize<=0){
			pageSize=10;
		}
		IUserInfoDao userInfo=new UserInfoDaoImpl();
		return userInfo.find(uname,pageNo, pageSize);
	}

	@Override
	public List<UserInfo> find(Map<String, String> map, Integer pageNo,Integer pageSize) {
		IUserInfoDao userInfo=new UserInfoDaoImpl();
		return userInfo.find(map,pageNo, pageSize);
	}

	@Override
	public Integer update(String uname, String relname, String pwd, String tel,
			String photo, String email) {
		return null;
	}

	@Override
	public Integer del(String uid) {
		IUserInfoDao userInfo=new UserInfoDaoImpl();
		return userInfo.del(uid);
	}

	@Override
	public Integer getTotal(Integer uid) {
		IUserInfoDao userInfo=new UserInfoDaoImpl();
		return userInfo.getTotal(uid);
	}
	
}
