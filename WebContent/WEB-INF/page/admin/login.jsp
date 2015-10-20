<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台登陆</title>
<link rel="stylesheet" href="/sp/dist/css/bootstrap.css" >

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/dist/js/bootstrap.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
function doLogin(){
	var userName = $("#username").val();	
	var pwd = $("#password").val();
	var adminCode = $("#adminCode").val();
	var params = "userName="+userName+"&password="+pwd+"&code="+adminCode;
	
	$.ajax({
		url:"/admin/doLogin.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				location.href = "/admin/index.html";
			}else{
				alert(res.errmsg);
			}
		}
	})
}
$(function(){
	getAdminCode();
})
function getAdminCode(){
	$.ajax({
		url:"/manager/getCode.html?time="+getRandom(),
		type:"GET",
		dataType:"json",
		success:function(res){
			$("#adminCodeText").text(res.adminCode);
		}
	})
}
</script>
</head>
<body>
	<div class="space-div-8" ></div>
	<div class=row >
		 <div class="col-sm-4"></div>
		 <div class="col-sm-4">
			<form class="form-horizontal">
			  <div class="form-group">
			    <label class="col-sm-2 control-label">账号</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="username" placeholder="账户">
			    </div>
			  </div>
			  <div class="form-group">
			    <label  class="col-sm-2 control-label">密码</label>
			    <div class="col-sm-10">
			      <input type="password" class="form-control" id="password" placeholder="密码">
			    </div>
			  </div>
			  <div class="form-group">
			    <label  class="col-sm-2 control-label">验证码</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="adminCode" placeholder="验证码">
			    </div>
			    <label  class="col-sm-4 control-label" onclick="getAdminCode()" id="adminCodeText" ></label>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10"></div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <button type="button" class="btn btn-default" onclick="doLogin()" >登陆</button>
			    </div>
			  </div>
			</form>
		</div>
		<div class="col-sm-4"></div>
	</div>
</body>
</html>