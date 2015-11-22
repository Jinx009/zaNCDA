<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />
<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<title>约谈管理</title>
<script type="text/javascript">
$(function(){
	$.ajax({
		url:"/customer/data/order.html?time="+getRandom(),
		type:"GET",
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				var htmlStr = "";
				for(var i = 0;i<res.errmsg.length;i++){
					htmlStr += "<div class='tutor-list'  onclick=goDetail('"+res.errmsg[i].id+"') >";
					htmlStr += "<div class='tutor-wrap'>";
					htmlStr += '<div class="tutor-img">';
					htmlStr += '<img src="/sp/images/headPortrait.png" />';
					htmlStr += '</div>';
					htmlStr += '<div class="tutor-text">';
					htmlStr += '<p class="tutor-name">'+res.errmsg[i].topicContent+'</p>';
					htmlStr += '<p class="tutor-desc">约谈导师：<span class="tutor-span">'+res.errmsg[i].qTutor.realName+'</span></p>';
					htmlStr += '<p class="tutor-desc">约谈时间：<span class="tutor-span">'+jsDateTimeOnly(res.errmsg[i].qTutorTime.realDate)+'('+res.errmsg[i].qTutorTime.realTime+')</span></p>';
					htmlStr += '<p class="tutor-desc">约谈状态：<span class="tutor-state-ing">'+getOrderStatus(res.errmsg[i].status)+'</span></p>';
					htmlStr += '</div>';
					htmlStr += '</div>';
					htmlStr += '</div>';
				}
				$("#dataList").html(htmlStr);
			}
		}
	})
})

/**
 * 详情
 */
function goDetail(id){
	location.href = "/customer/page/orderDetail.html?orderId="+id;
}
</script>
</head>
<body>
<a href="#"><div class="nav-title"><span class="nav-back" onclick="openUrl('/customer/page/index.html')" >&lt;</span>约谈管理</div></a>
<div id="dataList" ></div>
</body>
</html>