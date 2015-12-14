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
	var tutorName=$("#tutorName").val(),tutorPhone = $("#tutorPhone").val(),customerName = $("#customerName").val(),customerPhone = $("#customerPhone").val(),payStatus=$("#payStatus").val();
	var params = "pageNum="+pageNum+"&tutorName="+tutorName+"&tutorPhone="+tutorPhone+"&customerName="+customerName+"&customerPhone="+customerPhone+
	"&payStatus="+payStatus;
	
	$.ajax({
		url:"/admin/data/orderPay.html?time="+getRandom(),
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			data = res.data.list;
			var menuHtml = "",htmlStr = "";
			if(null!=res.data){
				for(var i = 1;i<=parseInt(res.data.page.pages);i++){
					menuHtml += "<li  id=page"+i+" ><a href=javascript:getData('"+i+"')>"+i+"</a></li>";
				}
		
				for(var i = 0;i<res.data.list.length;i++){
					htmlStr += "<tr>";
					htmlStr += "<td>"+res.data.list[i].id+"</td>";
					htmlStr += "<td>"+isNull(res.data.list[i].qName)+"</td>";
					htmlStr += "<td>"+isNull(res.data.list[i].cName)+"</td>";
					htmlStr += "<td>"+getOrderStatus(res.data.list[i].status)+"</td>";
					htmlStr += "<td>"+res.data.list[i].price+"</td>";
					htmlStr += "<td>"+isNull(res.data.list[i].procedures)+"</td>";
					htmlStr += "<td>"+isNull(res.data.list[i].payMoney)+"</td>";
					htmlStr += "<td>"+isNull(res.data.list[i].bank)+"</td>";
					htmlStr += "<td>"+isNull(res.data.list[i].bankName)+"</td>";
					htmlStr += "<td>"+isNull(res.data.list[i].reason)+"</td>";
					htmlStr += "<td>";
					if(1==res.data.list[i].payStatus){
						htmlStr += "已兑付";
					}else{
						htmlStr += "<a class='btn btn-default' onclick=openLook('"+res.data.list[i].id+"') >确认兑付</a>";
					}
					
					htmlStr += "</td></tr>";
				}
			}else{
				htmlStr += "<tr><td colspan='10' >查询条件暂无数据，请尝试更换条件查询</td></tr>";
			}
			$("#pageMenu").html(menuHtml);
			$("#page"+pageNum).addClass("active");
			$("#dataList").html(htmlStr);
		}
	})
}
var orderId;
function openLook(id){
	orderId = id;
	showAlert('lookDiv');
}


function savePay(){
	var procedures = $("#procedures").val();
	var reason = $("#reason").val();
	var payMoney = $("#payMoney").val();
	var bankName = $("#bankName").val();
	var bank = $("#bank").val();
	var id = orderId;
	
	var params = "procedures="+procedures+"&reason="+reason+"&payMoney="+payMoney+"&bank="+bank+"&bankName="+bankName+"&id="+id;
	
	$.ajax({
		url:"/order/data/update.html",
		data:params,
		dataType:"json",
		type:"POST",
		success:function(res){
			if("success"==res.result){
				alert("保存成功!");
				location.reload();
			}
		}
	})
}
/**
 * 下载Excel
 */
function getExcel(){
	$.ajax({
		url:"/excel/pay.html?time="+getRandom(),
		type:"GET",
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				window.open("/sp/excel/pay/"+res.errmsg);
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
			  <a href="/admin/page/util.html" class="list-group-item">资料管理</a>
			  <a href="/admin/page/pay.html" class="list-group-item  active">兑付管理</a>
			  <a href="/admin/loginOut.html" class="list-group-item">登出</a>
			</div>
		</div>
		<div class="col-md-10 index-col-md-10" >
			<div class="row" >
				<div class="col-md-4" >
				<form class="form-horizontal">
					  <div class="form-group">
					    <label class="col-sm-4 control-label">导师姓名:</label>
					    <div class="col-sm-4">
					      <input type="text" class="form-control" id="tutorName" value="" >
					      <label class="col-sm-4 control-label"></label>
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-sm-4 control-label">导师手机:</label>
					    <div class="col-sm-4">
					      <input type="text" class="form-control" id="tutorPhone"  value="" >
					    </div>
					     <label class="col-sm-4 control-label"></label>
					  </div>
				  </form>
				</div>
				<div class="col-md-4" >
				<form class="form-horizontal">
					  <div class="form-group">
					    <label class="col-sm-4 control-label">顾客姓名:</label>
					    <div class="col-sm-4">
					      <input type="text" class="form-control" id="customerName" value="" >
					      <label class="col-sm-4 control-label"></label>
					    </div>
					  </div>
					  <div class="form-group">
					    <label class="col-sm-4 control-label">顾客手机:</label>
					    <div class="col-sm-4">
					      <input type="text" class="form-control" id="customerPhone"  value="" >
					    </div>
					     <label class="col-sm-4 control-label"></label>
					  </div>
				  </form>
				  <form class="form-horizontal">
					  <div class="form-group">
					    <label class="col-sm-4 control-label">兑付状态:</label>
					    <div class="col-sm-4">
					      <select id="payStatus" class="form-control" >
								<option value="-1" >全部</option>
								<option value="0" >未兑付</option>
								<option value="1" >已兑付</option>
					      </select>
					      <label class="col-sm-4 control-label"></label>
					    </div>
					  </div>
					  <div class="form-group"></div>
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
			<a class="btn btn-default" onclick="getExcel()" >导出</a>
			<table class="table" >
				<thead>
					<tr>
						<td>订单编号</td>
						<td>导师姓名</td>
						<td>顾客姓名</td>
						<td>订单状态</td>
						<td>约谈金额</td>
						<td>手续比</td>
						<td>实际兑付金额</td>
						<td>银行卡号</td>
						<td>银行卡号</td>
						<td>备注/原因</td>
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
				<h4 class="modal-title" id="myriskLabel">确认兑付</h4>
			</div>
			<div class="modal-body height400">
				    <div class="col-sm-12">
				    	<table class="table table-bordered" id="scoreDiv" >
				    		<thead>
				    			<tr>
				    				<th>实际兑付金额</th>
				    				<th><input type="text" class="form-control" id="payMoney" ></th>
				    			</tr>
				    			<tr>
				    				<th>手续费</th>
				    				<th><input type="text" class="form-control" id="procedures" value="0.01" ></th>
				    			</tr>
				    			<tr>
				    				<th>理由/备注</th>
				    				<th><input type="text" class="form-control" id="reason"  ></th>
				    			</tr>
				    			<tr>
				    				<th>银行名称</th>
				    				<th><input type="text" class="form-control" id="bankName"  ></th>
				    			</tr>
				    			<tr>
				    				<th>银行卡号</th>
				    				<th><input type="text" class="form-control" id="bank"  ></th>
				    			</tr>
				    			<tr>
				    				<td colspan="2" >
				    					<a class="btn btn-info" onclick="savePay()"  >保存</a>
				    				</td>
				    			</tr>
				    		</thead>
				    	</table>
				    </div>
				      <div class="col-sm-12"></div>
			  	</div>
			</div>
			<div class="space-div-2" ></div>
		</div>
	</div>
</body>
</html>