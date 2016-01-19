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
var array = ['如何能够了解职业发展道路上的不同能力要求？','现任岗位上，提升哪些能力能让我有更突出的表现？','职业转型期需要的关键能力有哪些？','如何处理越来越大的职场压力？'];
var idArray = ['12','13','14','15'];
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
		}
	}
}
</script>
</head>
<body>
<a href="#"><div class="nav-title"><span class="nav-back"  onclick="openUrl('/customer/page/new.html')" >&lt;</span>职业能力发展</div></a>
<div class="choice-title" style="height:130px;" ><h1></h1>
<div style="height:10px;" ></div>
<p style="line-height:18px;font-size:14px;" >“如何让自己保持职场竞争力？”“怎样不断增加我的市场价值？”了解行业或者职业领域发展趋势，明确目标，快速提升自身专业与职业能力，你可以在这里得到专业导师的辅导，解决困惑。</p>
<div style="height:10px;" ></div>
<p style="line-height:18px;font-size:14px;"><b>以下是可供选择的具体场景或者挑战（单选）</b></p>
</div>
<ul class="choice-question">
	<li id="0" onclick="changeText('0')"  >如何能够了解职业发展道路上的不同能力要求？<span>&radic;</span></li>
	<li id="1" onclick="changeText('1')" >现任岗位上，提升哪些能力能让我有更突出的表现？</li>
	<li id="2" onclick="changeText('2')" >职业转型期需要的关键能力有哪些？</li>
	<li id="3" onclick="changeText('3')" >如何处理越来越大的职场压力？</li>
	<li class="choice-question-no" onclick="openUrl('/customer/page/new.html')" >没有找到符合您的描述？ 去其他场景找找看<span>&gt;</span></li>
</ul>
<div class="clear"></div>
<div class="choice-text">
	<p  style="font-size:14px;"><b>您有个性化问题？请写在这里吧：</b></p>
	<textarea placeholder="(限80字以内)" maxlength="80"></textarea>
</div>
<div class="sure-btn">
	<div class="tutor-search-btn" onclick="goNext()" >下一步</div>
</div>
</body>
</html>