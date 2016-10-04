package com.yc.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.yc.bean.AdminInfo;
import com.yc.biz.IAdminInfoBiz;
import com.yc.biz.impl.AdminInfoBizImpl;
import com.yc.utils.AttributeData;
import com.yc.utils.UploadUtil;

public class AdminInfoServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		if("adminLogin".equals(op)){
			adminLogin(request,response);
		}else if("getLoginInfo".equals(op)){
			getLoginInfo(request,response);
		}else if("getLoginInfo1".equals(op)){
			getLoginInfo1(request,response);
		}else if("findAdminInfoByPage".equals(op)){
			findAdminInfoByPage(request,response);
		}else if("addAdminInfo".equals(op)){
			addAdminInfo(request,response);
		}else if(op.equals("addAdmin")){
			addAdmin(request,response);
		}else if(op.equals("updateAdminInfo")){
			updateAdminInfo(request,response);
		}else if(op.equals("searchAdminInfoByPage")){
			searchAdminInfoByPage(request,response);
		}else if(op.equals("removeSession")){
			removeSession(request,response);
		}else if(op.equals("deleteAdmin")){
			deleteAdmin(request,response);
		}else if(op.equals("changepwd")){
			changepwd(request,response);
		}
	}
	
	private void changepwd(HttpServletRequest request,HttpServletResponse response) {
		String code=request.getParameter("code");
		String pwd=request.getParameter("pwd");
		String aname=request.getParameter("aname");
		HttpSession session=request.getSession();
		String codes=(String) session.getAttribute("sendCode");
		int result;
		if(codes==code){
			result=1;
			IAdminInfoBiz adminInfoBiz=new AdminInfoBizImpl();
			adminInfoBiz.update(aname, pwd);
			this.out(response, result);
		}else{
			result=0;
			this.out(response, result);
		}
	}

	private void deleteAdmin(HttpServletRequest request,HttpServletResponse response) {
		String aid=request.getParameter("aid");
		
		IAdminInfoBiz adminInfoBiz=new AdminInfoBizImpl();
		int result=adminInfoBiz.del(aid);
		this.out(response, result);
	}

	private void removeSession(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session=request.getSession();
		int result=0;
		if(session!=null){
			session.removeAttribute(AttributeData.CURRENTADMTNLOGiN);
			result=1;
		}
		this.out(response, result);
	}

	private void searchAdminInfoByPage(HttpServletRequest request,HttpServletResponse response) {
		String rid=request.getParameter("rid");
		String aname=request.getParameter("aname");
		String status=request.getParameter("status");
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		
		Map<String,String> map=new HashMap<String,String>();
		
		if(!"-1".equals(rid)){
			map.put("rid=", rid);
		}
		
		if(!"".equals(aname) && aname!=null){
			map.put("aname like ", "%"+aname+"%");
		}
		
		if(!"-1".equals(status)){
			map.put("status=", status);
		}
		
		IAdminInfoBiz adminInfoBiz=new AdminInfoBizImpl();
		List<AdminInfo> list=adminInfoBiz.find(map, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		List<AdminInfo> list1=adminInfoBiz.find(map, null, null);
		this.out(response, list, list1.size());
	}

	private void updateAdminInfo(HttpServletRequest request,HttpServletResponse response) {
		UploadUtil upload=new UploadUtil();
		PageContext pagecontext=JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 2048, true);
		Map<String,String> map=upload.upload(pagecontext);
		IAdminInfoBiz adminInfoBiz=new AdminInfoBizImpl();
		int result=adminInfoBiz.update(map.get("aname"), map.get("rid"), map.get("tel"), map.get("photo"), map.get("aid"));
		this.out(response,result);
	}

	private void addAdmin(HttpServletRequest request,HttpServletResponse response) {
		String role=request.getParameter("role");
		String rname=request.getParameter("rname");
		String rpwds=request.getParameter("rpwds");
		String email=request.getParameter("email");
		String tel=request.getParameter("tel");
		
		IAdminInfoBiz adminInfoBiz=new AdminInfoBizImpl();
		int result=adminInfoBiz.add(rname, rpwds, role, email, tel);
		if(result>0){
			result=1;
			out(response, result);
		}else{
			result=0;
			out(response, result);
		}
	}

	private void addAdminInfo(HttpServletRequest request,HttpServletResponse response) {
		String rid=request.getParameter("rid");
		String aname=request.getParameter("aname");
		String pwd=request.getParameter("pwd");
		String email=request.getParameter("email");
		String tel=request.getParameter("tel");
		String photo=request.getParameter("photo");
		PageContext pagecontext=JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 2048, true);
		int result = 0;
		
		if(photo==""){
			photo="../../../images/1.jpg";
		}else{
			UploadUtil upload=new UploadUtil();
			photo=upload.upload(pagecontext, photo, null);
			IAdminInfoBiz adminInfoBiz=new AdminInfoBizImpl();
			result=adminInfoBiz.add(aname, pwd, rid, email, tel,photo);
		}
		out(response, result);
	}

	private void findAdminInfoByPage(HttpServletRequest request,HttpServletResponse response) {
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		IAdminInfoBiz adminInfoBiz=new AdminInfoBizImpl();
		List<AdminInfo> list=adminInfoBiz.find(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		this.out(response, list,adminInfoBiz.getTotal(null));
	}

	private void getLoginInfo1(HttpServletRequest request,HttpServletResponse response) {
		IAdminInfoBiz adminInfoBiz=new AdminInfoBizImpl();
		List<AdminInfo> result=adminInfoBiz.find();
		this.out(response, result);
	}

	private void getLoginInfo(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session=request.getSession();
		Object obj=session.getAttribute(AttributeData.CURRENTADMTNLOGiN);
		if(obj==null){
			this.out(response, obj);
		}else{
			this.out(response, (AdminInfo)obj);
		}
	}

	private void adminLogin(HttpServletRequest request,HttpServletResponse response) {
		String role=request.getParameter("role");
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		String code=request.getParameter("code");
		
		HttpSession session=request.getSession();
		String codes=(String) session.getAttribute("rand");
		int result=0;
		if(codes.equals(code)){
			IAdminInfoBiz adminInfoBiz=new AdminInfoBizImpl();
			AdminInfo adminInfo=adminInfoBiz.login(name, pwd, role);
			if(adminInfo==null){
				result=2;
			}else{
				result=3;
				session.setAttribute(AttributeData.CURRENTADMTNLOGiN, adminInfo);
			}
		}else{
			result=1;
		}
		this.out(response, result);
	}

}
