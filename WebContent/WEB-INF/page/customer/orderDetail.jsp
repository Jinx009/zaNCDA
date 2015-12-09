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
				alert("保存成功!");
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
				alert("评论之后才可以查看小结!");
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
<div class="nav-title">约谈详情<span class="close" onclick="openUrl('/customer/page/manage.html')" >&Chi;</span></div>
<input type="hidden" id="orderId" value="${order.id }" >
<div class="interviewDetails-top">
	<div class="interviewDetails-top-left">
		<img src="/sp/images/headIcon_middle.png"/>
	</div>
	<div class="interviewDetails-top-right">
		<p class="interview-title">${order.topicContent }</p>
		<p class="interview-teacher"><span>约谈导师：</span>${order.qTutor.realName }</p>
	</div>
</div>

<div class="interviewDetails-case">
	<div>
		<input type="hidden" value="${order.qTutorTime.realDate }" id="timeValue" >
		<span>时间：</span>
		<label>2015年11月12日</label>
	</div>
	<div>
		<span>方式：</span>
		<label>见面授课</label>
	</div>
	<div>
		<span>课程费用:</span>
		<label>${order.qTutor.classPrice }</label>
	</div>
	<div>
		<span>支付:</span>
		<label class="money-percent">100%</label>
		<button class="cancel-small-btn" onclick="cacleOrder()" >申请取消</button>
	</div>
</div>

<div class="evaluate-text">
	<ul>
		<li>不同意</li>
		<li>完全同意</li>
	</ul>
</div>
<div class="evaluate">
	<div>
		<span style="width: 100%" >这次辅导完全达到了我的期望</span>
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
		<span style="width: 100%" >这次辅导是完整而系统的</span>
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
		<span style="width: 100%" >导师给了我非常有价值的职场建议</span>
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
		<span style="width: 100%" >我愿意推荐这个导师给我的朋友们</span>
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
	<textarea placeholder="对老师的评语" id="content" ></textarea>
</div>

<div class="sure-btn">
	<div class="tutor-search-btn btn-bottom" onclick="saveScore()" >提交评语</div>
	<div class="tutor-search-btn btn-orange-bg" onclick="goComments()" >查看来自导师的辅导小结</div>
</div>
</body>
</html>