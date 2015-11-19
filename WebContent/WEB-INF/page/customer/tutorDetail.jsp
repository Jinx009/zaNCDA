<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />
<title>导师详情</title>
<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
var tutorId = "",topicId = "";
$(function(){
	topicId = $("#topicId").val();
	tutorId = $("#tutorId").val();
})

/**
 * 返回
 */
function goBack(){
	location.href = "/customer/page/new.html";
}

function goNext(){
	location.href = "/customer/page/times.html?tutorId="+tutorId+"&topicId="+topicId;
}
</script>
</head>
<body>
<input type="hidden" value="${tutorId }" id="tutorId" />
<input type="hidden" value="${topicId }" id="topicId" >
<div class="main">
<a href="#"><div class="nav-title"><span class="nav-back" onclick="goBack()" >&lt;</span>导师介绍详情页</div></a>
	
	<div class="tutorDetails">
		<div class="tutorDetails-bg">
			<img src="/sp/images/tutorDetail_icon.png"/>
		</div>
		<div class="tutor-details-name">${tutor.realName }教师<img src="/sp/images/male.png"/></div>
		
		<ul class="tutor-title">
			<li><img src="/sp/images/small_bottom.png"/></li>
			<li>个人简介</li>
			<li><img src="/sp/images/small_bottom.png"/></li>
		</ul>
		<div class="tutor-content">
			${tutor.personalIntroduction }
		</div>
		
		<ul class="tutor-title">
			<li><img src="/sp/images/small_bottom.png"/></li>
			<li>认证资质</li>
			<li><img src="/sp/images/small_bottom.png"/></li>
		</ul>
		<div class="tutor-content">
			<p>${tutor.aptitude }</p>
		</div>
		
		<ul class="tutor-title">
			<li><img src="/sp/images/small_bottom.png"/></li>
			<li>行业</li>
			<li><img src="/sp/images/small_bottom.png"/></li>
		</ul>
		<div class="tutor-content">
			<ul class="tutor-content-li-business">
				<li>${tutor.tradeOne.tradeName }</li>
				<li>${tutor.tradeTwo.tradeName }</li>
				<li>${tutor.tradeThree.tradeName }</li>
			</ul>
		</div>
		<div class="clear"></div>
		
		<ul class="tutor-title">
			<li><img src="/sp/images/small_bottom.png"/></li>
			<li>擅长领域</li>
			<li><img src="/sp/images/small_bottom.png"/></li>
		</ul>
		<div class="tutor-content">
			<ul class="tutor-content-li-like">
				<li>${tutor.areaOne.tradeName }</li>
				<li>${tutor.areaTwo.tradeName }</li>
				<li>${tutor.areaThree.tradeName }</li>
			</ul>
		</div>
		<div class="clear"></div>
		
		<ul class="tutor-title">
			<li><img src="/sp/images/small_bottom.png"/></li>
			<li>课程价格</li>
			<li><img src="/sp/images/small_bottom.png"/></li>
		</ul>
		<div class="tutor-content">
			<p>${tutor.classPrice }</p>
		</div>
		
	</div>
	<div class="sure-btn">
		<div class="tutor-search-btn" onclick="goNext()" >立即约TA</div>
	</div>
</div>
</body>
</html>