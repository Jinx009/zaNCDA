<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台主页</title>
<link rel="stylesheet" href="/sp/dist/css/bootstrap.css" >
<link rel="stylesheet" href="/sp/css/main.css" >

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/dist/js/bootstrap.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript" src="/sp/js/date/WdatePicker.js" ></script>
<script type="text/javascript" src="/sp/dist/jquery.form.js" ></script>
<script type="text/javascript">
var data;

$(function(){
	 getList(1);
})

/**
 *获取列表 
 */
function getList(pageNum){
	var realName = $("#searchName").val();
	var mobilePhone = $("#searchMobile").val();
	var params = "pageNum="+pageNum+"&realName="+realName+"&mobilePhone="+mobilePhone;
	$.ajax({
		url:"/admin/tutor/list.html",
		data:params,
		type:"POST",
		dataType:"json",
		ansyc:false,
		success:function(res){
			console.log(res)
			data = res.data.list;
			var menuHtml = "",htmlStr = "";
			if(null!=res.data){
				for(var i = 1;i<=parseInt(res.data.page.total);i++){
					menuHtml += "<li  id=page"+i+" ><a href=javascript:getTeacherList('"+i+"')>"+i+"</a></li>";
				}
				
				for(var i = 0;i<res.data.list.length;i++){
					htmlStr += "<tr>";
					htmlStr += "<td>"+res.data.list[i].id+"</td>";
					htmlStr += "<td>"+res.data.list[i].realName+"</td>";
					htmlStr += "<td>"+res.data.list[i].mobilePhone+"</td>";
					htmlStr += "<td>"+res.data.list[i].qq+"</td>";
					htmlStr += "<td>"+res.data.list[i].email+"</td>";
					htmlStr += "<td>";
					htmlStr += "<div class='btn-toolbar' role='toolbar' aria-label='...'>";
					htmlStr += "<button type='button' class='btn btn-defaul' onclick=openEdit('"+i+"') >编辑</button>";
					htmlStr += "<button type='button' class='btn btn-defaul' onclick=deleteDiv('"+i+"')  >删除</button>";
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

/**
 * 编辑
 */
function openEdit(index){
	location.href = "/admin/teacherEdit.html?id="+dataJson[parseInt(index)].id;
}

/**
 * 删除
 */
function deleteDiv(index){
	$("#deleteId").val(dataJson[parseInt(index)].id);
	
	showAlert("deleteDiv");
}

/**
 * 删除
 */
function doDelete(){
	var id = $("#deleteId").val();
	var params = "id="+id;
	
	$.ajax({
		url:"/teacher/delete.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				getTeacherList(1);
				hideAlert("deleteDiv");
			}
		}
	})
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
</style>
</head>
<body>
	<div class="space-div-1" ></div>
	<div class="row index-row" >
		<div class="col-md-2 index-col-md-2" >
			<div class="list-group">
			  <a href="/admin/page/tutor.html" class="list-group-item active">导师管理</a>
			  <a href="/admin/student.html" class="list-group-item">顾客管理</a>
			  <a href="/admin/order.html" class="list-group-item">约谈管理</a>
			  <a href="/admin/util.html" class="list-group-item">资料管理</a>
			</div>
		</div>
		<div class="col-md-10 index-col-md-10" >
			<div class="row" >
				<div class="col-md-6" >
				<form class="form-horizontal">
					  <div class="form-group">
					    <label class="col-sm-3 control-label">导师姓名:</label>
					    <div class="col-sm-5">
					      <input type="text" class="form-control" id="searchName" value="" >
					      <label class="col-sm-4 control-label"></label>
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-sm-3 control-label">导师手机:</label>
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
					  <button type="button" class="btn btn-default" onclick="getTeacherList('1');" >执行查询</button>
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
	
		<!-- 确定删除-->
	<div class="modal face height"  id="deleteDiv" tabindex="-1" role="dialog" aria-labelledby="myDeleteLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="hideAlert('deleteDiv')" >&times;</button>
				<h4 class="modal-title" id="myriskLabel">确认删除</h4>
			</div>
			<div class="modal-body">
			  	<div class="space-div-4" id="alertMsg" >确定要删除?</div>
			  	<div class="form-group">
				    <label class="col-sm-4 control-label"></label>
				    <div class="col-sm-8">
				      <input type="hidden" class="form-control" id="deleteId" >
				    </div>
			  	</div>
			  	<div class="space-div-4" ></div>
			  	<div class="form-group">
				    <label class="col-sm-4 control-label"></label>
				    <div class="col-sm-4">
				      <input type="button" class="btn btn-info width100"  onclick="doDelete()" value="确定" >
				    </div>
				     <label class="col-sm-4 control-label"></label>
			  	</div>
			</div>
			<div class="space-div-2" ></div>
		</div>
	</div>
	</div>
</body>
</html>