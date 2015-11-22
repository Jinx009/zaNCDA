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
<script type="text/javascript">
var topicId = "";
var tradeData;
var topicArray = new Array();
$(function(){
	topicArray[4]="自我测评和岗位/行业评估";
	topicArray[5]="找到正确的求职渠道";
	topicArray[6]="如何制作和投递完美的简历";
	topicArray[7]="面试模拟舱：看到真实的自己";
	topicArray[8]="如何快速理解角色和融入团队";
	topicArray[9]="管理老板和关键决策人的关系";
	topicArray[10]="如何处理冲突和提升人机沟通";
	topicArray[11]="压力的管理和纾解";
	topicArray[12]="了解公司的架构和晋级之路";
	topicArray[13]="快速建立短期目标并积累经验和人脉";
	topicArray[14]="如何在职场提升关键技术和管理能力";
	topicArray[15]="为自己的市场价值添分的诀窍";
	
	topicId = $("#topicValue").val();
	topicId = parseInt(topicId);
	if(topicId>=4&&topicId<=7){
		$("#topicDiv1").css("display","block");
		$("#to"+topicId).addClass("choice-question-select");
		$("#to"+topicId).html(topicArray[parseInt(topicId)]+"<span>&radic;</span>");
	}else if(topicId>=8&&topicId<=11){
		$("#topicDiv2").css("display","block");
		$("#to"+topicId).addClass("choice-question-select");
		$("#to"+topicId).html(topicArray[parseInt(topicId)]+"<span>&radic;</span>");
	}else if(topicId>=12&&topicId<=15){
		$("#topicDiv3").css("display","block");
		$("#to"+topicId).addClass("choice-question-select");
		$("#to"+topicId).html(topicArray[parseInt(topicId)]+"<span>&radic;</span>");
	}else{
		changeData('1');
	}
	
	$.ajax({
		url:"/trade/data/list.html?time="+getRandom(),
		type:"GET",
		dataType:"json",
		success:function(res){
			tradeData = res.errmsg;
			var htmlStr = "";
			for(var i = 0;i<res.errmsg.length;i++){
				if("0"==res.errmsg[i].parentId){
					htmlStr += "<option value="+res.errmsg[i].id+" >"+res.errmsg[i].tradeName+"</option>";
				}
			}
			$("#trade1").html(htmlStr);
			$("#trade2").html(htmlStr);
			$("#trade3").html(htmlStr);
			
			changeBaseTrade();
		}
	})
})

/**
 * 基础数据更改
 */
function changeBaseTrade(){
	var t1 = $("#t1").val();
	var t2 = $("#t2").val();
	var t3 = $("#t3").val();
	var a1 = $("#a1").val();
	var a2 = $("#a2").val();
	var a3 = $("#a3").val();
	
	if("0"!=t1){
		var element1 = document.getElementById("trade1");   
        
        for(i=0;i<element1.length;i++)
        {
          if(t1==element1.options[i].value)
          {  
              element1.options[i].selected=true; 
          }  
        }  
        var htmlStr = "";
        for(var i = 0;i<tradeData.length;i++){
        	if(t1==tradeData[i].parentId){
        		htmlStr += "<option value="+tradeData[i].id+" >"+tradeData[i].tradeName+"<option>";
        	}
        	$("#area1").html(htmlStr);
        }
        if("0"!=a1){
        	var element1 = document.getElementById("a1");   
            
            for(i=0;i<element1.length;i++)
            {
              if(a1==element1.options[i].value)
              {  
                  element1.options[i].selected=true; 
              }  
            }  
        }
	}
	if("0"!=t2){
		var element1 = document.getElementById("trade2");   
        
        for(i=0;i<element1.length;i++)
        {
          if(t2==element1.options[i].value)
          {  
              element1.options[i].selected=true; 
          }  
        }  
        var htmlStr = "";
        for(var i = 0;i<tradeData.length;i++){
        	if(t2==tradeData[i].parentId){
        		htmlStr += "<option value="+tradeData[i].id+" >"+tradeData[i].tradeName+"<option>";
        	}
        	$("#area2").html(htmlStr);
        }
        if("0"!=a2){
        	var element1 = document.getElementById("a2");   
            
            for(i=0;i<element1.length;i++)
            {
              if(a2==element1.options[i].value)
              {  
                  element1.options[i].selected=true; 
              }  
            }  
        }
	}
	if("0"!=t3){
		var element1 = document.getElementById("trade3");   
        
        for(i=0;i<element1.length;i++)
        {
          if(t3==element1.options[i].value)
          {  
              element1.options[i].selected=true; 
          }  
        }  
        var htmlStr = "";
        for(var i = 0;i<tradeData.length;i++){
        	if(t3==tradeData[i].parentId){
        		htmlStr += "<option value="+tradeData[i].id+" >"+tradeData[i].tradeName+"<option>";
        	}
        	$("#area3").html(htmlStr);
        }
        if("0"!=a3){
        	var element1 = document.getElementById("a3");   
            
            for(i=0;i<element1.length;i++)
            {
              if(a3==element1.options[i].value)
              {  
                  element1.options[i].selected=true; 
              }  
            }  
        }
	}
}

/**
 * 切换主题
 */
function changeData(id){
	$("#topicDiv1").css("display","none");
	$("#topicDiv2").css("display","none");
	$("#topicDiv3").css("display","none");
	
	var value = $("#s"+id).val();
	$("#topicDiv"+value).css("display","block");
}

/**
 * 更换主题
 */
function getTopic(id){
	topicId = id;
	for(var i = 4;i<=15;i++){
		$("#to"+i).removeClass("choice-question-select");
		$("#to"+i).html(topicArray[i]);
	}
	$("#to"+id).addClass("choice-question-select");
	$("#to"+id).html(topicArray[parseInt(id)]+"<span>&radic;</span>");
}

/**
 * 变更领域
 */
function changeTrade(id){
	var value = $("#trade"+id).val();
	var htmlStr = "";
	for(var i = 0;i<tradeData.length;i++){
		if(value==tradeData[i].parentId){
			htmlStr += "<option value="+tradeData[i].id+" >"+tradeData[i].tradeName+"</option>";
		}
	}
	$("#area"+id).html(htmlStr);
}

function saveData(){
	var trade1 = $("#trade1").val();
	var trade2 = $("#trade2").val();
	var trade3 = $("#trade3").val();
	var area1 = $("#area1").val();
	var area2 = $("#area2").val();
	var area3 = $("#area3").val();
	var topic = topicId;
	
	var params = "trade1="+trade1+"&trade2="+trade2+"&trade3="+trade3+
				 "&area1="+area1+"&area2="+area2+"&area3="+area3+"&topic="+topic;
	$.ajax({
		url:"/tutor/data/saveInfoThree.html",
		type:"POST",
		data:params,
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				location.href = "/tutor/page/infoFour.html";
			}
		}
	})
}
</script>
</head>
<body class="tutor-bg">
<div class="nav-title">个人信息（三）<div class="close" onclick="openUrl('/tutor/page/index.html')" >&Chi;</div></div>
<input type="hidden" id="topicValue" value="${topicId }" >
<input type="hidden" id="t1" value="${t1 }" >
<input type="hidden" id="t2" value="${t2 }" >
<input type="hidden" id="t3" value="${t3 }" >
<input type="hidden" id="a1" value="${a1 }" >
<input type="hidden" id="a2" value="${a2 }" >
<input type="hidden" id="a3" value="${a3 }" >
<div class="information-question none" style="display: none;" id="topicDiv1" >
	<div class="choice-title"><p>请填写您最擅长的主题</p></div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">选择场景</span>
		<select class="register-select-long"  id="s1" onchange="changeData('1')"  >
			<option  selected="selected"  value="1" >职业选择</option>
			<option value="2">职场能力</option>
			<option value="3">职业发展</option>
		</select>
	</div>
	<ul class="choice-question select-answer">
		<li id="to4" onclick="getTopic('4')"  >自我测评和岗位/行业评估<span>&radic;</span></li>
		<li id="to5"  onclick="getTopic('5')" >找到正确的求职渠道</li>
		<li id="to6" onclick="getTopic('6')"  >如何制作和投递完美的简历</li>
		<li id="to7"  onclick="getTopic('7')" >面试模拟舱：看到真实的自己</li>
	</ul>
</div>
<div class="information-question none"  style="display: none;"  id="topicDiv2" >
	<div class="choice-title"><p>请填写您最擅长的主题</p></div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">选择场景</span>
		<select class="register-select-long"  id="s2" onchange="changeData('2')" >
			<option value="1" >职业选择</option>
			<option  selected="selected" value="2">职场能力</option>
			<option value="3">职业发展</option>
		</select>
	</div>
	<ul class="choice-question select-answer">
		<li id="to8" onclick="getTopic('8')" class="choice-question-select">如何快速理解角色和融入团队</li>
		<li id="to9" onclick="getTopic('9')"  >管理老板和关键决策人的关系</li>
		<li id="to10" onclick="getTopic('10')"  >如何处理冲突和提升人机沟通</li>
		<li id="to11" onclick="getTopic('11')"  >压力的管理和纾解</li>
	</ul>
</div>
<div class="information-question none"  style="display: none;"  id="topicDiv3" >
	<div class="choice-title"><p>请填写您最擅长的主题</p></div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">选择场景</span>
		<select class="register-select-long" id="s3" onchange="changeData('3')" >
			<option value="1" >职业选择</option>
			<option value="2">职场能力</option>
			<option value="3" selected="selected">职业发展</option>
		</select>
	</div>
	<ul class="choice-question select-answer">
		<li id="to12" onclick="getTopic('12')"   class="choice-question-select">了解公司的架构和晋级之路<span>&radic;</span></li>
		<li id="to13" onclick="getTopic('13')"  >快速建立短期目标并积累经验和人脉</li>
		<li id="to14" onclick="getTopic('14')"  >如何在职场提升关键技术和管理能力</li>
		<li id="to15" onclick="getTopic('15')"  >为自己的市场价值添分的诀窍</li>
	</ul>
</div>
<div class="information-question">
	<div class="choice-title"><p>擅长行业</p></div>
	<input type="hidden" value="" >
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">擅长行业1</span>
		<select class="register-select-long" id="trade1" onclick="changeTrade('1')" >
			<option value="" selected="selected"></option>
		</select>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">擅长行业2</span>
		<select class="register-select-long"  id="trade2" onclick="changeTrade('2')">
			<option value="" selected="selected"></option>
		</select>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">擅长行业3</span>
		<select class="register-select-long"  id="trade3" onclick="changeTrade('3')">
			<option value="" selected="selected"></option>
		</select>
	</div>
</div>
<div class="information-question">
	<div class="choice-title"><p>擅长领域</p></div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">擅长领域1</span>
		<select class="register-select-long" id="area1">
			<option value="" selected="selected"></option>
		</select>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">擅长领域2</span>
		<select class="register-select-long" id="area2">
			<option value="" selected="selected"></option>
		</select>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">擅长领域3</span>
		<select class="register-select-long" id="area3">
			<option value="" selected="selected"></option>
		</select>
	</div>
</div>
<div class="teacher-two-btn">
	<button class="teacher-orange-btn" onclick="openUrl('/tutor/page/infoTwo.html')" >上一步</button>
	<button class="teacher-orange-btn" onclick="saveData()" >下一步</button>
</div>
</body>
</html>