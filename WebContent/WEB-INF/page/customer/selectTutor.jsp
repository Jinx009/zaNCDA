<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />
<title>筛选导师</title>
<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
var areaData;
$(function(){
	$.ajax({
		url:'/trade/data/list.html?time='+getRandom(),
		type:'GET',
		dataType:'json',
		success:function(res){
			areaData = res.errmsg;
			var htmlStr = '';
			for(var i = 0;i<res.errmsg.length;i++){
				if('0'==res.errmsg[i].parentId){
					htmlStr += '<option value='+res.errmsg[i].id+' >'+res.errmsg[i].tradeName+'</option>';
				}
			}
			$('#trade').html(htmlStr);
		}
	})
})
/**
 * 下一步
 */
function goNext(){
	var trade = $("#trade").val();
	var area = $("#area").val();
	var workYears = $("#workYears").val();
	var type = $("#type").val();
	var id = $("#id").val();
	
	location.href = "/customer/page/selectResult.html?type="+type+"&trade="+trade+"&area="+area+"&workYears="+workYears+"&id="+id;
}

function changeTrade(){
	var trade = $("#trade").val();
	var htmlStr = "";
	for(var i = 0;i<areaData.length;i++){
		if(areaData[i].parentId==trade){
			htmlStr += "<option value="+areaData[i].id+" >"+areaData[i].tradeName+"</option>";
		}
	}
	$("#area").html(htmlStr);
}
</script>
</head>
<body>
<input type="hidden" value="${id }" id="id" >
<a><div class="nav-title"><span class="nav-back" onclick="openUrl('/customer/page/new.html')" >&lt;</span>新建辅导</div></a>
<div class="choice-title"><h1>请选择您心仪导师的属性</h1></div>
<div class="register-inp register-inp-top">
	<span class="register-inp-text">专注行业<b>*</b></span>
	<select class="register-select-long" id="trade" onchange="changeTrade()" >
		<option value="" selected="selected"></option>
	</select>
</div>
<div class="register-inp register-inp-top">
	<span class="register-inp-text">擅长领域<b>*</b></span>
	<select class="register-select-long" id="area" >
		<option value="" selected="selected"></option>
	</select>
</div>
<div class="register-inp register-inp-top">
	<span class="register-inp-text">从业年限</span>
	<select class="register-select-long" id="workYears" >
		<option value="1" selected="selected">1年以内</option>
		<option value="2" >1-3年</option>
		<option value="3" >3-5年</option>
		<option value="4" >5-7年</option>
		<option value="5" >7年以上</option>
	</select>
</div>

<div class="register-inp register-inp-top">
	<span class="register-inp-text">沟通方式</span>
	<select class="register-select-long" id="type" >
		<option value="1" selected="selected">当面约谈</option>
		<option value="2" >视频约谈</option>
		<option value="3" >电话约谈</option>
	</select>
</div>

<div class="btn-position">
	<div class="two-btn">
		<button onclick="openUrl('/customer/page/new.html')" >上一步</button>
		<button onclick="goNext()" >下一步</button>
	</div>
</div>
</body>
</html>