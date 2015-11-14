/**
 * 打开连接
 * @param url
 */
function openUrl(url){
	location.href = url;
}

/**
 * 获取随机数
 */
function getRandom(){
    return Math.random();
}

/**
 * 隐藏弹窗 
 * @param id
 */
function hideAlert(id){
	$("#"+id).hide();
}

/**
 * 显示弹窗
 * @param id
 */
function showAlert(id){
	$("#"+id).show();
}

/**
 * 转时间戳
 * @param unixtime
 * @returns
 */
function jsDateTimeOnly(unixtime)  
{  
	 var date = new Date(unixtime);
	 
	 return  date.format("yyyy-MM-dd"); 
} 
Date.prototype.format = function(format)
{
	var o = 
	{
	"M+" : this.getMonth()+1, 
	"d+" : this.getDate(), 
	"h+" : this.getHours(),
	"m+" : this.getMinutes(), 
	"s+" : this.getSeconds(), 
	"q+" : Math.floor((this.getMonth()+3)/3), 
	"S" : this.getMilliseconds() 
	}

	if(/(y+)/.test(format)) 
	{
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	}

	for(var k in o) 
	{
		if(new RegExp("("+ k +")").test(format))
		{
			format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
		}
	}
	return format;
} 

/**
 * 获取约谈状态
 */
function getOrderStatus(status){
	if(0==status){
		return "未支付";
	}else if(1==status){
		return "已支付";
	}else if(2==status){
		return "导师已评价";
	}else if(3==status){
		return "导师已评价";
	}else if(4==status){
		return "顾客已评价";
	}else if(5==status){
		return "双方已互评";
	}else if(6==status){
		return "约谈已结束";
	}else{
		return "约谈已取消";
	}
}

/**
 * 是否可以评价
 * @param status
 * @param time
 */
function getReplyStatus(status,time){
	var timestamp=new Date().getTime();
	if(parseInt(status)<1){
		return false;
	}
	if(parseFloat(time)<parseFloat(time)){
		return false;
	}
	return true;
}