$.fn.moveDiv=function(){
	var obj=$(this);
	var isDown=false;
	var cx=0,cy=0;
	var nowx=0,nowy=0;
	
	var y=obj.css("top");
	var x=obj.css("left");
	
	if(y=="auto"){
		y=0;
	}
	
	if(x=="auto"){
		x=($(window).width()-obj.outerWidth())/2;
	}
	
	obj.css({"position":"absolute","cursor":"move","top":y,"left":x});
	
	obj.bind({
		mousedown:function(e){
			isDown=true;
			cx=e.clientX;
			cy=e.clientY;
		},
		mouseup:function(){
			isDown=false;
			x=nowx;
			y=nowy;
		},
		mousemove:function(e){
			if(isDown){
				nowx=x+e.clientX-cx;
				nowy=y+e.clientY-cy;
				$(this).css({left:nowx,top:nowy});
			}
		}
	});
	
}