<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>约谈详情</title>
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
var replyStatus = false;

$(function(){
	var id = $("#id").val();
	var params = "id="+id;
	$.ajax({
		url:"/tutor/data/orderDetail.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			console.log(res);
			replyStatus = getReplyStatus(res.errmsg.order.status,res.errmsg.order.qTutorTime.realDate);
			
			var htmlStr = "";
			htmlStr += "<h3>["+res.errmsg.order.qCustomer.realName+"]</h3>";
			htmlStr += "<p>约谈主题:</p>"
			htmlStr += "<textarea readonly='readonly' >"+res.errmsg.order.topicContent+"</textarea>";
			htmlStr += "<p>约谈时间:<font>"+jsDateTimeOnly(res.errmsg.order.qTutorTime.realDate)+"("+res.errmsg.order.qTutorTime.realTime+")</font></p>";
			htmlStr += "<p>约谈状态:<font class='tutor-state-has'>"+getOrderStatus(res.errmsg.order.status)+"</font></p>";
			htmlStr += "<p>客户评语：</p>";
			if(null!=res.errmsg&&res.errmsg.length>0){
				htmlStr += "<textarea readonly='readonly' >"+res.errmsg.score[0].content+"</textarea>";
			}else{
				htmlStr += "<textarea readonly='readonly' >暂未评价</textarea>";
			}
			$(".main-list").html(htmlStr);
		}
	})
})

/**
 * 查看小结
 */
function sendComments(){
	var id = $("#id").val();
	var params = "id="+id;
	
	location.href = "/tutor/page/comments.html?id="+id;
}
</script>
</head>
<body>
	<input type="hidden" value="${orderId }" id="id" >
	<div class="nav-title">约谈详情<div class="close"onclick="openUrl('/tutor/page/index.html')"  >&Chi;</div></div>
	<div class="main-list"></div>
	<div class="tutor-search">
		<div class="tutor-search-btn btn-orange-bg" onclick="sendComments()" >查看并提交约谈小结</div>
	</div>
</body>
</html>