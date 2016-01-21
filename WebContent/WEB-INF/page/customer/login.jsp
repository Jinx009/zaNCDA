<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>学员登录</title>
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
var url = "";
$(function(){
	changeCode();
	
	var status = $("#status").val();
	var openid = $("#openid").val();
	url = $("#url").val();
	
	if("0"!=status){
		location.href = "/customer/page/index.html";
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
		url:"/customer/data/login.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				if(null!=url&&""!=url){
					location.href = url;
				}else{
					location.href = "/customer/page/index.html";
				}
			}else{
				$("#myAlertH").html(res.errmsg);
				showNewAlert();
			}	
		}
	})
}

/**
 * 登陆验证码
 */
function changeCode(){
	$.ajax({
		url:"/customer/data/getCode.html?time="+getRandom(),
		type:"GET",
		dataType:"json",
		success:function(res){
			$("#codeText").html(res.customer_code);
		}
	})
}
</script>
</head>
<body>
	<div id="newAlert">
		<h3  id="myAlertH" ></h3>
		<a id="newAlertBtn" onclick="hideNewAlert()" >确定</a>
	</div>
	<input type="hidden" value="${openid }" id="openid" >
	<input type="hidden" value="${status }" id="status" >
	<input type="hidden" value="${url }" id="url" >
	<div class="nav-title">登录</div>
	<div class="logo">
		<img src="/sp/images/logo.jpg"/>
	</div>
	<div class="register-inp login-inp-top">
		<span class="register-inp-text">手机号</span>
		<input type="text" placeholder="请输入您的手机号码"  value="" id="userName"  class="register-inp-long"/>
	</div>
	<div class="register-inp login-inp-top">
		<span class="register-inp-text">密码</span>
		<input type="password" placeholder="请输入您的密码"  value="" id="pwd" class="register-inp-long"/>
	</div>
	<div class="register-inp login-inp-top">
		<span class="register-inp-text">验证码</span>
		<input type="text"  value="" id="code" class="register-inp-short"/>
		<div class="register-inp-btn" id="codeText" onclick="changeCode()" ></div>
	</div>
	<div class="clear"></div>
	
	<div class="tutor-search">
		<div class="tutor-search-btn" onclick="doLogin()" >登录</div>
	</div>
	<div class="register-tip"><a href="/customer/register.html">立即注册</a>&nbsp;&nbsp;<a href="/customer/forget.html">忘记密码</a></div>
</body>
</html>