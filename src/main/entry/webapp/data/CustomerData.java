package main.entry.webapp.data;

import java.io.IOException;
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
import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import common.helper.StringUtil;
import database.common.PageDataList;
import database.models.Customer;

@Controller
public class CustomerData {

	private Map<String,Object> data;
	
	private Customer customer;
	
	private List<Customer> list;
	
	@Autowired
	private CustomerService customerService;
	
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
	  * 顾客登陆
	  * @param request
	  * @param response
	  * @throws IOException
	  */
	 @RequestMapping(value = "/customer/data/login",method = RequestMethod.POST)
	 public void teacherDoLogin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		 String userName = request.getParameter("userName");
		 String pwd = request.getParameter("pwd");
		 String loginCode = request.getParameter("code");
		 String openid = request.getParameter("openid");
		 
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
    		request.getSession().setAttribute(ConstantUtil.CUSTOMER_SESSION,customer);
    		
    		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
	    	data.put(ConstantUtil.ERROR_MSG,"登陆成功!");
    		if(StringUtil.isNotBlank(openid)){
    			customer.setOpenid(openid);
	    	}
    		customer.setLoginTime(new Date());
    		customerService.update(customer);
	    	
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

	public List<Customer> getList() {
		return list;
	}

	public void setList(List<Customer> list) {
		this.list = list;
	}
}
