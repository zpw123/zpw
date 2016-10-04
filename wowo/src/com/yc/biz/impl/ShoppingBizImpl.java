package com.yc.biz.impl;

import java.util.List;

import com.yc.bean.Shopping;
import com.yc.biz.IShoppingBiz;
import com.yc.dao.IShoppingDao;
import com.yc.dao.impl.ShoppingDaoImpl;

public class ShoppingBizImpl implements IShoppingBiz{

	@Override
	public int add(String sname, int aid, int tid, String prov, String city,
			String area, String points, String tel,String info) {
		IShoppingDao shoppingDao=new ShoppingDaoImpl();
		return shoppingDao.add(sname, aid, tid, prov, city, area, points, tel ,info);
	}

	@Override
	public int del(String spid) {
		IShoppingDao shoppingDao=new ShoppingDaoImpl();
		return shoppingDao.del(spid);
	}

	@Override
	public int update(int spid,String sname,int tid, String prov, String city, String area,
			String points, String tel,String info) {
		IShoppingDao shoppingDao=new ShoppingDaoImpl();
		return shoppingDao.update(spid, sname, tid, prov, city, area, points, tel, info);
	}

	@Override
	public List<Shopping> find(int aid, int pageNo, int pageSize) {
		IShoppingDao shoppingDao=new ShoppingDaoImpl();
		return shoppingDao.find(aid, pageNo, pageSize);
	}

	//管理员操作店铺界面（--审核店铺信息界面--）
	public List<Shopping> find(int pageNo, int pageSize) {
		IShoppingDao shoppingDao=new ShoppingDaoImpl();
		return shoppingDao.find(pageNo, pageSize);
	}

	//管理员操作店铺界面的查询（--审核店铺信息界面--）
	public List<Shopping> find(int aid) {
		IShoppingDao shoppingDao=new ShoppingDaoImpl();
		return shoppingDao.find(aid);
	}

	@Override
	public int getTotal(int aid) {
		IShoppingDao shoppingDao=new ShoppingDaoImpl();
		return shoppingDao.getTotal(aid);
	}

	@Override
	public int update(int spid, int status) {
		IShoppingDao shoppingDao=new ShoppingDaoImpl();
		return shoppingDao.update(spid, status);
	}

	@Override
	public List<Shopping> find(String status, int pageNo, int pageSize) {
		IShoppingDao shoppingDao=new ShoppingDaoImpl();
		return shoppingDao.find(status, pageNo, pageSize);
	}
	
}
