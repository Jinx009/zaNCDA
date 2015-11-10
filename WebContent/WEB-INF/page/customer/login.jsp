<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>顾客平台</title>
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
				location.href = "/customer/page/index.html";
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
		url:"/customer/data/getCode.html?time="+getRandom(),
		type:"GET",
		dataType:"json",
		success:function(res){
			$("#codeText").html(res.tutor_code);
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
	<div id="loginDiv"  >
		<div class="space-div-8" >
			<h2>导师登陆</h2>
		</div>
		<div class="row" >
		  <div class="col-sm-1"></div>
	      <div class="col-sm-10">
	      	<form class="form-inline">
			  <div class="form-group">
			    <div class="input-group">
			      <div class="input-group-addon">账户:</div>
			      <input type="text" class="form-control" id="userName" placeholder="请输入用户名">
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="input-group">
			      <div class="input-group-addon">密码:</div>
			      <input type="password" class="form-control" id="pwd" placeholder="请输入密码">
			    </div>
			  </div>
			   <div class="form-group">
			    <div class="input-group">
			      <input type="text" class="form-control" id="code" placeholder="请输入验证码">
			      <div class="input-group-addon" id="codeText" onclick="changeCode()" ></div>
			    </div>
			  </div>
			   <div class="form-group">
			  </div>
			  <button type="button" class="btn btn-primary" onclick="doLogin()" >登陆</button>
			</form>
	      </div>
	      <div class="col-sm-1"></div>
	     </div>
	</div>
</body>
</html>