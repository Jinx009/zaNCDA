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
	var id = $("#id").val();
	var params = "id="+id;
	$.ajax({
		url:"/tdata/orderDetail.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			console.log(res);
			var htmlStr = "";
			htmlStr += "<p>["+res.errmsg.nbStudentsUser.realName+"] 支付状态:"+getPayStatus(res.errmsg)+"%</p>";
			htmlStr += "<p>约谈主题:</p>"
			htmlStr += "<p>"+res.errmsg.courseTopic+"</p>";
			htmlStr += "<a class='btn btn-default' >申请取消</a>";
			htmlStr += "<p>约谈时间:"+jsDateTimeOnly(res.errmsg.studentPreferedDate)+"</p>";
			htmlStr += "<p>约谈状态；"+getOrderStatus(res.errmsg.commentsStatus)+"</p>";
			if(null!=res.score&&res.score.length){
				htmlStr += "<p>客户评分:"+res.score[0].score+"</p>";
				htmlStr += "<p>客户评语:</p><p>"+res.score[0].comments+"</p>";
			}else{
				htmlStr += "<p>客户评分:暂未打分!</p>";
				htmlStr += "<p>客户评语:</p><p>暂无</p>";
			}
			htmlStr += "<a class='btn btn-info' onclick=openUrl('/tpage/epilog.html?id="+id+"') >填小结</a>";
			$("#dataDiv").html(htmlStr);
		}
	})
})
</script>
<style type="text/css">

</style>
</head>
<body>
	<input type="hidden" value="${id }" id="id" >
	<div id="dataDiv" ></div>
</body>
</html>