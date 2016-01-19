package common.helper.tool.message;

import com.jianzhou.sdk.BusinessService;

public class MsgUtil {
	
	private static final String userName  = "sdk_caicheng";
	private static final String pwd = "20151215";
	private static final String code = "【才知道】";
	
	public static void sendMsg(String mobilePhone,String msg){
		BusinessService bs = new BusinessService();
		bs.setWebService("http://www.jianzhou.sh.cn/JianzhouSMSWSServer/services/BusinessService");
		System.out.println(mobilePhone+"---"+msg+"---"+bs.sendBatchMessage(userName,pwd,mobilePhone,msg+code));
	}
	
	public static void main(String[] args) {
		sendMsg("15238677147","起床尿尿了");
	}
}
