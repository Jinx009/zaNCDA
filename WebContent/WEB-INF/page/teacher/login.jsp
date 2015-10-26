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
	var openid = $("#openid").val();
	
	
})
</script>
<style type="text/css">
.width100{
	width: 100%;
}
body {
	width: 100%;
}
.space-div-8{
	height: 80px;
}
</style>
</head>
<body>
	<input type="hidden" value="${nbTeachersUser.openid }" id="openid" >
	<div class="space-div-8" ></div>
	<div class="space-div-8" ></div>
	<div class="btn btn-info width100">我的约谈</div>
	<div class="space-div-8" ></div>
	<div class="btn btn-info width100">我的信息</div>
	<div class="space-div-8" ></div>
	<div class="btn btn-info width100">我的账户</div>
</body>
</html>