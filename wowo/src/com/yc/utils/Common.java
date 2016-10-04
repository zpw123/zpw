package com.yc.utils;

import org.apache.log4j.Logger;
//常用对象及方法类
public class Common {
	//提供日志功能
	public static Logger log=Logger.getLogger(Common.class);
	public static void error(Exception e){
		StackTraceElement[] sts=e.getStackTrace();
		log.error("======================================");
		log.error(e.getMessage());
		for(StackTraceElement ste:sts){
			log.error(ste.toString());
		}
	}
}
