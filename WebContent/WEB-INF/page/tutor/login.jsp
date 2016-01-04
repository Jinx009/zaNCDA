<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>导师登陆</title>
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/dist/js/bootstrap.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
$(function(){
	changeCode();
	
	var status = $("#status").val();
	var openid = $("#openid").val();
	var url = $("#url").val();
	
	if("0"!=status){
		location.href = "/tutor/page/index.html";
	}else{
		
	}
})

/**
 * 登陆
 */
function doLogin(){
	var userName = $("#userName").val();
	var pwd = $("#pwd").val();
	var openid = $("#openid").val();
	var code = $("#code").val();
	var params = "userName="+userName+"&pwd="+pwd+"&openid="+openid+"&code="+code;
	
	$.ajax({
		url:"/tutor/data/login.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				location.href = "/tutor/page/index.html";
			}else{
				alert(res.errmsg);
			}	
		}
	})
}

/**
 * 登陆验证码
 */
function changeCode(){
	$.ajax({
		url:"/tutor/data/getCode.html?time="+getRandom(),
		type:"GET",
		dataType:"json",
		success:function(res){
			$("#codeText").html(res.tutor_code);
		}
	})
}
</script>
</head>
<body>
	<input type="hidden" value="${openid }" id="openid" >
	<input type="hidden" value="${status }" id="status" >
	<input type="hidden" value="${url }" id="url" >
	<input type="hidden" value="9999" id="code" >
	<span class="none" id="codeText" ></span>
	<div class="nav-title">登录</div>
	<div class="logo">
		<img src="/sp/images/logo.jpg"/>
	</div>
	<div class="register-inp login-inp-top">
		<span class="register-inp-text">手机号</span>
		<input type="text" placeholder="请输入您的手机号码" id="userName" value="" class="register-inp-long"/>
	</div>
	<div class="register-inp login-inp-top">
		<span class="register-inp-text">密码</span>
		<input type="password" placeholder="请输入您的密码" id="pwd"  value="" class="register-inp-long"/>
	</div>
	<div class="tutor-search">
		<div class="tutor-search-btn btn-orange-bg" onclick="doLogin()" >登录</div>
	</div>
	<div class="register-tip none"><a href="register.html">立即注册</a></div>
</body>
</html>