package com.yc.dao;

import java.util.List;
import java.util.Map;

import com.yc.bean.UserInfo;

public interface IUserInfoDao {
public UserInfo findLogin(String uname,String pwd);
	
	public List<UserInfo> find(Integer pageNo,Integer pageSize);
	
	public List<UserInfo> find(String uname,Integer pageNo,Integer pageSize);
	
	public List<UserInfo> find(Map<String,String> map,Integer pageNo,Integer pageSize); 
	
	public Integer update(String uname,String relname,String tel,String photo,String email);
	
	public Integer del(String uid);
	
	public Integer getTotal(Integer uid);
	
	public Integer adduser(String email,String uname,String tel,String pwd,String prov,String city,String area);
}
