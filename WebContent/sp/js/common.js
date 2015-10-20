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