<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>基本信息</title>
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript" src="/sp/js/date/WdatePicker.js" ></script>
<script type="text/javascript">
$(function(){
	$("input").each(function(){
		if(""!=$(this).val()&&null!=$(this).val()){
		}
	})
	var sex = $("#sexValue").val();
	if(""!=sex&&null!=sex){
		var element1 = document.getElementById("sex");   
        
        for(i=0;i<element1.length;i++){
          if(sex==element1.options[i].value){  
              element1.options[i].selected=true; 
          }  
        }  
	}
})

/**
 * 保存第一页信息
 */
function saveInfoOne(){
	$("#sex").attr("disabled",false);
	var realName = $("#realName").val();
	var sex = $("#sex").val();
	var birthday = $("#birthday").val();
	var email = $("#email").val();
	var qq = $("#qq").val();
	var wechatName = $("#wechatName").val();
	var idNumber = $("#idNumber").val();
	var bankCard = $("#bankCard").val();
	var reBankCard = $("#reBankCard").val();
	var bankName = $("#bankName").val();
	var classPrice = $("#classPrice").val();
	var bankAccount = $("#bankAccount").val();
	
	var params = "realName="+realName+"&sex="+sex+"&birthday="+birthday+"&bankAccount="+bankAccount+
				 "&email="+email+"&qq="+qq+"&wechatName="+wechatName+"&idNumber="+idNumber+
				 "&bankCard="+bankCard+"&bankName="+bankName+"&classPrice="+classPrice;
	
	if(reBankCard!=bankCard){
		$("#myAlertH").html("银行卡号不一致!");
		showNewAlert();
	/* 	alert("银行卡号不一致!"); */
	}else{
		$.ajax({
			url:"/tutor/data/saveInfoOne.html",
			type:"POST",
			data:params,
			dataType:"json",
			success:function(res){
				if("success"==res.result){
					location.href = "/tutor/page/infoTwo.html";
				}
			}
		})
	}
}
</script>
</head>
<body class="tutor-bg">
<div id="newAlert">
		<h3  id="myAlertH" ></h3>
		<a id="newAlertBtn" onclick="hideNewAlert()" >确定</a>
	</div>
<div class="main">
<div class="nav-title">个人信息（一）<div class="close" onclick="openUrl('/tutor/page/index.html')" >&Chi;</div></div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">姓名</span>
		<input  value="${tutor.realName}" readonly="readonly" id="realName" class="register-inp-long"/>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">性别</span>
		<input  value="${tutor.sex }" id="sexValue" type="hidden" class="register-inp-long"/>
		<select  class="register-select-long" id="sex" disabled="disabled" >
			<option value="女" >女</option>
			<option value="男" >男</option>
		</select>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">生日</span>
		<input value="${birthday }" id="birthday"  onClick="WdatePicker()" class="register-inp-long"/>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">辅导价格</span>
		<input  value="${tutor.classPrice }" readonly="readonly" id="classPrice" class="register-inp-long"/>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">E-mail</span>
		<input  value="${tutor.email }" id="email" class="register-inp-long"/>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">QQ</span>
		<input  value="${tutor.qq }"  id="qq" class="register-inp-long"/>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">微信号</span>
		<input  value="${tutor.wechatName }" id="wechatName" class="register-inp-long"/>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">身份证</span>
		<input  value="${tutor.idNumber }" id="idNumber" class="register-inp-long"/>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">银行名称</span>
		<input  value="${tutor.bankName }" id="bankName" class="register-inp-long"/>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">账户名称</span>
		<input  value="${tutor.bankAccount }" id="bankAccount" class="register-inp-long"/>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">银行账号</span>
		<input  value="${tutor.bankCard }" id="bankCard" class="register-inp-long"/>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">确认账号</span>
		<input  value="${tutor.bankCard }" id="reBankCard" class="register-inp-long"/>
	</div>
	<div class="sure-btn">
		<div class="tutor-search-btn btn-orange-bg" onclick="saveInfoOne()" >下一步</div>
	</div>
</div>
</body>
</html>