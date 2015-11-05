<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>导师平台</title>
<link rel="stylesheet" href="/sp/dist/css/bootstrap.css" >

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/dist/js/bootstrap.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
$(function(){
	$.ajax({
		url:"/tdata/orders.html",
		type:"POST",
		dataType:"json",
		success:function(res){
			console.log(res);
			var htmlStr = "";
			
			for(var i = 0;i<res.errmsg.length;i++){
				htmlStr += "<div onclick=javascript:openUrl('/tpage/orderDetail.html?id="+res.errmsg[i].id+"') >";
				htmlStr += "<p>【客户名称:"+res.errmsg[i].nbStudentsUser.realName+"】支付状态:"+getPayStatus(res.errmsg[i])+"%</p>";
				htmlStr += "<p>约谈主题</p>";
				htmlStr += "<p>"+res.errmsg[i].courseTopic+"</p>";
				htmlStr += "<p>约谈时间:"+jsDateTimeOnly(res.errmsg[i].studentPreferedDate)+"</p>";
				htmlStr += "<p>约谈状态:"+getOrderStatus(res.errmsg[i].commentsStatus)+"</p>";
				htmlStr += "</div>";
				htmlStr += "<hr>";
			}
			
			$("#dataInfo").html(htmlStr);
		}
	})
})

</script>
<style type="text/css">

</style>
</head>
<body>
	<div id="dataInfo" ></div>
</body>
</html>