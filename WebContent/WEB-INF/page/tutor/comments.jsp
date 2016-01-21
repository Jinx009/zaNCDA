<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>导师辅导小结</title>
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
var replyStatus = false;

$(function(){
	var id = $("#id").val();
	var params = "id="+id;
	$.ajax({
		url:"/comments/data/comments.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			if(null!=res.errmsg&&res.errmsg.length>0){
				$("#appeal_one").val(res.errmsg[0].appealOne);
				$("#appeal_two").val(res.errmsg[0].appealTwo);
				$("#appeal_three").val(res.errmsg[0].appealThree);
				$("#question").val(res.errmsg[0].question);
				$("#solve_status").val(res.errmsg[0].solveStatus);
				$("#solve_tool").val(res.errmsg[0].solveTool);
				$("#advice_one").val(res.errmsg[0].adviceOne);
				$("#advice_two").val(res.errmsg[0].adviceTwo);
				$("#advice_three").val(res.errmsg[0].adviceThree);
				$("#solve_result").val(res.errmsg[0].solveResult);
				$("#solve_assess").val(res.errmsg[0].solveAssess);
			}
		}
	})
})

/**
 * 执行保存
 */
function doSendComments(){
	var appealOne = $("#appeal_one").val();
	var appealTwo = $("#appeal_two").val();
	var appealThree = $("#appeal_three").val();
	var question = $("#question").val();
	var solveStatus = $("#solve_status").val();
	var solveTool = $("#solve_tool").val();
	var adviceOne = $("#advice_one").val();
	var adviceTwo = $("#advice_two").val();
	var adviceThree = $("#advice_three").val();
	var solveResult = $("#solve_result").val();
	var solveAssess = $("#solve_assess").val();
	var id = $("#id").val();
	
	var params = "appealOne="+appealOne+"&appealTwo="+appealTwo+"&appealThree="+appealThree+
				 "&question="+question+"&solveStatus="+solveStatus+"&solveTool="+solveTool+
				 "&adviceOne="+adviceOne+"&adviceTwo="+adviceTwo+"&adviceThree="+adviceThree+
				 "&solveAssess="+solveAssess+"&id="+id;
	
	$.ajax({
		url:"/comments/data/save.html",
		type:"POST",
		data:params,
		success:function(res){
			if("success"==res.result){
				 $("#myAlertH").html("提交成功!");
					$("#newAlertBtn").attr("onclick","goReload()");
					showNewAlert();
			/* 	alert("提交成功!");
				location.reload(); */
			}
		}
	})
}
function goReload(){
	location.reload();
}

/**
 * 保存小结
 */
function sendComments(){
	var status = $("#status").val();
	var time = $("#time").val();
	var replyStatus = getReplyStatus(status,time);
	
	if(replyStatus){
		doSendComments();
	}else{
		 $("#myAlertH").html("未支付和未到时间的约谈不能提交小结！");
			showNewAlert();
		/* alert("未支付和未到时间的约谈不能提交小结！"); */
	}
}
</script>
</head>
<body>
<div id="newAlert">
		<h3  id="myAlertH" ></h3>
		<a id="newAlertBtn" onclick="hideNewAlert()" >确定</a>
	</div>
<input type="hidden" value="${order.id }" id="id" >
<input type="hidden" value="${order.status }" id="status">
<input type="hidden" value="${order.qTutorTime.realTime }" id="time" >
<div class="nav-title">辅导小结具体内容<div class="close"  onclick="openUrl('/tutor/page/index.html')" >&Chi;</div></div>
	
	<div class="summaryDetails-title">
		<p>一、用户的主要诉求</p>
	</div>
	<div class="summaryDetails-sel">
		<ul>
			<li>
				<span>1</span>
				<div class="summaryDetails-sel-right">
					<textarea placeholder="请具体描述" id="appeal_one" ></textarea>
				</div>
			</li>
			<li>
				<span>2</span>
				<div class="summaryDetails-sel-right">
					<textarea placeholder="请具体描述" id="appeal_two" ></textarea>
				</div>
			</li>
			<li>
				<span>3</span>
				<div class="summaryDetails-sel-right">
					<textarea placeholder="请具体描述" id="appeal_three" ></textarea>
				</div>
			</li>
		</ul>
	</div>
	
	<div class="summaryDetails-title">
		<p>二、主要问题及案例分析</p>
	</div>
	<div class="summaryDetails-sel">
		<textarea placeholder="请具体描述" id="question"></textarea>
	</div>
	<div style="display: none;" >
		<div class="summaryDetails-title">
			<p>三、辅导策略</p>
		</div>
		<div class="summaryDetails-sel">
			<p>问题解决情况</p>
			<textarea placeholder="内容-文本框" id="solve_status"></textarea>
		</div>
		<div class="summaryDetails-sel">
			<p>主要运用辅导技术和工具</p>
			<textarea placeholder="内容-文本框" id="solve_tool"></textarea>
		</div>
	</div>
	<div class="summaryDetails-title">
		<p>三、对学员的后续发展建议</p>
	</div>
	<div class="summaryDetails-sel">
		<ul>
			<li>
				<span>1</span>
				<div class="summaryDetails-sel-right">
					<textarea placeholder="请具体描述" id="advice_one" ></textarea>
				</div>
			</li>
			<li>
				<span>2</span>
				<div class="summaryDetails-sel-right">
					<textarea placeholder="请具体描述" id="advice_two" ></textarea>
				</div>
			</li>
			<li>
				<span>3</span>
				<div class="summaryDetails-sel-right">
					<textarea placeholder="请具体描述" id="advice_three" ></textarea>
				</div>
			</li>
		</ul>
	</div>
	<div style="display: none;" >
		<div class="summaryDetails-title">
			<p>四、辅导成效评估与反思</p>
		</div>
		<div class="summaryDetails-sel">
			<p>问题解决情况</p>
			<textarea placeholder="请具体描述" id="solve_result" ></textarea>
		</div>
		<div class="summaryDetails-sel">
			<p>咨询能效自我评估</p>
			<textarea placeholder="请具体描述" id="solve_assess" ></textarea>
		</div>
	</div>
	<div class="tutor-search">
		<div class="tutor-search-btn btn-orange-bg" onclick="sendComments()" >提交辅导小结</div>
		<a href="#"><p class="right-cancel"  onclick="javascript:openUrl('/tutor/page/index.html');" >取消</p></a>
	</div>
</body>
</html>