package main.entry.webapp.page;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import service.basicFunctions.TutorService;

@Controller
public class OrderPage {

	@Autowired
	private TutorService tutorService;
	
	/**
	 * 导师订单详情
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/tutor/page/orderDetail")
	public String tutorOrderDetail(ModelMap modelMap,HttpServletRequest request){
		modelMap.addAttribute("orderId",request.getParameter("orderId"));
		return "/tutor/orderDetail";
	}
	
	/**
	 * 导师订单列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/tutor/page/order")
	public String tutorOrderList(){
		return "/tutor/order";
	}
	
	/**
	 * 顾客订单详情
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/customer/page/orderDetail")
	public String customerOrderDetail(ModelMap modelMap,HttpServletRequest request){
		modelMap.addAttribute("orderId",request.getParameter("orderId"));
		return "/customer/orderDetail";
	}
	
	/**
	 * 顾客订单列表
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/customer/page/order")
	public String customerOrderList(){
		return "/customer/order";
	}

	
	
	
	
	
	
}
