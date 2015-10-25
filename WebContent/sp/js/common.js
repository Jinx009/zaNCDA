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