package main.entry.webapp.page;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import database.models.Order;
import service.basicFunctions.OrderService;
import service.basicFunctions.TutorService;

@Controller
public class OrderPage {

	private Order order;
	
	@Autowired
	private OrderService orderService;
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
	 * 取消订单
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/customer/page/cacleOrder")
	public String cacle(ModelMap modelMap,HttpServletRequest request){
		modelMap.addAttribute("orderId",request.getParameter("orderId"));
		return "/customer/cacleOrder";
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
	 * 导师评语
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/customer/page/comments")
	public String comments(HttpServletRequest req){
		Integer orderId = Integer.valueOf(req.getParameter("orderId"));
		order = orderService.getById(orderId);
		req.setAttribute("order",order);
		return "/customer/comments";
	}
	
	
	/**
	 * 顾客订单详情
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/customer/page/orderDetail")
	public String customerOrderDetail(HttpServletRequest request){
		Integer orderId = Integer.valueOf(request.getParameter("orderId"));
		order = orderService.getById(orderId);
		request.setAttribute("order",order);
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


	public void setOrder(Order order) {
		this.order = order;
	}
}
