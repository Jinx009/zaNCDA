package main.entry.webapp.page;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.helper.ConstantUtil;
import common.helper.StringUtil;
import common.wechat.WechatData;
import common.wechat.WechatUtil;

import service.basicFunctions.CustomerService;
import database.models.Customer;

@Controller
public class CustomerPage {
	
	private Map<String,Object> data;
	
	private Customer customer;
	
	@Autowired
	private CustomerService customerService;

	/**
	 * 微信支付页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/customer/page/pay")
	public String pay(HttpServletRequest request,HttpServletResponse response){
		customer = (Customer) request.getSession().getAttribute(ConstantUtil.CUSTOMER_SESSION);
		
		request.setAttribute("openid",customer.getOpenid());
		request.setAttribute("orderId",request.getParameter("orderId"));
		
		return "/customer/pay";
	}
	
	/**
	 * 顾客登陆页面
	 * @param request
	 * @param response
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@RequestMapping(value = "/customer/login")
	public String login(HttpServletRequest request,HttpServletResponse response) throws ClientProtocolException, IOException{
		 String code = request.getParameter("code");
		 customer = null;
		 int status = 0;
		 
		 if(StringUtil.isNotBlank(code)){
			 String openid = WechatUtil.getOauthOpenId(WechatData.APP_ID,WechatData.APP_SECRET,code);
			 if(StringUtil.isNotBlank(openid)){
				 customer = customerService.getByOpenid(openid);
				 if(null!=customer){
					 status = 1;
				 }
			 }
		 }
		 request.setAttribute("status",status);
		 request.setAttribute("url",WechatData.getCustomerOauthUrl());
		 request.getSession().setAttribute(ConstantUtil.CUSTOMER_SESSION,customer);
		 return "/customer/login";
	 }
	
	/**
	 * 顾客首页
	 * @return
	 */
	@RequestMapping(value = "/customer/page/index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		String realName = "";
		customer = (Customer) request.getSession().getAttribute(ConstantUtil.CUSTOMER_SESSION);
		if(StringUtil.isNotBlank(customer.getRealName())){
			realName = customer.getRealName();
		}
		request.setAttribute("customer",realName);
		return "/customer/index";
	}
	
	
	
	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
