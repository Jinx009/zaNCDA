package main.entry.webapp.data;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import common.helper.MD5Util;

import database.models.Admin;
import service.basicFunctions.AdminService;

public class AdminData {
	
	@Autowired
	private AdminService adminUserService;
	
	/**
	 * 校验登陆方法
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/data/doLogin",method = RequestMethod.POST) 
    public void doLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{  
		Map<String,Object> data = new HashMap<String, Object>();
		String adminCode = request.getParameter("code");
		
		Admin nbAdminUser = new Admin();
		nbAdminUser.setPassword(MD5Util.toMD5(request.getParameter("password")));
		nbAdminUser.setUserName(request.getParameter("userName"));
    	Admin nbAdminUser2 = adminUserService.doAdminUserLogin(nbAdminUser);
    	
    	data.put(ConstantUtil.RESULT,ConstantUtil.FAILURE);
    	
    	if(!adminCode.equals(request.getSession().getAttribute("adminCode").toString())||null==adminCode){
    		data.put(ConstantUtil.ERROR_MSG,"验证码不正确!");
    	}
    	else if(null==nbAdminUser2){
    		data.put(ConstantUtil.ERROR_MSG,"账号或密码不正确!");
    	}
    	else{
    		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
    		data.put(ConstantUtil.ERROR_MSG,"登陆成功!");
    		request.getSession().setAttribute("admin_session_user",nbAdminUser2);
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
		Map<String,Object> data = new HashMap<String,Object>();
		int code = (int) (Math.random() * 9000 + 1000);
		data.put("adminCode",code);
		System.out.println("adminCode:"+code);
		request.getSession().setAttribute("adminCode",code);
		HttpWebIOHelper._printWebJson(data, response);
    }
}
