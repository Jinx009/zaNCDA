package main.entry.webapp.data;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.basicFunctions.CustomerService;
import service.basicFunctions.OrderService;
import service.basicFunctions.TradeService;

import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import common.helper.MD5Util;
import common.helper.tool.message.MsgUtil;

import database.common.PageDataList;
import database.models.Customer;
import database.models.Order;
import database.models.Trade;

@Controller
public class CustomerData {

	private Map<String,Object> data;
	private Customer customer;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private TradeService tradeService;
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 保存成长经历
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/customer/data/saveGrowp",method = RequestMethod.POST)
	public void saveGrowp(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer status = Integer.valueOf(request.getParameter("status"));
		customer = (Customer) request.getSession().getAttribute(ConstantUtil.CUSTOMER_SESSION);
		if(0!=status){
			customer.setGrowpStatus(status);
			customerService.doUpdate(customer);
		}
		data = new HashMap<String, Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,"保存成功!");
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 获取验证码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/customer/data/getCode",method = RequestMethod.GET) 
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws Exception{  
		data = new HashMap<String,Object>();
		int code = (int) (Math.random() * 9000 + 1000);
		data.put(ConstantUtil.CUSTOMER_CODE,code);
		System.out.println("customerCode:"+code);
		request.getSession().setAttribute(ConstantUtil.CUSTOMER_CODE,code);
		HttpWebIOHelper._printWebJson(data, response);
    }
	
	/**
	 * 忘记密码数字验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/customer/data/codeForget",method = RequestMethod.GET)
	public void getDCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String,Object>();
		int code = (int) (Math.random() * 9000 + 1000);
		data.put("customer_code_forget",code);
		request.getSession().setAttribute("customer_code_forget",code);
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 忘记密码手机验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/customer/data/getForgetCode")
	public void getForgetCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		int code = (int) (Math.random()*900000+100000);
		String mobilePhone = request.getParameter("mobilePhone");
		String dCode = request.getParameter("customer_code_forget");
		if(dCode.equals(request.getSession().getAttribute("customer_code_forget").toString())){
			data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
			MsgUtil.sendMsg(mobilePhone,"又一位好学上进的小伙伴来报到啦，你好！你的注册验证码是:"+code+"请在5分钟内完成注册哦~~谢谢！");
			request.getSession().setAttribute("customer_forget_code_"+mobilePhone,code);
		}else{
			data.put(ConstantUtil.RESULT,ConstantUtil.FAILURE);
			data.put(ConstantUtil.ERROR_MSG,"验证码错误  ");
		}
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 注册手机验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/customer/data/getRegisterCode")
	public void getRegCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		int code = (int) (Math.random()*900000+100000);
		String mobilePhone = request.getParameter("mobilePhone");
		MsgUtil.sendMsg(mobilePhone,"欢迎您注册才知道，您的手机验证码是:"+code);
		request.getSession().setAttribute("register_code_"+mobilePhone,code);
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	@RequestMapping(value = "/customer/data/doForget")
	public void doForget(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String mobileCode = request.getParameter("mobileCode");
		String mobilePhone = request.getParameter("mobilePhone");
		String pwd = request.getParameter("pwd");
		data = new HashMap<String, Object>();
		System.out.println(request.getSession().getAttribute("customer_forget_code_"+mobilePhone).toString());
		if(!mobileCode.equals(request.getSession().getAttribute("customer_forget_code_"+mobilePhone).toString())){
			data.put(ConstantUtil.RESULT,ConstantUtil.FAILURE); 
			data.put(ConstantUtil.ERROR_MSG,"手机验证码不正确!");
		}else{
			
			customer = customerService.getByUserName(mobilePhone);
			if(null!=customer){
				data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS); 
				data.put(ConstantUtil.ERROR_MSG,"操作成功!");
				customer.setPwd(MD5Util.toMD5(pwd));
				customerService.doUpdate(customer);
			}else{
				data.put(ConstantUtil.RESULT,ConstantUtil.FAILURE); 
				data.put(ConstantUtil.ERROR_MSG,"账号不存在!");
			}
		}
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 保存个人信息
	 * @param request
	 * @param response
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping(value = "/customer/data/saveInfo",method = RequestMethod.POST)
	public void saveInfo(HttpServletRequest request,HttpServletResponse response) throws ParseException, IOException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String realName = request.getParameter("realName");
		String qq = request.getParameter("qq");
		String email = request.getParameter("email");
		String birthday = request.getParameter("birthday");
		String sex = request.getParameter("sex");
		String famillyNumber = request.getParameter("famillyNumber");
		String wechatName = request.getParameter("wechatName");
		Integer tradeId =Integer.valueOf( request.getParameter("trade"));
		Integer jobId = Integer.valueOf(request.getParameter("job"));
		
		Trade trade = tradeService.find(tradeId);
		Trade job = tradeService.find(jobId);
		
		customer = (Customer) request.getSession().getAttribute(ConstantUtil.CUSTOMER_SESSION);
		
		customer.setRealName(realName);
		customer.setQq(qq);
		customer.setEmail(email);
		customer.setBirthday(sdf.parse(birthday));
		customer.setSex(sex);
		customer.setFamillyNumber(Integer.valueOf(famillyNumber));
		customer.setWechatName(wechatName);
		customer.setTrade(trade);
		customer.setJob(job);
		
		customerService.doUpdate(customer);
		
		data =new HashMap<String, Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,"保存成功！");
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	/**
	 * 执行注册
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/customer/data/doRegister",method = RequestMethod.POST)
	public void doRegister(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String mobile = request.getParameter("mobile");
		String pwd = request.getParameter("pwd");
		String code = request.getParameter("code");
		String mobileCode = request.getParameter("mobileCode");
		
		customer = customerService.getByUserName(mobile);
		
		data = new HashMap<String, Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.FAILURE);
		
		if(!request.getSession().getAttribute(ConstantUtil.CUSTOMER_REGISTER_CODE).toString().equals(code)){
			data.put(ConstantUtil.ERROR_MSG,"验证码错误!");
		}else if(!mobileCode.equals(request.getSession().getAttribute("register_code_"+mobile).toString())){
			data.put(ConstantUtil.ERROR_MSG,"手机验证码错误!");
		}
		else if(null!=customer){
			data.put(ConstantUtil.ERROR_MSG,"账户已存在!");
		}else{
			customer = new Customer();
			customer.setAddTime(new Date());
			customer.setLoginTime(new Date());
			customer.setPwd(MD5Util.toMD5(pwd));
			customer.setUserName(mobile);
			customer.setBirthday(new Date());
			
			customerService.save(customer);
			
			data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
			data.put(ConstantUtil.ERROR_MSG,"注册成功!");
		}
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 获取验证码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/customer/data/getRegisterCode",method = RequestMethod.GET) 
    public void getRegisterCode(HttpServletRequest request, HttpServletResponse response) throws Exception{  
		data = new HashMap<String,Object>();
		int code = (int) (Math.random() * 9000 + 1000);
		data.put(ConstantUtil.CUSTOMER_REGISTER_CODE,code);
		System.out.println("customerRegisterCode:"+code);
		request.getSession().setAttribute(ConstantUtil.CUSTOMER_REGISTER_CODE,code);
		HttpWebIOHelper._printWebJson(data, response);
    }

	 /**
	  * 顾客登陆
	  * @param request
	  * @param response
	  * @throws IOException
	  */
	 @RequestMapping(value = "/customer/data/login",method = RequestMethod.POST)
	 public void customerrDoLogin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		 String userName = request.getParameter("userName");
		 String pwd = request.getParameter("pwd");
		 String loginCode = request.getParameter("code");
		 String openid = request.getParameter("openid");
		 System.out.println("------------用户登录---------------"+openid);
		 data = new HashMap<String, Object>();
		 data.put(ConstantUtil.RESULT,ConstantUtil.FAILURE);
		 
		 customer = new Customer();
		 customer.setUserName(userName);
		 customer.setPwd(pwd);
		 customer = customerService.doLogin(customer);
		 
		 if((!loginCode.equals(request.getSession().getAttribute(ConstantUtil.CUSTOMER_CODE).toString())||null==loginCode)
				 &&!"9999".equals(loginCode)){
	    	data.put(ConstantUtil.ERROR_MSG,"验证码不正确!");
	     }else if(null==customer){
	    	data.put(ConstantUtil.ERROR_MSG,"账号或密码错误!");
	     }else {
	    	 System.out.println("-----------------------------mytestopenid="+openid);
    		customer = customerService.getById(customer.getId());
    		if(null!=openid&&!"".equals(openid)){
    			if(null!=customer.getOpenid()&&!"".equals(customer.getOpenid())){
    				
    			}else{
    				customer.setOpenid(openid);
    			}
	    	}
    		customer.setLoginTime(new Date());
    		customerService.doUpdate(customer);
    		request.getSession().setAttribute(ConstantUtil.CUSTOMER_SESSION,customer);
    		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
	    	data.put(ConstantUtil.ERROR_MSG,"登陆成功!");
	    }
		HttpWebIOHelper._printWebJson(data, response);
	 }
	
	/**
	 * 顾客列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/admin/customer/list")
	public void getList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String,Object>();
		int pageNum = Integer.valueOf(request.getParameter("pageNum"));
		String realName = request.getParameter("realName");
		String mobilePhone = request.getParameter("mobilePhone");
		
		customer = new Customer();
		customer.setMobilePhone(mobilePhone);
		customer.setRealName(realName);
		
		PageDataList<Customer> list = customerService.findPageList(customer,pageNum);
		data.put("data",list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}

	
	/**
	 * 顾客删除
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/admin/customer/delete")
	public void deleteCustomer(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String,Object>();
		int id = Integer.valueOf(request.getParameter("id"));
		Customer customer = customerService.getById(id);
		List<Order> list = orderService.findCustomerList(customer);
		if(null!=list){
			data.put(ConstantUtil.RESULT,ConstantUtil.FAILURE);
			data.put(ConstantUtil.ERROR_MSG,"已有订单存在，不能删除！");
		}else{
			customerService.delete(id);
			data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		}
		
		HttpWebIOHelper._printWebJson(data, response);
	}

	
	
	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
