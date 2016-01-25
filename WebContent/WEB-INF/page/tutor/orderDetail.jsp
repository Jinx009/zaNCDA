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
			replyStatus = getReplyStatus(res.errmsg.order.status,res.errmsg.order.qTutorTime.realDate);
			
			var htmlStr = "";
			htmlStr += "<h3>[学员姓名："+isNull(res.errmsg.order.qCustomer.realName)+"]</h3>";
			htmlStr += "<p>行业：<font>"+isNull(res.errmsg.tradeName)+"</font></p>";
			htmlStr += "<p>职能：<font>"+isNull(res.errmsg.jobName)+"</font></p>";
			htmlStr += "<p>工作年限：<font>"+getWorkYears(res.errmsg.order.famillyNumber)+"</font></p>";
			htmlStr += "<p>成长阶段：<font>"+getGrowpStatus(res.errmsg.order.growpStatus)+"</font></p>";
			htmlStr += "<p>约谈日期：<font>"+jsDateTimeOnly(res.errmsg.order.qTutorTime.realDate)+"</font></p>";
			htmlStr += "<p>约谈时间：<font>"+res.errmsg.order.qTutorTime.realTime+"</font></p>";
			htmlStr += "<p>辅导主题:</p>"
			htmlStr += "<textarea readonly='readonly' >"+res.errmsg.order.topicContent+"</textarea>";
			htmlStr += "<p>辅导方式：<font>当面约谈（60分钟）</font></p>";
			htmlStr += "<p>约谈状态:<font class='tutor-state-has'>"+getOrderStatus(res.errmsg.order.status)+"</font></p>";
			htmlStr += "<p>客户评语：</p>";
			if(null!=res.errmsg.score&&res.errmsg.score.length>0){
				htmlStr += "<textarea readonly='readonly' >"+res.errmsg.score[0].content+"</textarea>";
			}else{
				htmlStr += "<textarea readonly='readonly' >暂未评价</textarea>";
			}
			$(".main-list").html(htmlStr);
		}
	})
})

function getGrowpStatus(num){
	if(null!=num&&""!=num){
		if("1"==num){
			return "探索阶段";
		}else if("2"==num){
			return "建立阶段";
		}else if("3"==num){
			return "成熟阶段";
		}else if("4"==num){
			return "退出";
		}
	}
	return "";
}

function getWorkYears(num){
	if(null!=num&&""!=num){
		if("1"==num){
			return "在读/兼职";
		}else if("2"==num){
			return "1-3年";
		}else if("3"==num){
			return "3-5年";
		}else if("4"==num){
			return "5-10年";
		}else if("5"==num){
			return "10年以上";
		}else{
			return "";
		}
	}
	return "";
}

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
	<div class="nav-title"><span class="nav-back" onclick="openUrl('/tutor/page/order.html')" >&lt;</span>约谈详情<div class="close"onclick="openUrl('/tutor/page/index.html')"  >&Chi;</div></div>
	<div class="main-list"></div>
	<div class="tutor-search">
		<div class="tutor-search-btn btn-orange-bg" onclick="sendComments()" >编写辅导小结</div>
	</div>
</body>
</html>