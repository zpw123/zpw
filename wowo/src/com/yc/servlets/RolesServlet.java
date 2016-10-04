package com.yc.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Roles;
import com.yc.biz.IRolesBiz;
import com.yc.biz.impl.RolesBizImpl;
import com.yc.utils.AttributeData;

public class RolesServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		
		if("findAllRoles".equals(op)){
			findAllRoles(request,response);
		}else if("addRoles".equals(op)){
			addRoles(request,response);
		}else if("findRolesByPage".equals(op)){
			findRolesByPage(request,response);
		}else if("deleteRoles".equals(op)){
			deleteRoles(request,response);
		}else if("updateRoles".equals(op)){
			updateRoles(request,response);
		}
	}

	private void updateRoles(HttpServletRequest request,HttpServletResponse response) {
		String rname=request.getParameter("rname");
		String mark=request.getParameter("mark");
		String rid=request.getParameter("rid");
		IRolesBiz roleBiz=new RolesBizImpl();
		
		int result=roleBiz.update(rname, mark,rid);
		if(result>0){
			this.getServletContext().setAttribute(AttributeData.ALLROLES,roleBiz.find());
		}
		this.out(response, result);
	}

	private void deleteRoles(HttpServletRequest request,HttpServletResponse response) {
		String rid=request.getParameter("rid");
		IRolesBiz roleBiz=new RolesBizImpl();
		int result=(int)roleBiz.del(rid);
		if(result>0){
			this.getServletContext().setAttribute(AttributeData.ALLROLES,roleBiz.find());
		}
		this.out(response,result);
	}

	private void findRolesByPage(HttpServletRequest request,HttpServletResponse response) {
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		IRolesBiz roleBiz=new RolesBizImpl();
		List<Roles> list=roleBiz.find(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		
		List<Roles> rs=(List<Roles>) this.getServletContext().getAttribute(AttributeData.ALLROLES);
		this.out(response, list,rs.size());
	}

	private void addRoles(HttpServletRequest request,HttpServletResponse response) {
		String rname=request.getParameter("rname");
		String mark=request.getParameter("mark");
		IRolesBiz roleBiz=new RolesBizImpl();
		
		int result=roleBiz.add(rname, mark);
		if(result>0){
			this.getServletContext().setAttribute(AttributeData.ALLROLES,roleBiz.find());
		}
		this.out(response, result);
	}

	private void findAllRoles(HttpServletRequest request,HttpServletResponse response) {
		Object roles=this.getServletContext().getAttribute(AttributeData.ALLROLES);
		List<Roles> list=null;
		if(roles!=null){
			list=(List<Roles>)roles;
		}else{
			IRolesBiz roleBiz=new RolesBizImpl();
			list=roleBiz.find();
			this.getServletContext().setAttribute(AttributeData.ALLROLES,list);
		}
		this.out(response, list);
	}
}
