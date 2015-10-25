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
var jsonData;
$(function(){
	getData(1);
})

/**
 * 获取数据
 */
function getData(pageNum){
	var params = "pageNum="+pageNum+"&startDate="+$("#startDate").val()+"&endDate="+$("#endDate").val()+"&teacherName="+
				 $("#teacherName").val()+"&teacherPhone="+$("#teacherPhone").val()+"&studentName="+
				 $("#studentName").val()+"&studentPhone="+$("#studentPhone").val();
	
	$.ajax({
		url:"/order/list.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			jsonData = res.list;
			
			var menuHtml = "",htmlStr = "";
			
			for(var i = 1;i<=parseInt(res.data.page.total);i++){
				menuHtml += "<li id=page"+i+" ><a href=javascript:getData('"+i+"')>"+i+"</a></li>";
			}
			
			for(var i = 0;i<res.data.list.length;i++){
				htmlStr += "<tr>";
				htmlStr += "<td>"+res.data.list[i].id+"</td>";
				htmlStr += "<td>"+res.data.list[i].nbTeachersUser.realName+"</td>";
				htmlStr += "<td>"+res.data.list[i].nbTeachersUser.mobilePhone+"</td>";
				htmlStr += "<td>"+res.data.list[i].nbStudentsUser.realName+"</td>";
				htmlStr += "<td>"+res.data.list[i].nbStudentsUser.mobilePhone+"</td>";
				htmlStr += "<td>"+res.data.list[i].firstPayPrice+"元</td>";
				htmlStr += "<td>"+res.data.list[i].secondPayPrice+"元</td>";
				htmlStr += "<td>";
				htmlStr += "<div class='btn-toolbar' role='toolbar' aria-label='...'>";
				htmlStr += "<button type='button' class='btn btn-defaul' onclick=openDiv('"+res.data.list[i].id+"') >查看评价</button>";
				htmlStr += "</div>";
				htmlStr += "</td>";
			}
			
			$("#pageMenu").html(menuHtml);
			$("#page"+pageNum).addClass("active");
			$("#dataList").html(htmlStr);
		}
	})
}

function openDiv(id){
	$.ajax({
		url:"/comments/list.html",
		type:"POST",
		data:"orderId="+id,
		dataType:"json",
		success:function(res){
			var htmlStr = "",htmlStr2 = "";
			for(var i = 0;i<res.student.length;i++){
				htmlStr += "<tr><td>"+res.student[i].id+"</td>";
				htmlStr += "<td>"+res.student[i].comments+"</td>";
				htmlStr += "<<td>"+jsDateTimeOnly(res.student[i].submitTime)+"</td></tr>";
			}
			for(var i = 0;i<res.teacher.length;i++){
				htmlStr2 += "<tr><td>"+res.teacher[i].id+"</td>";
				htmlStr2 += "<td>"+res.teacher[i].score+"</td>";
				htmlStr2 += "<td>"+res.teacher[i].comments+"</td>";
				htmlStr2 += "<<td>"+jsDateTimeOnly(res.teacher[i].submitTime)+"</td></tr>";
			}
			
			$("#studentList").html(htmlStr);
			$("#teacherList").html(htmlStr2);
		}
	})
	
	$("#scoreDiv").show();
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
			  <a href="/admin/index.html" class="list-group-item">教师管理</a>
			  <a href="/admin/student.html" class="list-group-item">顾客管理</a>
			  <a href="/admin/order.html" class="list-group-item  active">兑付管理</a>
			  <a href="/admin/util.html" class="list-group-item">辅助管理</a>
			</div>
		</div>
		<div class="col-md-10 index-col-md-10" >
			<div class="row" >
				<div class="col-md-4" >
				<form class="form-horizontal">
					  <div class="form-group">
					    <label class="col-sm-3 control-label">导师姓名:</label>
					    <div class="col-sm-5">
					      <input type="text" class="form-control" id="teacherName" value="" >
					      <label class="col-sm-4 control-label"></label>
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-sm-3 control-label">导师手机:</label>
					    <div class="col-sm-5">
					      <input type="text" class="form-control" id="teacherPhone"  value="" >
					    </div>
					     <label class="col-sm-4 control-label"></label>
					  </div>
				  </form>
				</div>
				<div class="col-md-4" >
				<form class="form-horizontal">
					  <div class="form-group">
					    <label class="col-sm-3 control-label">顾客姓名:</label>
					    <div class="col-sm-5">
					      <input type="text" class="form-control" id="studentName" value="" >
					      <label class="col-sm-4 control-label"></label>
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-sm-3 control-label">顾客手机:</label>
					    <div class="col-sm-5">
					      <input type="text" class="form-control" id="studentPhone"  value="" >
					    </div>
					     <label class="col-sm-4 control-label"></label>
					  </div>
				  </form>
				</div>
				<div class="col-md-4" >
				<form class="form-horizontal">
					  <div class="form-group">
					    <label class="col-sm-3 control-label">开始时间:</label>
					    <div class="col-sm-5">
					      <input type="text" class="form-control" id="startDate" onClick="WdatePicker()" readonly="readonly"  >
					      <label class="col-sm-4 control-label"></label>
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-sm-3 control-label">结束时间:</label>
					    <div class="col-sm-5">
					      <input type="text" class="form-control" id="endDate" onClick="WdatePicker()" readonly="readonly" >
					    </div>
					     <label class="col-sm-4 control-label"></label>
					  </div>
				  </form>
				</div>
			</div> 
			<hr class="width100" >
			<a class="btn btn-default" >导出</a>
			<a class="btn btn-default" onclick="getData('1')" >查询</a>
			<table class="table" >
				<thead>
					<tr>
						<td>编号</td>
						<td>导师名称</td>
						<td>导师电话</td>
						<td>辅导对象</td>
						<td>辅导对象电话</td>
						<td>第一次支付金额</td>
						<td>第二次支付金额</td>
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
	
		<!--评价信息-->
	<div class="modal face height"  id="scoreDiv" tabindex="-1" role="dialog" aria-labelledby="myDeleteLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="hideAlert('scoreDiv')" >&times;</button>
				<h4 class="modal-title" id="myriskLabel">评价预览</h4>
			</div>
			<div class="modal-body">
			  	<div class="space-div-4" >给教师的评价</div>
			  	<table  class="table table-bordered" >
			  		<thead>
			  			<tr>
			  				<th>编号</th>
			  				<th>分数</th>
			  				<th>内容</th>
			  				<th>提交时间</th>
			  			</tr>
			  		</thead>
			  		<tbody id="teacherList" ></tbody>
			  	</table>
			  	<div class="space-div-4" >给顾客的评价</div>
			  	<table class="table table-bordered" >
			  		<thead>
			  			<tr>
			  				<th>编号</th>
			  				<th>内容</th>
			  				<th>提交时间</th>
			  			</tr>
			  		</thead>
			  		<tbody id="studentList" ></tbody>
			  	</table>
			  	<div class="space-div-4" ></div>
			</div>
			<div class="space-div-2" ></div>
		</div>
	</div>
	</div>
</body>
</html>