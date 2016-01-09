<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />
<title>场景详情</title>
<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
var array = ['遇到难懂的上司，如何才能抓住老板的心？','空降兵苦恼：怎样破冰，快速开展工作？','我的能力很强，但是不被认可，该怎么办？','面对复杂的职场关系，如何提升自己的情商？'];
var idArray = ['8','9','10','11'];
var index = "8";

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
<a href="#"><div class="nav-title"><span class="nav-back" onclick="openUrl('/customer/page/new.html')" >&lt;</span>选择题</div></a>
<div class="choice-title"><h1>职场关系</h1></div>
<ul class="choice-question">
	<li id="0" onclick="changeText('0')" class="choice-question-select">遇到难懂的上司，如何才能抓住老板的心？<span>&radic;</span></li>
	<li id="1" onclick="changeText('1')" >空降兵苦恼：怎样破冰，快速开展工作？</li>
	<li id="2" onclick="changeText('2')" >我的能力很强，但是不被认可，该怎么办？</li>
	<li id="3" onclick="changeText('3')" >面对复杂的职场关系，如何提升自己的情商？</li>
	<li class="choice-question-no" onclick="openUrl('/customer/page/new.html')" >没有找到您的描述，去其他场景找找看<span>&gt;</span></li>
</ul>
<div class="clear"></div>
<div class="choice-text">
	<p>我想自己书写我的职场困惑场景</p>
	<textarea placeholder="输入您的职场困惑" ></textarea>
</div>
<div class="sure-btn">
	<div class="tutor-search-btn" onclick="goNext()" >下一步</div>
</div>
</body>
</html>