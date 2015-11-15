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
			$(this).attr("readOnly","readOnly");
		}
	})
})
</script>
</head>
<body class="tutor-bg">
<div class="main">
	<div class="nav-title">首次注册完善个人信息</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">姓名</span>
		<input  value="${tutor.realName}" id="realName" class="register-inp-long"/>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">性别</span>
		<input  value="${tutor.sex }" id="sex" class="register-inp-long"/>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">生日</span>
		<input value="${tutor.birthday }" id="birthday"  onClick="WdatePicker()" class="register-inp-long"/>
	</div>
	<div class="register-inp register-inp-top">
		<input type="hidden" id="tradeValue" value="${tutor.qTrade.tradeName }" >
		<span class="register-inp-text">行业</span>
		<select class="register-select-long" id="trade" >
			<option selected="selected" >请选择行业</option>
		</select>
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
		<span class="register-inp-text">银行账号</span>
		<input  value="${tutor.bankCard }" id="bankCard" class="register-inp-long"/>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">确认账号</span>
		<input  value="${tutor.bankCard }" class="register-inp-long"/>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">银行名称</span>
		<input  value="${tutor.bankName }" id="bankName" class="register-inp-long"/>
	</div>
	<div class="sure-btn">
		<div class="tutor-search-btn btn-orange-bg">下一步</div>
	</div>
</div>
</body>
</html>