<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />
<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<title>约谈详情</title>
<script type="text/javascript">
var scoreOne = 1,scoreTwo = 1,scoreThree = 1,scoreFour = 1;
var score = new Array();
$(function(){
	var orderId = $("#orderId").val();
	var time = $("#time").val();
	score[1] = 1;
	score[2] = 1;
	score[3] = 1;
	score[4] = 1;
	$.ajax({
		url:"/score/data/score.html",
		data:"id="+orderId,
		type:"POST",
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				if(null!=res.errmsg){
					$("#content").html(res.errmsg[0].content);
					scoreOne = res.errmsg[0].oneScore;
					scoreTwo = res.errmsg[0].twoScore;
					scoreThree = res.errmsg[0].threeScore;
					scoreFour = res.errmsg[0].fourScore;
					score[1] = res.errmsg[0].oneScore;
					score[2] = res.errmsg[0].twoScore;
					score[3] = res.errmsg[0].threeScore;
					score[4] = res.errmsg[0].fourScore;
					
					changeStar('1',scoreOne);
					changeStar('2',scoreTwo);
					changeStar('3',scoreThree);
					changeStar('4',scoreFour);
				}
			}
		}
	})
})

/**
 * 分配分数
 */
function changeStar(index,scoreValue){
	score[parseInt(index)] = scoreValue;
	
	for(var i = 1;i<=6;i++){
		$("#"+index+i).attr("src","/sp/images/evaluate.png");
	}
	for(var i = 1;i<=parseInt(scoreValue);i++){
		$("#"+index+i).attr("src","/sp/images/evaluate_actived.png");
	}
}

/**
 * 保存分数
 */
function saveScore(){
	var content = $("#content").val();
	var orderId = $("#orderId").val();
	var params = "oneScore="+score[1]+"&twoScore="+score[2]+"&threeScore="+score[3]+"&fourScore="+
				 score[4]+"&content="+content+"&orderId="+orderId;
	$.ajax({
		url:"/score/data/save.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				$("#myAlertH").html("保存成功!");
				showNewAlert();
				//alert("保存成功!");
			}
		}
	})
}

/**
 * 教师评语
 */
function goComments(){
	var orderId = $("#orderId").val();
	var params = "id="+orderId;
	$.ajax({
		url:"/score/data/score.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			if(null!=res.errmsg){
				location.href = "/customer/page/comments.html?orderId="+orderId;
			}else{
				$("#myAlertH").html("评论之后才可以查看小结!");
				showNewAlert();
			/* 	alert("评论之后才可以查看小结!"); */
			}
		}
	})
}

/**
 * 取消
 */
function cacleOrder(){
	var orderId = $("#orderId").val();
	location.href = "/customer/page/cacleOrder.html?orderId="+orderId;
}
</script>
</head>
<body>
	<div id="newAlert">
		<h3  id="myAlertH" ></h3>
		<a id="newAlertBtn" onclick="hideNewAlert()" >确定</a>
	</div>
<div class="nav-title"><span class="nav-back" onclick="openUrl('/customer/page/manage.html')" >&lt;</span>约谈详情<span class="close" onclick="openUrl('/customer/page/index.html')" >&Chi;</span></div>
<input type="hidden" id="orderId" value="${order.id }" >
<div class="interviewDetails-top">
	<div class="interviewDetails-top-left">
		<img src="${order.qTutor.photoPath }"/>
	</div>
	<div class="interviewDetails-top-right">
	</div>
</div>

<div class="interviewDetails-case">
	<div>
		<span>约谈导师：</span>
		<label>${order.qTutor.realName }</label>
	</div>
	<div>
		<input type="hidden" value="${order.qTutorTime.realDate }" id="timeValue" >
		<span>约谈日期：</span>
		<label>${orderDate }</label>
	</div>
	<div>
		<span>约谈时间：</span>
		<label>${order.qTutorTime.realTime }</label>
	</div>
	<div>
		<span>辅导主题：</span>
	</div>
	<div>
		<label>${mytopic }</label>
	</div>
	<div>
		<span>辅导方式：</span>
		<label>当面约谈（60分钟）</label>
	</div>
	<div>
		<span>辅导费用:</span>
		<label>1500元/60分钟</label>
	</div>
	<div>
		<span></span>
		<label class="money-percent"></label>
		<button class="cancel-small-btn" onclick="cacleOrder()" >申请取消</button>
	</div>
	<div>
		<span>请对这次辅导做出评价：</span>
		<label></label>
	</div>
</div>

<div class="evaluate-text">
	<ul style="width: 100%;" >
		<li style="margin-left:4%;">不同意</li>
		<li>完全同意</li>
	</ul>
</div>
<div class="evaluate">
	<div>
		<span style="width: 100%;color: black;" >这次辅导完全达到了我的期望</span>
		<br>
		<ul style="width: 80%;margin-left: 10%;text-align: center;" >
			<li><img id="11" onclick="changeStar('1','1')" src="/sp/images/evaluate.png"/></li>
			<li><img id="12" onclick="changeStar('1','2')" src="/sp/images/evaluate.png"/></li>
			<li><img id="13" onclick="changeStar('1','3')" src="/sp/images/evaluate.png"/></li>
			<li><img id="14" onclick="changeStar('1','4')" src="/sp/images/evaluate.png"/></li>
			<li><img id="15" onclick="changeStar('1','5')" src="/sp/images/evaluate.png"/></li>
			<li><img id="16" onclick="changeStar('1','6')" src="/sp/images/evaluate.png"/></li>
		</ul>
	</div>
	
	<div>
		<span style="width: 100%;color: black;" >这次辅导是完整而系统的</span>
		<br>
		<ul style="width: 80%;margin-left: 10%;text-align: center;" >
			<li><img id="21" onclick="changeStar('2','1')" src="/sp/images/evaluate.png"/></li>
			<li><img id="22" onclick="changeStar('2','2')" src="/sp/images/evaluate.png"/></li>
			<li><img id="23" onclick="changeStar('2','3')" src="/sp/images/evaluate.png"/></li>
			<li><img id="24" onclick="changeStar('2','4')" src="/sp/images/evaluate.png"/></li>
			<li><img id="25" onclick="changeStar('2','5')" src="/sp/images/evaluate.png"/></li>
			<li><img id="26" onclick="changeStar('2','6')" src="/sp/images/evaluate.png"/></li>
		</ul>
	</div>
	
	<div>
		<span style="width: 100%;color: black;" >导师给了我非常有价值的职场建议</span>
		<ul style="width: 80%;margin-left: 10%;text-align: center;" >
			<li><img id="31" onclick="changeStar('3','1')" src="/sp/images/evaluate.png"/></li>
			<li><img id="32" onclick="changeStar('3','2')" src="/sp/images/evaluate.png"/></li>
			<li><img id="33" onclick="changeStar('3','3')" src="/sp/images/evaluate.png"/></li>
			<li><img id="34" onclick="changeStar('3','4')" src="/sp/images/evaluate.png"/></li>
			<li><img id="35" onclick="changeStar('3','5')" src="/sp/images/evaluate.png"/></li>
			<li><img id="36" onclick="changeStar('3','6')" src="/sp/images/evaluate.png"/></li>
		</ul>
	</div>
	
	<div>
		<span style="width: 100%;color: black;" >我愿意推荐这个导师给我的朋友们</span>
		<ul style="width: 80%;margin-left: 10%;text-align: center;" >
			<li><img id="41" onclick="changeStar('4','1')" src="/sp/images/evaluate.png"/></li>
			<li><img id="42" onclick="changeStar('4','2')" src="/sp/images/evaluate.png"/></li>
			<li><img id="43" onclick="changeStar('4','3')" src="/sp/images/evaluate.png"/></li>
			<li><img id="44" onclick="changeStar('4','4')" src="/sp/images/evaluate.png"/></li>
			<li><img id="45" onclick="changeStar('4','5')" src="/sp/images/evaluate.png"/></li>
			<li><img id="46" onclick="changeStar('4','6')" src="/sp/images/evaluate.png"/></li>
		</ul>
	</div>
	
</div>

<div class="interview-textarea">
	<textarea placeholder="请对辅导或导师做出评价，谢谢！" id="content" ></textarea>
</div>

<div class="sure-btn">
	<div class="tutor-search-btn btn-bottom" onclick="saveScore()" >提交评价</div>
	<div class="tutor-search-btn btn-orange-bg" onclick="goComments()" >查看来自导师的辅导小结</div>
</div>
</body>
</html>