package com.yc.biz.impl;

import java.util.List;

import com.yc.bean.Goodstype;
import com.yc.biz.ITypesBiz;
import com.yc.dao.ITypesDao;
import com.yc.dao.impl.TypesDaoImpl;

public class TypesBizImpl implements ITypesBiz{

	@Override
	public Integer add(String tname, String des, Integer status) {
		ITypesDao typesDao=new TypesDaoImpl();
		return typesDao.add(tname, des, status);
	}

	@Override
	public Integer del(String tid) {
		if(tid==null||"".equals(tid)){
			return 0;
		}
		ITypesDao typesDao=new TypesDaoImpl();
		return typesDao.del(tid);
	}

	@Override
	public Integer update(String tname, String des, Integer status) {
		ITypesDao typesDao=new TypesDaoImpl();
		return typesDao.update(tname, des, status);
	}

	@Override
	public List<Goodstype> find(Integer pageNo, Integer pageSize) {
		if(pageNo<=0){
			pageNo=1;
		}
		if(pageSize<=0){
			pageSize=10;
		}
		ITypesDao typesDao=new TypesDaoImpl();
		return typesDao.find(pageNo, pageSize);
	}

	@Override
	public List<Goodstype> find() {
		ITypesDao typesDao=new TypesDaoImpl();
		return typesDao.find();
	}

}
