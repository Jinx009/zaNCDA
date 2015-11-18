<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>导师时间</title>
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript" src="/sp/js/laydate/laydate.js" ></script>
<script type="text/javascript">
$(function(){
	laydate.skin('molv');
	 var width = $(window).width();
	 var height = $(document).height();
	 $("#add").bind("click",function(){
		$(".mask").show();
		$(".boxWrap").show();
		$(".mask").css("width",width+"px");
		$(".mask").css("height",height+"px");
		$(".boxWrap").css("height",height*0.68+"px");
		$(".boxWrap").css("top",height*0.10+"px");
		$("#calendar").css("height",height*0.35+"px");
	 })
	 
	 
	 $("#date").val(getNowDate());
	 
	 getDate();
})

/**
 * 选择日期
 */
function getDate(){
	$.ajax({
		url:"/tutor/data/time.html?time="+getRandom(),
		type:"GET",
		dataType:"json",
		success:function(res){
			var htmlStr = "";
			for(var i = 0;i<res.errmsg.length;i++){
				htmlStr += "<div class='appoint-inp'>";
				htmlStr += "<input readOnly='readOnly'  value="+jsDateTimeOnly(res.errmsg[i].realDate)+"&nbsp;&nbsp;"+res.errmsg[i].realTime+" class='appoint-inp-short'/>";
				htmlStr += "<div class='appoint-inp-btn' onclick=deleteTime('"+res.errmsg[i].id+"') >删除</div>";
				htmlStr += "</div>";
			}
			$("#dataTime").html(htmlStr);
		}
	})
}

/**
 * 删除时间
 */
function deleteTime(id){
	var params = "id="+id;
	
	$.ajax({
		url:"/tutor/data/deleteTime.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				alert("删除成功!");
				getDate();
			}
		}
	})
}

/**
 * 添加时间
 */
function addTime(){
	var date = $("#date").val();
	var time = $("#time").val();
	
	var params = "date="+date+"&time="+time;
	
	$.ajax({
		url:"/tutor/data/addTime.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			hideDiv();
			getDate();
		}
	})
}

/**
 * 隐藏
 */
function hideDiv(){
	$(".mask").hide();
	$(".boxWrap").hide();
}
</script>
</head>
<body class="tutor-bg">
<div class="main">
	<div class="nav-title">提交可约的时间<div class="close" onclick="openUrl('/tutor/page/index.html')" >&Chi;</div></div>
	<div class="appoint-list">
		<h3>我的时间</h3>
		<div id="dataTime" ></div>
	</div>
	<div class="teacher-two-btn">
		<button class="teacher-normal-btn" id="add">添加</button>
	</div>
	<!--蒙版-->
	<div class="mask"></div>
	<!--悬浮框	start-->
	<div class="boxWrap">
		<div class="interview-box">
			<input placeholder="请输入日期" readonly="readonly" id="date" class="interview-inp left" onclick="laydate()">
			<select class="interview-sel center" id="time" >
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
			<div class="teacher-two-btn">
				<button class="teacher-normal-btn" onclick="addTime()">添加</button>
				<button class="teacher-normal-btn" onclick="hideDiv()">取消</button>
			</div>
		</div>
	</div>
	<!--悬浮框	end-->
</div>
</body>
</html>