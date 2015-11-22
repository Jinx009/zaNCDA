<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台主页</title>
<link rel="stylesheet" href="/sp/dist/css/bootstrap.css" >
<link rel="stylesheet" href="/sp/css/main.css" >

<script type="text/javascript" src="/sp/dist/jquery.js" ></script>
<script type="text/javascript" src="/sp/dist/js/bootstrap.js" ></script>
<script type="text/javascript" src="/sp/js/common.js" ></script>
<script type="text/javascript" src="/sp/js/date/WdatePicker.js" ></script>
<script type="text/javascript" src="/sp/dist/jquery.form.js" ></script>
<script type="text/javascript">
var dataJson;

$(function(){
	getArea();
	getJob();
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
						htmlStr1 += "<div><input type='radio' name='topicId' value="+res.errmsg[i].id+" />"+res.errmsg[i].name+"</div>";
					}
					if("2"==res.errmsg[i].parentId){
						htmlStr2 += "<div><input type='radio' name='topicId' value="+res.errmsg[i].id+" />"+res.errmsg[i].name+"</div>";
					}
					if("3"==res.errmsg[i].parentId){
						htmlStr3 += "<div><input type='radio' name='topicId' value="+res.errmsg[i].id+" />"+res.errmsg[i].name+"</div>";
					}
				}
				$("#topicDiv1").html(htmlStr1);
				$("#topicDiv2").html(htmlStr2);
				$("#topicDiv3").html(htmlStr3);
			}
		}
	})
}




/**
 * 擅长领域
 */
function getArea(){
	$.ajax({
		url:"/trade/data/list.html",
		type:"POST",
		dataType:"json",
		success:function(res){
			var htmlStr = "";
			
			for(var i = 0;i<res.errmsg.length;i++){
				htmlStr += "<option value="+res.errmsg[i].id+" >";
				htmlStr += res.errmsg[i].tradeName;
				htmlStr += "</option>";
			}
			
			$("#area1").html(htmlStr);
			$("#area2").html(htmlStr);
			$("#area3").html(htmlStr);
		}
	})
}

/**
 *获取职位
 */
function getJob(){
	$.ajax({
		url:"/trade/data/list.html",
		type:"POST",
		dataType:"json",
		success:function(res){
			var htmlStr = "";
			
			for(var i = 0;i<res.errmsg.length;i++){
				htmlStr += "<option value="+res.errmsg[i].id+"  >";
				htmlStr += res.errmsg[i].tradeName;
				htmlStr += "</option>";
			}
			
			$("#job1").html(htmlStr);
			$("#job2").html(htmlStr);
			$("#job3").html(htmlStr);
		}
	})
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
			  <a href="/admin/index.html" class="list-group-item active">教师管理</a>
			  <a href="/admin/student.html" class="list-group-item">顾客管理</a>
			  <a href="/admin/order.html" class="list-group-item">兑付管理</a>
			  <a href="/admin/util.html" class="list-group-item ">辅助管理</a>
			</div>
		</div>
		<div class="col-md-10 index-col-md-10" >
			<div class="btn-group" role="group" aria-label="...">
			  <button type="button" class="btn btn-default" onclick="openUrl('/admin/page/index.html')" >返回首页</button>
			  <button type="button" class="btn btn-info" >教师编辑</button>
			</div>
			<input type="hidden" value="${errmsg}" id="editId" >
			<div class="space-div-4" ></div>
			<table class="table table-bordered" >
				<tbody>
					<tr>
						<th>姓名：</th>
						<td> <input type="text" class="form-control" id="username"  value="${tutor.realName }"  ></td>
						<th>头像:</th>
						<td><img  src="${tutor.photoPath }" class="img-width" id="imgId" ></td>
					</tr>
					<tr>
						<th>性别：</th>
						<td>
							 <input type="hidden" id="genderValue" value="${tutor.sex }" >
					     	 <input type="text" class="form-control"  id="sex" value="${tutor.sex }" >
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
						<td><input class="form-control" value="${tutor.mobilePhone }" id="mobilePhone"  ></td>
						<th>认证资质：</th>
						<td><textarea  id="highlight"  style="height: 80px;" class="form-control" >${tutor.aptitude }</textarea></td>
					</tr>
					<tr>
						<th>生日：</th>
						<td><input class="form-control" value="${tutor.birthday }" id="birth"  onClick="WdatePicker()" readonly="readonly" ></td>
						<th>单价：</th>
						<td><input type="text" id="unitPrice"  class="form-control" value="${tutor.classPrice }" placeholder="元/次"  ></td>
					</tr>
					<tr>
						<th>行业1：</th>
						<td> <select class="form-control" id="job1" name="job1" ></select></td>
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
						<td> <select class="form-control" id="job2" name="job2" ></select></td>
						<th>银行卡号：</th>
						<td><input type="text" class="form-control" value="${tutor.bankCard }" id="bankAccount"  ></td>
					</tr>
					<tr>
						<th>行业3：</th>
						<td> <select class="form-control" id="job3" name="job3" ></select></td>
						<th>银行卡号：</th>
						<td><input type="text" class="form-control" value="${tutor.bankCard }" id="bankAccount"  ></td>
					</tr>
					<tr>
						<th>擅长领域1：</th>
						<td> <select class="form-control" id="area1" name="area1" ></select></td>
						<th>银行名称：</th>
						<td><input type="text" class="form-control" value="${tutor.bankName}" /></td>
					</tr>
					<tr>
						<th>擅长领域2：</th>
						<td> <select class="form-control" id="area2" name="area2" ></select></td>
						<th>身份证号：</th>
						<td> <input type="text" id="idCard" value="${tutor.idNumber }" class="form-control"  ></td>
					</tr>
					<tr>
						<th>擅长领域3：</th>
						<td> <select class="form-control" id="area3" name="area3" ></select></td>
						<th>邮箱：</th>
						<td> <input type="text" id="email" value="${tutor.email }" class="form-control"  ></td>
					</tr>
					<tr>
						<th>工作年限：</th>
						<td>  <input class="form-control" value="${tutor.workYears }" id="workYears" ></td>
						<th>邮箱：</th>
						<td>  <input type="text" id="qq" value="${tutor.qq }" class="form-control"  ></td>
					</tr>
					<tr>
						<th></th>
						<td></td>
						<th>openid：</th>
						<td>  <input type="text" id="openid" value="${tutor.openid }" class="form-control"  ></td>
					</tr>
					<tr>
						<th colspan="4" >辅助方式：</th>
					</tr>
					<tr>
						<td>电话约谈</td>
						<td colspan="3" >
							<input type="hidden" id="mobileValue" value="${tutor.mobileStatus }" >	
							<input type="checkbox" class="checkbox" name="talkPhoneCall" id="mobileStatus" >
						</td>
					</tr>
					<tr>
						<td>当面约谈</td>
						<td colspan="3" >
							<input type="hidden" id="faceValue" value="${tutor.faceStatus }" >	
							<input type="checkbox" class="checkbox" name="faceToFace" id="faceStatus" >
						</td>
					</tr>
					<tr>
						<td>视频约谈</td>
						<td colspan="3" >
							<input type="hidden" id="videoValue" value="${tutor.videoStatus }" >	
							<input type="checkbox" class="checkbox" name="talkVideoChat" id="videoStatus" >
						</td>
					</tr>
					<tr>
						<th colspan="4" >特色管理：<input type="hidden" value="${tutor.qTopic.id }" > </th>
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
						<td colspan="3" ><textarea class="form-control" id="personalIntroduction" >${tutor.personalIntroduction }</textarea></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	
</body>
</html>