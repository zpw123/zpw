package com.yc.servlets;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

import com.yc.bean.Goods;
import com.yc.biz.impl.GoodsBizImpl;
import com.yc.biz.impl.IGoodsBiz;
import com.yc.dao.DBHelper;
import com.yc.utils.UploadUtil;

public class GoodsServlet extends BasicServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op=request.getParameter("op");
		if(op.equals("findGoodsByPage")){
			findGoodsByPage(request,response);
		}else if(op.equals("addGoods")){
			addGoods(request,response);
		}else if(op.equals("delGoods")){
			delGoods(request,response);
		}else if(op.equals("updateGoods")){
			updateGoods(request,response);
		}else if(op.equals("find")){
			find(request,response);
		}else if(op.equals("getGoodsOne")){
			getGoodsOne(request,response);
		}else if(op.equals("getGoodsMessage")){
			getGoodsMessage(request,response);
		}else if(op.equals("searchGoods")){
			searchGoods(request,response);
		}else if(op.equals("getIndex")){
			getIndex(request,response);
		}
	}

	private void getIndex(HttpServletRequest request,HttpServletResponse response) {
		IGoodsBiz goodsBiz=new GoodsBizImpl();
		int result=goodsBiz.getIndex();
		this.out(response, result);
	}

	private void searchGoods(HttpServletRequest request,HttpServletResponse response) {
		String name=request.getParameter("name");
		DBHelper db=new DBHelper();
		db.search(name);
	}

	private void getGoodsOne(HttpServletRequest request,HttpServletResponse response) {
		String gid=request.getParameter("gid");
		IGoodsBiz goodsBiz=new GoodsBizImpl();
		this.out(response, goodsBiz.find( Integer.parseInt(gid)));
	}
	
	private void getGoodsMessage(HttpServletRequest request,HttpServletResponse response) {
		String gid=request.getParameter("gid");
		IGoodsBiz goodsBiz=new GoodsBizImpl();
		this.out(response, goodsBiz.find(Integer.parseInt(gid)));
	}

	private void updateGoods(HttpServletRequest request,HttpServletResponse response) {
		PageContext pagecontext=JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 2048, true);
		UploadUtil upload=new UploadUtil();
		Map<String,String> map=upload.upload(pagecontext);
		IGoodsBiz goodsBiz=new GoodsBizImpl();
		this.out(response, goodsBiz.update( Integer.parseInt(map.get("gid")),  Integer.parseInt(map.get("status")), map.get("gname"), map.get("des"),  map.get("update_goods_pic")));
	}

	private void delGoods(HttpServletRequest request,HttpServletResponse response) {
		String gid=request.getParameter("gid");
		
		IGoodsBiz goodsBiz=new GoodsBizImpl();
		this.out(response, goodsBiz.del(gid));
	}

	private void addGoods(HttpServletRequest request,HttpServletResponse response) {
		PageContext pagecontext=JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 2048, true);
		UploadUtil upload=new UploadUtil();
		Map<String,String> map=upload.upload(pagecontext);
		IGoodsBiz goodsBiz=new GoodsBizImpl();
		this.out(response, goodsBiz.add(map.get("gname"), map.get("des"), map.get("goods_pic"), Integer.parseInt(map.get("price")), Integer.parseInt(map.get("aid")), Integer.parseInt(map.get("spid"))));
	}

	private void findGoodsByPage(HttpServletRequest request,HttpServletResponse response) {
		String pageNo=request.getParameter("page");
		String pageSize=request.getParameter("rows");
		String aid=request.getParameter("aid");
		
		IGoodsBiz goodsBiz=new GoodsBizImpl();
		List<Goods> list=goodsBiz.find(Integer.parseInt(aid), Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		this.out(response, list, goodsBiz.getTotal(Integer.parseInt(aid)));
	}
	
	private void find(HttpServletRequest request,HttpServletResponse response) {
		IGoodsBiz goodsBiz=new GoodsBizImpl();
		List<Goods> list=goodsBiz.find();
		this.out(response, list);
	}

}
