package main.entry.webapp.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.helper.ConstantUtil;

import database.models.Customer;
import service.basicFunctions.TutorTimeService;

@Controller
public class TimePage {

	@Autowired
	private TutorTimeService tutorTimeService;
	
	/**
	 * 顾客选择导师时间
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/customer/page/times")
	public String time(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("tutorId",request.getParameter("tutorId"));
		request.setAttribute("topicId",request.getParameter("topicId"));
		Customer customer = (Customer) request.getSession().getAttribute(ConstantUtil.CUSTOMER_SESSION);
		request.setAttribute("openid",customer.getOpenid());
		return "/customer/time";
	}
	
	/**
	 * 顾客选择时间
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/tutor/page/time")
	public String index(HttpServletRequest request,HttpServletResponse response){
		return "/tutor/time";
	}
	
}
