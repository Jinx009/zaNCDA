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
<script type="text/javascript">
$(function(){
	var width = $(window).width();
	var height = $(document).height();
	$(".main").css("width",width+"px");
	$(".main").css("height",height+"px");
	$("#appoint").bind("click",function(){
		$(".mask").show();
		$(".mask").css("width",width+"px");
		$(".mask").css("height",height+"px");
		$(".treaty").css("height",height*0.5+"px");
		$(".personal-boxWrap").css("height",height*0.75+"px");
		$(".personal-boxWrap").show();
	})
})

/**
 * 选择导师时间
 */
function showTime(){
	var tutorId = $("#tutorId").val();
	
	$.ajax({
		url:"/customer/data/time.html?time="+getRandom(),
		type:"GET",
		dataType:"json",
		success:function(res){
			console.log(res)
		}
	})
}
</script>
</head>
<body>
<input type="hidden" value="${tutorId }" id="tutorId" />
<input type="hidden" value="${topicId }" id="topicId" >
<div class="main">
	<a href="#"><div class="nav-title"><span class="nav-back">&lt;</span>确定您的个人信息</div></a>
	<div class="personal-wrap">
		<p>一句话概括您相约谈的主题内容(限80字内)</p>
		<textarea placeholder="输入您想约谈的内容……"></textarea>
		<div class="checked">
			<img src="/sp/images/check.png"/>
			<span>已阅读并同意我们的条约</span>
		</div>
	</div>
	
	<div class="personal-wrap">
		<p>选择您希望约谈的时候：</p>
		<input onclick="showTime()"  />
	</div>
	
	<div class="personal-tip">
		<span>导师仅限双休日有空</span>
	</div>
	
	<div class="btn-position">
		<div class="two-btn">
			<button id="lastStep">上一步</button>
			<button id="appoint">约</button>
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
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
					1.各种跳跃明细
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