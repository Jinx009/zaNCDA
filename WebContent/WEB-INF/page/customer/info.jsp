<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>个人信息</title>
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript" src="/sp/js/date/WdatePicker.js" ></script>
<script type="text/javascript">
$(function(){
	$("#birthday").val(jsDateTimeOnly($("#birth").val()));
})

function saveInf(){
	var realName = $("#realName").val();
	var qq = $("#qq").val();
	var email =  $("#email").val();
	var sex = $("#sex").val();
	var birthday = $("#birthday").val();
	var famillyNumber = $("#familly").val();
	var wechatName = $("#wechatName").val();

	var params = "realName="+realName+"&qq="+qq+"&email="+email+"&sex="+sex+"&birthday="+birthday+
				"&famillyNumber="+famillyNumber+"&wechatName="+wechatName;
	$.ajax({
		url:"/customer/data/saveInfo.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				alert("保存成功!");
			}
		}
	})
}
</script>
</head>
<body>
<a href="#"><div class="nav-title"><span class="nav-back" onclick="openUrl('/customer/page/index.html')" >&lt;</span>个人信息</div></a>
<div class="register-inp register-inp-top">
	<span class="register-inp-text">姓名</span>
	<input  value="${customer.realName }" id="realName" class="register-inp-long"/>
</div>
<div class="register-inp register-inp-top">
	<span class="register-inp-text">性别</span>
	<input  value="${customer.sex }" id="sex" class="register-inp-long"/>
</div>
<div class="register-inp register-inp-top">
	<span class="register-inp-text">生日</span>
	<input type="hidden" id="birth" value="${customer.birthday }" >
	<input  value="" id="birthday" onClick="WdatePicker()"  class="register-inp-long"/>
</div>
<!-- <div class="register-inp register-inp-top">
	<span class="register-inp-text">行业</span>
	<select class="register-select-long">
		<option value="IT" selected="selected">IT</option>
		<option value="石油">石油</option>
		<option value="销售">销售</option>
	</select>
</div>
<div class="register-inp register-inp-top">
	<span class="register-inp-text">职位</span>
	<select class="register-select-long">
		<option value="医生" selected="selected">医生</option>
		<option value="教授">教授</option>
	</select>
</div> -->
<div class="register-inp register-inp-top">
	<span class="register-inp-text">家庭成员个数</span>
	<input  value="${customer.famillyNumber }" type="text" id="familly" class="register-inp-long"/>
</div>
<div class="register-inp register-inp-top">
	<span class="register-inp-text">E-mail</span>
	<input  value="${customer.email }" id="email" class="register-inp-long"/>
</div>
<div class="register-inp register-inp-top">
	<span class="register-inp-text">QQ</span>
	<input  value="${customer.qq }" id="qq" class="register-inp-long"/>
</div>
<div class="register-inp register-inp-top">
	<span class="register-inp-text">微信</span>
	<input  value="${customer.wechatName }" id="wechatName" class="register-inp-long"/>
</div>
<div class="sure-btn">
	<div class="tutor-search-btn" id="sure" onclick="saveInf()" >提交</div>
</div>
<!--蒙版-->
<div class="mask"></div>
<!--悬浮框	start-->
<div class="boxWrap">
	<!--注册失败	start-->
	<div class="failure-box">
		<div class="success-img">
			<img src="/sp/images/failure.png"/>
		</div>
		<p class="success-p">注册失败，请重新注册！</p>
		<div class="success-btn failure-btn-bgColor">再试一次</div>
	</div>
	<!--注册失败	start-->
	
</div>
		<!--悬浮框	end-->
	
</body>
</html>