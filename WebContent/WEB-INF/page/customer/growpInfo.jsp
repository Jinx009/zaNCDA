<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />
<title>个人职业生涯成长阶段</title>
<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
$(function(){
	var status = $("#status").val();
	if(null!=status&&""!=status){
		status = parseInt(status);
		statusValue = status;
		$("#growp"+status+"  li ").each(function(){
			$(this).css("color","red");
		})
	}
})
var statusValue = 1;
/**
 * 保存
 */
function saveGrowp(){
	var params = "status="+statusValue;
	$.ajax({
		url:"/customer/data/saveGrowp.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				alert("保存成功!");
location.href = "/customer/page/index.html";
			}
		}
	})
}
/**
 * 更换状态
 */
function changeStatus(index){
	$(".personal-inp-content li").each(function(){
		$(this).css("color","rgb(102,102,102)")
	})
	$("#growp"+index+"  li ").each(function(){
		$(this).css("color","red");
	})
	statusValue = index;
}
</script>
<style type="text/css">
.personal-inp-content h1{
	text-align: center;
	font-size: 22px;
}
.space-4{
	height: 40px;
}
</style>
</head>
<body>
<input type="hidden" value="${status }" id="status" >
<a href="#"><div class="nav-title"><span class="nav-back" onclick="openUrl('/customer/page/info.html')" >&lt;</span>个人职业生涯成长阶段<span class="close" onclick="openUrl('/customer/page/index.html')" >&Chi;</span></div></a>
	<div class="personal-inp-content">
		<img src="/sp/images/personal_input.png"/>
		<div><h1>选择您所属的阶段</h1></div>
		<div class="space-4" ></div>
		<ul id="growp1" onclick="changeStatus('1')" >
			<li><span class="personal-inp-content_a">A</span></li>
			<li>探索阶段</li>
			<li>15岁-25岁</li>
		</ul>
		
		<ul id="growp2" onclick="changeStatus('2')"  >
			<li><span class="personal-inp-content_b">B</span></li>
			<li>建立阶段</li>
			<li>25岁-40岁</li>
		</ul>
		
		<ul id="growp3" onclick="changeStatus('3')"  >
			<li><span class="personal-inp-content_c">C</span></li>
			<li>成熟阶段</li>
			<li>40岁-60岁</li>
		</ul>
		
		<ul id="growp4" onclick="changeStatus('4')"  >
			<li><span class="personal-inp-content_d">D</span></li>
			<li>退出</li>
			<li>60岁+</li>
		</ul>
	</div>
	<div class="tutor-search">
		<div class="tutor-search-btn" onclick="saveGrowp()" >保存</div>
	</div>
<div class="code">
	<img src="/sp/images/code.png"/>
</div>
</body>
</html>