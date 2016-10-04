package com.yc.listeners;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.yc.bean.Goodstype;
import com.yc.bean.Roles;
import com.yc.biz.IRolesBiz;
import com.yc.biz.ITypesBiz;
import com.yc.biz.impl.RolesBizImpl;
import com.yc.biz.impl.TypesBizImpl;
import com.yc.utils.AttributeData;

public class MyServletContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		arg0.getServletContext().removeAttribute(AttributeData.ALLROLES);
		arg0.getServletContext().removeAttribute(AttributeData.GOODSTYPE);
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		IRolesBiz roleBiz=new RolesBizImpl();
		List<Roles> roles=roleBiz.find();
		arg0.getServletContext().setAttribute(AttributeData.ALLROLES,roles);
		
		ITypesBiz typesBiz=new TypesBizImpl();
		List<Goodstype> goodstype=typesBiz.find();
		arg0.getServletContext().setAttribute(AttributeData.GOODSTYPE,goodstype);
	}

}
