package com.yc.biz;

import java.util.List;
import java.util.Map;

import com.yc.bean.AdminInfo;

public interface IAdminInfoBiz {
	//用户登录
	public AdminInfo login(String name,String pwd,String rid);
	
	public List<AdminInfo> find();
	
	//根据编号查询管理员信息
	public AdminInfo find(Integer aid);

	//分页查询
	public List<AdminInfo> find(Integer pageNo,Integer pageSize);
	
	//根据角色分页查询
	public List<AdminInfo> find(Integer rid,Integer pageNo,Integer pageSize);
	
	//修改管理员状态
	public Integer update(String aid,String status,String mark); 
	
	//修改管理员图像
	public Integer update(Integer aid,String photo); 
	
	//修改管理员密码
	public Integer update(String aname, String pwd);
	
	//修改密码重置
	public Integer update(String aid);
	
	//修改管理员信息
	public Integer update(String aname,String rid,String tel,String photo,String aid);
	
	//添加管理员信息
	public Integer add(String aname,String pwd,String rid,String email,String tel);
	public Integer add(String aname,String pwd,String rid,String email,String tel,String photo);
	
	//删除管理员信息
	public Integer del(String aid);
	
	public Integer getTotal(Integer rid);
	
	public List<AdminInfo> find(Map<String,String> map,Integer pageNo,Integer pageSize); 
}
