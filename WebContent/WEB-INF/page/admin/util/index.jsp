<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台主页</title>
<link rel="stylesheet" href="/sp/dist/css/bootstrap.css" >
<link rel="stylesheet" href="/sp/css/admin/main.css" >

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/dist/js/bootstrap.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript" src="/sp/js/date/WdatePicker.js" ></script>
<script type="text/javascript">
var tradeArray = new Array();

$(function(){
	getData();
})

/**
 *获取列表 
 */
function getData(){
	$.ajax({
		url:"/trade/data/list.html",
		type:"GET",
		dataType:"json",
		ansyc:false,
		success:function(res){
			var htmlStr = "",htmlStr1 = "";
			if("success"==res.result){
				for(var i = 0;i<res.errmsg.length;i++){
					if("0"==res.errmsg[i].parentId){
						tradeArray[parseInt(res.errmsg[i].id)] = res.errmsg[i].tradeName;
						htmlStr += "<tr>";
						htmlStr += "<td>"+res.errmsg[i].id+"</td>";
						htmlStr += "<td>"+res.errmsg[i].tradeName+"</td>";
						htmlStr += "</tr>";
					}else{
						htmlStr1 += "<tr>";
						htmlStr1 += "<td>"+res.errmsg[i].id+"</td>";
						htmlStr1 += "<td>"+tradeArray[parseInt(res.errmsg[i].parentId)]+"</td>";
						htmlStr1 += "<td>"+res.errmsg[i].tradeName+"</td>";
						htmlStr1 += "</tr>";
					}
				}
				
				$("#tradeData").html(htmlStr);
				$("#jobData").html(htmlStr1);
			}
		}
	})
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
			  <a href="/admin/page/order.html" class="list-group-item">约谈管理</a>
			  <a href="/admin/page/util.html" class="list-group-item active">资料管理</a>
			</div>
		</div>
		<div class="col-md-10 index-col-md-10" >
			<div>
				  <ul class="nav nav-tabs" role="tablist">
				    <li role="presentation" class="active"><a href="#trade" aria-controls="home" role="tab" data-toggle="tab">行业</a></li>
				    <li role="presentation"><a href="#job" aria-controls="profile" role="tab" data-toggle="tab">领域</a></li>
				  </ul>
				  <div class="tab-content">
				    <div role="tabpanel" class="tab-pane active" id="trade">
				    	<table class="table" id="tradeTable" >
							<thead>
								<tr>
									<td>编号</td>
									<td>行业名称</td>
								</tr>
							</thead>
							<tbody id="tradeData" ></tbody>
						</table>
				    </div>
				    <div role="tabpanel" class="tab-pane" id="job">
				    	<table class="table" id="jobTable"  >
							<thead>
								<tr>
									<td>编号</td>
									<td>领域名称</td>
								</tr>
							</thead>
							<tbody id="jobData" ></tbody>
						</table>
				    </div>
				  </div>
			</div>
		</div>
	</div>
	
</body>
</html>