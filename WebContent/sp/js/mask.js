function showBox(id){
	var width = $(window).width();
	var height = $(window).height();
	$("#"+id).bind("click",function(){
		$(".mask").show();
		$(".mask").css("width",width+"px");
		$(".mask").css("height",height+"px");
		$(".personal-boxWrap").show();
	})
}
