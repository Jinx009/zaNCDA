<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>修改密码</title>
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/dist/js/bootstrap.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
/**
 * 修改密码
 */
function doChange(){
	var mobilePhone = $("#userName").val();
	var pwd = $("#pwd").val();
	var newPwd = $("#newPwd").val();
	var rePwd = $("#rePwd").val();
	var params = "mobilePhone="+mobilePhone+"&pwd="+pwd+"&newPwd="+newPwd;
	var result = validateTel(mobilePhone);
	if("success"!=result){
		alert(result);
	}else if(newPwd!=rePwd){
		$("#myAlertH").html("两次密码不一致！");
			showNewAlert();
		/* alert("两次密码不一致！"); */
	}else{
		$.ajax({
			url:"/tutor/data/changePwd.html",
			type:"POST",
			data:params,
			dataType:"json",
			success:function(res){
				if("success"==res.result){
					 $("#myAlertH").html("操作成功!");
 					$("#newAlertBtn").attr("onclick","openMyUrl('/tutor/login.html')");
 					showNewAlert();
				/* 	alert("操作成功!");
					location.href = "/tutor/login.html"; */
				}else{
					$("#myAlertH").html(res.errmsg);
 					showNewAlert();
				/* 	alert(res.errmsg); */
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
	<div class="nav-title">修改密码<div class="close" onclick="openUrl('/tutor/login.html')" >&Chi;</div></div>
	<div class="logo">
		<img src="/sp/images/logo.jpg"/>
	</div>
	<div class="register-inp login-inp-top">
		<span class="register-inp-text">手机号</span>
		<input type="text" placeholder="请输入您的手机号码" id="userName" value="" class="register-inp-long"/>
	</div>
	<div class="register-inp login-inp-top">
		<span class="register-inp-text">原始密码</span>
		<input type="password" placeholder="请输入您的原始密码" id="pwd"  value="" class="register-inp-long"/>
	</div>
	<div class="register-inp login-inp-top">
		<span class="register-inp-text">新密码</span>
		<input type="password" placeholder="请输入您的新密码" id="newPwd"  value="" class="register-inp-long"/>
	</div>
	<div class="register-inp login-inp-top">
		<span class="register-inp-text">确认密码</span>
		<input type="password" placeholder="请输入确认密码" id="rePwd"  value="" class="register-inp-long"/>
	</div>
	<div class="tutor-search">
		<div class="tutor-search-btn btn-orange-bg" onclick="doChange()" >确定</div>
	</div>
</body>
</html>