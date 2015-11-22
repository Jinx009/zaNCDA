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
<title>首页</title>
</head>
<body>
<div class="nav-title">首页</div>
<div class="index-main">
	<div class="index-box">
		<div>
			<h3 class="no-img-h3">我们是谁：</h3>
			<div class="qa">
				<img src="/sp/images/qa_left.png" class="qa_left"/>
				<p class="index-content_p">才知道－Career Partners，是中国最专业的职业生涯规划和发展咨询平台。作为美国国家生涯发展协会(National Career Development Association – NCDA)的官方授权代表，我们提供国内最专业，也最具有企业实战经验的职业生涯规划教练团体，通过定制的一对一辅导，帮助大家找准自己的职业定位，解决职场困惑，实现职业成长梦想。</p>
				<img src="/sp/images/qa_right.png" class="qa_right"/>
			</div>	
		</div>
	</div>
	
	<div class="index-box"  onclick="openUrl('/customer/page/caseDetail.html')" >
		<div class="index-img">
			<img src="/sp/images/caseDetail_bg.png" class="index-big"/>
			<img src="/sp/images/index-label-recommend.png" class="index-small"/>
		</div>
		<div class="index-content">
			<h3>张三，强生中国，2014届管理培训生</h3>
			<div class="qa">	
				<img src="/sp/images/qa_left.png" class="qa_left"/>
				<p>如何快速融入充满挑战的企业职场？</p>
				<img src="/sp/images/qa_right.png" class="qa_right"/>
			</div>	
		</div>
		
	</div>
	
	<div class="index-box" onclick="openUrl('/customer/page/caseDetail.html')" >
		<div class="index-img">
			<img src="/sp/images/caseDetail_bg.png" class="index-big"/>
			<img src="/sp/images/index-label-hot.png" class="index-small"/>
		</div>
		<div class="index-content">
			<h3>李斯，联合利华，研发部门经理</h3>
			<div class="qa">
				<img src="/sp/images/qa_left.png" class="qa_left"/>
				<p>如何突破职业生涯瓶颈</p>
				<img src="/sp/images/qa_right.png" class="qa_right"/>
			</div>
				
		</div>
	</div>
	
	<div class="index-box"  onclick="openUrl('/customer/page/caseDetail.html')" >
		<div class="index-img">
			<img src="/sp/images/caseDetail_bg.png" class="index-big"/>
			<img src="/sp/images/index-label-only.png" class="index-small"/>
		</div>
		<div class="index-content">
			<h3>陈梅，埃森哲中国，咨询顾问</h3>
			<div class="qa">
				<img src="/sp/images/qa_left.png" class="qa_left"/>
				<p>如何在复杂的项目中提升自己的咨询能力</p>
				<img src="/sp/images/qa_right.png" class="qa_right"/>
			</div>
		</div>
	</div>
	<div class="index-nav">
		<div><a href="/customer/page/new.html">新建辅导</a></div>
		<div><a href="/customer/page/manage.html">约谈管理</a></div>
	</div>
</div>
</body>
</html>