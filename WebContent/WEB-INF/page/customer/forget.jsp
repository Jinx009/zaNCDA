<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>顾客平台</title>
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
$(function(){
	changeCode();
})
/**
 * 忘记密码数字验证码
 */
function changeCode(){
	$.ajax({
		url:"/customer/data/codeForget.html?time="+getRandom(),
		type:"GET",
		dataType:"json",
		success:function(res){
			$("#codeText").html(res.customer_code_forget);
		}
	})
}

/**
 * 获取手机验证码
 */
function getCode(){
	var mobilePhone = $("#userName").val();
	var code = $("#code").val();
	var params = "mobilePhone="+mobilePhone+"&customer_code_forget="+code;
	var result = validateTel(mobilePhone);
	if("success"!=result){
		alert(result);
	}else{
		$.ajax({
			url:"/customer/data/getForgetCode.html",
			type:"POST",
			data:params,
			dataType:"json",
			success:function(res){
				if("success"==res.result){
					changeGetCodeBtn();
				}else{
					$("#myAlertH").html(res.errmsg);
					showNewAlert();
				}
			}
		})
	}
}

function doForget(){
	var mobilePhone = $("#userName").val();
	var mobileCode = $("#mobileCode").val();
	var code = $("#code").val();
	var pwd = $("#pwd").val();
	var rePwd = $("#repwd").val();
	var params = "mobilePhone="+mobilePhone+"&mobileCode="+mobileCode+"&pwd="+pwd;
	var result = validateTel(mobilePhone);
	if("success"!=result){
		alert(result);
	}else if(pwd!=rePwd){
		alert("确认密码不一致！");
	}else{
		$.ajax({
			url:"/customer/data/doForget.html",
			type:"POST",
			data:params,
			dataType:"json",
			success:function(res){
				if("success"==res.result){
					$("#myAlertH").html("操作成功!");
					$("#newAlertBtn").attr("onclick","openMyUrl('/customer/login.html')");
					showNewAlert();
				/* 	alert("操作成功!");
					location.href = "/customer/login.html"; */
				}else{
					$("#myAlertH").html(res.errmsg);
					showNewAlert();
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
</script>
</head>
<body>
<div id="newAlert">
		<h3  id="myAlertH" ></h3>
		<a id="newAlertBtn" onclick="hideNewAlert()" >确定</a>
	</div>
	<div class="nav-title">登录</div>
	<div class="logo">
		<img src="/sp/images/logo.jpg"/>
	</div>
	<div class="register-inp login-inp-top">
		<span class="register-inp-text">手机号</span>
		<input type="text" placeholder="请输入您的手机号码"  value="" id="userName"  class="register-inp-long"/>
	</div>
	<div class="register-inp login-inp-top">
		<span class="register-inp-text">新密码</span>
		<input type="password" placeholder="请输入您的新密码"  value="" id="pwd" class="register-inp-long"/>
	</div>
	<div class="register-inp login-inp-top">
		<span class="register-inp-text">确认密码</span>
		<input type="password" placeholder="请输入您的确认密码"  value="" id="repwd" class="register-inp-long"/>
	</div>
	<div class="register-inp login-inp-top">
		<span class="register-inp-text">验证码</span>
		<input type="text"  value="" id="code" class="register-inp-short"/>
		<div class="register-inp-btn" id="codeText" onclick="changeCode()" ></div>
	</div>
	<div class="register-inp login-inp-top">
		<span class="register-inp-text">验证码</span>
		<input type="text"  value="" id="mobileCode" class="register-inp-short"/>
		<div class="register-inp-btn" id="codeMobileText" onclick="getCode()" >获取验证码</div>
	</div>
	<div class="clear"></div>
	
	<div class="tutor-search">
		<div class="tutor-search-btn" onclick="doForget()" >确定</div>
	</div>
</body>
</html>