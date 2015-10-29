package common.wechat;

public class WechatData {
	
	public static final String APP_ID = "wx08411a74145eb7dc";
	
	public static final String APP_SECRET = "852e83b7030c1723271ffce48540d4a8";
	
	public static final String TOKEN = "test";
	
	public static final String CHAR_SET = "utf-8";
	
	public static final String OAUTH_URL_ONE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APP_ID+"&redirect_uri=";
	
	public static final String OAUTH_URL_TWO = "http://www.jinxq.club";
	
	public static final String OAUTH_URL_THREE = "&response_type=code&scope=snsapi_base&state=state#wechat_redirect";
	
	
	/**
	 * 重定向链接
	 * @return
	 */
	public static String getTeacherOauthUrl(){
		StringBuffer buffer = new StringBuffer();
		buffer.append(OAUTH_URL_ONE);
		buffer.append(OAUTH_URL_TWO);
		buffer.append("/teacher/login.html");
		buffer.append(OAUTH_URL_THREE);
		
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getTeacherOauthUrl());
	}
}
