package com.yc.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.jsp.PageContext;

import sun.misc.BASE64Decoder;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;

public class UploadUtil {
	public static String PATH="../upload";
	private static final String ALLOWED="gif,png,jpg,txt,doc,xls";
	private static final String DENIDE="exe,bat,jsp,html";
	private static final int SINGLEFILESIZE=2*1024*1024;
	private static final int TOTALMAXSIZE=20*1024*1024;
	
	//把关联页面的的request上传到服务器（包括文件和普通的表单元素）
	public Map<String,String> upload(PageContext pagecontext){
		Map<String,String> map=new HashMap<String,String>();
		//new一个上传
		SmartUpload su=new SmartUpload();
		
		try {
			//限制上传的文件类型、大小等
			su.initialize(pagecontext);
			su.setDeniedFilesList(DENIDE);
			su.setAllowedFilesList(ALLOWED);
			su.setMaxFileSize(SINGLEFILESIZE);
			su.setTotalMaxFileSize(TOTALMAXSIZE);
			su.setCharset("utf-8");
			
			//开始上传
			su.upload();
			
			//获取上传文件的请求
			Request request=su.getRequest();
			
			//如果上传的文件是普通的表单元素...
			Enumeration<String> names=request.getParameterNames();
			String str;
			while(names.hasMoreElements()){
				str=names.nextElement();
				map.put(str, request.getParameter(str));
			}
			
			//如果上传的文件是文件...
			Files files=su.getFiles();
			if(files!=null && files.getCount()>0){
				Collection<File> cols=files.getCollection();
				String fname=null;
				String fpath="";
				String fieldName=null;
				for(File file:cols){
					if(!file.isMissing()){
						fname=new Date().getTime()+""+new Random().nextInt(1000)+"."+file.getFileExt();
						file.saveAs(PATH+"/"+fname);
						fpath+=PATH+"/"+fname+",";
						fieldName=file.getFieldName();
					}
				}
				if(fieldName!=null){
					if(fpath.contains(",")){
						fpath=fpath.substring(0, fpath.lastIndexOf(","));
					}
					map.put(fieldName, fpath);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
		
	}
	
	public String upload(PageContext pagecontext,String picData,String path){
		String realpath=null;
		BASE64Decoder base64=new BASE64Decoder();
		FileOutputStream fos=null;
		
		try {
			byte[] buffer=base64.decodeBuffer(picData);
			if(path==null){
				String fname=new Date().getTime()+""+new Random().nextInt(1000)+".png";
				String filePath=pagecontext.getServletContext().getRealPath(PATH+"/"+fname);
				fos=new FileOutputStream(filePath);
				realpath=PATH+"/"+fname;
			}else{
				fos=new FileOutputStream(path);
			}
			fos.write(buffer);
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
			realpath=null;
		}finally{
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return realpath;
	}
}
