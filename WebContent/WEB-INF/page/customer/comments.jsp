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
 * 返回
 */
function goBack(){
	var id = $("#id").val();
	location.href = "/customer/page/orderDetail.html?orderId="+id;
}
</script>
</head>
<body>
<input type="hidden" value="${order.id }" id="id" >
<input type="hidden" value="${order.status }" id="status">
<input type="hidden" value="${order.qTutorTime.realTime }" id="time" >
<div class="nav-title">辅导小结具体内容<div class="close"  onclick="openUrl('/customer/page/index.html')" >&Chi;</div></div>
	
	<div class="summaryDetails-title">
		<p>一、用户的主要诉求</p>
	</div>
	<div class="summaryDetails-sel">
		<ul>
			<li>
				<span>1</span>
				<div class="summaryDetails-sel-right">
					<textarea readonly="readonly" placeholder="请具体描述" id="appeal_one" ></textarea>
				</div>
			</li>
			<li>
				<span>2</span>
				<div class="summaryDetails-sel-right">
					<textarea readonly="readonly"  placeholder="请具体描述" id="appeal_two" ></textarea>
				</div>
			</li>
			<li>
				<span>3</span>
				<div class="summaryDetails-sel-right">
					<textarea readonly="readonly"  placeholder="请具体描述" id="appeal_three" ></textarea>
				</div>
			</li>
		</ul>
	</div>
	
	<div class="summaryDetails-title">
		<p>二、主要问题及案例分析</p>
	</div>
	<div class="summaryDetails-sel">
		<textarea readonly="readonly"  placeholder="请具体描述" id="question"></textarea>
	</div>
	<div style="display: none;" >
		<div class="summaryDetails-title">
			<p>三、辅导策略</p>
		</div>
		<div class="summaryDetails-sel">
			<p>问题解决情况</p>
			<textarea readonly="readonly"  placeholder="请具体描述" id="solve_status"></textarea>
		</div>
		<div class="summaryDetails-sel">
			<p>主要运用辅导技术和工具</p>
			<textarea readonly="readonly"  placeholder="请具体描述" id="solve_tool"></textarea>
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
					<textarea readonly="readonly"  placeholder="发展建议一" id="advice_one" ></textarea>
				</div>
			</li>
			<li>
				<span>2</span>
				<div class="summaryDetails-sel-right">
					<textarea readonly="readonly"  placeholder="发展建议二" id="advice_two" ></textarea>
				</div>
			</li>
			<li>
				<span>3</span>
				<div class="summaryDetails-sel-right">
					<textarea readonly="readonly"  placeholder="发展建议三" id="advice_three" ></textarea>
				</div>
			</li>
		</ul>
	</div>
	<div style="display: none;" >
		<div class="summaryDetails-title">
			<p>五、辅导成效评估与反思</p>
		</div>
		<div class="summaryDetails-sel">
			<p>问题解决情况</p>
			<textarea readonly="readonly"  placeholder="内容-文本框" id="solve_result" ></textarea>
		</div>
		<div class="summaryDetails-sel">
			<p>咨询能效自我评估</p>
			<textarea readonly="readonly"  placeholder="内容-文本框" id="solve_assess" ></textarea>
		</div>
	</div>
	<div class="tutor-search">
		<a href="#"><p class="right-cancel"  onclick="goBack();" >返回</p></a>
	</div>
</body>
</html>