<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>取消订单</title>
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />
<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
function saveCacle(){
	var orderId = $("#orderId").val();
	var bank = $("#bank").val();
	var reason = $("#reason").val();
	var bankAccount = $("#bankAccount").val();
	
	var params = "orderId="+orderId+"&bank="+bank+"&reason="+reason+"&bankAccount="+bankAccount;
	
	$.ajax({
		url:"/customer/data/cacleOrder.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				$("#myAlertH").html("取消已提交，等待审核!");
				$("#newAlertBtn").attr("onclick","openMyUrl('/customer/page/manage.html')");
				showNewAlert();
				/* alert("取消已提交，等待审核!");
				location.href = "/customer/page/manage.html"; */
			}
		}
	})
	
}
</script>
</head>
<body>
	<div id="newAlert">
		<h3  id="myAlertH" ></h3>
		<a id="newAlertBtn" onclick="hideNewAlert()" >确定</a>
	</div>
<input type="hidden" value="${orderId}" id="orderId" >
<div class="nav-title">取消订单<span class="close" onclick="openUrl('/customer/page/index.html')" >&Chi;</span></div>
  <div class="mask"></div>
	<div class="big-boxWrap">
		<div class="cancel-box">
			<dl>
				<dt><img src="/sp/images/cancel.png"/></dt>
				<dd>正在取消</dd>
			</dl>
			<h3>请填写您的收款信息</h3>
			<input placeholder="请填写您的银行名称" id="bank"  />
			<input placeholder="请填写您的账户名称" id="username" />
			<input placeholder="请填写您的银行帐号" id="bankAccount" />
			<div class="cancel-tip">
				<img src="/sp/images/tip.png"/>
				<p>手续费：10%&nbsp;&nbsp;&nbsp;1-5个工作日内到账</p>
			</div>
			<div class="clear"></div>
			<textarea placeholder="请填写您取消的原因	" id="reason" ></textarea>
			<div class="cancel-box-btn-position">
				<div class="cancel-box-btn">
					<button onclick="saveCacle()" >确定</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>