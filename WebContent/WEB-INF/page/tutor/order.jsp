<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>导师平台</title>
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
$(function(){
	$.ajax({
		url:"/tutor/data/order.html",
		type:"POST",
		dataType:"json",
		success:function(res){
			console.log(res);
			var htmlStr = "";
			for(var i = 0;i<res.errmsg.length;i++){
				htmlStr += "<div class='main-list' onclick=javascript:openUrl('/tutor/page/orderDetail.html?orderId="+res.errmsg[i].id+"') >";
				htmlStr += "<h3>【客户名称:"+isNull(res.errmsg[i].cName)+"】</h3>";
				htmlStr += "<p>约谈主题</p>";
				htmlStr += "<textarea  readonly='readonly' >"+res.errmsg[i].tName+"</textarea>";
				htmlStr += "<p>约谈时间:<font>"+jsDateTimeOnly(res.errmsg[i].qTutorTime.realDate)+"("+res.errmsg[i].qTutorTime.realTime+")</font></p>";
				htmlStr += "<p>约谈状态:<font class='tutor-state-has'>"+getOrderStatus(res.errmsg[i].status)+"</font></p>";
				htmlStr += "</div>";
				htmlStr += "<hr>";
			}
			
			$("#dataInfo").html(htmlStr);
		}
	})
})

</script>
</head>
<body class="tutor-bg">
	<div class="nav-title"><div class="close" onclick="openUrl('/tutor/page/index.html')" >&Chi;</div>约谈列表</div>
	<div id="dataInfo" ></div>
</body>
</html>