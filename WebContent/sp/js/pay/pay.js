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
    return "wx08411a74145eb7dc";
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
     var params = "appId=wx08411a74145eb7dc&nonceStr="+nonce+"&package=prepay_id="+pay_id+"&signType=MD5&timeStamp="+time+"&key=jinxjinxjinxjinxjinxjinxjinxjinx";
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
           "appId":"wx08411a74145eb7dc",    
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
        	   var params = "tutorId="+tutorId+"&topicId="+topicId+"&timeId="+timeId+"&topicContent="+topicContent;
        	   $.ajax({
        		   url:"/tutor/data/saveOrder.html",
        		   type:"POST",
        		   dataType:"json",
        		   data:params,
        		   success:function(data){
        			   if("success"==data.result){
        				   alert("支付成功,订单已保存!");
        				   location.href = "/customer/page/manager.html";
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
	var order_id = "NCDA"+$("#orderId").val();    
	var total_fee = parseFloat($("#fee").val())*100;   
	var openId = $("#openId").val();
	var nonceStr = getNonceStr();
	var str = "appid=wx08411a74145eb7dc"+
			   "&body=NCDA"+
			   "&mch_id=1280820801"+
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
		alert("尚未从微信登陆，无法完成交易!");
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
}

