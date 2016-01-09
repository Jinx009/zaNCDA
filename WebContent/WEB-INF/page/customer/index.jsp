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
			<!-- 	<img src="/sp/images/qa_left.png" class="qa_left"/> -->
				<p class="index-content_p">“才知道－Career Partners，是中国最专业的职业生涯规划和发展咨询平台。作为美国国家生涯发展协会(National Career Development Association – NCDA)的官方授权代表，我们提供国内最专业，也最具有企业实战经验的职业生涯规划教练团体，通过定制的一对一辅导，帮助大家找准自己的职业定位，解决职场困惑，实现职业成长梦想。”</p>
			<!-- 	<img src="/sp/images/qa_right.png" class="qa_right"/> -->
			</div>	
		</div>
	</div>
	
	<div class="index-box"  onclick="openUrl('/customer/page/caseDetail.html?id=3')" >
		<div class="index-img">
			<img src="/sp/images/caseDetail_bg.png" class="index-big"/>
			<img src="/sp/images/zyfz.png" class="index-small"/>
		</div>
		<div class="index-content">
			<h3>张小丽，英语培训公司，企业销售经理</h3>
			<div class="qa">	
				<img src="/sp/images/qa_left.png" class="qa_left"/>
				<p>职场情商</p>
				<img src="/sp/images/qa_right.png" class="qa_right"/>
			</div>	
		</div>
		
	</div>
	
	<div class="index-box" onclick="openUrl('/customer/page/caseDetail.html?id=1')" >
		<div class="index-img">
			<img src="/sp/images/caseDetail_bg.png" class="index-big"/>
			<img src="/sp/images/zyxz.png" class="index-small"/>
		</div>
		<div class="index-content">
			<h3>陈美，全球知名4A广告公司，媒体策划经理</h3>
			<div class="qa">
				<img src="/sp/images/qa_left.png" class="qa_left"/>
				<p>自我定位和职业发展</p>
				<img src="/sp/images/qa_right.png" class="qa_right"/>
			</div>
				
		</div>
	</div>
	
	<div class="index-box"  onclick="openUrl('/customer/page/caseDetail.html?id=2')" >
		<div class="index-img">
			<img src="/sp/images/caseDetail_bg.png" class="index-big"/>
			<img src="/sp/images/zcgx.png" class="index-small"/>
		</div>
		<div class="index-content">
			<h3>张磊，欧洲大型工业企业，招聘经理</h3>
			<div class="qa">
				<img src="/sp/images/qa_left.png" class="qa_left"/>
				<p>职业发展下一站——转型</p>
				<img src="/sp/images/qa_right.png" class="qa_right"/>
			</div>
		</div>
	</div>
	<div class="index-nav" style="position:fixed;bottom: 0px;left: 0px;width: 100%;height: 50px;z-index: 9999;" >
		<div><a href="/customer/page/new.html">开始预约</a></div>
		<div><a href="/customer/page/manage.html">约谈管理</a></div>
	</div>
</div>
</body>
</html>