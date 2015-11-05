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
 * 获取支付进度
 */
function getPayStatus(data){
	if(1==data.secondPayIsDone){
		return 100;
	}else{
		if(1==data.firstPayIsDone){
			return 50;
		}else{
			return 0;
		}
	}
}

/**
 * 获取约谈状态
 */
function getOrderStatus(status){
	if(0==status){
		return "约谈进行中";
	}else if(1==status){
		return "顾客已评价";
	}else if(2==status){
		return "教师已评价";
	}else{
		return "双方已互评";
	}
}