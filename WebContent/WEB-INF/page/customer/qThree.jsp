<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />
<title>场景详情</title>
<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
var array = ['了解公司的架构和晋级之路','快速建立短期目标并积累经验和人脉','如何在职场提升关键技术和管理能力','为自己的市场价值添分的诀窍'];
var idArray = ['12','13','14','15'];
var index = "12";

/**
 * 更换内容
 */
function changeText(id){
	index = idArray[parseInt(id)];
	
	$(".choice-question li").each(function(){
		$(this).removeClass("choice-question-select");
		$(this).html(array[parseInt($(this).attr("id"))]);
	})
	$("#"+id).html($("#"+id).html()+"<span>&radic;</span>");
	$("#"+id).addClass("choice-question-select");
}

/**
 * 跳转下一页
 */
function goNext(){
	location.href = "/customer/page/selectTutor.html?id="+index;
}
</script>
</head>
<body>
<a href="#"><div class="nav-title"><span class="nav-back"  onclick="openUrl('/customer/page/new.html')" >&lt;</span>选择题</div></a>
<div class="choice-title"><h1>职业发展</h1></div>
<ul class="choice-question">
	<li id="0" onclick="changeText('0')" class="choice-question-select">了解公司的架构和晋级之路<span>&radic;</span></li>
	<li id="1" onclick="changeText('1')" >快速建立短期目标并积累经验和人脉</li>
	<li id="2" onclick="changeText('2')" >如何在职场提升关键技术和管理能力</li>
	<li id="3" onclick="changeText('3')" >为自己的市场价值添分的诀窍</li>
	<li class="choice-question-no" onclick="openUrl('/customer/page/new.html')" >没有找到您的描述，去其他场景找找看<span>&gt;</span></li>
</ul>
<div class="clear"></div>
<div class="choice-text">
	<p>我想自己书写我的职场困惑场景</p>
	<textarea placeholder="输入您的职场困惑"></textarea>
</div>
<div class="sure-btn">
	<div class="tutor-search-btn" onclick="goNext()" >下一步</div>
</div>
</body>
</html>