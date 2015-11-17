<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>基本信息</title>
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
</head>
<body class="tutor-bg">
<div class="nav-title">完善信息<div class="close">&Chi;</div></div>

<div class="information-content">
	<p>导师认证资质</p>
	<textarea placeholder="200字，导师认证资质"  id="aptitude" class="big-textarea">${tutor.aptitude }</textarea>
</div>

<div class="information-content">
	<p>导师个人简介</p>
	<textarea placeholder="200字，导师个人简介" id="personalIntroduction" class="big-textarea">${tutor.personalIntroduction }</textarea>
</div>
<div class="teacher-two-btn">
	<button class="teacher-orange-btn">上一步</button>
	<button class="teacher-orange-btn">下一步</button>
</div>
</body>
</html>