﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="stylesheet" href="/sp/css/base.css" />
<link rel="stylesheet" href="/sp/css/ncda.css" />
<title>约谈时间</title>
<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript" src="/sp/js/pay/lazyloadv3.js" ></script>
<script type="text/javascript" src="/sp/js/pay/md5.js" ></script>
<script type="text/javascript" src="/sp/js/pay/sha1.js" ></script>
<script type="text/javascript" src="/sp/js/pay/pay.js" ></script>
<script type="text/javascript">
$(function(){
	getDate();

	var timestamp = Date.parse(new Date());

	$("#orderId").val(timestamp);
	var width = $(window).width();
	var height = $(document).height();
	$(".checked").bind("click",function(){
		$(".mask").show();
		$(".mask").css("height",height+"px");
		$(".treaty").css("height",height*0.5+"px");
		$(".personal-boxWrap").css("height",height*0.75+"px");
		$(".personal-boxWrap").show();
	})
})

function hideThis(){
	$(".mask").hide();
	$(".personal-boxWrap").hide();
}

/**
 * 选择导师时间
 */
function getDate(){
	var tutorId = $("#tutorId").val();
	var params = "tutorId="+tutorId;
	
	$.ajax({
		url:"/customer/data/orderDate.html?time="+getRandom(),
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			console.log(res)
			if(null!=res.errmsg){
				var htmlStr = "";
				for(var i = 0;i<res.errmsg.length;i++){
					htmlStr += "<option value = '"+jsDateTimeOnly(res.errmsg[i].realDate)+"' >"+jsDateTimeOnly(res.errmsg[i].realDate)+"</option>"
				}
				$("#date").html(htmlStr);
				changeTime();
			}else{
				$("#myAlertH").html("很抱歉，该导师暂时没有可预约时间.点击确定重新选择导师!");
				$("#newAlertBtn").attr("onclick","myGoBack()");
				showNewAlert();
				/* alert("很抱歉，该导师暂时没有可预约时间.点击确定重新选择导师!");
				window.history.back(); */
			}
		}
	})
}
function myGoBack(){
	window.history.back();
}
function changeTime(){
	var date = $("#date").val();
	var tutorId = $("#tutorId").val();
	var params = "tutorId="+tutorId+"&realDate="+date;
	
	$.ajax({
		url:"/customer/data/orderTime.html?time="+getRandom(),
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			if(null!=res.errmsg){
				var htmlStr = "";
				for(var i = 0;i<res.errmsg.length;i++){
					htmlStr += "<option value = '"+res.errmsg[i].id+"' >"+res.errmsg[i].realTime+"</option>"
				}
				$("#time").html(htmlStr);
			}
		}
	})
}

function goBack(){
	window.history.back();
}
</script>
</head>
<body>
	<div id="newAlert">
		<h3  id="myAlertH" ></h3>
		<a id="newAlertBtn" onclick="hideNewAlert()" >确定</a>
	</div>
<input type="hidden" value="${tutorId }" id="tutorId" />
<input type="hidden" value="${topicId }" id="topicId" >
<input type="hidden" value="" id="orderId" >
<input type="hidden" value="${openid }" id="openId" >
<input type="hidden" value="${fee }" id="fee" >
<div class="main">
	<a href="#"><div class="nav-title"><span class="nav-back" onclick="goBack()" >&lt;</span>导师时间<span class="close" onclick="openUrl('/customer/page/new.html')" >&Chi;</span></div></a>
	<div class="personal-wrap">
		<p>选择您希望约谈的日期：</p>
		<select class="interview-sel center" id="date" onchange="changeTime()" ></select>
		<p>选择您希望约谈的具体时间：</p>
		<select class="interview-sel center" id="time"  ></select>
		<p></p>
	</div>
	
	<div class="personal-wrap">
		<div class="checked">
			<img src="/sp/images/check.png"/>
			<span>已阅读并同意我们的条款</span>
		</div>
	</div>
	
	<div class="btn-position" style="margin-top: 0px;">
		<div class="two-btn">
			<button id="appoint" onclick="pay()">付款约谈</button>
		</div>
	</div>
	<!--蒙版
	<div class="mask"></div>-->
	<!--悬浮框	start
	<div class="personal-boxWrap">
		<div class="personal-box">
			<h3>客户须知</h3>
			<div class="treaty">
				<p>
				
				</p>
			</div>
			<div class="cancel-box-btn">
				<button>确定</button>
			</div>
		</div>
	</div>
</div>-->

<!--蒙版-->
<div class="mask"></div>
<!--悬浮框	start-->
<div class="personal-boxWrap">
	<div class="personal-box">
		<h3>客户须知</h3>
		<div class="treaty">
		
			<p>“才知道”是一个职业生涯规划和发展咨询平台，隶属于上海才程信息科技有限公司。在“才知道”，用户分为两类，分别是导师和学员。</p>
			<p>当学员遇到职业困惑时，可以在平台上选择自己的解惑情景和主题，系统会推荐匹配的专业认证导师，学员通过支付一定的费用，获得与导师一对一的定制辅导机会。</p>
			<p>本用户协议是用户与“才知道”之间就相关事宜所订立的契约,包含用户 (“学员”及“导师”)和“才知道”之间所有的条款和条件。 </p>
			<p>作为用户,在“才知道”网站或者移动客户端注册前,必须事先认真阅读本用户协议,特别 是本协议中关于限制、减轻或者免除“才知道”责任的全部协议内容以及含有限制用户权利 的协议内容。 </p>
			<p>如果您(指“用户”)同意本用户协议,或者实际存在使用行为,将被视为您完全接受本用 户协议,包括接受“才知道”对用户协议随时所做的任何修改,同时也表示您同意“才知道” 隐私声明,本协议即构成对双方有约束力的法律文件。如不同意本用户协议,您应不得使用 或主动取消“才知道”提供的服务。 的各类规则。所有规则都被视为用户协议的组成部分,具有同等的法律效力。 </p>
			<p>1.2 用户下载、注册、登录、使用及连接“才知道”服务均被视为用户完全了解、接受并同 意遵守本用户协议的所有内容。“才知道”有权在必要时单方修改用户协议,并通过网站或 者客户端发布修改变更,且不再另行通知。修改后的协议一经发布即代替原来协议,自动生 成为用户与“才知道”之间全部、最新的协议。如果在用户协议更改生效后,用户继续使用 “才知道”服务,则视为您接受用户协议的变动并愿意遵守最新用户协议。如果用户不接受 “才知道”修改后的用户协议,请立即停止使用“才知道”服务。</p> 
			<p>1.3 “才知道“所有服务的所有权、运作权和解释权归“才知道”所有。 用户同意本用户协议内容并完成注册程序,即成为“才知道”的正式用户。 </p>
			<p>1.4 用户应当为具有完全民事行为能力的自然人,或者是具有独立承担法律责任能力的其他 合法主体。若用户属于无民事行为能力、限制民事行为能力人的,或是不具有独立承担法律 责任能力的其他主体的,您应在监护人监护下或是得到有权主体授权后使用“才知道”网站 及移动客户端。 </p>
			<p>1.5 用户特此声明,已经完全理解本用户协议所有内容,并不存在任何重大误解;同时,认 >可本用户协议并不存在显失公平的情形。 第2条 用户信息 </p>
			<p>2.1 用户个人信息。用户个人信息包括下列信息:用户真实姓名、身份证号、职业、职务、 头像、手机电话、银行账号、第三方支付账号、电子邮件、教育经历、从业经历等。 </p>
			<p>2.2 非用户个人信息。用户在“才知道“服务器上,包括导师话题、用户 私聊、操作状态、使用记录、使用习惯等在内的全部记录信息。除本条第 1 款所列用户个人 信息范围外的所有信息,均为非用户个人信息。 </p>
			<p>2.3 第三方平台记录信息。用户通过新浪微博、腾讯微信等第三方平台账号注册、登陆、使 用“才知道”服务的,将被视为用户完全理解、同意并接受“才知道”已包括但不限于收集、 统计、分析等方式使用其在新浪微博、腾讯微信等第三方平台填写、登记、公布、记录的全 部信息。用户一旦使用第三方平台账号注册、登陆、使用“才知道”服务,“才知道”对该 第三方记录信息的任何使用,均被视为已经获得了用户本人的完全同意并接受。 </p>
			<p>2.4 用户在“才知道”所涉及的包括个人真实姓名、手机电话、电子邮件、 通信地址等在内的用户个人信息、第三方平台账号信息以及非用户个人信息,“才知道”均 予以严格保密,除非用户授权或者法律另有规定之外,“才知道”不会向外界披露用户隐私 信息。“才知道”尊重用户的合法权利,不会以违反法律、行政法规以及本协议约定的方式 收集、使用用户信息。 </p>
			<p>2.5 用户自行向“才知道”提供个人信息、教育经历、工作经历、交谈话题以及其他信息, 所提供的信息必须在合法基础上保证真实、准确、完整,并保证及时更新以上信息。如因提 供的信息存在非法、抄袭、错误等问题,用户需承担因此引发的相应责任以及后果,且“才 知道”保留终止用户使用“才知道”各项服务的权利。 </p>
			<p>2.6 用户应维护个人 “才知道”帐户和密码安全,并对此帐户在“才知道”网站以及移动客 户端上的所有行为负完全责任,不得将帐户借给他人使用,否则应承担由此产生的全部责任, 并与实际使用人承担连带责任。当遇到账户或者密码未获授权使用,或者发生任何安全问题 时,用户有责任及时有效地通知到“才知道”并向公安机关报案。 </p>
			<p>2.7 用户信息使用,用户在使用过程中发现任何不妥或者不满意之处,有权以邮件或者电话方 式向“才知道”提出申请,要求进行相关信息删除等处理;“才知道”不承担主动删除、销 毁用户信息的责任。 </p>
			<p>2.8 为向用户提供服务,“才知道”将在合理范围内使用用户个人信息、非用户个人信息以及 第三方平台记录信息。用户一旦注册、登录、使用“才知道”服务,将被视为“才知道”已 包括但不限于收集、统计、分析、商业用途等方式使用用户信息。“才知道“对用户信息的 使用无需其他意思表示,无需向用户支付任何费用。 </p>
			<p>2.9 用户同意接受“才知道”通过短信、电话、电子邮件、即时通信的客户端(网页)或者 其他合法方式,向“才知道“用户发送包括订单信息、专题推荐、促销活 动等信息。 </p>
			<p>第3条 辅导服务 </p>
			<p>本协议所称“辅导”特指通过“才知道”提交的话题辅导行为,辅导交互 主体为学员用户和导师用户;学员与导师之间的辅导服务交易必须通过”才知道”网站或者 移动客户端完成,不得私下交易。如有违背,“才知道”有权拒绝支付、罚款、暂停或者关 闭相关用户帐户的权利。 </p>
			<p>3.1 “才知道”网站及客户端上的导师辅导的价格由导师的级别而定。 </p>
			<p>3.2 学员下单时须仔细确认您购买的辅导内容、导师价格及支付方式、联系人、联系方式、 联系地址、约见时间地点等内容;订单一旦生成,即被视为学员向“才知道”导师用户发出 合同要约。 </p>
			<p>3.3 导师收到学员的合同要约后,有权利对学员的约见申请进行审核,行使同意约见或者拒 绝约见的权利;导师确定接受订单并就相关话题向学员提供实际的交谈服务时,方视为学员 与导师用户之间建立了合同关系。 </p>
			<p>3.4 导师与学员双方确定约见后,学员支付交谈费用,视为服务交易确立。在会见约定日 48 小时之前,学员可向“才知道”客服提出解除约见申请,否则视为约见协议成立,学员必须 向导师支付约见费用。 </p>
			<p>3.5 除台风、地震、海啸、洪水、战争、恐怖袭击等国际约定不可抗力因素外,学员迟到半 小时及其以上或者没有赴约,不影响服务交易的成立,导师在此情况下可与该学员协商是否 继续提供交谈服务,但是无论导师拒绝或者愿意继续向该学员提供交谈服务的,学员都必须 向导师支付全部费用;除上述不可抗力因素外,导师迟到半小时以上(包括半小时,即 30 分钟)或者没有赴约,视为服务交易不成立,学员有权要求取消订单,要求退还约见费用。 </p>
			<p>3.6 导师应确保在自身专业领域具备相关专业知识或经验,并向“才知道”提交证明自身在 某一方面具备专业能力的相关材料。交谈范围依据只限于学员的主观描述,导师需尽其所能 利用自身专业领域的所积累的知识和经验,对自己提供给学员的话题以及服务质量负责,不 得提供超出个人领域以及能力的服务。如有违背,由此引发的纠纷完全由导师承担。“才知 道”不对结果是否符合学员用户预期做出保证,不承担任何责任。 </p>
			<p>3.7 导师所提供建议均是个人建议,学员需知晓并同意该建议仅供参考。若将导师用户的建 议作为决策依据,由此可能引发的任何损失后果自负,“才知道”不予以负责,同时学员用 户必须在此声明同意不会向“才知道”、导师用户提出由于采纳导师建议所做决策造成的任 何损失的索赔。 </p>
			<p>3.8 线下交谈时,学员和导师用户自行确定会见场所,必须在公共场合,保证人身安全;未 成年人必须在父母或者监护人陪同之下进行交谈服务;“才知道”不承当任何安全方面的责 
			任。 学员和导师在交谈过程中要文明用语,平等交流。学员(导师)不得在语言和行为上恐吓、 
			威胁或骚扰导师(学员),或者侵犯、试图侵犯导师(学员)的隐私以及身体安全,如发生 以上行为,导师用户有权立即中断交谈,学员无权要求退款;学员有权立即中断交谈,并要 求退款;“才知道”有权终止相关方的所有权利。 </p>
			<p>4第4条 费用和支付</p> <p>4.1 学员用户如需使用“才知道”导师用户提供的收费交谈服务,必须不可撤销地授权委托 </p>
			<p>“才知道”才知道家用户提供完成交谈服务时向导师用户支付咨询费用。 </p>
			<p>4.2 用户须遵守“才知道”定价条款规则。导师拥有设定、调整话题价格的权利,有权按事 先公布的收费方式向学员收取交谈费用,并不可撤销地授权委托“才知道”代为向学员收取 交谈费用。 </p>
			<p>4.3 “才知道”有权调整导师提供交谈服务所涉及的计费方式和收费方式,具体以“才知道” 公布的相关收费规则或“才知道”与导师之间的相关协议为准。 </p>
			<p>4.4 学员必须遵守导师的定价条款。学员在双方约见确立后 24 小时内应将话题费用支付至 “才知道”企业帐户。 </p>
		<p>	4.5 为保证及有效监督服务质量,交易费用由“才知道”代为收取,在服务交易完成 10 个工 作日内由“才知道”向导师所提供的第三方支付帐户或银行账户发放费用。 </p>
		<p>	4.6 学员在约见完成后一个工作日内需以书面形式提出不满意申诉。 4.7 任何用户在交易完成 10 个工作日内有申诉的权利,用户不可实施任何损坏、破坏或操纵 </p>
		<p>	反馈及评价体系的行为。 第 5 条 “才知道”与用户双方的权利和义务 </p>
		<p>	“才知道”提供平台,导师可在“才知道”网站或者移动客户端通过话题的形式向学员提供 有偿的经验交谈服务(以下统称“服务”);学员通过“才知道”网站或者移动客户端有偿获 得导师提供的经验交谈服务(以下统称“服务”)。 
			<p>5.1 作为经验交谈服务平台,“才知道”不直接参与学员与导师的交易。基于交易纠纷、技术 原因等因素,“才知道”保有复制、审查交易过程及内容的权利。</p> 
		<p>	5.2“才知道”展示的服务和价格等信息仅仅是导师的要约邀请,不代表 “才知道”为该服务的提供方或担保方。 </p>
			<p>5.3 “才知道”有义务对相关数据、所有的申请行为以及与咨询有关的其它事项进行审查; </p>
		<p>	同时有权根据不同情况选择保留或删除相关信息或继续、停止对该用户提供服务,并追究相 关法律责任。 </p>
			<p>5.4“才知道”不对以下结果进行保证: 1)不保证平台上任何用户所提供的资料的真实、准确、安全、合法等问题,由此引发的纠 纷,用户自行承担风险。 2)“才知道”所承载的内容均为传播资讯目的,不对其真实性、科学性、严肃性做任何形式 保证。 3)“才知道”不保证导师所提供服务的意愿和能力,以及是否适用于学员的实际需求。 4)“才知道”不保证学员对于导师服务的满意度,以及学员对于导师的评价。 </p>
			<p>5.5 为便于学员与导师的沟通,“才知道”有义务维护平台服务的正常运行,提升及改进技 术。 </p>
			<p>5.6 学员对咨询内容不满意,有权利向“才知道”提出投诉,“才知道”有义务依据情况协 调沟通。如因在“才知道”上发生服务纠纷,“才知道”有义务向双方了解相关信息,进行 沟通、调节;用户通过司法部门或行政部门依照法定程序要求“才知道”提供相关数据,“才 知道”将积极配合并提供有关资料。 </p>
			<p>5.7 为保证服务质量,“才知道”可为用户提供客服电话服务、在线交谈等功能。为保证服务 质量,保障交易过程及内容的可追溯性,“才知道”保有在客服与用户服务过程中全程录音 的权利;“才知道”承诺不主动将录音内容泄露给第三方,但基于交易纠纷、技术原因等因 素,“才知道”保有复制、审查服务过程中录音内容的权利。 </p>
			<p>第6条 用户言行 </p>
			<p>6.1 用户同意在使用“才知道”服务过程中,必须严格遵守以下规则: 1) 遵守中国法律法规、行政规章以及规范性文件; 2) 遵守“才知道”的所有用户协议、通知、协议等文件; 3) 不得为违法、犯罪等目的使用“才知道”网站及其移动客户端; </p>
			<p>4) 不得在“才知道”上传输及发布以下内容:煽动抗拒、破坏宪法及法 律法规实施的言论;煽动颠覆国家政权、破坏国家统一的言论;违背社会风俗和社会道德的 言论;煽动民族仇恨、民族歧视,破坏民族团结的言论; 5) 不得使用任何侮辱或毁谤他人,性骚扰,或对未成年人有不良影响的内容; </p>
			<p>6) 不得散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的行为; 7) 不得利用本站从事洗钱、窃取商业秘密、窃取其他用户个人信息等违法犯罪活动; 8) 不得侵入本站及国家计算机信息系统,不得传播病毒、特洛依木马、定时炸弹等可能对 “才知道”系统造成伤害或影响其正常运转的恶意病毒或程序; 9) 不得在“才知道”平台从事非经“才知道”同意的所有牟利性经营活动; 10) 不得侵犯第三方权利,特别是他人著作权、商标权等知识产权或者合法权利。 </p>
			<p>6.2 若用户有发布违法信息、严重违背社会公德、以及其他违反法律禁止性规定的行为,“才 知道”保有删除各类不符合法律政策或者不真实信息内容而无须通知用户的权利。若用户未 
			遵守以上约定,“才知道”有权立即终止对用户提供服务,采取暂停或者关闭用户账户等措 施。“用户”须对自己的言论和行为负法律责任。 </p>
			<p>第 7 条 知识产权协议 </p>
			<p>7.1 对于用户通过“才知道”发布的任何公开信息,用户同意“才知道”在全世界范围内具 有免费的、永久性的、不可撤销的、非独家的和完全再许可的权利和许可以使用、复制、修 改、改编、出版、翻译、据以创作衍生作品、传播、表演和展示此等内容(整体或部分), 和/或将此等内容编入当前已知的或以后开发的其他任何形式的作品、媒体或技术中的权利。 </p>
			<p>7.2 除法律规定外,未经“才知道”书面等任何形式明确许可,任何单位或个人不得以任何 方式非法地全部或部分复制、转载、引用、链接、抓取或以其他方式使用“才知道”网站及 移动客户端的信息内容,否则,“才知道”有权追究其法律责任。 </p>
			<p>7.3 用户在“才知道”所发布的内容,必须保证已经拥有必要权利或授权以进行该内容的提 供、发布、提交等行为。非经“才知道”同意,用户承诺不得将已发表于“才知道”网站及 客户端的话题等信息,发布或授权同类与“才知道”存在直接竞争关系的网站或客户端使用。</p> 
			<p>7.4 本用户协议已经构成《中华人民共和国著作权法》及相关法律规定的著作财产权等权利 转让书面协议,其效力及于用户在“才知道”网站和移动客户端上发布的任何受著作权法保 护的作品内容。 </p>
			<p>第8条 隐私声明 </p>
			<p>“才知道”非常重视对您(所有用户包括学员及导师)个人隐私的保护。您所提供的个人信 息等资料以及“才知道”所保留的用户个人资料,将受到中国有关隐私的法律以及“才知道” 隐私政策的保护。 </p>
			<p>“才知道”在必要时候需要某些信息才能为您提供所请求的服务,本隐私声明针对这些情况 下的数据收集和使用情况。 </p>
			<p>“才知道”在必要时候需要某些信息才能为您提供所请求的服务,本隐私声明针对这些情况 下的数据收集和使用情况。 </p>
			<p>作为“才知道”用户,如果同意接受“才知道”用户协议及隐私声明,表明您授权“才知道” 对任何您所提供的、或者“才知道”所收集到的信息有权进行处理、传播、使用。 </p>
			本隐私声明适用于“才知道”网站以及移动客户端的所有服务,随着服务的变化,“才知道” 有权对隐私条款不时进行修改更新,且不再另行通知。更新后的隐私声明一旦在网页或者移 动客户端上公布即有效代替原来的隐私声明,您在访问和使用“才知道”的网站或者移动客 户端时,即表示您已同意遵守并接受最新的隐私政策。建议您及时关注隐私条款的变更。 </p>
			<p>8.1 “才知道”隐私信息范围 </p>
			<p>1)个人信息 通常情况下,在使用“才知道”网站或者移动客户端时,您能在匿名的状态下访问“才知 
			道”并获取信息。您在“才知道”网站或者移动客户端注册时所提交的个人信息,包括姓名、 联系方式、通讯地址、第三方帐户信息等。 </p>
			<p>2)帐户信息 学员为获得导师交谈服务时,必须提供财务信息,如姓名、银行帐户、第三方支付帐户信 
			息;导师在服务交易完成后接收费用,必须提供个人财务信息,如姓名、银行帐户、第三方 支付帐户信息等。 </p>
			<p>3)交流信息 “才知道”通过网站、移动客户端或者才知道顾问,在用户与“才知道”客服之间,在学员 与导师之间,通过电话、评价反馈体系、在线留言和日程安排等方式产生信息交流。 </p>
			<p>8.2 信息隐私的保护 “才知道”严格保护您个人信息的安全。我们使用各种安全技术和程序来保护您的个人信息 </p>
			<p>不被未经授权的访问、使用或泄露。</p>
			<p>当用户对网站或者移动客户端的服务表示兴趣时,或者向用户提供服务出现问题或者困难时, 我们使用这些信息来联系用户。 </p>
			<p>未经用户同意,“才知道”不会向第三方提供用户信息,也不会在用户之间传递这些信息。 未经用户同意,“才知道”不会对用户之间的交流信息,包括学员评价、交流文本和图片内 容进行编辑、筛选、篡改。 </p>
			<p>如果有明确证据表明您所提供的信息存在不符合法律政策或者不真实情况,我们有权无须通 知您对信息进行删除、更改等处理。 </p>
			<p>第9条 免责说明 </p>
			<p>9.1 就下列相关事宜的发生,“才知道”不承担任何法律责任: 1)用户应遵守国家的有关法律、法规和行政规章制度。如有用户违反国家法律法规或本用 户协议,“才知道”有权停止向用户提供任务而不需要承担任何责任,如导致“才知道”遭 受任何损害或者遭受任何来自第三方的纠纷、诉讼、索赔要求等,用户须向“才知道”赔偿 相应的损失,用户需对其违反用户协议所产生的一切后果承担全部法律责任。 2)由于您将用户密码告知他人或与他人共享注册帐户,由此导致的任何个人信息的泄露, 或其他非因“才知道”原因导致的个人信息的泄露; 
			3)根据法律规定或政府相关政策要求提供您的个人信息; 4)任何通过黑客攻击、电脑病毒侵入等非法截取、访问等方式从我们网站上获取的信息; 5)因台风、地震、海啸、洪水、停电、战争、恐怖袭击等不可抗力因素导致的任何后果; </p>
			<p>9.2 本服务涉及到互联网及移动通讯等服务,可能受各个环节不稳定因素的影响,比如不可 抗力、计算机病毒或者黑客攻击等造成的服务中断或不能满足用户要求的风险,用户须理解 和认可,并承担以上风险。“才知道”对服务的及时性、安全性、准确性不作担保,对因此 导致用户不能接收信息,或者传递错误等问题不承担任何责任。 </p>
			<p>9.3 如“才知道”的系统发生故障影响到本服务的正常运行,“才知道”承诺第一时间内与相 关单位配合,及时处理进行修复。但用户因此而产生的经济损失,“才知道”不承担责任。 此外,“才知道”保留不经事先通知为维修保养、升级或其他目的暂停本服务任何部分的权 利。 </p>
			<p>第 10 条 适用法律框架以及纠纷解决途径 </p>
			<p>本协议的订立、执行和解释及争议的解决均应适用在中华人民共和国大陆地区适用之有效法 律。如发生本协议与适用之法律相抵触时,则这些协议将完全按法律规定重新解释,而其它 有效协议继续有效。如缔约方就本协议内容或其执行发生任何争议,双方应尽力友好协商解 决;协商不成时,任何一方均可向有管辖权的中华人民共和国大陆地区法院提起诉讼。 </p>
			<p>第 11 条 其他 11.1 如果您在中国大陆以外的国家或地区访问或使用“才知道”网站或移动客户端,您有责 任遵守所在辖区内有关在线行为和可接受内容的法律。</p>
			<p>11.2 本服务的所有权、运作权和一切解释权归“才知道”所有。“才知道”有权在必要时修 改用户协议,并通过网站或者客户端发布修改变更,且不再另行通知。如果在更改生效后用 户继续使用服务,则视为您接受用户协议的变动并遵守最新用户协议。 </p>
			


		</div>
		
		<div class="cancel-box-btn">
			<button  onclick="hideThis()" >确定</button>
		</div>
		
	</div>
	
</div>
<!--悬浮框	end-->
</body>
</html>