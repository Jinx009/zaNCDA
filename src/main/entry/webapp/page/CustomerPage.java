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
	
	/**
	 * 新建约谈
	 * @return
	 */
	@RequestMapping(value = "/customer/page/new")
	public String newPage(){
		return "/customer/new";
	}
	
	/**
	 * 约谈管理
	 * @return
	 */
	@RequestMapping(value = "/customer/page/manage")
	public String manage(){
		return "/customer/manage";
	}
	
	/**
	 * 筛选结果
	 * @return
	 */
	@RequestMapping(value = "/customer/page/selectResult")
	public String selectResult(HttpServletRequest request){
		Integer trade = Integer.valueOf(request.getParameter("trade"));
		Integer area = Integer.valueOf(request.getParameter("area"));
		Integer type = Integer.valueOf(request.getParameter("type"));
		Integer workYears = Integer.valueOf(request.getParameter("workYears"));
		Integer id = Integer.valueOf(request.getParameter("id"));

		request.setAttribute("trade",trade);
		request.setAttribute("id",id);
		request.setAttribute("area",area);
		request.setAttribute("type",type);
		request.setAttribute("workYears",workYears);
		return "/customer/selectResult";
	}
	
	/**
	 * 筛选条件
	 * @return
	 */
	@RequestMapping(value = "/customer/page/selectTutor")
	public String selectTutor(HttpServletRequest request){
		request.setAttribute("id",request.getParameter("id"));
		return "/customer/selectTutor";
	}
	
	@RequestMapping(value = "/customer/page/qOne")
	public String qOne(){
		return "/customer/qOne";
	}
	
	@RequestMapping(value = "/customer/page/qTwo")
	public String qTwo(){
		return "/customer/qTwo";
	}
	
	@RequestMapping(value = "/customer/page/qThree")
	public String qThree(){
		return "/customer/qThree";
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
