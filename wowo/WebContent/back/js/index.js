$(function(){
	$.post("../../adminInfoServlet",{op:"getLoginInfo"},function(data){
		if(data==""||data==null){
			location.href="../login.html";
		}else{
			$("#index_loginuser").text(data.aname);
			$("#index_loginphoto").attr("src","../../"+data.photos);
		}	
	},"json");
	
	$('#index_content_info').tabs('add',{ 
		title:'源城信息',      
	    closable:true,
	    fit:true,
	    href:"yc.html",
	}); 
	
	$('#index_menu_tree').tree({   
	    onClick:function(node){
	    	var tabs=$('#index_content_info');
	    	if(node.id=="index_role"){
	    		if(tabs.tabs("exists","角色管理")){
	    			tabs.tabs("select","角色管理");
	    		}else{
	    			tabs.tabs('add',{ 
		    			title:'角色管理',      
		    		    closable:true,
		    		    fit:true,
		    		    href:"roles.html",
	    			}); 
	    		}
	    	}else if(node.id=="index_admin"){
	    		if(tabs.tabs("exists","管理员信息")){
	    			tabs.tabs("select","管理员信息");
	    		}else{
	    			tabs.tabs('add',{ 
		    			title:'管理员信息',      
		    		    closable:true,
		    		    fit:true,
		    		    href:"admin.html",
	    			}); 
	    		}
	    	}else if(node.id=="index_user"){
	    		if(tabs.tabs("exists","会员信息")){
	    			tabs.tabs("select","会员信息");
	    		}else{
	    			tabs.tabs('add',{ 
		    			title:'会员信息',      
		    		    closable:true,
		    		    fit:true,
		    		    href:"user.html",
	    			}); 
	    		}
	    	}else if(node.id=="index_types"){
	    		if(tabs.tabs("exists","商品类型信息")){
	    			tabs.tabs("select","商品类型信息");
	    		}else{
	    			tabs.tabs('add',{ 
		    			title:'商品类型信息',      
		    		    closable:true,
		    		    fit:true,
		    		    href:"types.html",
	    			}); 
	    		}
	    	}else if(node.id=="index_shopping3"){
	    		if(tabs.tabs("exists","管理店铺信息")){
	    			tabs.tabs("select","管理店铺信息");
	    		}else{
	    			tabs.tabs('add',{ 
		    			title:'管理店铺信息',      
		    		    closable:true,
		    		    fit:true,
		    		    href:"managershopping.html",
	    			}); 
	    		}
	    	}else if(node.id=="index_shopping2"){
	    		if(tabs.tabs("exists","查看店铺信息")){
	    			tabs.tabs("select","查看店铺信息");
	    		}else{
	    			tabs.tabs('add',{ 
		    			title:'查看店铺信息',      
		    		    closable:true,
		    		    fit:true,
		    		    href:"lookshopping.html",
	    			}); 
	    		}
	    	}else if(node.id=="index_shopping1"){
	    		if(tabs.tabs("exists","审核店铺信息")){
	    			tabs.tabs("select","审核店铺信息");
	    		}else{
	    			tabs.tabs('add',{ 
		    			title:'审核店铺信息',      
		    		    closable:true,
		    		    fit:true,
		    		    href:"throughshopping.html",
	    			}); 
	    		}
	    	}else if(node.id=="index_goods"){
	    		if(tabs.tabs("exists","编辑商品信息")){
	    			tabs.tabs("select","编辑商品信息");
	    		}else{
	    			tabs.tabs('add',{ 
		    			title:'编辑商品信息',      
		    		    closable:true,
		    		    fit:true,
		    		    href:"editgoods.html",
	    			}); 
	    		}
	    	}else if(node.id=="index_message"){
	    		if(tabs.tabs("exists","消息管理")){
	    			tabs.tabs("select","消息管理");
	    		}else{
	    			tabs.tabs('add',{ 
		    			title:'消息管理',      
		    		    closable:true,
		    		    fit:true,
		    		    href:"message.html",
	    			}); 
	    		}
	    	}else if(node.id=="index_order"){
	    		if(tabs.tabs("exists","订单管理")){
	    			tabs.tabs("select","订单管理");
	    		}else{
	    			tabs.tabs('add',{ 
		    			title:'订单管理',      
		    		    closable:true,
		    		    fit:true,
		    		    href:"orders.html",
	    			}); 
	    		}
	    	}
	    	
	    }
	});  
});

function edit(){
	$.post("../../adminInfoServlet",{op:"removeSession"},function(data){
		data=parseInt(data);
		if(data>0){
			location.href="../login.html";
		}else{
			alert("退出失败");
		}
	},"json");
}