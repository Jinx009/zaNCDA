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

</script>
<style type="text/css">

</style>
</head>
<body>
	<div class="panel panel-primary">您好：${tutor }</div>
	<div class="panel panel-primary" onclick="openUrl('/tutor/page/order.html')" >我的约谈</div>
	<div class="panel panel-success" onclick="openUrl('/tutor/page/info.html')" >我的信息</div>
	<div class="panel panel-info" onclick="openUrl('/tutor/page/time.html')" >我的时间</div>
</body>
</html>