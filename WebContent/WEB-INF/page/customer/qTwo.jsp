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
var index = "0";

/**
 * 更换内容
 */
function changeText(id){
	
	$(".choice-question li").each(function(){
		$(this).removeClass("choice-question-select");
		$(this).html(array[parseInt($(this).attr("id"))]);
	})
	if(index!=idArray[parseInt(id)]){
		$("#"+id).html($("#"+id).html()+"<span>&radic;</span>");
		$("#"+id).addClass("choice-question-select");
	}
	index = idArray[parseInt(id)];
}

/**
 * 跳转下一页
 */
function goNext(){
	var topicContent = $("#topicContent").val();
	if("0"!=index){
		location.href = "/customer/page/selectTutor.html?id="+index;
	}else{
		if(""!=topicContent){
			var storage = window.sessionStorage;
			storage.setItem("localTopic",topicContent);
			location.href = "/customer/page/selectTutor.html?id="+index;
		}else{
			$("#myAlertH").html("约谈主题不能为空!");
			showNewAlert();
		}
	}
}
</script>
</head>
<body>
<div id="newAlert">
		<h3  id="myAlertH" ></h3>
		<a id="newAlertBtn" onclick="hideNewAlert()" >确定</a>
	</div>
<a href="#"><div class="nav-title"><span class="nav-back" onclick="openUrl('/customer/page/new.html')" >&lt;</span>职场关系</div></a>
<div class="choice-title" style="height:150px;" ><h1></h1>
<div style="height:10px;" ></div>
<p style="line-height:18px;font-size:14px;" >“我们公司的同事都很难相处。”“我老板很奇怪，搞不懂他想要怎样”。世上最难懂的是人心，把握好职场中的关系，可以让工作成就事半功倍。你也许对纷繁复杂的人际关系感觉难以应对，在这里可以得到专业导师的咨询与建议，更加高效地管理职场人际。</p>
<div style="height:10px;" ></div>
<p style="line-height:18px;font-size:14px;"><b>以下是可供选择的具体场景或者挑战（单选）</b></p></div>
<ul class="choice-question">
	<li id="0" onclick="changeText('0')" >遇到难懂的上司，如何才能抓住老板的心？</li>
	<li id="1" onclick="changeText('1')" >空降兵苦恼：怎样破冰，快速开展工作？</li>
	<li id="2" onclick="changeText('2')" >我的能力很强，但是不被认可，该怎么办？</li>
	<li id="3" onclick="changeText('3')" >面对复杂的职场关系，如何提升自己的情商？</li>
	<li class="choice-question-no" onclick="openUrl('/customer/page/new.html')" >没有找到符合您的描述？ 去其他场景找找看<span>&gt;</span></li>
</ul>
<div class="clear"></div>
<div class="choice-text">
	<p  style="font-size:14px;"><b>您有个性化问题？请写在这里吧：</b></p>
	<textarea id="topicContent" placeholder="(限80字以内)" maxlength="80" ></textarea>
</div>
<div class="sure-btn">
	<div class="tutor-search-btn" onclick="goNext()" >下一步</div>
</div>
</body>
</html>