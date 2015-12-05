<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新建导师</title>
<link rel="stylesheet" href="/sp/dist/css/bootstrap.css" >
<link rel="stylesheet" href="/sp/css/main.css" >

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/dist/js/bootstrap.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript" src="/sp/js/date/WdatePicker.js" ></script>
<script type="text/javascript" src="/sp/dist/jquery.form.js" ></script>
<script type="text/javascript">
var dataJson;
var tradeData = new Array(),areaData = new Array();

$(function(){
	getTrade();
	loadForms();
	getData()
})

/**
 * 职业特色
 */
function getData(){
	
	$.ajax({
		url:"/topic/data/list.html?time="+getRandom(),
		type:"GET",
		dataType:"json",
		success:function(res){
			if("success"==res.result){
				var htmlStr1 = "",htmlStr2 = "",htmlStr3 = "";
				for(var i = 0;i<res.errmsg.length;i++){
					if("1"==res.errmsg[i].parentId){
						htmlStr1 += "<div><input type='radio' id=radio"+res.errmsg[i].id+" name='topicId' value="+res.errmsg[i].id+" />"+res.errmsg[i].name+"</div>";
					}
					if("2"==res.errmsg[i].parentId){
						htmlStr2 += "<div><input type='radio' id=radio"+res.errmsg[i].id+"  name='topicId' value="+res.errmsg[i].id+" />"+res.errmsg[i].name+"</div>";
					}
					if("3"==res.errmsg[i].parentId){
						htmlStr3 += "<div><input type='radio' id=radio"+res.errmsg[i].id+"  name='topicId' value="+res.errmsg[i].id+" />"+res.errmsg[i].name+"</div>";
					}
				}
				$("#topicDiv1").html(htmlStr1);
				$("#topicDiv2").html(htmlStr2);
				$("#topicDiv3").html(htmlStr3);
				$("#radio4").attr("checked","checked");
			}
		}
	})
}




/**
 * 擅长领域
 */
function getTrade(){
	$.ajax({
		url:"/trade/data/list.html",
		type:"POST",
		dataType:"json",
		success:function(res){
			var htmlStr = "",htmlStr1 = "";
			
			for(var i = 0;i<res.errmsg.length;i++){
				if("0"==res.errmsg[i].parentId){
					tradeData.push(res.errmsg[i]);
					htmlStr += "<option value="+res.errmsg[i].id+" >";
					htmlStr += res.errmsg[i].tradeName;
					htmlStr += "</option>";
				}else{
					areaData.push(res.errmsg[i]);
					htmlStr1 += "<option value="+res.errmsg[i].id+" >";
					htmlStr1 += res.errmsg[i].tradeName;
					htmlStr1 += "</option>";
				}
			}
			
			$("#trade1").html(htmlStr);
			$("#trade2").html(htmlStr);
			$("#trade3").html(htmlStr);
			
			var trade1Value = $("#trade1").val();
			var trade2Value = $("#trade2").val();
			var trade3Value = $("#trade3").val();
			
			var areaHtml1 = "",areaHtml2 = "",areaHtml3 = "";
			for(var i = 0;i<areaData.length;i++){
				if(trade1Value==areaData[i].parentId){
					areaHtml1  += "<option value="+areaData[i].id+" >"+areaData[i].tradeName+"</option>";
				}
				if(trade2Value==areaData[i].parentId){
					areaHtml2  += "<option value="+areaData[i].id+" >"+areaData[i].tradeName+"</option>";
				}
				if(trade3Value==areaData[i].parentId){
					areaHtml3  += "<option value="+areaData[i].id+" >"+areaData[i].tradeName+"</option>";
				}
			}
			$("#area1").html(areaHtml1);
			$("#area2").html(areaHtml2);
			$("#area3").html(areaHtml3);
			
		}
	})
}

function changeTrade(index){
	var trade = $("#trade"+index).val();
	var htmlStr = "";
	for(var i = 0;i<areaData.length;i++){
		if(trade==areaData[i].parentId){
			htmlStr += "<option value="+areaData[i].id+" >"+areaData[i].tradeName+"</option>";
		}
	}
	$("#area"+index).html(htmlStr);
}

/**
 * 更换头像
 */
function editImgs(){
	  $("#form").submit();
}

/**
* ajax form 表单
*/
function loadForms(){
	 $("#form").ajaxForm({
		 success :function(data){
			if("success"==data.result){
				$("#imgId").attr("src",data.errmsg);
				$("#photo").val(data.errmsg);
			}
		 },
		 complete:function(t){
		 }
	});
}

function saveInfo(){
	var realname = $("#realname").val();
	var pwd = $("#pwd").val();
	var username = $("#username").val();
	var photoPath = $("#imgId").attr("src");
	var sex = $("#sex").val();
	var mobilePhone = $("#mobilePhone").val();
	var aptitude = $("#aptitude").val();
	var birth = $("#birth").val();
	var classPrice = $("#classPrice").val();
	var trade1 = $("#trade1").val();
	var trade2 = $("#trade2").val();
	var trade3 = $("#trade3").val();
	var area1 = $("#area1").val();
	var area2 = $("#area2").val();
	var area3 = $("#area3").val();
	var isOnline = $("#isOnline").val();
	var bankAccount = $("#bankAccount").val();
	var bankAccount1 = $("#bankAccount1").val();
	var bankName = $("#bankName").val();
	var idCard = $("#idCard").val();
	var email = $("#email").val();
	var workYears = $("#workYears").val();
	var qq = $("#qq").val();
	var facePrice = $("#facePrice").val();
	var mobileStatus = 0,videoStatus = 0,faceStatus = 0;
	if("checked"==$("#mobileStatus").is(":checked")||true==$("#mobileStatus").is(":checked")){
		mobileStatus = 1;
	}
	if("checked"==$("#videoStatus").is(":checked")||true==$("#videoStatus").is(":checked")){
		videoStatus = 1;
	}
	if("checked"==$("#faceStatus").is(":checked")||true==$("#faceStatus").is(":checked")){
		faceStatus = 1;
	}
	var topicId = $('input[name="topicId"]:checked').val();
	var personalIntroduction = $("#personalIntroduction").val();

	var params = "username="+username+"&photoPath="+photoPath+"&sex="+sex+"&mobilePhone="+mobilePhone+"&aptitude="+aptitude+"&birth="+
				birth+"&classPrice="+classPrice+"&trade1="+trade1+"&trade2="+trade2+"&trade3="+trade3+"&area1="+area1+"&area2="+
				area2+"&area3="+area3+"&isOnline="+isOnline+"&bankAccount="+bankAccount+"&bankName="+bankName+"&idCard="+idCard+
				"&email="+email+"&workYears="+workYears+"&qq="+qq+"&mobileStatus="+mobileStatus+"&faceStatus="+faceStatus+"&videoStatus="+
				videoStatus+"&personalIntroduction="+personalIntroduction+"&topicId="+topicId+"&realname="+realname+"&pwd="+pwd+"&facePrice="+facePrice;
	if(bankAccount!=bankAccount1){
		alert("银行卡号确认信息不一致!");
	}else{
		$.ajax({
			url:"/tutor/data/save.html",
			type:"POST",
			data:params,
			dataType:"json",
			success:function(res){
				if("success"==res.result){
					alert("操作成功!");
				}
			}
		})
	}
}
</script>

<style type="text/css">
.index-row{
	width: 96%;
	margin-left: 2%;
}
.index-col-md-2{
	width: 10%;
}
.index-col-md-10{
	width: 90%;
}
.img-width{
	width: 200px;
	height: 200px;
}
</style>
</head>
<body>
	<div class="space-div-1" ></div>
	<div class="row index-row" >
		<div class="col-md-2 index-col-md-2" >
			<div class="list-group">
			 <a href="/admin/page/tutor.html" class="list-group-item active">导师管理</a>
			    <a href="/admin/page/customer.html" class="list-group-item">顾客管理</a>
			  <a href="/admin/page/order.html" class="list-group-item">约谈管理</a>
			  <a href="/admin/page/util.html" class="list-group-item">资料管理</a>
			  <a href="/admin/page/pay.html" class="list-group-item">兑付管理</a>
			  <a href="/admin/loginOut.html" class="list-group-item">登出</a>
			</div>
		</div>
		<div class="col-md-10 index-col-md-10" >
			<div class="btn-group" role="group" aria-label="...">
			  <button type="button" class="btn btn-default" onclick="openUrl('/admin/page/tutor.html')" >返回首页</button>
			  <button type="button" class="btn btn-info" >新建导师</button>
			</div>
			<div class="space-div-4" ></div>
			<table class="table table-bordered" >
				<tbody>
					<tr>
						<th>账号：</th>
						<td> <input type="text" class="form-control" id="username"  value=""  ></td>
						<th>密码:</th>
						<td> <input type="text" class="form-control" id="pwd"  value=""  ></td>
					</tr>
					<tr>
						<th>姓名：</th>
						<td> <input type="text" class="form-control" id="realname"  value=""  ></td>
						<th>头像:</th>
						<td><img  src="" class="img-width" id="imgId" ></td>
					</tr>
					<tr>
						<th>性别：</th>
						<td>
							 <input type="hidden" id="genderValue" value="" >
					     	 <input type="text" class="form-control"  id="sex" value="" >
					    </td>
						<td colspan="2" >
							<form action="/uploadImg.html" method="post" id="form" enctype="multipart/form-data"  >
								<div class="form-group">
								    <div class="col-sm-6">
								     	 <input type="file"  name="file" id="file" class="form-control" />
								    </div>
								    <label class="col-sm-6 control-label btn btn-info" onclick="editImgs()" >上传</label>
						  		</div>
								<input type="hidden" id="photo" >
							</form>
						</td>
					</tr>
					<tr>
						<th>电话：</th>
						<td><input class="form-control" value="" id="mobilePhone"  ></td>
						<th>认证资质：</th>
						<td><textarea  id="aptitude"  style="height: 80px;" class="form-control" ></textarea></td>
					</tr>
					<tr>
						<th>生日：</th>
						<td><input class="form-control" value="" id="birth"  onClick="WdatePicker()" readonly="readonly" ></td>
						<th>单价：</th>
						<td><input type="text" id="classPrice"  class="form-control" value="" placeholder="元/次"  ></td>
					</tr>
					<tr>
						<th>行业1：</th>
						<td> <select class="form-control" id="trade1"  onchange="changeTrade('1')"  ></select></td>
						<th>有效：</th>
						<td>
							<select class="form-control" id="isOnline" >
					     	 	<option value="1" >是</option>
					     	 	<option value="2" >否</option>
					     	 </select>
						</td>
					</tr>
					<tr>
						<th>行业2：</th>
						<td> <select class="form-control" id="trade2" onchange="changeTrade('2')"   ></select></td>
						<th>银行卡号：</th>
						<td><input type="text" class="form-control" value="" id="bankAccount"  ></td>
					</tr>
					<tr>
						<th>行业3：</th>
						<td> <select class="form-control" id="trade3" onchange="changeTrade('3')" ></select></td>
						<th>银行卡号：</th>
						<td><input type="text" class="form-control" value="" id="bankAccount1"  ></td>
					</tr>
					<tr>
						<th>擅长领域1：</th>
						<td> <select class="form-control" id="area1" name="area1" ></select></td>
						<th>银行名称：</th>
						<td><input type="text" id="bankName" class="form-control" value="" /></td>
					</tr>
					<tr>
						<th>擅长领域2：</th>
						<td> <select class="form-control" id="area2" name="area2" ></select></td>
						<th>身份证号：</th>
						<td> <input type="text" id="idCard" value="" class="form-control"  ></td>
					</tr>
					<tr>
						<th>擅长领域3：</th>
						<td> <select class="form-control" id="area3" name="area3" ></select></td>
						<th>邮箱：</th>
						<td> <input type="text" id="email" value="" class="form-control"  ></td>
					</tr>
					<tr>
						<th>工作年限：</th>
						<td>  <input class="form-control" value="1" id="workYears" ></td>
						<th>qq：</th>
						<td>  <input type="text" id="qq" value="" class="form-control"  ></td>
					</tr>
					<tr>
						<th>约谈价格</th>
						<td> <input type="text" id="facePrice"  value="0" class="form-control"  ></td>
						<th>openid：</th>
						<td>  <input type="text" id="openid" readonly="readonly" value="" class="form-control"  ></td>
					</tr>
					<tr>
						<th colspan="4" >辅助方式：</th>
					</tr>
					<tr>
						<td>电话约谈</td>
						<td colspan="3" >
							<input type="hidden" id="mobileValue" value="" >	
							<input type="checkbox" class="checkbox" name="talkPhoneCall" id="mobileStatus" >
						</td>
					</tr>
					<tr>
						<td>当面约谈</td>
						<td colspan="3" >
							<input type="hidden" id="faceValue" value="" >	
							<input type="checkbox" class="checkbox" name="faceToFace" id="faceStatus" >
						</td>
					</tr>
					<tr>
						<td>视频约谈</td>
						<td colspan="3" >
							<input type="hidden" id="videoValue" value="" >	
							<input type="checkbox" class="checkbox" name="talkVideoChat" id="videoStatus" >
						</td>
					</tr>
					<tr>
						<th colspan="4" >特色管理：<input type="hidden" value="" > </th>
					</tr>
					<tr>
						<td>职业选择</td>
						<td colspan="3" ><div id="topicDiv1" ></div></td>
					</tr>
					<tr>
						<td>职场关系</td>
						<td colspan="3" ><div id="topicDiv2" ></div></td>
					</tr>
					<tr>
						<td>职业发展</td>
						<td colspan="3" ><div id="topicDiv3" ></div></td>
					</tr>
					<tr>
						<th>导师专长自我介绍:</th>
						<td colspan="3" ><textarea class="form-control" id="personalIntroduction" ></textarea></td>
					</tr>
					<tr>
						<td colspan="4" >
							<a class="btn btn-info"  onclick="saveInfo()" >保存</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
</body>
</html>