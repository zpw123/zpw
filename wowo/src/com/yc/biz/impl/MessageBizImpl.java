package com.yc.biz.impl;

import java.util.Date;
import java.util.List;

import com.yc.bean.Message;
import com.yc.biz.IMessageBiz;
import com.yc.dao.IMessageDao;
import com.yc.dao.impl.MessageDaoImpl;

public class MessageBizImpl implements IMessageBiz{

	@Override
	public int add(int status,int aid,int spit, String sname, String date, String title) {
		IMessageDao messageDao=new MessageDaoImpl();
		return messageDao.add(status,aid,spit, sname, date, title);
	}

	@Override
	public List<Message> find(int aid, int pageNo, int pageSize) {
		IMessageDao messageDao=new MessageDaoImpl();
		return messageDao.find(aid, pageNo, pageSize);
	}

	@Override
	public int del(String mid) {
		IMessageDao messageDao=new MessageDaoImpl();
		return messageDao.del(mid);
	}

	@Override
	public int getTotal(Integer aid) {
		IMessageDao messageDao=new MessageDaoImpl();
		return messageDao.getTotal(aid);
	}
}
