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
/**
 * 登陆
 */
function doLogin(){
	var userName = $("#userName").val();	
	var pwd = $("#pwd").val();
	var code = $("#code").val();
	var params = "userName="+userName+"&pwd="+pwd+"&code="+code;
	
	$.ajax({
		url:"/admin/data/login.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				location.href = "/admin/page/tutor.html";
			}else{
				alert(res.errmsg);
			}
		}
	})
}

$(function(){
	getCode();
})

/**
 * 验证码
 */
function getCode(){
	$.ajax({
		url:"/admin/data/getCode.html?time="+getRandom(),
		type:"GET",
		dataType:"json",
		success:function(res){
			$("#codeText").text(res.admin_code);
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
			      <input type="text" class="form-control" id="userName" placeholder="账户">
			    </div>
			  </div>
			  <div class="form-group">
			    <label  class="col-sm-2 control-label">密码</label>
			    <div class="col-sm-10">
			      <input type="password" class="form-control" id="pwd" placeholder="密码">
			    </div>
			  </div>
			  <div class="form-group">
			    <label  class="col-sm-2 control-label">验证码</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="code" placeholder="验证码">
			    </div>
			    <label  class="col-sm-4 control-label" onclick="getCode()" id="codeText" ></label>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10"></div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <button type="button" class="btn btn-default" onclick="doLogin()" >登录</button>
			    </div>
			  </div>
			</form>
		</div>
		<div class="col-sm-4"></div>
	</div>
</body>
</html>