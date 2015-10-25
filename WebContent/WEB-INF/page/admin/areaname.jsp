<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>辅助管理</title>
<link rel="stylesheet" href="/sp/dist/css/bootstrap.css" >
<link rel="stylesheet" href="/sp/css/main.css" >

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/dist/js/bootstrap.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
var jsonData;

$(function(){
	getData();
})

/**
 * 获取数据
 */
function getData(){
	$.ajax({
		url:"/areaname/list.html",
		type:"POST",
		dataType:"json",
		success:function(res){
			jsonData = res.data;
			var htmlStr = "";
			
			for(var i = 0;i<res.data.length;i++){
				htmlStr += "<tr>";
				htmlStr += "<td>"+res.data[i].id+"</td>";
				htmlStr += "<td>"+res.data[i].areaName+"</td>";
				htmlStr += "<td>";
				htmlStr += "<div class='btn-toolbar' role='toolbar' aria-label='...'>";
				htmlStr += "<button type='button' class='btn btn-defaul' onclick=editDiv('"+i+"') >编辑</button>";
				htmlStr += "<button type='button' class='btn btn-defaul' onclick=deleteDiv('"+i+"')  >删除</button>";
				htmlStr += "</div>";
				htmlStr += "</td>";
				htmlStr += "</tr>";
			}
			
			$("#dataList").html(htmlStr);
		}
	})
}

/**
 * 编辑
 */
function doEdit(){
	var id = $("#editId").val();
	var value = $("#editValue").val();
	var params = "id="+id+"&areaName="+value;
	
	$.ajax({
		url:"/areaname/update.html",
		data:params,
		type:"POST",
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				hideAlert('editDiv');
				getData();
			}
		}
	})
}

/**
 * 保存
 */
function doSave(){
	var value = $("#saveValue").val();
	var params = "areaName="+value;
	
	$.ajax({
		url:"/areaname/save.html",
		data:params,
		type:"POST",
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				hideAlert('saveDiv');
				getData();
			}
		}
	})
}

/**
 * 删除
 */
function doDelete(){
	var id = $("#deleteId").val();
	var params = "id="+id;
	
	$.ajax({
		url:"/areaname/delete.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				hideAlert("deleteDiv");
				getData();
			}
		}
	})
}

/**
 * 删除弹框
 */
function deleteDiv(index){
	$("#deleteId").val(jsonData[parseInt(index)].id);
	
	showAlert("deleteDiv");
}

/**
 * 弹出新建框
 */
function saveDiv(){
	$("#saveValue").val("");
	showAlert("saveDiv");
}

/**
 * 编辑职位弹窗
 */
function editDiv(index){
	$("#editId").val(jsonData[parseInt(index)].id);
	$("#editValue").val(jsonData[parseInt(index)].areaName);
	
	showAlert("editDiv");
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
</style>
</head>
<body>
	<div class="space-div-1" ></div>
	<div class="row index-row" >
		<div class="col-md-2 index-col-md-2" >
			<div class="list-group">
			  <a href="/admin/index.html" class="list-group-item">教师管理</a>
			  <a href="/admin/student.html" class="list-group-item">顾客管理</a>
			  <a href="/admin/order.html" class="list-group-item">兑付管理</a>
			  <a href="/admin/util.html" class="list-group-item active">辅助管理</a>
			</div>
		</div>
		<div class="col-md-10 index-col-md-10" >
		<div class="btn-group" role="group" aria-label="...">
		  <button type="button" class="btn btn-default" onclick="openUrl('/admin/util.html')" >职务管理</button>
		  <button type="button" class="btn btn-info" onclick="openUrl('/admin/areaname.html')" >擅长领域</button>
		</div>
			<div class="row" >
				<div class="col-md-3" >
					<div class="space-div-3 " ></div>
					<div class="btn-group" role="group" aria-label="...">
					  <button type="button" class="btn btn-default" onclick="saveDiv()" >新增</button>
					</div>
				</div>
				<div class="col-md-9" ></div>
			</div> 
			<hr class="width100" >
			<table class="table" >
				<thead>
					<tr>
						<td>编号</td>
						<td>名称</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody id="dataList" ></tbody>
			</table>
		</div>
	</div>
	
	<!-- 编辑 -->
	<div class="modal face height"  id="editDiv" tabindex="-1" role="dialog" aria-labelledby="myDeleteLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="hideAlert('editDiv')" >&times;</button>
				<h4 class="modal-title" id="myriskLabel">修改</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
				    <label class="col-sm-4 control-label">编号</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="editId" placeholder="编号" readonly="readonly">
				    </div>
			  	</div>
			  	<div class="space-div-4" ></div>
			  	<div class="form-group">
				    <label class="col-sm-4 control-label">名称</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="editValue" placeholder="擅长领域" >
				    </div>
			  	</div>
			  	<div class="space-div-4" ></div>
			  	<div class="form-group">
				    <label class="col-sm-4 control-label"></label>
				    <div class="col-sm-4">
				      <input type="button" class="btn btn-info width100"  onclick="doEdit()" value="确定" >
				    </div>
				     <label class="col-sm-4 control-label"></label>
			  	</div>
			</div>
			<div class="space-div-2" ></div>
		</div>
	</div>
	</div>
	
	<!-- 新建 -->
	<div class="modal face height"  id="saveDiv" tabindex="-1" role="dialog" aria-labelledby="myDeleteLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="hideAlert('saveDiv')" >&times;</button>
				<h4 class="modal-title" id="myriskLabel">新建</h4>
			</div>
			<div class="modal-body">
			  	<div class="space-div-4" ></div>
			  	<div class="form-group">
				    <label class="col-sm-4 control-label">名称</label>
				    <div class="col-sm-8">
				      <input type="text" class="form-control" id="saveValue" placeholder="擅长领域" >
				    </div>
			  	</div>
			  	<div class="space-div-4" ></div>
			  	<div class="form-group">
				    <label class="col-sm-4 control-label"></label>
				    <div class="col-sm-4">
				      <input type="button" class="btn btn-info width100"  onclick="doSave()" value="确定" >
				    </div>
				     <label class="col-sm-4 control-label"></label>
			  	</div>
			</div>
			<div class="space-div-2" ></div>
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