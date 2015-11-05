package main.entry.webapp.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

import common.helper.ConstantUtil;

import database.models.Admin;

public class AdminPage {
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
	@RequestMapping(value = "/admin/page/order") 
    public String order() throws Exception{  
        return "/admin/order";  
    }
	
	/**
	 * 跳转后台顾客管理
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/page/customer") 
    public String student() throws Exception{  
        return "/admin/customer";  
    }
	
	/**
	 * 跳转后台辅助列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/page/util") 
    public String util(HttpServletRequest request, HttpServletResponse response) throws Exception{  
		Admin nbAdminUser = (Admin) request.getSession().getAttribute(ConstantUtil.ADMIN_SESSION);
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
	@RequestMapping(value = "/admin/page/index") 
    public String index(HttpServletRequest request, HttpServletResponse response) throws Exception{  
		Admin nbAdminUser = (Admin) request.getSession().getAttribute(ConstantUtil.ADMIN_SESSION);
		if(null!=nbAdminUser){
			 return "/admin/index";  
		}
		return "/admin/login";  
    }
}
