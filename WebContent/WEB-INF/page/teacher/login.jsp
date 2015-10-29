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
	var status = $("#status").val();
	
	if("1"==status){
		$("#accountDiv").css("display","block");
		$("#loginDiv").css("display","none");
	}
	else{
		$("#accountDiv").css("display","none");
		$("#loginDiv").css("display","block");
	}
})
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
	<input type="hidden" value="${status }" id="status" >
	<div id="accountDiv" style="display:none;" >
		<div class="space-div-8" ></div>
		<div class="space-div-8" ></div>
		<div class="btn btn-info width100">我的约谈</div>
		<div class="space-div-8" ></div>
		<div class="btn btn-info width100">我的信息</div>
		<div class="space-div-8" ></div>
		<div class="btn btn-info width100">我的账户</div>
		<div class="space-div-8" ></div>
		<div class="space-div-8 right " onclick="showDiv('loginDiv')" >切换其他账号</div>
	</div>
	<div id="loginDiv" style="display:none;" >
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
				  <a href="" >忘记密码</a>
			  </div>
			  <button type="button" class="btn btn-primary">登陆</button>
			  <button type="button" class="btn btn-default" onclick="showDiv('accountDiv')" >取消</button>
			</form>
	      </div>
	      <div class="col-sm-1"></div>
	     </div>
	</div>
</body>
</html>