package com.yc.servlets;

import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import com.yc.utils.UploadUtil;

public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException{
		String path="";
		if(this.getInitParameter("uploadPath")!=null){
			path=this.getInitParameter("uploadPath");
		}
		File file=new File(this.getServletContext().getRealPath(path));
		if(!file.exists()){
			file.mkdirs();
		}
		UploadUtil.PATH=path;
	}
}
