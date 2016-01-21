<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>学员注册</title>
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
 * 获取手机验证码
 */
function getCode(){
	var mobilePhone = $("#mobilePhone").val();
	var params = "mobilePhone="+mobilePhone;
	var result = validateTel(mobilePhone);
	if("success"!=result){
		alert(result);
	}else{
		$.ajax({
			url:"/customer/data/getRegisterCode.html",
			type:"POST",
			data:params,
			dataType:"json",
			success:function(res){
				if("success"==res.result){
					changeGetCodeBtn();
				}else{
					$("#myAlertH").html(res.errmsg);
					showNewAlert();
					//alert(res.errmsg);
				}
			}
		})
	}
}

/**
 * 验证码状态
 */
function changeGetCodeBtn(){
	var time=60;
	
	var timeFun=setInterval(function(){
		time--;
		
		if(time>0){
			$("#codeMobileText").html(time+"秒后重发").attr("onclick","");
		}
		else{
			time=60;
				
			$("#codeMobileText").html("获取验证码").attr("onclick","getCode()");
				
			clearInterval(timeFun);
		}
	},1000)
}

/**
 * 执行注册
 */
function doRegister(){
	var mobile = $("#mobilePhone").val();
	var pwd = $("#pwd").val();
	var repwd = $("#repwd").val();
	var code = $("#code").val();
	var mobileCode = $("#mobileCode").val();
	var params = "mobile="+mobile+"&pwd="+pwd+"&code="+code+"&mobileCode="+mobileCode;
	
	if("success"!=validateTel(mobile)){
		$("#myAlertH").html(validateTel(mobile));
		showNewAlert();
		//alert(validateTel(mobile));
	}else if(pwd!=repwd){
		$("#myAlertH").html("两次密码不一致!");
		showNewAlert();
		//alert("两次密码不一致!");
	}else{
		$.ajax({
			url:"/customer/data/doRegister.html",
			type:"POST",
			data:params,
			dataType:"json",
			success:function(res){
				if("success"==res.result){
					$("#myAlertH").html("注册成功！");
					$("#newAlertBtn").attr("onclick","openMyUrl('/customer/page/info.html')");
					showNewAlert();
				/* 	alert("注册成功！");
					location.href = "/customer/page/info.html"; */
				}else{
					$("#myAlertH").html(res.errmsg);
					showNewAlert();
					//alert(res.errmsg);
				}
			}
		})
	}
}
</script>
</head>
<body>
	<div id="newAlert">
		<h3  id="myAlertH" ></h3>
		<a id="newAlertBtn" onclick="hideNewAlert()" >确定</a>
	</div>
<a href="#"><div class="nav-title"><span class="close"  onclick="openUrl('/customer/login.html')" >&lt;</span>注册</div></a>
	
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">手机号</span>
		<input placeholder="请输入您的手机号码" id="mobilePhone"  value="" class="register-inp-long"/>
	</div>
	
	<div class="clear"></div>
	
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">密码</span>
		<input placeholder="请输入您的密码" id="pwd" type="password" value="" class="register-inp-long"/>
	</div>
	
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">重复密码</span>
		<input placeholder="请重复输入您的密码" id="repwd" type="password" value="" class="register-inp-long"/>
	</div>
	
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">验证码</span>
		<input value="" id="code" class="register-inp-short"/>
		<div class="register-inp-btn" id="codeText" onclick="getRegisterCode();" ></div>
	</div>
	<div class="register-inp login-inp-top">
		<span class="register-inp-text">验证码</span>
		<input type="text"  value="" id="mobileCode" class="register-inp-short"/>
		<div class="register-inp-btn" id="codeMobileText" onclick="getCode()" >获取验证码</div>
	</div>
	<div class="clear"></div>
	
	<div class="tutor-search">
		<div class="tutor-search-btn" onclick="doRegister()" >注册</div>
	</div>
	<div class="register-tip">已有账号<a href="/customer/login.html">立即登录</a></div>
</body>
</html>