<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />
<title>筛选结果</title>
<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript" src="/sp/js/pay/lazyloadv3.js" ></script>
<script type="text/javascript" src="/sp/js/pay/md5.js" ></script>
<script type="text/javascript" src="/sp/js/pay/sha1.js" ></script>
<script type="text/javascript" src="/sp/js/pay/pay.js" ></script>
<script type="text/javascript">

$(function(){
	var trade = $("#trade").val();
	var area = $("#area").val();
	var type = $("#type").val();
	var workYears = $("#workYears").val();
	var id = $("#id").val();
	
	var params =  "trade="+trade+"&area="+area+"&type="+type+
				  "&workYears="+workYears+"&id="+id;
	
	$.ajax({
		url:'/tutpr/data/selectList.html',
		type:'POST',
		data:params,
		dataType:'json',
		success:function(res){
			if(null!=res.errmsg&&res.errmsg.length>0){
				var htmlStr = '';
				for(var i = 0;i<res.errmsg.length;i++){
					htmlStr += '<div class="tutor-list">';
					htmlStr += '<div class="tutor-wrap">';
					htmlStr += '<div class="tutor-img">';
					htmlStr += '<img src="/sp/images/headPortrait.png" />';
					htmlStr += '</div>';
					htmlStr += '<div class="tutor-text">';
					htmlStr += '<p class="tutor-name">'+res.errmsg[i].realName+'</p>';
					htmlStr += '<div class="tutor-desc">';
					htmlStr += '<h3>行业：</h3>';
					htmlStr += '<ul>';
					htmlStr += '<li>'+res.errmsg[i].tradeOne.tradeName+'</li>';
					htmlStr += '<li>'+res.errmsg[i].tradeTwo.tradeName+'</li>';
					htmlStr += '</ul>';
					htmlStr += '</div>';
					htmlStr += '<div class="tutor-like">';
					htmlStr += '<h3>擅长领域：</h3>';
					htmlStr += '<ul>';
					htmlStr += '<li>'+res.errmsg[i].areaOne.tradeName+'</li>';
					htmlStr += '</ul>';
					htmlStr += '</div>';
					htmlStr += '</div>';
					htmlStr += '</div>';
					htmlStr += '<div class="tutor-bottom">';
					htmlStr += '<div class="tutor-btn"  onclick="saveOrder('+res.errmsg[i].id+')" >立即约TA</div>';
					htmlStr += '</div>';
					htmlStr += '</div>';
				}
				$('#dataDiv').html(htmlStr);
			}else{
				var htmlStr = '';
				htmlStr += '<p>很抱歉，没有匹配的导师！</p>';
				htmlStr += '<p>你可以重新筛选条件。</p>';
				htmlStr += '<p>系统为你推荐了可能合适的导师</p>';
				$('#tutor-tip-success').html(htmlStr);
				
				$.ajax({
					url:'/tutor/data/list.html?time='+getRandom(),
					type:'GET',
					dataType:'json',
					success:function(res){
						var length = 10;
						var htmlStr = '';
						if(10>=res.errmsg.length){
							length = res.errmsg.length;
						}
						for(var i = 0;i<length;i++){
							htmlStr += '<div class="tutor-list">';
							htmlStr += '<div class="tutor-wrap">';
							htmlStr += '<div class="tutor-img">';
							htmlStr += '<img src="/sp/images/headPortrait.png" />';
							htmlStr += '</div>';
							htmlStr += '<div class="tutor-text">';
							htmlStr += '<p class="tutor-name">'+res.errmsg[i].realName+'</p>';
							htmlStr += '<div class="tutor-desc">';
							htmlStr += '<h3>行业：</h3>';
							htmlStr += '<ul>';
							htmlStr += '<li>'+res.errmsg[i].tradeOne.tradeName+'</li>';
							htmlStr += '<li>'+res.errmsg[i].tradeTwo.tradeName+'</li>';
							htmlStr += '</ul>';
							htmlStr += '</div>';
							htmlStr += '<div class="tutor-like">';
							htmlStr += '<h3>擅长领域：</h3>';
							htmlStr += '<ul>';
							htmlStr += '<li>'+res.errmsg[i].areaOne.tradeName+'</li>';
							htmlStr += '</ul>';
							htmlStr += '</div>';
							htmlStr += '</div>';
							htmlStr += '</div>';
							htmlStr += '<div class="tutor-bottom">';
							htmlStr += '<div class="tutor-btn" onclick="saveOrder('+res.errmsg[i].id+')" >立即约TA</div>';
							htmlStr += '</div>';
							htmlStr += '</div>';
						}
						$('#dataDiv').html(htmlStr);
					}
				})
			}
		}
	})
})

/**
 * 保存订单
 */
function  saveOrder(id){
	var topicId = $('#id').val();
	var tutorId = id;
	
	location.href = '/customer/page/time.html?topicId='+topicId+'&tutorId='+tutorId;
}

/**
 * 上一步
 */
function goBack(){
	window.history.back();
}
</script>
</head>
<body class="tutor-bg">
<input type="hidden" value="${trade }" id="trade" >
<input type="hidden" value="${area }" id="area" >
<input type="hidden" value="${type }" id="type" >
<input type="hidden" value="${workYears }" id="workYears" >
<input type="hidden" value="${id }" id="id" >
<a ><div class="nav-title"><span class="nav-back" onclick="goBack();" >&lt;</span>我们推荐的导师</div></a>
<!--匹配成功-->
<div class="tutor-tip" id="tutor-tip-success">
	<p>恭喜!</p>
	<p>根据你的期望，我们已经为你推荐匹配的导师们。</p>
	<p>开始选择吧！</p>
</div>
<div id="dataDiv" ></div>
<div class="tutor-search">
	<div class="tutor-search-btn" onclick="goBack()" >更换条件再检索</div>
</div>
</body>
</html>