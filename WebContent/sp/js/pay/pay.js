var client_ip = "127.0.0.1";

/**
 * url参数格式化
 * @param str
 * @param is_global
 * @returns
 */
function Trim(str,is_global){
   var result;
   result = str.replace(/(^\s+)|(\s+$)/g,"");
   if(is_global.toLowerCase()=="g") result = result.replace(/\s/g,"");
   return result;
}

/**
 * 去除换行
 * @param key
 * @returns
 */
function clearBr(key){
   key = Trim(key,"g");
   key = key.replace(/<\/?.+?>/g,"");
   key = key.replace(/[\r\n]/g, "");
   return key;
}

/**
 * 获取随机数
 * @returns
 */
function getANumber(){
   var date = new Date();
   var times1970 = date.getTime();
   var times = date.getDate() + "" + date.getHours() + "" + date.getMinutes() + "" + date.getSeconds();
   var encrypt = times * times1970;
   if(arguments.length == 1){
 	  return arguments[0] + encrypt;
   }else{
      return encrypt;
   }
}

var oldPackageString;

/**
 * 获取appId
 * @returns {String}
 */
function getAppId(){
    return "wxad7987ba40989a97";
}


var oldTimeStamp ;
var oldNonceStr ; 

/**
 * 获取时间戳
 * @returns
 */
function getTimeStamp(){
    var timestamp=new Date().getTime();
    var timestampstring = timestamp.toString();//一定要转换字符串
    oldTimeStamp = timestampstring;
    return timestampstring;
}

/**
 * 获取随机戳
 * @returns {String}
 */
function getNonceStr(){
     var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
     var maxPos = $chars.length;
     var noceStr = "";
     for (i = 0; i < 32; i++) {
          noceStr += $chars.charAt(Math.floor(Math.random() * maxPos));
     }
     oldNonceStr = noceStr;
     return noceStr;
}

/**
 * 获取加密方式
 * @returns {String}
 */
function getSignType(){
     return "MD5";
}
var time;
var nonce;
var sign;

/**
 * 签名
 * @returns {___anonymous_sign}
 */
function getSign(){
	 time = getTimeStamp();
	 nonce = getNonceStr();
     var params = "appId=wxad7987ba40989a97&nonceStr="+nonce+"&package=prepay_id="+pay_id+"&signType=MD5&timeStamp="+time+"&key=jinxjinxjinxjinxjinxjinxjinxjinx";
    // alert(params);
     sign = CryptoJS.MD5(params).toString().toUpperCase();
     
     return sign;
}

var pay_id;

/**
 * 支付回调
 */
function callPlay(){
   sign = getSign();
   WeixinJSBridge.invoke('getBrandWCPayRequest', {
           "appId":"wxad7987ba40989a97",    
           "timeStamp":time,        
           "nonceStr":nonce, 
           "package":"prepay_id="+pay_id,     
           "signType":"MD5",           
           "paySign":sign 
       },
       function(res){     
    	   WeixinJSBridge.log(res.err_msg);
           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
        	   var tutorId = $("#tutorId").val();
        	   var topicId = $("#topicId").val();
        	   var timeId = $("#time").val();
        	   var topicContent = $("#topicContent").val();
        	   var storage = window.sessionStorage;
        	   topicContent = storage.getItem("localTopic");
        	   if(null!=topicContent&&""!=topicContent){
        		   topicContent = storage.getItem("localTopic");
        	   }else{
        		   topicContent = "";
        	   }
        	   var params = "tutorId="+tutorId+"&topicId="+topicId+"&timeId="+timeId+"&topicContent="+topicContent;
        	   $.ajax({
        		   url:"/tutor/data/saveOrder.html",
        		   type:"POST",
        		   dataType:"json",
        		   data:params,
        		   success:function(data){
        			   if("success"==data.result){
        				   $("#myAlertH").html("支付成功，预约已经生效。请注意查收预约辅导通知短信。");
        					$("#newAlertBtn").attr("onclick","openMyUrl('/customer/page/manage.html')");
        					showNewAlert();
        				  /* alert("支付成功，预约已经生效。请注意查收预约辅导通知短信。");
        				   location.href = "/customer/page/manage.html";*/
        			   }
        		   }
        	   })
           }     
       }
   ); 
}

/**
 * 发起支付
 */
function pay(){
	var timeId = $("#time").val();
	var param = "time="+timeId;
	$.ajax({
		url:"/customer/data/checkTutorTime.html",
		data:param,
		dataType:"json",
		type:"POST",
		success:function(res){
			if("success"==res.result){
				var order_id = "NCDA"+$("#orderId").val();    
				var total_fee = parseFloat($("#fee").val())*100;   
				var openId = $("#openId").val();
				var nonceStr = getNonceStr();
				var str = "appid=wxad7987ba40989a97"+
						   "&body=职业生涯规划辅导"+
						   "&mch_id=1304560401"+
						   "&nonce_str="+nonceStr+
						   "&notify_url=http://t03.0angel.com/pay/callBack.html"+
						   "&openid="+openId+
						   "&out_trade_no="+order_id+
						   "&spbill_create_ip=127.0.0.1"+
						   "&total_fee="+total_fee+
						   "&trade_type=JSAPI"+
						   "&key=jinxjinxjinxjinxjinxjinxjinxjinx";
				var md5 = CryptoJS.MD5(str).toString();
				
				var params = "sign="+md5+"&openId="+openId+"&fee="+total_fee+"&nonce_str="+nonceStr+"&client_ip=127.0.0.1&order_id="+order_id;
				if(null==openId||""==openId){
					$("#myAlertH").html("请在微信登录后再预约!");
					showNewAlert();
				}else{
					$.ajax({
						url:"/getPayId.html",
						type:"POST",
						data:params,
						dataType:"json",
						success:function(res){
							pay_id = res.errmsg;
							callPlay();
						}
					})
				}
			}else{
				$("#myAlertH").html("很可惜，该时间已经被其他学员抢占，请更换预约时间!");
				$("#newAlertBtn").attr("onclick","hideNewAlert()");
				showNewAlert();
			}
		}
	})
}

