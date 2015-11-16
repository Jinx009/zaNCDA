<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />
<title>筛选导师</title>
<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
/**
 * 下一步
 */
function goNext(){
	location.href = "/customer/page/selectResult.html";
}
</script>
</head>
<body>
<a><div class="nav-title"><span class="nav-back" onclick="openUrl('/customer/page/new.html')" >&lt;</span>新建辅导</div></a>
<div class="choice-title"><h1>请选择您心仪导师的属性</h1></div>
<div class="register-inp register-inp-top">
	<span class="register-inp-text">擅长领域<b>*</b></span>
	<select class="register-select-long">
		<option value="" selected="selected"></option>
		
	</select>
</div>

<div class="register-inp register-inp-top">
	<span class="register-inp-text">专注行业<b>*</b></span>
	<select class="register-select-long">
		<option value="" selected="selected"></option>
		
	</select>
</div>

<div class="register-inp register-inp-top">
	<span class="register-inp-text">从业年限</span>
	<select class="register-select-long">
		<option value="" selected="selected"></option>
		
	</select>
</div>

<div class="register-inp register-inp-top">
	<span class="register-inp-text">沟通方式</span>
	<select class="register-select-long">
		<option value="" selected="selected"></option>
		
	</select>
</div>

<div class="register-inp register-inp-top">
	<span class="register-inp-text">辅导方式<b>*</b></span>
	<select class="register-select-long">
		<option value="" selected="selected"></option>
		
	</select>
</div>

<div class="register-inp register-inp-top">
	<span class="register-inp-text">常驻地区</span>
	<select class="register-select-long">
		<option value="" selected="selected"></option>
		
	</select>
</div>

<div class="btn-position">
	<div class="two-btn">
		<button onclick="openUrl('/customer/page/new.html')" >上一步</button>
		<button onclick="goNext()" >下一步</button>
	</div>
</div>
</body>
</html>