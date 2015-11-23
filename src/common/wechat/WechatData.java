package common.wechat;

import java.io.UnsupportedEncodingException;

public class WechatData {
	
	public static final String APP_ID = "wx08411a74145eb7dc";
	
	public static final String APP_SECRET = "852e83b7030c1723271ffce48540d4a8";
	
	public static final String TOKEN = "test";
	
	public static final String CHAR_SET = "utf-8";
	
	public static final String OAUTH_URL_ONE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APP_ID+"&redirect_uri=";
	
	public static final String OAUTH_URL_TWO = "http://t03.0angle.com";
	
	public static final String OAUTH_URL_THREE = "&response_type=code&scope=snsapi_base&state=state#wechat_redirect";
	
	
	/**
	 * 导师重定向链接
	 * @return
	 */
	public static String getTutorOauthUrl(){
		StringBuffer buffer = new StringBuffer();
		buffer.append(OAUTH_URL_ONE);
		buffer.append(OAUTH_URL_TWO);
		buffer.append("/tutor/login.html");
		buffer.append(OAUTH_URL_THREE);
		
		return buffer.toString();
	}
	
	/**
	 * 顾客重定向链接
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String getCustomerOauthUrl(String redirectUrl) throws UnsupportedEncodingException{
		StringBuffer buffer = new StringBuffer();
		buffer.append(OAUTH_URL_ONE);
		buffer.append(OAUTH_URL_TWO);
		buffer.append("/customer/login.html?redirectUrl=");
		buffer.append(java.net.URLEncoder.encode(redirectUrl,"utf-8"));
		buffer.append(OAUTH_URL_THREE);
		
		return buffer.toString();
	}
	
}
