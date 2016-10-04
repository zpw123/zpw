package com.yc.biz.impl;

import java.util.List;
import java.util.Map;

import com.yc.bean.Orders;
import com.yc.biz.IOrdersBiz;
import com.yc.dao.IOrdersDao;
import com.yc.dao.impl.OrdersDaoImpl;

public class OrdersBizImpl implements IOrdersBiz {
	public int add(String dates, int usid, int gid, int nums, int totalprice){
		IOrdersDao ordersDao=new OrdersDaoImpl();
		return ordersDao.add(dates, usid, gid, nums, totalprice);
	}

	@Override
	public int del(String oid) {
		IOrdersDao ordersDao=new OrdersDaoImpl();
		return ordersDao.del(oid);
	}

	@Override
	public int update(int oid,  int status) {
		IOrdersDao ordersDao=new OrdersDaoImpl();
		return ordersDao.update(oid, status);
	}

	@Override
	public List<Orders> find(int usid, int pageNo, int pageSize) {
		IOrdersDao ordersDao=new OrdersDaoImpl();
		return ordersDao.find(usid, pageNo, pageSize);
	}

	@Override
	public List<Orders> find(Map<String, String> map, int pageNo, int pageSize) {
		IOrdersDao ordersDao=new OrdersDaoImpl();
		return ordersDao.find(map, pageNo, pageSize);
	}

	@Override
	public int getTotal(Integer usid) {
		IOrdersDao ordersDao=new OrdersDaoImpl();
		return ordersDao.getTotal(usid);
	}

	@Override
	public List<Orders> find(int pageNo, int pageSize) {
		IOrdersDao ordersDao=new OrdersDaoImpl();
		return ordersDao.find(pageNo, pageSize);
	}
	
}
