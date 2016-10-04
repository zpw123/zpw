$(function(){
	var xmldom = document.implementation.createDocument("","",null);
	xmldom.async = false;
	xmldom.load("../../city.xml");//这个文件不是放在和js文件一个目录下  要和html页面放在同一个目录下
	//开始解析；
	var provinces = xmldom.getElementsByTagName("province");
	for(var i=0;i<provinces.length;i++){
		addOption(provinces[i],province);
	}
	$("#province").change(function(){
		var code = province.value;
		city.length = 1;
		for(var i = 0 ; i < provinces.length ; i ++){
			if(provinces[i].nodeType == 1 && provinces[i].getAttribute("postcode") == code){
				var cities = provinces[i].childNodes;
				for(var j = 0 ; j < cities.length ; j ++){
					if(cities[j].nodeType == 1){
						addOption(cities[j],city);	
					}
				}	
			}	
		}	
	});
	$("#city").change(function(){
		var selectedProvince = province.value;//获取省份
		var code = city.value;					//获取城市
		district.length = 1;
		for(var i = 0 ; i < provinces.length ; i ++){
			if(provinces[i].nodeType == 1 && provinces[i].getAttribute("postcode") == selectedProvince){
				var cities = provinces[i].childNodes; 
				for(var j = 0 ; j < cities.length ; j ++){
					if(cities[j].nodeType == 1 && cities[j].getAttribute("postcode") == code){
						var areas = cities[j].childNodes;
						for(var k = 0 ; k < areas.length ; k ++){
							if(areas[k].nodeType == 1){
								addOption(areas[k],district);	
							}	
						}
					}
				}
			}
		}	
	});
	
	
});

function addOption(node,obj){
	var opt = document.createElement("option");
	opt.appendChild(document.createTextNode(node.getAttribute("name")));
	opt.setAttribute("value",node.getAttribute("postcode"));
	obj.appendChild(opt);	
}