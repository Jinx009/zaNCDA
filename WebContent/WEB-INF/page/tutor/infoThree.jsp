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
var topic2 = "";
var topic3 = "";
var topic4 = "";
var topic5 = "";
var topic6 = "";
var topic7 = "";
var topic8 = "";
var topic9 = "";
var topic10 = "";
var topic11 = "";
var topic12 = "";
var topic13 = "";
var tradeData;
var topicArray = new Array();
$(function(){
	topicArray[4]="兴趣还是专业，我该如何选择职业方向？";
	topicArray[5]="有一个看似美好的机会摆在面前，走还是留？";
	topicArray[6]="管理路线、专业路线，哪一个更加适合我？";
	topicArray[7]="感受到了职场瓶颈，下一步该怎么办？";
	topicArray[8]="遇到难懂的上司，如何才能抓住老板的心？";
	topicArray[9]="空降兵苦恼：怎样破冰，快速开展工作？";
	topicArray[10]="我的能力很强，但是不被认可，该怎么办？";
	topicArray[11]="面对复杂的职场关系，如何提升自己的情商？";
	topicArray[12]="如何能够了解职业发展道路上的不同能力要求？";
	topicArray[13]="现任岗位上，提升哪些能力能让我有更突出的表现？";
	topicArray[14]="职业转型期需要的关键能力有哪些？";
	topicArray[15]="如何处理越来越大的职场压力？";
	topicArray[16]="我是否适合创业？";
	
	topic2 = $("#topic2").val();
	topic2 = parseInt(topic2);
	for(var i = 1;i<14;i++){
		if(0==i){
			topicId = $("#topicValue").val();
			topicId = parseInt(topicId);
			if((topicId>=4&&topicId<=7)||16==topicId){
				$("#to"+topicId).addClass("choice-question-select");
				$("#to"+topicId).html(topicArray[parseInt(topicId)]+"<span>&radic;</span>");
			}else if(topicId>=8&&topicId<=11){
				$("#to"+topicId).addClass("choice-question-select");
				$("#to"+topicId).html(topicArray[parseInt(topicId)]+"<span>&radic;</span>");
			}else if(topicId>=12&&topicId<=15){
				$("#to"+topicId).addClass("choice-question-select");
				$("#to"+topicId).html(topicArray[parseInt(topicId)]+"<span>&radic;</span>");
			}
			$("#topicDiv1").css("display","block");
		}else{
			topicId = $("#topic"+i).val();
			topicId = parseInt(topicId);
			if((topicId>=4&&topicId<=7)||16==topicId){
				$("#to"+topicId).addClass("choice-question-select");
				$("#to"+topicId).html(topicArray[parseInt(topicId)]+"<span>&radic;</span>");
			}else if(topicId>=8&&topicId<=11){
				$("#to"+topicId).addClass("choice-question-select");
				$("#to"+topicId).html(topicArray[parseInt(topicId)]+"<span>&radic;</span>");
			}else if(topicId>=12&&topicId<=15){
				$("#to"+topicId).addClass("choice-question-select");
				$("#to"+topicId).html(topicArray[parseInt(topicId)]+"<span>&radic;</span>");
			}
			$("#topicDiv1").css("display","block");
		}
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
				if("1"==res.errmsg[i].parentId){
					htmlStr += "<option value="+res.errmsg[i].id+" >"+res.errmsg[i].tradeName+"</option>";
				}
			}
			$("#trade1").html(htmlStr);
			$("#trade2").html(htmlStr);
			$("#trade3").html(htmlStr);
			$("#area1").html(htmlStr);
			$("#area2").html(htmlStr);
			$("#area3").html(htmlStr);
			
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
        
        for(i=0;i<element1.length;i++){
          if(t1==element1.options[i].value){  
              element1.options[i].selected=true; 
          }  
        }  
	}
	 if("0"!=a1){
     	var element1 = document.getElementById("area1");   
         
         for(i=0;i<element1.length;i++){
           if(a1==element1.options[i].value){  
               element1.options[i].selected=true; 
           }  
         }  
     }
	if("0"!=t2){
		var element1 = document.getElementById("trade2");   
        
        for(i=0;i<element1.length;i++){
          if(t2==element1.options[i].value){  
              element1.options[i].selected=true; 
          }  
        }  
	}
   if("0"!=a2){
     	var element1 = document.getElementById("area2");   
         
         for(i=0;i<element1.length;i++){
           if(a2==element1.options[i].value){  
               element1.options[i].selected=true; 
           }  
         }  
     }
	if("0"!=t3){
		var element1 = document.getElementById("trade3");   
        
        for(i=0;i<element1.length;i++){
          if(t3==element1.options[i].value){  
              element1.options[i].selected=true; 
          }  
        }  
	}
   if("0"!=a3){
      	var element1 = document.getElementById("area3");   
          
          for(i=0;i<element1.length;i++){
            if(a3==element1.options[i].value){  
                element1.options[i].selected=true; 
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
	$("#trade1").attr("disabled",false);
	$("#trade2").attr("disabled",false);
	$("#trade3").attr("disabled",false);
	$("#area1").attr("disabled",false);
	$("#area2").attr("disabled",false);
	$("#area3").attr("disabled",false);
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
<input type="hidden" id="topic2" value="${topic2 }" >
<input type="hidden" id="topic3" value="${topic3 }" >
<input type="hidden" id="topic4" value="${topic4 }" >
<input type="hidden" id="topic5" value="${topic5 }" >
<input type="hidden" id="topic6" value="${topic6 }" >
<input type="hidden" id="topic7" value="${topic7 }" >
<input type="hidden" id="topic8" value="${topic8 }" >
<input type="hidden" id="topic9" value="${topic9 }" >
<input type="hidden" id="topic10" value="${topic10 }" >
<input type="hidden" id="topic11" value="${topic11 }" >
<input type="hidden" id="topic12" value="${topic12 }" >
<input type="hidden" id="topic13" value="${topic13 }" >

<input type="hidden" id="t1" value="${t1 }" >
<input type="hidden" id="t2" value="${t2 }" >
<input type="hidden" id="t3" value="${t3 }" >
<input type="hidden" id="a1" value="${a1 }" >
<input type="hidden" id="a2" value="${a2 }" >
<input type="hidden" id="a3" value="${a3 }" >
<div class="information-question none" style="display: none;" id="topicDiv1" >
	<div class="choice-title"><p>擅长的辅导场景和主题</p></div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">选择场景</span>
		<select class="register-select-long"  onchange="changeData('1')"   id="s1"   >
			<option  selected="selected"  value="1" >职业选择</option>
			<option value="2">职场能力</option>
			<option value="3">职业发展</option>
		</select>
	</div>
	<ul class="choice-question select-answer">
		<li id="to4"  >自我测评和岗位/行业评估</li>
		<li id="to5"  >找到正确的求职渠道</li>
		<li id="to6"  >如何制作和投递完美的简历</li>
		<li id="to7"  >面试模拟舱：看到真实的自己</li>
	</ul>
</div>
<div class="information-question none"  style="display: none;"   id="topicDiv2" >
	<div class="choice-title"><p>请填写您最擅长的主题</p></div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">选择场景</span>
		<select class="register-select-long"  onchange="changeData('2')"   id="s2"  >
			<option value="1" >职业选择</option>
			<option  selected="selected" value="2">职场能力</option>
			<option value="3">职业发展</option>
		</select>
	</div>
	<ul class="choice-question select-answer">
		<li id="to8" >如何快速理解角色和融入团队</li>
		<li id="to9"  >管理老板和关键决策人的关系</li>
		<li id="to10"  >如何处理冲突和提升人机沟通</li>
		<li id="to11"   >压力的管理和纾解</li>
	</ul>
</div>
<div class="information-question none"  style="display: none;"  id="topicDiv3" >
	<div class="choice-title"><p>请填写您最擅长的主题</p></div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">选择场景</span>
		<select class="register-select-long"   onchange="changeData('3')"  id="s3" >
			<option value="1" >职业选择</option>
			<option value="2">职场能力</option>
			<option value="3" selected="selected">职业发展</option>
		</select>
	</div>
	<ul class="choice-question select-answer">
		<li id="to12"   >了解公司的架构和晋级之路<span>&radic;</span></li>
		<li id="to13"   >快速建立短期目标并积累经验和人脉</li>
		<li id="to14"   >如何在职场提升关键技术和管理能力</li>
		<li id="to15"   >为自己的市场价值添分的诀窍</li>
	</ul>
</div>
<div class="information-question">
	<div class="choice-title"><p>擅长行业</p></div>
	<input type="hidden" value="" >
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">擅长行业1</span>
		<select class="register-select-long" id="trade1" disabled="disabled" >
			<option value="" selected="selected"></option>
		</select>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">擅长行业2</span>
		<select class="register-select-long"  id="trade2" disabled="disabled">
			<option value="" selected="selected"></option>
		</select>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">擅长行业3</span>
		<select class="register-select-long"  id="trade3" disabled="disabled">
			<option value="" selected="selected"></option>
		</select>
	</div>
</div>
<div class="information-question">
	<div class="choice-title"><p>擅长领域</p></div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">擅长职能1</span>
		<select class="register-select-long" id="area1" disabled="disabled">
			<option value="" selected="selected"></option>
		</select>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">擅长职能2</span>
		<select class="register-select-long" id="area2" disabled="disabled">
			<option value="" selected="selected"></option>
		</select>
	</div>
	<div class="register-inp register-inp-top">
		<span class="register-inp-text">擅长职能3</span>
		<select class="register-select-long" id="area3" disabled="disabled">
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