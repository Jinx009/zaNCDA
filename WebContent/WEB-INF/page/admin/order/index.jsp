<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台</title>
<link rel="stylesheet" href="/sp/dist/css/bootstrap.css" >
<link rel="stylesheet" href="/sp/css/admin/main.css" >

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/dist/js/bootstrap.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript" src="/sp/js/date/WdatePicker.js" ></script>
<script type="text/javascript">
var data;

$(function(){
	 getData(1);
})

function getData(pageNum){
	var tutorName = "",tutorPhone = "",customerName = "",customerPhone = "";
	var params = "pageNum="+pageNum+"&tutorName="+tutorName+"&tutorPhone="+tutorPhone+"&customerName="+customerName+"&customerPhone="+customerPhone;
	
	$.ajax({
		url:"/admin/data/order.html?time="+getRandom(),
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			data = res.data.list;
			var menuHtml = "",htmlStr = "";
			if(null!=res.data){
				for(var i = 1;i<=parseInt(res.data.page.total);i++){
					menuHtml += "<li  id=page"+i+" ><a href=javascript:getTeacherList('"+i+"')>"+i+"</a></li>";
				}
				
				for(var i = 0;i<res.data.list.length;i++){
					htmlStr += "<tr>";
					htmlStr += "<td>"+res.data.list[i].id+"</td>";
					htmlStr += "<td>"+isNull(res.data.list[i].qTutor.realName)+"</td>";
					htmlStr += "<td>"+isNull(res.data.list[i].qCustomer.realName)+"</td>";
					htmlStr += "<td>";
					htmlStr += "<a class='btn btn-default' onclick=openLook('"+i+"','comments') >查看导师评论</a>";
					htmlStr += "<a class='btn btn-default' onclick=openLook('"+i+"','score') >查看顾客评论</a>";
					htmlStr += "</td>";
				}
			}
			$("#pageMenu").html(menuHtml);
			$("#page"+pageNum).addClass("active");
			$("#dataList").html(htmlStr);
		}
	})
}

function openLook(index,type){
	if("comments"==type){
		getComments(index);
	}else{
		getScore(index);
	}
}

/**
 * 获取顾客评分
 */
function getScore(index){
	var id = data[parseInt(index)].id;
	
	$.ajax({
		url:"/score/data/score.html",
		data:"id="+id,
		dataType:"json",
		type:"POST",
		success:function(res){
			if("success"==res.result){
				var htmlStr = "";
				
				for(var i = 0;i<res.errmsg.length;i++){
					htmlStr += "<tr>";
					htmlStr += "<td>"+jsDateTimeOnly(res.errmsg[i].addTime)+"</td>";
					htmlStr += "<td>"+res.errmsg[i].oneScore+"</td>";
					htmlStr += "<td>"+res.errmsg[i].twoScore+"</td>";
					htmlStr += "<td>"+res.errmsg[i].threeScore+"</td>";
					htmlStr += "<td>"+res.errmsg[i].fourScore+"</td>";
					htmlStr += "<td>"+res.errmsg[i].content+"</td>";
					htmlStr += "</tr>";
				}
				$("#scoreData").html(htmlStr);
			}
		}
	})
	$("#scoreDiv").show();
	$("#commentsDiv").hide();
	
	showAlert("lookDiv");	
}

/**
 * 获取导师评语
 */
function getComments(index){
	var id = data[parseInt(index)].id;
	
	$.ajax({
		url:"/comments/data/comments.html",
		data:"id="+id,
		dataType:"json",
		type:"POST",
		success:function(res){
			if("success"==res.result){
				var htmlStr = "";
				
				for(var i = 0;i<res.errmsg.length;i++){
					htmlStr += "<tr><th>编号:"+res.errmsg[i].id+"添加时间:"+jsDateTimeOnly(res.errmsg[i].addTime)+"</th></tr>";
					htmlStr += "<tr><td>诉求一:"+res.errmsg[i].appealOne+"</td></tr>";
					htmlStr += "<tr><td>诉求二:"+res.errmsg[i].appealTwo+"</td></tr>";
					htmlStr += "<tr><td>诉求三:"+res.errmsg[i].appealThree+"</td></tr>";
					htmlStr += "<tr><td>主要问题:"+res.errmsg[i].question+"</td></tr>";
					htmlStr += "<tr><td>辅导策略（问题解决情况）:"+res.errmsg[i].solveStatus+"</td></tr>";
					htmlStr += "<tr><td>辅导策略（主要辅导工具）:"+res.errmsg[i].solveTool+"</td></tr>";
					htmlStr += "<tr><td>发展建议一:"+res.errmsg[i].adviceOne+"</td></tr>";
					htmlStr += "<tr><td>发展建议二:"+res.errmsg[i].adviceTwo+"</td></tr>";
					htmlStr += "<tr><td>发展建议三:"+res.errmsg[i].adviceThree+"</td></tr>";
					htmlStr += "<tr><td>辅导成效（问题解决情况）:"+res.errmsg[i].solveResult+"</td></tr>";
					htmlStr += "<tr><td>辅导成效（自我评估）:"+res.errmsg[i].solveAssess+"</td></tr>";
				}
				$("#commentsData").html(htmlStr);
			}
		}
	})
	$("#scoreDiv").hide();
	$("#commentsDiv").show();
	
	showAlert("lookDiv");	
}
</script>
</head>
<body>
	<div class="space-div-1" ></div>
	<div class="row index-row" >
		<div class="col-md-2 index-col-md-2" >
			<div class="list-group">
			  <a href="/admin/page/tutor.html" class="list-group-item ">导师管理</a>
			  <a href="/admin/page/customer.html" class="list-group-item">顾客管理</a>
			  <a href="/admin/page/order.html" class="list-group-item active">约谈管理</a>
			  <a href="/admin/page/util.html" class="list-group-item">资料管理</a>
			</div>
		</div>
		<div class="col-md-10 index-col-md-10" >
			<div class="row" >
				<div class="col-md-4" >
				<form class="form-horizontal">
					  <div class="form-group">
					    <label class="col-sm-3 control-label">导师姓名:</label>
					    <div class="col-sm-5">
					      <input type="text" class="form-control" id="tutorName" value="" >
					      <label class="col-sm-4 control-label"></label>
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-sm-3 control-label">导师手机:</label>
					    <div class="col-sm-5">
					      <input type="text" class="form-control" id="tutorPhone"  value="" >
					    </div>
					     <label class="col-sm-4 control-label"></label>
					  </div>
				  </form>
				</div>
				<div class="col-md-4" >
				<form class="form-horizontal">
					  <div class="form-group">
					    <label class="col-sm-3 control-label">导师姓名:</label>
					    <div class="col-sm-5">
					      <input type="text" class="form-control" id="tutorName" value="" >
					      <label class="col-sm-4 control-label"></label>
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-sm-3 control-label">导师手机:</label>
					    <div class="col-sm-5">
					      <input type="text" class="form-control" id="tutorPhone"  value="" >
					    </div>
					     <label class="col-sm-4 control-label"></label>
					  </div>
				  </form>
				</div>
				<div class="col-md-4" >
					<div class="space-div-3 " ></div>
					<div class="btn-group" role="group" aria-label="...">
					  <button type="button" class="btn btn-default" onclick="getData('1');" >执行查询</button>
					</div>
				</div>
			</div> 
			<hr class="width100" >
			<a class="btn btn-default" >导出</a>
			<table class="table" >
				<thead>
					<tr>
						<td>编号</td>
						<td>导师姓名</td>
						<td>顾客姓名</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody id="dataList" ></tbody>
			</table>
			<div  class="row center" >
				<nav>
				  <ul class="pagination" id="pageMenu" > </ul>
				</nav>
			</div>
		</div>
	</div>
	
		<!-- 确定删除-->
	<div class="modal face height"  id="lookDiv" tabindex="-1" role="dialog" aria-labelledby="myDeleteLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="hideAlert('lookDiv')" >&times;</button>
				<h4 class="modal-title" id="myriskLabel">所有评论</h4>
			</div>
			<div class="modal-body height400">
				    <div class="col-sm-12">
				    	<table class="table table-bordered" id="scoreDiv" >
				    		<thead>
				    			<tr>
				    				<th>添加时间</th>
				    				<th>第一维度</th>
				    				<th>第二维度</th>
				    				<th>第三维度</th>
				    				<th>第四维度</th>
				    				<th>评语</th>
				    			</tr>
				    		</thead>
				    		<tbody id="scoreData" ></tbody>
				    	</table>
				    </div>
				      <div class="col-sm-12">
				    	<table class="table table-bordered" id="commentsDiv" >
				    		<tbody id="commentsData" ></tbody>
				    	</table>
				    </div>
			  	</div>
			</div>
			<div class="space-div-2" ></div>
		</div>
	</div>
</body>
</html>