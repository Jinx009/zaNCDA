<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />
<title>约谈时间</title>
<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript" src="/sp/js/pay/lazyloadv3.js" ></script>
<script type="text/javascript" src="/sp/js/pay/md5.js" ></script>
<script type="text/javascript" src="/sp/js/pay/sha1.js" ></script>
<script type="text/javascript" src="/sp/js/pay/pay.js" ></script>
<script type="text/javascript">
$(function(){
	getDate();
	$("#orderId").val(getRandom());
})

/**
 * 选择导师时间
 */
function getDate(){
	var tutorId = $("#tutorId").val();
	var params = "tutorId="+tutorId;
	
	$.ajax({
		url:"/customer/data/orderDate.html?time="+getRandom(),
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			if(null!=res.errmsg){
				var htmlStr = "";
				for(var i = 0;i<res.errmsg.length;i++){
					htmlStr += "<option value = '"+jsDateTimeOnly(res.errmsg[i].realDate)+"' >"+jsDateTimeOnly(res.errmsg[i].realDate)+"</option>"
				}
				$("#date").html(htmlStr);
				changeTime();
			}else{
				alert("很抱歉，该导师暂时没有可预约时间!");
			}
		}
	})
}

function changeTime(){
	var date = $("#date").val();
	var tutorId = $("#tutorId").val();
	var params = "tutorId="+tutorId+"&realDate="+date;
	
	$.ajax({
		url:"/customer/data/orderTime.html?time="+getRandom(),
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			if(null!=res.errmsg){
				var htmlStr = "";
				for(var i = 0;i<res.errmsg.length;i++){
					htmlStr += "<option value = '"+res.errmsg[i].id+"' >"+res.errmsg[i].realTime+"</option>"
				}
				$("#time").html(htmlStr);
			}
		}
	})
}

</script>
</head>
<body>
<input type="hidden" value="${tutorId }" id="tutorId" />
<input type="hidden" value="${topicId }" id="topicId" >
<input type="hidden" value="" id="orderId" >
<input type="hidden" value="${openid }" id="openId" >
<input type="hidden" value="1" id="fee" >
<div class="main">
	<a href="#"><div class="nav-title"><span class="nav-back">&lt;</span>确定您的个人信息</div></a>
	<div class="personal-wrap">
		<p>一句话概括您相约谈的主题内容(限80字内)</p>
		<textarea placeholder="输入您想约谈的内容……" id="topicContent" ></textarea>
		<div class="checked">
			<img src="/sp/images/check.png"/>
			<span>已阅读并同意我们的条约</span>
		</div>
	</div>
	
	<div class="personal-wrap">
		<p>选择您希望约谈的日期：</p>
		<select class="interview-sel center" id="date" onchange="changeTime()" ></select>
		<p>选择您希望约谈的具体时间：</p>
		<select class="interview-sel center" id="time"  ></select>
		<p></p>
	</div>
	
	<div class="personal-tip">
	</div>
	
	<div class="btn-position">
		<div class="two-btn">
			<button id="appoint" onclick="pay()">付款约谈</button>
		</div>
	</div>
	<!--蒙版-->
	<div class="mask"></div>
	<!--悬浮框	start-->
	<div class="personal-boxWrap">
		<div class="personal-box">
			<h3>客户须知</h3>
			<div class="treaty">
				<p>
				
				</p>
			</div>
			<div class="cancel-box-btn">
				<button>确定</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>