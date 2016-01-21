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
				htmlStr += '<p>恭喜你！</p>';
				htmlStr += '<p>你期望的导师就在这里！</p>';
				htmlStr += '<p>开始选择吧！</p>';
				$('#tutor-tip-success').html(htmlStr);
				htmlStr = '';
				for(var i = 0;i<res.errmsg.length;i++){
					htmlStr += '<div class="tutor-list">';
					htmlStr += '<div class="tutor-wrap">';
					htmlStr += '<div class="tutor-img">';
					if(null!=res.errmsg[i].photoPath&&""!=res.errmsg[i].photoPath){
						htmlStr += '<img src='+res.errmsg[i].photoPath+' />';
					}else{
						htmlStr += '<img src="/sp/images/headPortrait.png" />';
					}
					htmlStr += '</div>';
					htmlStr += '<div class="tutor-text">';
					htmlStr += '<p class="tutor-name">'+res.errmsg[i].realName+'</p>';
					htmlStr += '<div class="tutor-like">';
					htmlStr += '<h3>擅长行业：</h3>';
					htmlStr += '<ul>';
					htmlStr += '<li></li>';
					htmlStr += '</ul>';
					htmlStr += "</div>";
					if("不选择"!=res.errmsg[i].t1){
						htmlStr += '<div class="tutor-like">';
						htmlStr += '<h3></h3>';
						htmlStr += '<ul>';
						htmlStr += '<li>'+res.errmsg[i].t1+'</li>';
						htmlStr += '</ul>';
						htmlStr += "</div>";
					}
					if("不选择"!=res.errmsg[i].t2){
						htmlStr += '<div class="tutor-like">';
						htmlStr += '<h3></h3>';
						htmlStr += '<ul>';
						htmlStr += '<li>'+res.errmsg[i].t2+'</li>';
						htmlStr += '</ul>';
						htmlStr += '</div>';
					}
					if("不选择"!=res.errmsg[i].t3){
						htmlStr += '<div class="tutor-like">';
						htmlStr += '<h3></h3>';
						htmlStr += '<ul>';
						htmlStr += '<li>'+res.errmsg[i].t3+'</li>';
						htmlStr += '</ul>';
						htmlStr += '</div>';
					}
					htmlStr += '<div class="tutor-like">';
					htmlStr += '<h3>擅长职能：</h3>';
					htmlStr += '<ul>';
					htmlStr += '<li></li>';
					htmlStr += '</ul>';
					htmlStr += "</div>";
					if("不选择"!=res.errmsg[i].a1){
						htmlStr += '<div class="tutor-like">';
						htmlStr += '<h3></h3>';
						htmlStr += '<ul>';
						htmlStr += '<li>'+res.errmsg[i].a1+'</li>';
						htmlStr += '</ul>';
						htmlStr += '</div>';
					}
					if("不选择"!=res.errmsg[i].a2){
						htmlStr += '<div class="tutor-like">';
						htmlStr += '<h3></h3>';
						htmlStr += '<ul>';
						htmlStr += '<li>'+res.errmsg[i].a2+'</li>';
						htmlStr += '</ul>';
						htmlStr += '</div>';
					}
					if("不选择"!=res.errmsg[i].a3){
						htmlStr += '<div class="tutor-like">';
						htmlStr += '<h3></h3>';
						htmlStr += '<ul>';
						htmlStr += '<li>'+res.errmsg[i].a3+'</li>';
						htmlStr += '</ul>';
						htmlStr += '</div>';
					}
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
			/* 	htmlStr += '<p>加油！你距离导师又近了一步！</p>';
				htmlStr += '<p>我们从系统中筛选出与你的要求相关度最高的导师。</p>';
				htmlStr += '<p>开始选择吧！</p>'; */
				htmlStr += '<p>恭喜你！</p>';
				htmlStr += '<p>你期望的导师就在这里！</p>';
				htmlStr += '<p>开始选择吧！</p>';
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
							if(null!=res.errmsg[i].photoPath&&""!=res.errmsg[i].photoPath){
						htmlStr += '<img src='+res.errmsg[i].photoPath+' />';
					}else{
						htmlStr += '<img src="/sp/images/headPortrait.png" />';
					}
							htmlStr += '</div>';
							htmlStr += '<div class="tutor-text">';
							htmlStr += '<p class="tutor-name">'+res.errmsg[i].realName+'</p>';
							htmlStr += '<div class="tutor-like">';
							htmlStr += '<h3>擅长行业：</h3>';
							htmlStr += '<ul>';
							htmlStr += '<li></li>';
							htmlStr += '</ul>';
							htmlStr += "</div>";
							if("不选择"!=res.errmsg[i].t1){
								htmlStr += '<div class="tutor-like">';
								htmlStr += '<h3></h3>';
								htmlStr += '<ul>';
								htmlStr += '<li>'+res.errmsg[i].t1+'</li>';
								htmlStr += '</ul>';
								htmlStr += '</div>';
							}
							if("不选择"!=res.errmsg[i].t2){
								htmlStr += '<div class="tutor-like">';
								htmlStr += '<h3></h3>';
								htmlStr += '<ul>';
								htmlStr += '<li>'+res.errmsg[i].t2+'</li>';
								htmlStr += '</ul>';
								htmlStr += '</div>';
							}
							if("不选择"!=res.errmsg[i].t3){
								htmlStr += '<div class="tutor-like">';
								htmlStr += '<h3></h3>';
								htmlStr += '<ul>';
								htmlStr += '<li>'+res.errmsg[i].t3+'</li>';
								htmlStr += '</ul>';
								htmlStr += '</div>';
							}
							htmlStr += '<div class="tutor-like">';
							htmlStr += '<h3>擅长职能：</h3>';
							htmlStr += '<ul>';
							htmlStr += '<li></li>';
							htmlStr += '</ul>';
							htmlStr += '</div>';
							if("不选择"!=res.errmsg[i].a1){
								htmlStr += '<div class="tutor-like">';
								htmlStr += '<h3></h3>';
								htmlStr += '<ul>';
								htmlStr += '<li>'+res.errmsg[i].a1+'</li>';
								htmlStr += '</ul>';
								htmlStr += '</div>';
							}
							if("不选择"!=res.errmsg[i].a2){
								htmlStr += '<div class="tutor-like">';
								htmlStr += '<h3></h3>';
								htmlStr += '<ul>';
								htmlStr += '<li>'+res.errmsg[i].a2+'</li>';
								htmlStr += '</ul>';
								htmlStr += '</div>';
							}
							if("不选择"!=res.errmsg[i].a3){
								htmlStr += '<div class="tutor-like">';
								htmlStr += '<h3></h3>';
								htmlStr += '<ul>';
								htmlStr += '<li>'+res.errmsg[i].a3+'</li>';
								htmlStr += '</ul>';
								htmlStr += '</div>';
							}
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
	
	location.href = '/customer/page/tutorDetail.html?topicId='+topicId+'&tutorId='+tutorId;
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
<a ><div class="nav-title"><span class="nav-back" onclick="goBack();" >&lt;</span>我们推荐的导师<span class="close" onclick="openUrl('/customer/page/new.html')" >&Chi;</span></div></a>
<!--匹配成功-->
<div class="tutor-tip" id="tutor-tip-success">
	<p></p>
	<p></p>
	<p></p>
</div>
<div id="dataDiv" ></div>
<div class="tutor-search">
	<div class="tutor-search-btn" onclick="goBack()" >更换条件再检索</div>
</div>
</body>
</html>