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
	$.ajax({
		url:"/tdata/tcomments.html",
		type:"POST",
		dataType:"json",
		success:function(res){
			console.log(res);
			var htmlStr = "";
			
			for(var i = 0;i<res.errmsg.length;i++){
				htmlStr += "<div>";
				htmlStr += "<p>【客户名称:"+res.errmsg[i].nbStudentsUser.realName+"】支付状态:"+getPayStatus(res.errmsg[i])+"%</p>";
				htmlStr += "<p>"+res.errmsg[i].courseTopic+"</p>";
				htmlStr += "";
			}
		}
	})
})

/**
 * 获取支付进度
 */
function getPayStatus(data){
	if(1==data.secondPayIsDone){
		return 100;
	}else{
		if(1==data.firstPayIsDone){
			return 50;
		}else{
			return 0;
		}
	}
}
</script>
<style type="text/css">

</style>
</head>
<body>
	<div id="dataInfo" ></div>
</body>
</html>