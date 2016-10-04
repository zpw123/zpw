package com.yc.dao;

import java.util.List;

import com.yc.bean.Message;

public interface IMessageDao {
	public int add(int status,int aid,int spit,String sname,String date,String title);
	
	public List<Message> find(int aid,int pageNo,int pageSize);
	
	public int del(String mid);
	
	public int getTotal(Integer aid);
}
