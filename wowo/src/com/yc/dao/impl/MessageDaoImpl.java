package com.yc.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yc.bean.Message;
import com.yc.dao.DBHelper;
import com.yc.dao.IMessageDao;

public class MessageDaoImpl implements IMessageDao{

	@Override
	public int add(int status,int aid,int spit, String sname, String date, String title) {
		DBHelper db=new DBHelper();
		String sql="insert into message values(seq_message_mid.nextval,?,?,?,?)";
		List<Object> params=new ArrayList<Object>();
		String str="";
		if(status==1){
			str="未通过";
		}else if(status==2){
			str="通过了";
		}
		String content="您编号为"+spit+",店名为"+sname+"的商店"+str+"审核";
		System.out.println(content);
		params.add(title);
		params.add(content);
		params.add(date);
		params.add(aid);
		return db.doUpdate(sql, params);
	}

	@Override
	public List<Message> find(int aid, int pageNo, int pageSize) {
		DBHelper db=new DBHelper();
		String sql="select * from (select a.*,rownum as rn from (select *from message where aid=? order by mid asc) a where rownum<=?) where rn>?";
		List<Object> params=new ArrayList<Object>();
		params.add(aid);
		params.add(pageNo*pageSize);
		params.add((pageNo-1)*pageSize);
		return db.find(sql, params, Message.class);
	}

	@Override
	public int del(String mid) {
		DBHelper db=new DBHelper();
		String sql="";
		List<Object> params=new ArrayList<Object>();
		if(mid.contains(",")){
			sql="delete from message where mid in("+mid+")";
		}else{
			sql="delete from message where mid=?";
			params.add(mid);
		}
		return db.doUpdate(sql, params);
	}

	@Override
	public int getTotal(Integer aid) {
		DBHelper db=new DBHelper();
		String sql="";
		List<Object> params=new ArrayList<Object>();
		if(aid!=null){
			sql="select count(mid) from message where aid=?";
			params.add(aid);
		}else{
			sql="select count(mid) from message";
		}
		return db.findByOne(sql, params);
	}

}
