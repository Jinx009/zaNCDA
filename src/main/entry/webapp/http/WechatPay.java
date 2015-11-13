package main.entry.webapp.http;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import common.helper.HttpWebIOHelper;
import common.wechat.WechatData;
import common.wechat.WechatUtil;

@Controller
public class WechatPay {

	/**
	 * 获取微信支付统一订单编号
	 * @param request
	 * @param response
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	@RequestMapping(value = "/getPayId",method = RequestMethod.POST)
	public void getPrePay_Id(HttpServletRequest request,HttpServletResponse response) throws ClientProtocolException, IOException, NoSuchAlgorithmException
	{
		
		String openId = request.getParameter("openId");
		String orderId = request.getParameter("order_id");
		String ip = request.getParameter("client_ip");
		String total_fee = request.getParameter("fee");
		String sign = request.getParameter("sign");
		String nonce_str = request.getParameter("nonce_str");
		
		String xml = 
				"<xml>"+
				   "<appid>"+WechatData.APP_ID+"</appid>"+
				   "<body>NCDA</body>"+
				   "<mch_id>1230109502</mch_id>"+
				   "<nonce_str>"+nonce_str+"</nonce_str>"+
				   "<notify_url>http://t03.0angel.com/pay/callBack.html</notify_url>"+
				   "<openid>"+openId+"</openid>"+
				   "<out_trade_no>"+orderId+"</out_trade_no>"+
				   "<spbill_create_ip>"+ip+"</spbill_create_ip>"+
				   "<total_fee>"+total_fee+"</total_fee>"+
				   "<trade_type>JSAPI</trade_type>"+
				   "<sign>"+sign.toUpperCase()+"</sign>"+
				"</xml> ";
		
		System.out.println(xml);
		
		String res = WechatUtil.getPrePayId(xml);
		Map<String,String> map = WechatUtil.parseXml(res);
		
		HttpWebIOHelper._printWebJson(map.get("prepay_id"), response);
	}
}
