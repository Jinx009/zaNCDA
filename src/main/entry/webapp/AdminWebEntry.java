package main.entry.webapp;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import common.helper.MD5Util;
import service.basicFunctions.AdminUserService;
import database.models.NbAdminUser;

@Controller
public class AdminWebEntry {
	
	@Autowired
	private AdminUserService adminUserService;
	
	/**
	 * 跳转后台登陆页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/login") 
    public String login() throws Exception{  
        return "/admin/login";  
    }
	
	/**
	 * 跳转后台订单页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/order") 
    public String order() throws Exception{  
        return "/admin/order";  
    }
	
	/**
	 * 跳转后台顾客管理
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/student") 
    public String student() throws Exception{  
        return "/admin/student";  
    }
	
	/**
	 * 跳转后台辅助列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/util") 
    public String util(HttpServletRequest request, HttpServletResponse response) throws Exception{  
		NbAdminUser nbAdminUser = (NbAdminUser) request.getSession().getAttribute("admin_session_user");
		if(null!=nbAdminUser){
			 return "/admin/util";  
		}
		return "/admin/login";  
    }
	
	/**
	 * 跳转后台主页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/index") 
    public String index(HttpServletRequest request, HttpServletResponse response) throws Exception{  
		NbAdminUser nbAdminUser = (NbAdminUser) request.getSession().getAttribute("admin_session_user");
		if(null!=nbAdminUser){
			 return "/admin/index";  
		}
		return "/admin/login";  
    }
	
	/**
	 * 校验登陆方法
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/doLogin",method = RequestMethod.POST) 
    public void doLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{  
		Map<String,Object> data = new HashMap<String, Object>();
		String adminCode = request.getParameter("code");
		
		NbAdminUser nbAdminUser = new NbAdminUser();
		nbAdminUser.setPassword(MD5Util.toMD5(request.getParameter("password")));
		nbAdminUser.setUserName(request.getParameter("userName"));
    	NbAdminUser nbAdminUser2 = adminUserService.doAdminUserLogin(nbAdminUser);
    	
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
	@RequestMapping(value = "/manager/getCode",method = RequestMethod.GET) 
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws Exception{  
		Map<String,Object> data = new HashMap<String,Object>();
		int code = (int) (Math.random() * 9000 + 1000);
		data.put("adminCode",code);
		System.out.println("adminCode:"+code);
		request.getSession().setAttribute("adminCode",code);
		HttpWebIOHelper._printWebJson(data, response);
    }
}
