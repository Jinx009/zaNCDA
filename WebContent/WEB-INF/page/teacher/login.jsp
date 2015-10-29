<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>导师平台</title>
<link rel="stylesheet" href="/sp/dist/css/bootstrap.css" >

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
		$("#accountDiv").css("display","block");
		$("#loginDiv").css("display","none");
	}else{
		$("#cancelBtn").css("display","none");
		if(""!=openid){
			$("#accountDiv").css("display","none");
			$("#loginDiv").css("display","block");
		}else{
			location.href = url;
		}
	}
})

/**
 * 登陆
 */
function doLogin(){
	var url = $("#url").val();
	var username = $("#username").val();
	var password = $("#password").val();
	var openid = $("#openid").val();
	var code = $("#code").val();
	var params = "username="+username+"&password="+password+"&openid="+openid+"&code="+code;
	
	$.ajax({
		url:"/teacher/doLogin.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				location.href = url;
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
		url:"/teacher/getCode.html?time="+getRandom(),
		type:"GET",
		dataType:"json",
		success:function(res){
			$("#codeText").html(res.teacherCode);
		}
	})
}
</script>
<style type="text/css">
.width100{
	width: 100%;
}
body {
	width: 100%;
	font-family: "Microsoft Yahei";
	text-align: center;
}
.space-div-8{
	height: 80px;
}
.right{
	text-align: right;
}
</style>
</head>
<body>
	<input type="hidden" value="${openid }" id="openid" >
	<input type="hidden" value="${status }" id="status" >
	<input type="hidden" value="${url }" id="url" >
	<div id="accountDiv" style="display:none;" >
		<div class="space-div-8" ></div>
		<div class="space-div-8" ></div>
		<div class="btn btn-info width100"><a href="/tpage/comments.html" >我的约谈</a></div>
		<div class="space-div-8" ></div>
		<div class="btn btn-info width100"><a href="/tpage/info.html" >我的信息</a></div>
		<div class="space-div-8" ></div>
		<div class="btn btn-info width100"><a href="/tpage/account.html" >我的账户</a></div>
		<div class="space-div-8" ></div>
		<div class="space-div-8 right " onclick="showDiv('loginDiv')" >切换其他账号</div>
	</div>
	<div id="loginDiv" style="display:none;" >
		<div class="space-div-8" >
			<h2>导师登陆</h2>
			<input type="file" value="hehe" >
		</div>
		<div class="row" >
		  <div class="col-sm-1"></div>
	      <div class="col-sm-10">
	      	<form class="form-inline">
			  <div class="form-group">
			    <div class="input-group">
			      <div class="input-group-addon">账户:</div>
			      <input type="text" class="form-control" id="username" placeholder="请输入用户名">
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="input-group">
			      <div class="input-group-addon">密码:</div>
			      <input type="text" class="form-control" id="password" placeholder="请输入密码">
			    </div>
			  </div>
			   <div class="form-group">
			    <div class="input-group">
			      <input type="text" class="form-control" id="code" placeholder="请输入验证码">
			      <div class="input-group-addon" id="codeText" onclick="changeCode()" ></div>
			    </div>
			  </div>
			   <div class="form-group">
				  <a href="" >忘记密码</a>
			  </div>
			  <button type="button" class="btn btn-primary" onclick="doLogin()" >登陆</button>
			  <button type="button" class="btn btn-primary" onclick="goRegister" >注册</button>
			  <button type="button" class="btn btn-default" id="cancelBtn" onclick="showDiv('accountDiv')" >取消</button>
			</form>
	      </div>
	      <div class="col-sm-1"></div>
	     </div>
	</div>
</body>
</html>