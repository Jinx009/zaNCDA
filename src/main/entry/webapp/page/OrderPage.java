package main.entry.webapp.page;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import service.basicFunctions.TutorService;
import database.models.Customer;
import database.models.Order;
import database.models.Tutor;

@Controller
public class OrderPage {

	private Order order;
	private Tutor tutor;
	private Customer customer;
	private Map<String,Object> data;
	
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public TutorService getTutorService() {
		return tutorService;
	}

	public void setTutorService(TutorService tutorService) {
		this.tutorService = tutorService;
	}
}
