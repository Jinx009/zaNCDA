package main.entry.webapp.page;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import common.helper.StringUtil;
import common.wechat.WechatData;
import common.wechat.WechatUtil;
import service.basicFunctions.CustomerService;
import service.basicFunctions.TutorService;
import database.models.Customer;

@Controller
public class CustomerPage {
	
	private Customer customer;
	private Map<String,Object> data;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private TutorService tutorService;

	/**
	 * 忘记密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/customer/forget")
	public String forget(HttpServletRequest request,HttpServletResponse response){
		return "/customer/forget";
	}
	
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
	 * 登出
	 * @throws IOException 
	 */
	@RequestMapping(value = "/customer/loginOut")
	public void loginOut(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.getSession().setAttribute(ConstantUtil.CUSTOMER_SESSION,null);
		
		data = new HashMap<String,Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,"登出成功!");
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 个人信息
	 * @return
	 */
	@RequestMapping(value = "/customer/page/info")
	public String info(HttpServletRequest req){
		customer = (Customer)req.getSession().getAttribute(ConstantUtil.CUSTOMER_SESSION);
		if(null!=customer.getTrade()){
			req.setAttribute("tradeId",customer.getTrade().getId());
		}else{
			req.setAttribute("tradeId","");
		}
		if(null!=customer.getJob()){
			req.setAttribute("jobId",customer.getJob().getId());
		}else{
			req.setAttribute("jobId","");
		}
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		if(null!=customer.getBirthday()){
			req.setAttribute("birthday",df.format(customer.getBirthday()));
		}else{
			req.setAttribute("birthday",df.format(new Date()));
		}
		req.setAttribute("customer",customer);
		return "/customer/info";
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
		 String redirectUrl = request.getParameter("redirectUrl");
		 customer = null;
		 String openid = "";
		 int status = 0;
		 
		 if(StringUtil.isNotBlank(code)){
			 openid = WechatUtil.getOauthOpenId(WechatData.APP_ID,WechatData.APP_SECRET,code);
			 System.out.println("登陆页面获取openid"+openid);
			 if(StringUtil.isNotBlank(openid)){
				 customer = customerService.getByOpenid(openid);
				 if(null!=customer){
					 status = 0;
				 }
			 }
		 }
		 request.setAttribute("openid",openid);
		 request.setAttribute("status",status);
		 request.getSession().setAttribute(ConstantUtil.CUSTOMER_SESSION,customer);
		 request.setAttribute("url",redirectUrl);
		 return "/customer/login";
	 }
	
	/**
	 * 顾客首页
	 * @return
	 */
	@RequestMapping(value = "/customer/page/index")
	public String index(HttpServletRequest request,HttpServletResponse response){
		return "/customer/index";
	}
	
	
	/**
	 * 顾客成长信息
	 * @return
	 */
	@RequestMapping(value = "/customer/page/growpInfo")
	public String growpInfo(HttpServletRequest request,HttpServletResponse response){
		customer = (Customer) request.getSession().getAttribute(ConstantUtil.CUSTOMER_SESSION);
		customer = customerService.getById(customer.getId());
		request.setAttribute("status",customer.getGrowpStatus());
		return "/customer/growpInfo";
	}
	
	
	/**
	 * 成功案例详情
	 * @return
	 */
	@RequestMapping(value = "/customer/page/caseDetail")
	public String detail(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("id", request.getParameter("id"));
		return "/customer/caseDetail";
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
	 * 顾客选择导师详情
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/customer/page/tutorDetail")
	public String tutorDetail(HttpServletRequest request,HttpServletResponse response){
		Integer tutorId = Integer.valueOf(request.getParameter("tutorId"));
		request.setAttribute("tutor",tutorService.find(tutorId));
		request.setAttribute("tutorId",tutorId);
		request.setAttribute("topicId",request.getParameter("topicId"));
		
		return "/customer/tutorDetail";
	}
	
	
	/**
	 * 注册
	 * @return
	 */
	@RequestMapping(value = "/customer/register")
	public String register(){
		return "/customer/register";
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

	
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}
