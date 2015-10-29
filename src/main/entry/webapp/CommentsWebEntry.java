package main.entry.webapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import common.helper.HttpWebIOHelper;
import database.models.NbCommentsFromTeacher;
import database.models.NbOrder;
import database.models.NbTeacherScore;
import service.basicFunctions.CommentsFromTeacherService;
import service.basicFunctions.OrderService;
import service.basicFunctions.TeacherScoreServie;

public class CommentsWebEntry {

	@Autowired
	private CommentsFromTeacherService commentsFromTeacherService;
	@Autowired 
	private TeacherScoreServie teacherScoreServie;
	@Autowired
	private OrderService orderService;
	
	private Map<String,Object> data;
	
	/**
	 * 约谈详情
	 * @return
	 */
	@RequestMapping(value = "/tpage/commentsDetail")
	public String commentsDetail(){
		return "/teacher/commentDetail";
	}
	
	/**
	 * 获取评论详情
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value = "/tdata/comments",method = RequestMethod.POST)
	public void getComments(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer orderId = Integer.valueOf(request.getParameter("orderId"));
		List<NbCommentsFromTeacher> studentList = commentsFromTeacherService.getByOrderId(orderId);
		List<NbTeacherScore> teacherList = teacherScoreServie.getByOrderId(orderId);
		NbOrder order = orderService.getById(orderId);
		
		data = new HashMap<String, Object>();
		data.put("teacher",teacherList);
		data.put("student",studentList);
		data.put("order",order);
		
		HttpWebIOHelper._printWebJson(data, response);
	}

	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
