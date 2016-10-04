package com.yc.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.AdminInfo;
import com.yc.bean.Goodstype;
import com.yc.bean.Message;
import com.yc.bean.Shopping;
import com.yc.biz.IMessageBiz;
import com.yc.biz.IShoppingBiz;
import com.yc.biz.impl.MessageBizImpl;
import com.yc.biz.impl.ShoppingBizImpl;
import com.yc.utils.AttributeData;

public class ShoppingServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		if(op.equals("getAdmin")){
			getAdmin(request,response);
		}else if(op.equals("getType")){
			getType(request,response);
		}else if(op.equals("addShopping")){
			addShopping(request,response);
		}else if(op.equals("findShoppingByPage")){
			findShoppingByPage(request,response);
		}else if(op.equals("findShoppingAllByPage")){
			findShoppingAllByPage(request,response);
		}else if(op.equals("updateShopping")){
			updateShopping(request,response);
		}else if(op.equals("adminsLookshopping")){
			adminsLookshopping(request,response);
		}else if(op.equals("changeStatus")){
			changeStatus(request,response);
		}else if(op.equals("addMessage")){
			addMessage(request,response);
		}else if(op.equals("findMessageByPage")){
			findMessageByPage(request,response);
		}else if(op.equals("delMessage")){
			delMessage(request,response);
		}else if(op.equals("getshoppings")){
			getshoppings(request,response);
		}else if(op.equals("delShopping")){
			delShopping(request,response);
		}
	}

	private void delShopping(HttpServletRequest request,HttpServletResponse response) {
		String spid=request.getParameter("spid");
		IShoppingBiz shoppingBiz=new ShoppingBizImpl();
		this.out(response, shoppingBiz.del(spid));
	}

	private void getshoppings(HttpServletRequest request,HttpServletResponse response) {
		String aid=request.getParameter("aid");
		IShoppingBiz shoppingBiz=new ShoppingBizImpl();
		this.out(response, shoppingBiz.find(Integer.parseInt(aid)));
	}

	private void delMessage(HttpServletRequest request,HttpServletResponse response) {
		String mid=request.getParameter("mid");
		IMessageBiz messageBiz=new MessageBizImpl();
		this.out(response, messageBiz.del(mid));
	}

	private void findMessageByPage(HttpServletRequest request,HttpServletResponse response) {
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		String aid=request.getParameter("aid");
		IMessageBiz messageBiz=new MessageBizImpl();
		List<Message> list=messageBiz.find(Integer.parseInt(aid),Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		this.out(response, list, messageBiz.getTotal(Integer.parseInt(aid)));
	}

	private void addMessage(HttpServletRequest request,HttpServletResponse response) {
		String sname=request.getParameter("sname");
		String spid=request.getParameter("spid");
		String status=request.getParameter("status");
		String aid=request.getParameter("aid");	
		String title=request.getParameter("title");		
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		IMessageBiz messageBiz=new MessageBizImpl();
		this.out(response, messageBiz.add(Integer.parseInt(status),Integer.parseInt(aid), Integer.parseInt(spid), sname, sdf.format(date), title));
	}

	private void changeStatus(HttpServletRequest request,HttpServletResponse response) {
		String status=request.getParameter("status");
		String spid=request.getParameter("spid");	
		IShoppingBiz shoppingBiz=new ShoppingBizImpl();
		this.out(response, shoppingBiz.update(Integer.parseInt(spid), Integer.parseInt(status)));
	}

	private void adminsLookshopping(HttpServletRequest request,HttpServletResponse response) {
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		String status=request.getParameter("status");
		IShoppingBiz shoppingBiz=new ShoppingBizImpl();
		List<Shopping> list=shoppingBiz.find(status,Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		this.out(response, list, shoppingBiz.getTotal(0));
	}

	private void updateShopping(HttpServletRequest request,HttpServletResponse response) {
		String sname=request.getParameter("sname");
		String tid=request.getParameter("tid");
		String city=request.getParameter("city");
		String prov=request.getParameter("prov");
		String area=request.getParameter("area");
		String addr=request.getParameter("addr");
		String tel=request.getParameter("tel");
		String spid=request.getParameter("spid");
		String shopmessage=request.getParameter("shopmessage");
		
		IShoppingBiz shoppingBiz=new ShoppingBizImpl();
		this.out(response, shoppingBiz.update(Integer.parseInt(spid), sname, Integer.parseInt(tid), prov, city, area, addr, tel, shopmessage));
	}

	private void findShoppingAllByPage(HttpServletRequest request,HttpServletResponse response) {
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		IShoppingBiz shoppingBiz=new ShoppingBizImpl();
		List<Shopping> list=shoppingBiz.find(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		this.out(response, list, shoppingBiz.getTotal(0));
	}

	private void findShoppingByPage(HttpServletRequest request,HttpServletResponse response) {
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		String aid=request.getParameter("aid");
		IShoppingBiz shoppingBiz=new ShoppingBizImpl();
		List<Shopping> list=shoppingBiz.find(Integer.parseInt(aid),Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		this.out(response, list, shoppingBiz.getTotal(Integer.parseInt(aid)));
	}

	private void addShopping(HttpServletRequest request,HttpServletResponse response) {
		String sname=request.getParameter("sname");
		String aid=request.getParameter("aid");
		String tid=request.getParameter("tid");
		String city=request.getParameter("city");
		String prov=request.getParameter("prov");
		String area=request.getParameter("area");
		String addr=request.getParameter("addr");
		String tel=request.getParameter("tel");
		String shopmessage=request.getParameter("shopmessage");
		
		IShoppingBiz shoppingBiz=new ShoppingBizImpl();
		this.out(response, shoppingBiz.add(sname, Integer.parseInt(aid), Integer.parseInt(tid), prov, city, area, addr, tel, shopmessage));
	}

	private void getType(HttpServletRequest request,HttpServletResponse response) {
		List<Goodstype> types=(List<Goodstype>) this.getServletContext().getAttribute(AttributeData.GOODSTYPE);
		this.out(response, types);
	}

	private void getAdmin(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session=request.getSession();
		Object obj=session.getAttribute(AttributeData.CURRENTADMTNLOGiN);
		if(obj==null){
			this.out(response, obj);
		}else{
			this.out(response, (AdminInfo)obj);
		}
		
	}

}
