package com.yc.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.Orders;
import com.yc.biz.IOrdersBiz;
import com.yc.biz.impl.OrdersBizImpl;

public class OrdersServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		if("findOrdersByPage".equals(op)){
			findOrdersByPage(request,response);
		}else if("deleteOrder".equals(op)){
			deleteOrder(request,response);
		}else if("updateOrder".equals(op)){
			updateOrder(request,response);
		}else if("getOrders".equals(op)){
			getOrders(request,response);
		}
	}

	private void getOrders(HttpServletRequest request,HttpServletResponse response) {
		String usid=request.getParameter("usid");
		String totalprice=request.getParameter("totalprice");
		String gid=request.getParameter("gid");
		String nums=request.getParameter("nums");
		
		IOrdersBiz ordersBiz=new OrdersBizImpl();
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date1=sdf.format(date);
		this.out(response, ordersBiz.add(date1, Integer.parseInt(usid), Integer.parseInt(gid), Integer.parseInt(nums), Integer.parseInt(totalprice)));
	}

	private void updateOrder(HttpServletRequest request,HttpServletResponse response) {
		String oid=request.getParameter("oid");
		int status=1;
		IOrdersBiz ordersBiz=new OrdersBizImpl();
		this.out(response, ordersBiz.update(Integer.parseInt(oid), status));
	}

	private void deleteOrder(HttpServletRequest request,HttpServletResponse response) {
		String oid=request.getParameter("oid");
		IOrdersBiz ordersBiz=new OrdersBizImpl();
		int result=ordersBiz.del(oid);
		this.out(response, result);
	}

	private void findOrdersByPage(HttpServletRequest request,HttpServletResponse response) {
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		IOrdersBiz ordersBiz=new OrdersBizImpl();
		List<Orders> list=ordersBiz.find(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		this.out(response, list,ordersBiz.getTotal(null));
	}

}
