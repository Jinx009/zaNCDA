package main.entry.webapp.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.helper.ConstantUtil;


@Controller
public class AdminPage {
	/**
	 * 跳转后台登陆页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/login") 
    public String login(){  
        return "/admin/login";  
    }
	
	/**
	 * 跳转后台登陆页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/loginOut") 
    public String loginOut(HttpServletRequest request){  
		request.getSession().setAttribute(ConstantUtil.ADMIN_SESSION,null);
        return "/admin/login";  
    }
	
	/**
	 * 跳转后台兑付
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/page/pay") 
    public String pay(){  
        return "/admin/order/pay";  
    }
	
	
	/**
	 * 跳转后台订单页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/page/order") 
    public String order(){  
        return "/admin/order/index";  
    }
	
	/**
	 * 跳转后台顾客管理
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/page/customer") 
    public String student(){  
        return "/admin/customer/index";  
    }
	
	/**
	 * 跳转后台辅助列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/page/util") 
    public String util(HttpServletRequest request, HttpServletResponse response){  
		return "/admin/util/index";  
    }
	
	/**
	 * 跳转后台主页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/page/tutor") 
    public String index(HttpServletRequest request, HttpServletResponse response){  
		return "/admin/tutor/index";  
    }
	
	/**
	 * 跳转后台主页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/admin/page/edit") 
    public String edit(HttpServletRequest request, HttpServletResponse response){  
		return "/admin/tutor/edit";  
    }
}
