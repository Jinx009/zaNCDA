<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>顾客注册</title>
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
$(function(){
	getRegisterCode();
})

/**
 * 获取验证码
 */
function getRegisterCode(){
	$.ajax({
		url:"/customer/data/getRegisterCode.html?time="+getRandom(),
		type:"GET",
		dataType:"json",
		success:function(res){
			$("#codeText").html(res.customer_register_code);
		}
	})
}

/**
 * 执行注册
 */
function doRegister(){
	var mobile = $("#mobile").val();
	var pwd = $("#pwd").val();
	var repwd = $("#repwd").val();
	var code = $("#code").val();
	var params = "mobile="+mobile+"&pwd="+pwd+"&code="+code;
	
	if("success"!=validateTel(mobile)){
		alert(validateTel(mobile));
	}else if(pwd!=repwd){
		alert("两次密码不一致!");
	}else{
		$.ajax({
			url:"/customer/data/doRegister.html",
			type:"POST",
			data:params,
			dataType:"json",
			success:function(res){
				if("success"==res.result){
					alert("注册成功！");
					location.href = "/customer/page/index.html";
				}else{
					alert(res.errmsg);
				}
			}
		})
	}
}
</script>
</head>
<body>
<a href="#"><div class="nav-title"><span class="nav-back"  onclick="openUrl('/customer/login.html')" >&lt;</span>注册</div></a>
	
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">手机号</span>
		<input placeholder="请输入您的手机号码" id="mobilePhone"  value="" class="register-inp-long"/>
	</div>
	
	<div class="clear"></div>
	
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">密码</span>
		<input placeholder="请输入您的密码" id="pwd"  value="" class="register-inp-long"/>
	</div>
	
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">重复密码</span>
		<input placeholder="请重复输入您的密码" id="repwd"  value="" class="register-inp-long"/>
	</div>
	
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">验证码</span>
		<input value="" id="code" class="register-inp-short"/>
		<div class="register-inp-btn" id="codeText" onclick="getRegisterCode();" ></div>
	</div>
	<div class="clear"></div>
	
	<div class="tutor-search">
		<div class="tutor-search-btn" onclick="doRegister()" >注册</div>
	</div>
	<div class="register-tip">已有账号<a href="/customer/login.html">立即登录</a></div>
</body>
</html>