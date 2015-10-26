package common.wechat;

public class WechatData {
	
	public static final String APP_ID = "";
	
	public static final String APP_SECRET = "";
	
	public static final String TOKEN = "test";
	
	public static final String CHAR_SET = "utf-8";
	
	public static final String OAUTH_URL_ONE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APP_ID+"&redirect_uri=";
	
	public static final String OAUTH_URL_TWO = "&response_type=code&scope=snsapi_base&state=state#wechat_redirect";
	
}
