package com.yc.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Goodstype;
import com.yc.bean.Roles;
import com.yc.biz.IRolesBiz;
import com.yc.biz.ITypesBiz;
import com.yc.biz.impl.RolesBizImpl;
import com.yc.biz.impl.TypesBizImpl;
import com.yc.utils.AttributeData;

public class TypesServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		if("addTypes".equals(op)){
			addType(request,response);
		}else if("editTypes".equals(op)){
			editTypes(request,response);
		}else if("findTypesByPage".equals(op)){
			findTypesByPage(request,response);
		}else if("delTypes".equals(op)){
			delTypes(request,response);
		}
	}

	private void delTypes(HttpServletRequest request,HttpServletResponse response) {
		String tid=request.getParameter("tid");
		ITypesBiz typesBiz=new TypesBizImpl();
		int result=typesBiz.del(tid);
		if(result>0){
			this.getServletContext().setAttribute(AttributeData.GOODSTYPE,typesBiz.find());
		}
		this.out(response, result);
	}

	private void findTypesByPage(HttpServletRequest request,HttpServletResponse response) {
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		ITypesBiz typesBiz=new TypesBizImpl();
		List<Goodstype> list=typesBiz.find(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		
		List<Goodstype> rs=(List<Goodstype>) this.getServletContext().getAttribute(AttributeData.GOODSTYPE);
		this.out(response, list,rs.size());
	}

	private void editTypes(HttpServletRequest request,HttpServletResponse response) {
		String tname=request.getParameter("tname");
		String des=request.getParameter("des");
		String status=request.getParameter("status");
		ITypesBiz typesBiz=new TypesBizImpl();
		int result=typesBiz.update(tname, des, Integer.parseInt(status));
		if(result>0){
			this.getServletContext().setAttribute(AttributeData.GOODSTYPE,typesBiz.find());
		}
		this.out(response, result);
	}

	private void addType(HttpServletRequest request,HttpServletResponse response) {
		String tname=request.getParameter("tname");
		String des=request.getParameter("des");
		String status=request.getParameter("status");
		ITypesBiz typesBiz=new TypesBizImpl();
		int result=typesBiz.add(tname, des, Integer.parseInt(status));
		if(result>0){
			this.getServletContext().setAttribute(AttributeData.GOODSTYPE,typesBiz.find());
		}
		this.out(response, result);
	}

}
