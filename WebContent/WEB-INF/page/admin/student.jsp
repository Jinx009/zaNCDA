<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>顾客管理</title>
<link rel="stylesheet" href="/sp/dist/css/bootstrap.css" >
<link rel="stylesheet" href="/sp/css/main.css" >

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/dist/js/bootstrap.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
var dataList;
$(function(){
	getDataList(1);
})

/**
 * 获取顾客列表
 */
function getDataList(pageNum){
	var realName = $("#searchName").val();
	var mobilePhone = $("#searchMobile").val();
	
	var params = "realName="+realName+"&mobilePhone="+mobilePhone+"&pageNum="+pageNum;
	
	$.ajax({
		url:"/student/list.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			dataList = res.data.list;
			var menuHtml = "",htmlStr = "";
			if(null!=res.data){
				for(var i = 1;i<=parseInt(res.data.page.total);i++){
					menuHtml += "<li  id=page"+i+" ><a href=javascript:getDataList('"+i+"')>"+i+"</a></li>";
				}
				for(var i = 0;i<res.data.list.length;i++){
					htmlStr += "<tr>";
					htmlStr += "<td>"+res.data.list[i].id+"</td>";
					htmlStr += "<td>"+res.data.list[i].realName+"</td>";
					htmlStr += "<td>"+res.data.list[i].mobilePhone+"</td>";
					htmlStr += "<td>"+res.data.list[i].qq+"</td>";
					htmlStr += "<td>"+res.data.list[i].email+"</td>";
					htmlStr += "<td>";
					htmlStr += "<button type='button' class='btn btn-defaul' onclick=openLook('"+i+"') >查看</button>";
					htmlStr += "</div>";
					htmlStr += "</td>";
				}
			}
			$("#pageMenu").html(menuHtml);
			$("#page"+pageNum).addClass("active");
			$("#dataList").html(htmlStr);
		}
	})
}

/***
 * 查看
 */
function openLook(index){

	$("#lookRealName").html(dataList[parseInt(index)].realName);
	$("#lookMobilePhone").html(dataList[parseInt(index)].mobilePhone);
	$("#lookBirth").html(jsDateTimeOnly(dataList[parseInt(index)].birth));
	$("#lookFamillyNum").html(dataList[parseInt(index)].famillyNum);
	$("#lookEmail").html(dataList[parseInt(index)].email);
	$("#lookQQ").html(dataList[parseInt(index)].qq);
	
	showAlert("lookDiv");
}
</script>

<style type="text/css">
.index-row{
	width: 96%;
	margin-left: 2%;
}
.index-col-md-2{
	width: 10%;
}
.index-col-md-10{
	width: 90%;
}
.img-width{
	width: 100px;
}
.list-group-item span{
	color: blue;
}
</style>
</head>
<body>
	<div class="space-div-1" ></div>
	<div class="row index-row" >
		<div class="col-md-2 index-col-md-2" >
			<div class="list-group">
			  <a href="/admin/index.html" class="list-group-item">教师管理</a>
			  <a href="/admin/student.html" class="list-group-item  active">顾客管理</a>
			  <a href="/admin/order.html" class="list-group-item">兑付管理</a>
			  <a href="/admin/util.html" class="list-group-item">辅助管理</a>
			</div>
		</div>
		<div class="col-md-10 index-col-md-10" >
			<div class="row" >
				<div class="col-md-6" >
				<form class="form-horizontal">
					  <div class="form-group">
					    <label class="col-sm-3 control-label">顾客姓名:</label>
					    <div class="col-sm-5">
					      <input type="text" class="form-control" id="searchName" value="" >
					      <label class="col-sm-4 control-label"></label>
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-sm-3 control-label">顾客手机:</label>
					    <div class="col-sm-5">
					      <input type="text" class="form-control" id="searchMobile"  value="" >
					    </div>
					     <label class="col-sm-4 control-label"></label>
					  </div>
				  </form>
				</div>
				<div class="col-md-3" >
					<div class="space-div-3 " ></div>
					<div class="btn-group" role="group" aria-label="...">
					  <button type="button" class="btn btn-default" onclick="getDataList('1');" >执行查询</button>
					</div>
				</div>
				<div class="col-md-3" ></div>
			</div> 
			<hr class="width100" >
			<a class="btn btn-default" >导出</a>
			<table class="table" >
				<thead>
					<tr>
						<td>编号</td>
						<td>姓名</td>
						<td>手机</td>
						<td>QQ</td>
						<td>邮箱</td>
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
	
	
	<!--查看-->
	<div class="modal face height"  id="lookDiv" tabindex="-1" role="dialog" aria-labelledby="myDeleteLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="hideAlert('lookDiv')" >&times;</button>
				<h4 class="modal-title" id="myriskLabel">查看</h4>
			</div>
			<div class="modal-body">
			  	<ul class="list-group">
				   <li class="list-group-item">姓名:<span id="lookRealName" ></span></li>
				   <li class="list-group-item">手机:<span id="lookMobilePhone" ></span></li>
				   <li class="list-group-item">生日:<span id="lookBirth" ></span></li>
				   <li class="list-group-item">家庭成员:<span id="lookFamillyNum" ></span></li>
				   <li class="list-group-item">邮箱:<span id="lookEmail" ></span></li>
				   <li class="list-group-item">QQ:<span id="lookQQ" ></span></li>
				</ul>
			</div>
			<div class="space-div-2" ></div>
		</div>
	</div>
	</div>
</body>
</html>