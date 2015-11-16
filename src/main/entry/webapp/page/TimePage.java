package main.entry.webapp.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import service.basicFunctions.TutorTimeService;

@Controller
public class TimePage {

	@Autowired
	private TutorTimeService tutorTimeService;
	
	@RequestMapping(value = "/customer/page/time")
	public String time(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("tutorId",request.getParameter("tutorId"));
		request.setAttribute("topicId",request.getParameter("topicId"));
		return "/customer/time";
	}
	
}
