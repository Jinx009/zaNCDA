<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>基本信息</title>
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
var type = {};
$(function(){
	type.video = {};
	type.mobile = {};
	type.face = {};
	
	type.video.content = "视频约谈";
	type.mobile.content = "电话约谈";
	type.face.content = "当面约谈";
	
	type.mobile.status = $("#mobileStatus").val();
	type.face.status = $("#faceStatus").val();
	type.video.status = $("#videoStatus").val();
	
	getColor('mobile');
	getColor('face');
	getColor('video');
	
	var workYearsValue = $("#workYearsValue").val();
	var element1 = document.getElementById("workYears");   
       
    for(i=0;i<element1.length;i++)
    {
      if(workYearsValue==element1.options[i].value)
      {  
          element1.options[i].selected=true; 
      }  
    }  
})

/**
 * 点击事件
 */
function changeColor(id){
	if(0==type[id].status){
		type[id].status = 1;
		$("#"+id).addClass("choice-question-select");
		$("#"+id).html(type[id].content+"<span>&radic;</span>");
	}else{
		type[id].status = 0;
		$("#"+id).removeClass("choice-question-select");
		$("#"+id).html(type[id].content);
	}
}

/**
 * 第一次赋值
 */
function getColor(id){
	if(1==type[id].status){
		$("#"+id).addClass("choice-question-select");
		$("#"+id).html(type[id].content+"<span>&radic;</span>");
	}else{
		$("#"+id).removeClass("choice-question-select");
		$("#"+id).html(type[id].content);
	}
}

function saveInfoTwo(){
	var face = type.face.status;
	var mobile = type.mobile.status;
	var video = type.video.status;
	var workYears = $("#workYears").val();
	var params = "face="+face+"&mobile="+mobile+"&video="+video+"&workYears="+workYears;
	$.ajax({
		url:"/tutor/data/saveInfoTwo.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				location.href = "/tutor/page/infoThree.html";
			}
		}
	})
	
}
</script>
</head>
<body>
<div class="nav-title">个人信息（二）<div class="close" onclick="openUrl('/tutor/page/index.html')" >&Chi;</div></div>
<div class="register-inp register-inp-top">
	<span class="register-inp-text">从业年限</span>
	<input type="hidden" value="${tutor.workYears }" id="workYearsValue" >
	<input type="hidden" value="${tutor.faceStatus }" id="faceStatus" >
	<input type="hidden" value="${tutor.videoStatus }" id="videoStatus" >
	<input type="hidden" value="${tutor.mobileStatus }" id="mobileStatus" >
	<select class="register-select-long" id="workYears" >
		<option value="1" selected="selected">1年以内</option>
		<option value="2" >1-3年</option>
		<option value="3" >3-5年</option>
		<option value="4" >5-7年</option>
		<option value="5" >7年以上</option>
	</select>
</div>
<h3 class="select-title">辅导方式</h3>
<ul class="choice-question select-answer">
	<li class="choice-question-select" id="face" onclick="changeColor('face')" >当面约谈<span>&radic;</span></li>
	<li id="video"  onclick="changeColor('video')"  >视频约谈</li>
	<li id="mobile"  onclick="changeColor('mobile')" >电话约谈</li>
</ul>
<div class="btn-position">
	<div class="teacher-two-btn">
		<button class="teacher-orange-btn" onclick="openUrl('/tutor/page/infoOne.html')" >上一步</button>
		<button class="teacher-orange-btn" onclick="saveInfoTwo()" >下一步</button>
	</div>
</div>
</body>
</html>