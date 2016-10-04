package com.yc.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.bean.AdminInfo;
import com.yc.bean.UserInfo;
import com.yc.biz.IUserInfoBiz;
import com.yc.biz.impl.UserInfoBizImpl;
import com.yc.utils.AttributeData;

public class UserInfoServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		if(op.equals("userLogin")){
			userLogin(request,response);
		}else if(op.equals("getUserLogin")){
			getUserLogin(request,response);
		}else if(op.equals("userReg")){
			userReg(request,response);
		}else if(op.equals("findUserInfoByPage")){
			findUserInfoByPage(request,response);
		}else if(op.equals("delUserInfo")){
			delUserInfo(request,response);
		}else if(op.equals("searchUserInfoByPage")){
			searchUserInfoByPage(request,response);
		}
		
	}

	private void searchUserInfoByPage(HttpServletRequest request,HttpServletResponse response) {
		String uname=request.getParameter("uname");
		String status=request.getParameter("status");
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		
		Map<String,String> map=new HashMap<String,String>();
		if(!"".equals(uname) && uname!=null){
			map.put("uname like ","%"+uname+"%");
		}
		
		if(!"-1".equals(status)){
			map.put("status=", status);
		}
		
		IUserInfoBiz userInfoBiz=new UserInfoBizImpl();
		List<UserInfo> result=userInfoBiz.find(map,Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		List<UserInfo> list=userInfoBiz.find(map, null, null);
		this.out(response, result, list.size());
	}

	private void delUserInfo(HttpServletRequest request,HttpServletResponse response) {
		String uid=request.getParameter("uid");
		IUserInfoBiz userInfoBiz=new UserInfoBizImpl();
		int result=userInfoBiz.del(uid);
		this.out(response, result);
	}

	private void findUserInfoByPage(HttpServletRequest request,HttpServletResponse response) {
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		IUserInfoBiz userInfoBiz=new UserInfoBizImpl();
		List<UserInfo> list=userInfoBiz.find(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		this.out(response, list,userInfoBiz.getTotal(null));
	}

	private void userReg(HttpServletRequest request,HttpServletResponse response) {
		String email=request.getParameter("email");
		String uname=request.getParameter("uname");
		String tel=request.getParameter("tel");
		String pwd=request.getParameter("pwd");
		String prov=request.getParameter("prov");
		String city=request.getParameter("city");
		String area=request.getParameter("area");
				
		IUserInfoBiz userInfoBiz=new UserInfoBizImpl();
		int result=userInfoBiz.adduser(email, uname, tel, pwd, prov, city, area);
		if(result>0){
			this.out(response, result);
		}
	}

	private void getUserLogin(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session=request.getSession();
		Object obj=session.getAttribute(AttributeData.USERLOGIN);
		if(obj==null){
			return;
		}else{
			this.out(response, (UserInfo)obj);
		}
	}

	private void userLogin(HttpServletRequest request,HttpServletResponse response) {
		String uname=request.getParameter("uname");
		String pwd=request.getParameter("pwd");
		String code=request.getParameter("code");
		
		HttpSession session=request.getSession();
		String codes=(String) session.getAttribute("rand");
		int result=0;
		if(codes.equals(code)){
			IUserInfoBiz userInfoBiz=new UserInfoBizImpl();
			UserInfo userInfo=userInfoBiz.findLogin(uname, pwd);
			if(userInfo==null){
				result=2;
			}else{
				result=3;
				HttpSession session1=request.getSession();
				session1.setAttribute(AttributeData.USERLOGIN, userInfo);
			}
		}else{
			result=1;
		}
		out(response, result);
	}

}
