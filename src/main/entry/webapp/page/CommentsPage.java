package main.entry.webapp.page;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import service.basicFunctions.OrderService;
import database.models.Order;


@Controller
public class CommentsPage {

	private Order order;
	
	@Autowired
	private OrderService orderService;

	/**
	 * 导师评语
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/tutor/page/comments")
	public String index(HttpServletRequest request,HttpServletResponse response){
		Integer id = Integer.valueOf(request.getParameter("id"));
		order = orderService.getById(id);
		request.setAttribute("order",order);
		return "/tutor/comments";
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
