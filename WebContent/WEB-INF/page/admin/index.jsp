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
<script type="text/javascript">
$(function(){
	 getTeacherList(1);
	 getArea();
	 getJob();
})

/**
 * 擅长领域
 */
function getArea(){
	$.ajax({
		url:"/areaname/list.html",
		type:"POST",
		dataType:"json",
		success:function(res){
			var htmlStr = "";
			
			for(var i = 0;i<res.data.length;i++){
				htmlStr += "<option>";
				htmlStr += res.data[i].areaName;
				htmlStr += "</option>";
			}
			
			$("#area1").html(htmlStr);
			$("#area2").html(htmlStr);
			$("#area3").html(htmlStr);
		}
	})
}

/**
 *获取职位
 */
function getJob(){
	$.ajax({
		url:"/jobname/list.html",
		type:"POST",
		dataType:"json",
		success:function(res){
			var htmlStr = "";
			
			for(var i = 0;i<res.data.length;i++){
				htmlStr += "<option>";
				htmlStr += res.data[i].jobName;
				htmlStr += "</option>";
			}
			
			$("#job1").html(htmlStr);
			$("#job2").html(htmlStr);
			$("#job3").html(htmlStr);
		}
	})
}

/**
 *获取列表 
 */
function getTeacherList(pageNum){
	var params = "pageNum="+pageNum+"&userName="+$("#searchUserName").val()+"&mobilePhone="+$("#searchMobile").val();
	$.ajax({
		url:"/teacher/list.html",
		data:params,
		type:"POST",
		dataType:"json",
		success:function(res){
			var menuHtml = "",htmlStr = "";
			if(null!=res.list&&res.list.length>0){
				menuHtml += "<li><a href=javascript:getTeacherList('"+res.page.pages+"')>"+res.page.pages+"</a></li>";
				for(var i = 0;i<res.list.length;i++){
					htmlStr += "<tr>";
					htmlStr += "<td>"+res.list[i].id+"</td>";
					htmlStr += "<td>"+res.list[i].username+"</td>";
					htmlStr += "<td>"+res.list[i].mobilePhone+"</td>";
					htmlStr += "<td>"+res.list[i].qq+"</td>";
					htmlStr += "<td>"+res.list[i].qq+"</td>";
					htmlStr += "<td>"+res.list[i].email+"</td>";
					htmlStr += "<td>";
					htmlStr += "<div class='btn-toolbar' role='toolbar' aria-label='...'>";
					htmlStr += "<button type='button' class='btn btn-defaul' onclick=editDiv('"+i+"') >编辑</button>";
					htmlStr += "<button type='button' class='btn btn-defaul' onclick=deleteDiv('"+i+"')  >删除</button>";
					htmlStr += "</div>";
					htmlStr += "</td>";
				}
			}
			$("#pageMenu").html(menuHtml);
			$("#dataList").html(htmlStr);
		}
	})
}

/**
 * 新增
 */
function saveDiv(){
	
	$("#saveDiv input").each(function(){
		$(this).val("");
	})
	
	showAlert("saveDiv");
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
			  <a href="/admin/index.html" class="list-group-item active">教师管理</a>
			  <a href="#" class="list-group-item">顾客管理</a>
			  <a href="#" class="list-group-item">兑付管理</a>
			  <a href="/admin/util.html" class="list-group-item">辅助管理</a>
			</div>
		</div>
		<div class="col-md-10 index-col-md-10" >
			<div class="row" >
				<div class="col-md-6" >
				<form class="form-horizontal">
					  <div class="form-group">
					    <label class="col-sm-3 control-label">导师姓名:</label>
					    <div class="col-sm-5">
					      <input type="text" class="form-control" id="searchUserName" value="" >
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
					  <button type="button" class="btn btn-default" onclick="saveDiv()" >新增</button>
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
						<td>微信</td>
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
				  	<div class="row" >
				  		<div class="col-sm-6" >
						  	<div class="form-group">
							    <label class="col-sm-4 control-label">姓名：</label>
							    <div class="col-sm-8">
							     	 <input type="text" class="form-control" id="username" value="" placeholder="职位" >
							    </div>
						  	</div>
						  	<div class="space-div-4" ></div>
						  	<div class="form-group">
							    <label class="col-sm-4 control-label">性别：</label>
							    <div class="col-sm-8">
							     	 <select class="form-control" id="sex" >
							     	 	<option value="1" >男</option>
							     	 	<option value="2" >女</option>
							     	 </select>
							    </div>
						  	</div>
						  	<div class="space-div-4" ></div>
						  	<div class="form-group">
							    <label class="col-sm-4 control-label">电话：</label>
							    <div class="col-sm-8">
							     	 <input class="form-control" value="" id="mobilePhone" placeholder="电话" >
							    </div>
						  	</div>
						  	<div class="space-div-4" ></div>
						  	<div class="form-group">
							    <label class="col-sm-4 control-label">生日：</label>
							    <div class="col-sm-8">
							     	 <input class="form-control" value="" id="birthday" placeholder="生日" onClick="WdatePicker()" readonly="readonly" >
							    </div>
						  	</div>
						  	<div class="space-div-4" ></div>
						  	<div class="form-group">
							    <label class="col-sm-4 control-label">行业：</label>
							    <div class="col-sm-8">
							     	 <select class="form-control" id="job1" ></select>
							    </div>
						  	</div>
						  	<div class="space-div-2" ></div>
						  	<div class="form-group">
							    <label class="col-sm-4 control-label"></label>
							    <div class="col-sm-8">
							     	 <select class="form-control" id="job2" ></select>
							    </div>
						  	</div>
						  	<div class="space-div-2" ></div>
						  	<div class="form-group">
							    <label class="col-sm-4 control-label"></label>
							    <div class="col-sm-8">
							     	 <select class="form-control" id="job3" ></select>
							    </div>
						  	</div>
						  		<div class="space-div-4" ></div>
						  	<div class="form-group">
							    <label class="col-sm-4 control-label">领域：</label>
							    <div class="col-sm-8">
							     	 <select class="form-control" id="area1" ></select>
							    </div>
						  	</div>
						  	<div class="space-div-2" ></div>
						  	<div class="form-group">
							    <label class="col-sm-4 control-label"></label>
							    <div class="col-sm-8">
							     	 <select class="form-control" id="area2" ></select>
							    </div>
						  	</div>
						  	<div class="space-div-2" ></div>
						  	<div class="form-group">
							    <label class="col-sm-4 control-label"></label>
							    <div class="col-sm-8">
							     	 <select class="form-control" id="area3" ></select>
							    </div>
						  	</div>
						  	<div class="space-div-4" ></div>
						  	<div class="form-group">
							    <label class="col-sm-4 control-label">工作时间</label>
							    <div class="col-sm-8">
							     	 <input class="form-control" value="" id="workdate" placeholder="工作开始时间" onClick="WdatePicker()" readonly="readonly" >
							    </div>
						  	</div>
						  	<div class="space-div-4" ></div>
						 </div>
						 
						 <div class="col-sm-6" >
						 	<div class="form-group">
							    <label class="col-sm-4 control-label">头像</label>
							    <div class="col-sm-8">
							      	<img alt="" src="http://www.800bank.com.cn:8089/data/upload/20151019/1445222980641.png" class="img-width" >
							    </div>
						  	</div>
						  	<div class="space-div-4" ></div>
						  	<div class="space-div-4" ></div>
						  	<div class="form-group">
							    <label class="col-sm-4 control-label">光辉：</label>
							    <div class="col-sm-8">
							     	 <textarea rows="" cols="" style="height: 80px;" class="form-control" ></textarea>
							    </div>
						  	</div>
						  	<div class="space-div-4" ></div>
						  	<div class="form-group">
							    <label class="col-sm-4 control-label">单价：</label>
							    <div class="col-sm-8">
							     	 <input type="text" class="form-control" value="" placeholder="元/次"  >
							    </div>
						  	</div>
						  	<div class="space-div-4" ></div>
						  	<div class="form-group">
							    <label class="col-sm-4 control-label">有效</label>
							    <div class="col-sm-8">
							     	 <select class="form-control" >
							     	 	<option value="1" >是</option>
							     	 	<option value="2" >否</option>
							     	 </select>
							    </div>
						  	</div>
						  	<div class="space-div-4" ></div>
						  	<div class="form-group">
							    <label class="col-sm-4 control-label">银行账号</label>
							    <div class="col-sm-8">
							     	 <input type="text" class="form-control"  >
							    </div>
						  	</div>
						  	<div class="space-div-4" ></div>
						  	<div class="form-group">
							    <label class="col-sm-4 control-label">银行名称</label>
							    <div class="col-sm-8">
							     	 <input type="text" class="form-control"  >
							    </div>
						  	</div>
						  	<div class="space-div-4" ></div>
						  	<div class="form-group">
							    <label class="col-sm-4 control-label">身份证号</label>
							    <div class="col-sm-8">
							     	 <input type="text" class="form-control"  >
							    </div>
						  	</div>
						  		<div class="space-div-4" ></div>
						  	<div class="form-group">
							    <label class="col-sm-4 control-label">email</label>
							    <div class="col-sm-8">
							     	 <input type="text" class="form-control"  >
							    </div>
						  	</div>
						  	<div class="form-group">
							    <label class="col-sm-4 control-label">QQ</label>
							    <div class="col-sm-8">
							     	 <input type="text" class="form-control"  >
							    </div>
						  	</div>
						  	<div class="form-group">
							    <label class="col-sm-4 control-label">微信</label>
							    <div class="col-sm-8">
							     	 <input type="text" class="form-control"  >
							    </div>
						  	</div>
						 </div>
			  		</div>
			</div>
			<div class="space-div-2" ></div>
			<div class="row" >
				<div class="col-md-1" ></div>
				<div class="col-md-10" >
					<div class="form-group">
						<label class="col-sm-4 control-label">微信</label>
						<div class="col-sm-8">
							  <input type="text" class="form-control"  >
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">视频约谈</label>
						<div class="col-sm-6">
							 <input type="checkbox" class="checkbox"  >
						</div>
					</div>
				</div>
				<div class="col-md-1" ></div>
			</div>
			<div class="space-div-2" ></div>
		</div>
	</div>
	</div>
</body>
</html>