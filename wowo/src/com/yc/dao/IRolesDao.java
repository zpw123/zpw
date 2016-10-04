package com.yc.dao;

import java.util.List;

import com.yc.bean.Roles;

public interface IRolesDao {
	//查找所有的角色信息
	public List<Roles> find();
	
	//分页查询
	public List<Roles> find(Integer pageNo,Integer pageSize);
	
	//添加角色
	public Integer add(String rname,String mark);
	
	//根据ID删除角色
	public Integer del(String rid);
	
	//修改角色
	public Integer update(String rname,String mark,String rid);
}
