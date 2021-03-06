﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>个人信息</title>
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript" src="/sp/js/date/WdatePicker.js" ></script>
<script type="text/javascript">
var tradeData = new Array(),areaData = new Array();
$(function(){

	
	$.ajax({
		url:"/trade/data/list.html?time="+getRandom(),
		type:"GET",
		dataType:"json",
		success:function(res){
			var htmlStr = "",htmlStr1 = "";
			for(var i = 0;i<res.errmsg.length;i++){
				if("2"==res.errmsg[i].parentId){
					htmlStr += "<option value="+res.errmsg[i].id+" >"+res.errmsg[i].tradeName+"</option>";
					tradeData.push(res.errmsg[i]);
				}
				if("3"==res.errmsg[i].parentId){
					htmlStr1 += "<option value="+res.errmsg[i].id+" >"+res.errmsg[i].tradeName+"</option>";
					areaData.push(res.errmsg[i]);
				}
			}
			$("#trade").html(htmlStr);
			$("#job").html(htmlStr1);
			var trade = $("#tradeValue").val();
			var area = $("#jobValue").val();
			var sex = $("#sexValue").val();
			if(""!=sex&&null!=sex){
				var element1 = document.getElementById("sex");   
		        
		        for(i=0;i<element1.length;i++){
		          if(sex==element1.options[i].value){  
		              element1.options[i].selected=true; 
		          }  
		        }  
			}
			

		    if(""!=trade){
		    	var element3 = document.getElementById("trade");   
		   	 	for(i=0;i<element3.length;i++){
		   	      if(trade==element3.options[i].value){  
		   	          element3.options[i].selected=true; 
		   	      }  
		   	    }  
		    }
		    
			 
		    if(""!=area){
		    	var element2 = document.getElementById("job");  
		   	 	for(i=0;i<element2.length;i++){
		   	      if(area==element2.options[i].value){  
		   	          element2.options[i].selected=true; 
		   	      }  
		   	    } 
		    }
		    
		    var familly = $("#familly").val();
		    if(null!=familly&&""!=familly){
		    	var element3 = document.getElementById("famillyNumber");  
		   	 	for(i=0;i<element3.length;i++){
		   	      if(familly==element3.options[i].value){  
		   	          element3.options[i].selected=true; 
		   	      }  
		   	    } 
		    }
		}
	})
	
})

/* 
function changeTrade(){
	var trade = $("#trade").val();
	var htmlStr = "";
	for(var i = 0;i<areaData.length;i++){
		if(trade == areaData[i].parentId){
			htmlStr += "<option value="+areaData[i].id+" >"+areaData[i].tradeName+"</option>";
		}
	}
	$("#job").html(htmlStr);
} */
/**
 * 保存个人信息
 */
function saveInf(){
	var realName = $("#realName").val();
	var qq = $("#qq").val();
	var email =  $("#email").val();
	var sex = $("#sex").val();
	var birthday = $("#birthday").val();
	var famillyNumber = $("#famillyNumber").val();
	var wechatName = $("#wechatName").val();
	var trade = $("#trade").val();
	var job = $("#job").val();

	var params = "realName="+realName+"&qq="+qq+"&email="+email+"&sex="+sex+"&birthday="+birthday+
				"&famillyNumber="+famillyNumber+"&wechatName="+wechatName+"&trade="+trade+"&job="+job;
	if(!checkEmail(email)){
		alert("邮箱格式不正确");
	}else if(""==qq){
		alert("qq未填写!");
	}else if(""==wechatName){
		alert("微信号未填写！");
	}else if(""==realName){
		alert("用户姓名未填写!");
	}
	else{
		$.ajax({
			url:"/customer/data/saveInfo.html",
			type:"POST",
			data:params,
			dataType:"json",
			success:function(res){
				if("success"==res.result){
					$("#myAlertH").html("保存成功!");
					$("#newAlertBtn").attr("onclick","openMyUrl('/customer/page/growpInfo.html')");
					showNewAlert();
					/* alert("保存成功!");
					location.href = "/customer/page/growpInfo.html"; */
				}
			}
		})
	}
}

function loginOut(){
	$.ajax({
		url:"/customer/loginOut.html",
		type:"POST",
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				location.href = "/customer/login.html";
			}
		}
	})
}
var errorMsg = "";
var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
function checkTel(phone){
	if(!myreg.test(phone)) 
	{ 
		errorMsg = '请输入有效的手机号码！';
	    return false; 
	} 
	if(phone.length!=11) 
    { 
		errorMsg = '请输入有效的手机号码！';
        return false; 
    } 
}
function checkEmail(email){
   var myreg = /^([a-zA-Z0-9]+[_|\_|\._|\-]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
   if(!myreg.test(email))
   {
	   errorMsg = '请输入有效的邮箱地址！';
       return false;
   }
   return true;
}
</script>
</head>
<body>
<div id="newAlert">
		<h3  id="myAlertH" ></h3>
		<a id="newAlertBtn" onclick="hideNewAlert()" >确定</a>
	</div>
<a href="#"><div class="nav-title">个人信息<span class="close" onclick="openUrl('/customer/page/index.html')" >&Chi;</span></div></a>
<div class="register-inp register-inp-top">
	<span class="register-inp-text">姓名</span>
	<input  value="${customer.realName }" id="realName" class="register-inp-long"/>
</div>
<div class="register-inp register-inp-top">
	<span class="register-inp-text">性别</span>
	<input  value="${customer.sex }" id="sexValue" type="hidden" class="register-inp-long"/>
	<select  class="register-select-long" id="sex" >
			<option value="女" >女</option>
			<option value="男" >男</option>
		</select>
</div>
<div class="register-inp register-inp-top">
	<span class="register-inp-text">生日</span>
	<input type="hidden" id="birth" value="${customer.birthday }" >
	<input   value="${birthday }" id="birthday" onClick="WdatePicker()"  class="register-inp-long"/>
</div>
<div class="register-inp register-inp-top">
	<input type="hidden" value="${tradeId }" id="tradeValue" >
	<span class="register-inp-text"  >行业</span>
	<select class="register-select-long" id="trade" ></select>
</div>
<div class="register-inp register-inp-top">
	<span class="register-inp-text">职能</span>
	<input type="hidden" value="${jobId }" id="jobValue" >
	<select class="register-select-long" id="job" ></select>
</div>
<div class="register-inp register-inp-top">
	<span class="register-inp-text">工作年限</span>
	<select class="register-select-long" id="famillyNumber" >
		<option value="1" >在读/兼职</option>
		<option value="2" >1-3年</option>
		<option value="3" >3-5年</option>
		<option value="4" >5-10年</option>
		<option value="5" >10年以上</option>
	</select>
	<input  value="${customer.famillyNumber }" type="hidden" id="familly" class="register-inp-long"/>
</div>
<div class="register-inp register-inp-top">
	<span class="register-inp-text">E-mail</span>
	<input  value="${customer.email }" id="email" class="register-inp-long"/>
</div>
<div class="register-inp register-inp-top">
	<span class="register-inp-text">QQ</span>
	<input  value="${customer.qq }" id="qq" class="register-inp-long"/>
</div>
<div class="register-inp register-inp-top">
	<span class="register-inp-text">微信</span>
	<input  value="${customer.wechatName }" id="wechatName" class="register-inp-long"/>
</div>
<div class="sure-btn">
	<div class="tutor-search-btn" id="sure" onclick="saveInf()" >提交</div>
	<div class="space-15" ></div>
</div>
<!--蒙版-->
<div class="mask"></div>
<!--悬浮框	start-->
<div class="boxWrap">
	<!--注册失败	start-->
	<div class="failure-box">
		<div class="success-img">
			<img src="/sp/images/failure.png"/>
		</div>
		<p class="success-p">注册失败，请重新注册！</p>
		<div class="success-btn failure-btn-bgColor">再试一次</div>
	</div>
	<!--注册失败	start-->
	
</div>
		<!--悬浮框	end-->
	
</body>
</html>