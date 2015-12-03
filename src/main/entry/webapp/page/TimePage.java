package main.entry.webapp.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.helper.ConstantUtil;
import common.helper.tool.util.AgentUtil;
import database.models.Customer;
import database.models.Tutor;
import service.basicFunctions.TutorService;
import service.basicFunctions.TutorTimeService;

@Controller
public class TimePage {

	@Autowired
	private TutorTimeService tutorTimeService;
	@Autowired
	private TutorService tutorService;
	
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
		Tutor tutor = tutorService.find(Integer.valueOf(request.getParameter("tutorId")));
		Customer customer = (Customer) request.getSession().getAttribute(ConstantUtil.CUSTOMER_SESSION);
		if(AgentUtil.judgeAgent(request)){
			request.setAttribute("openid",customer.getOpenid());
		}else{
			request.setAttribute("openid","");
		}
		request.setAttribute("fee",tutor.getFacePrice());
		
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
