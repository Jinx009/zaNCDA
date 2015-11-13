package main.entry.webapp.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.basicFunctions.AdminService;

import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import common.helper.MD5Util;

import database.models.Admin;

@Controller
public class AdminData {
	
	@Autowired
	private AdminService adminService;
	
	private Admin admin;
	private Map<String,Object> data;
	
	/**
	 * 校验登陆方法
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/data/login",method = RequestMethod.POST) 
    public void doLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{  
		data = new HashMap<String, Object>();
		String loginCode = request.getParameter("code");
		
		admin = new Admin();
		admin.setPwd(MD5Util.toMD5(request.getParameter("pwd")));
		admin.setUserName(request.getParameter("userName"));
		admin = adminService.doLogin(admin);
		
		System.out.println(request.getSession().getAttribute(ConstantUtil.ADMIN_CODE)+"---");
    	
    	data.put(ConstantUtil.RESULT,ConstantUtil.FAILURE);
    	if(null==loginCode||!loginCode.equals(request.getSession().getAttribute(ConstantUtil.ADMIN_CODE).toString())){
    		data.put(ConstantUtil.ERROR_MSG,"验证码不正确!");
    	}
    	else if(null==admin){
    		data.put(ConstantUtil.ERROR_MSG,"账号或密码不正确!");
    	}
    	else{
    		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
    		data.put(ConstantUtil.ERROR_MSG,"登陆成功!");
    		request.getSession().setAttribute(ConstantUtil.ADMIN_SESSION,admin);
    		
    		admin.setLoginTime(new Date());
    		adminService.update(admin);
    	}
    	HttpWebIOHelper._printWebJson(data, response);
    }
	
	/**
	 * 获取验证码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/data/getCode",method = RequestMethod.GET) 
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws Exception{  
		data = new HashMap<String,Object>();
		int code = (int) (Math.random() * 9000 + 1000);
		data.put(ConstantUtil.ADMIN_CODE,code);
		System.out.println("adminCode:"+code);
		request.getSession().setAttribute(ConstantUtil.ADMIN_CODE,code);
		HttpWebIOHelper._printWebJson(data, response);
    }

	 
	
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
