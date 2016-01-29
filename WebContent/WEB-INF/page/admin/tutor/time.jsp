<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>导师时间</title>
<link rel="stylesheet" href="/sp/dist/css/bootstrap.css" >
<link rel="stylesheet" href="/sp/css/main.css" >

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/dist/js/bootstrap.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript" src="/sp/js/date/WdatePicker.js" ></script>
<script type="text/javascript" src="/sp/dist/jquery.form.js" ></script>
<script type="text/javascript" src="/sp/js/laydate/laydate.js" ></script>
<script type="text/javascript">
$(function(){
	getData();
})
function getData(){
	var id = $("#id").val();
	$.ajax({
		url:"/admin/data/time.html?id="+id,
		type:"GET",
		dataType:"json",
		success:function(res){
			var htmlStr = "";
			if(null!=res.errmsg){
				$("#name").html(res.errmsg[0].qTutor.realName);
				for(var i = 0;i<res.errmsg.length;i++){
					htmlStr += "<tr>";
					htmlStr += "<td>"+res.errmsg[i].id+"</td>";
					htmlStr += "<td>"+jsDateTimeOnly(res.errmsg[i].realDate)+"</td>";
					htmlStr += "<td>"+res.errmsg[i].realTime+"</td>";
					if("1"==res.errmsg[i].status){
						htmlStr += "<td>已使用</td>";
						htmlStr += "<td></td>";
					}else{
						htmlStr += "<td>未使用</td>";
						htmlStr += "<td><a class='btn btn-info'  onclick=deleteTime('"+res.errmsg[i].id+"') >删除</a></td>";
					}
					htmlStr += "</tr>";
				}
			}
			$("#dataDiv").html(htmlStr);
		}
	})
}

function addTime(){
	var date = $("#date").val();
	var time = $("#time").val();
	var id = $("#id").val();
	var params = "id="+id+"&date="+date+"&time="+time;
	
	$.ajax({
		url:"/admin/data/addTime.html",
		data:params,
		type:"POST",
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				alert("添加成功!");
				getData();
			}else{
				alert("该时间已存在!");
			}
		}
	})
	
}

function deleteTime(id){
	$.ajax({
		url:"/tutor/data/deleteTime.html",
		data:"id="+id,
		type:"POST",
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				alert("删除成功!");
				getData();
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
	width: 200px;
	height: 200px;
}
</style>
</head>
<body>
<input type="hidden" id="id" value="${id }" >
	<div class="space-div-1" ></div>
	<div class="row index-row" >
		<div class="col-md-2 index-col-md-2" >
			<div class="list-group">
			  <a href="/admin/page/tutor.html" class="list-group-item active">导师管理</a>
			  <a href="/admin/page/customer.html" class="list-group-item">顾客管理</a>
			  <a href="/admin/page/order.html" class="list-group-item">约谈管理</a>
			  <a href="/admin/page/util.html" class="list-group-item">资料管理</a>
			  <a href="/admin/page/pay.html" class="list-group-item">兑付管理</a>
			  <a href="/admin/loginOut.html" class="list-group-item">退出登录</a>
			</div>
		</div>
		<div class="col-md-10 index-col-md-10" >
			<div class="btn-group" role="group" aria-label="...">
			  <button type="button" class="btn btn-default" onclick="openUrl('/admin/page/tutor.html')" >返回首页</button>
			  <button type="button" class="btn btn-info" >导师时间<span id="name" ></span> </button>
	   		</div>
	   		<table class="table width100" >
	   			<thead>
	   				<tr>
	   					<th>列表</th>
	   					<th>新增</th>
	   				</tr>
	   			</thead>
	   			<tbody>
	   				<tr>
	   					<td>
	   						<table class="table width100" >
	   							<thead>
	   								<tr>
	   									<th>编号</th>
	   									<th>日期</th>
	   									<th>时间</th>
	   									<th>使用状态</th>
	   									<th>操作</th>
	   								</tr>
	   							</thead>
	   							<tbody id="dataDiv" ></tbody>
	   						</table>
	   					</td>
	   					<td>
	   						<table class="table width100" >
	   							<tr>
	   								<th>选择日期</th><td><input placeholder="请输入日期" readonly="readonly" id="date" class="form-control" onclick="laydate()"></td>
	   							</tr>
	   							<tr>
	   								<th>选择时间</th>
	   								<td>
	   									<select class="form-control" id="time" >
											<option value="7:00-8:00" selected="selected">7:00-8:00</option>
											<option value="8:00-9:00" >8:00-9:00</option>
											<option value="9:00-10:00" >9:00-10:00</option>
											<option value="10:00-11:00" >10:00-11:00</option>
											<option value="11:00-12:00" >11:00-12:00</option>
											<option value="12:00-13:00" >12:00-13:00</option>
											<option value="13:00-14:00" >13:00-14:00</option>
											<option value="14:00-15:00" >14:00-15:00</option>
											<option value="15:00-16:00" >15:00-16:00</option>
											<option value="16:00-17:00" >16:00-17:00</option>
											<option value="17:00-18:00" >17:00-18:00</option>
											<option value="19:00-20:00" >19:00-20:00</option>
											<option value="20:00-21:00" >20:00-21:00</option>
										</select>
	   								</td>
	   							</tr>
	   							<tr>
	   								<td colspan="2" >
	   									<a class="btn btn-info" onclick="addTime()" >添加</a>
	   								</td>
	   							</tr>
	   						</table>
	   					</td>
	   				</tr>
	   			</tbody>
	   		</table>
	   	</div>
		<div class="col-md-2 index-col-md-2" ></div>
	</div>
</body>
</html>