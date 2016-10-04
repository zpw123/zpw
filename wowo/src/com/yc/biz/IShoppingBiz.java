package com.yc.biz;

import java.util.List;

import com.yc.bean.Shopping;

public interface IShoppingBiz {
	public int add(String sname,int aid, int tid, String prov,String city,String area,String points,String tel,String info);
	
	public int del(String spid);
	
	public int update(int spid,String sname,int tid, String prov, String city, String area,String points, String tel,String info);
	
	public int update(int spid,int status);
	
	public List<Shopping> find(int aid,int pageNo,int pageSize);
	
	public List<Shopping> find(int pageNo,int pageSize);
	
	public List<Shopping> find(String status,int pageNo, int pageSize);
	
	public List<Shopping> find(int aid);
	
	public int getTotal(int aid);
}
