<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />
<title>筛选结果</title>
<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript">
/**
 * 下一步
 */
function goNext(){
	location.href = "/customer/page/selectResult.html";
}
</script>
</head>
<body class="tutor-bg">
<a href="#"><div class="nav-title"><span class="nav-back">&lt;</span>我们推荐的导师</div></a>
<!--匹配成功-->
<div class="tutor-tip" id="tutor-tip-success">
	<p>恭喜!</p>
	<p>根据你的期望，我们已经为你推荐匹配的导师们。</p>
	<p>开始选择吧！</p>
</div>
<div class="tutor-list">
	<div class="tutor-wrap">
		<div class="tutor-img">
			<img src="/sp/images/headPortrait.png" />
		</div>
		<div class="tutor-text">
			<p class="tutor-name">刘教师</p>
			<div class="tutor-desc">
				<h3>行业：</h3>
				<ul>
					<li>教育行业</li>
					<li>影视</li>
					<li>航天</li>
				</ul>
			</div>
			<div class="tutor-like">
				<h3>擅长领域：</h3>
				<ul>
					<li>数学</li>
					<li>语文</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="tutor-bottom">
		<div class="tutor-btn">立即约TA</div>
	</div>
</div>

<div class="tutor-list">
	<div class="tutor-wrap">
		<div class="tutor-img">
			<img src="/sp/images/headPortrait.png" />
		</div>
		<div class="tutor-text">
			<p class="tutor-name">刘教师</p>
			<div class="tutor-desc">
				<h3>行业：</h3>
				<ul>
					<li>航天</li>
				</ul>
			</div>
			<div class="tutor-like">
				<h3>擅长领域：</h3>
				<ul>
					<li>数学</li>
					<li>语文</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="tutor-bottom">
		<div class="tutor-btn">立即约TA</div>
	</div>
</div>

<div class="tutor-list">
	<div class="tutor-wrap">
		<div class="tutor-img">
			<img src="/sp/images/headPortrait.png" />
		</div>
		<div class="tutor-text">
			<p class="tutor-name">刘教师</p>
			<div class="tutor-desc">
				<h3>行业：</h3>
				<ul>
					<li>影视</li>
					<li>航天</li>
				</ul>
			</div>
			<div class="tutor-like">
				<h3>擅长领域：</h3>
				<ul>
					<li>数学</li>
					<li>语文</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="tutor-bottom">
		<div class="tutor-btn">立即约TA</div>
	</div>
</div>

<div class="tutor-search">
	<div class="tutor-search-btn">更换条件再检索</div>
</div>
</body>
</html>