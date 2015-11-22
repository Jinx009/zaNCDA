package main.entry.webapp.data;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import service.basicFunctions.CommentsService;
import service.basicFunctions.OrderService;
import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import database.models.Comments;
import database.models.Order;

@Controller
public class CommentsData {

	private Map<String,Object> data;
	private List<Comments> list;
	private Order order;
	private Comments comments;
	
	@Autowired
	private CommentsService commentsService;
	
	@Autowired
	private OrderService orderService;

	/**
	 * 导师小结
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/comments/data/comments",method=RequestMethod.POST)
	public void commentsData(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer id = Integer.valueOf(request.getParameter("id"));
		
		list = commentsService.getByOrderId(id);
		
		data = new HashMap<String, Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 新增导师小结
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/comments/data/save",method=RequestMethod.POST)
	public void commentsSave(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer id = Integer.valueOf(request.getParameter("id"));
		order = orderService.getById(id);
		comments = new Comments();
		
		comments.setAddTime(new Date());
		comments.setAdviceOne(request.getParameter("adviceOne"));
		comments.setAdviceThree(request.getParameter("adviceThree"));
		comments.setAdviceTwo(request.getParameter("adviceTwo"));
		comments.setAppealOne(request.getParameter("appealOne"));
		comments.setAppealThree(request.getParameter("appealThree"));
		comments.setAppealTwo(request.getParameter("appealTwo"));
		comments.setOrder(order);
		comments.setQuestion(request.getParameter("question"));
		comments.setSolveAssess(request.getParameter("solveAssess"));
		comments.setSolveResult(request.getParameter("solveResult"));
		comments.setSolveStatus(request.getParameter("solveStatus"));
		comments.setSolveTool(request.getParameter("solveTool"));
		
		commentsService.save(comments);
		
		order = orderService.getById(id);
		if(2==order.getStatus()){
			order.setStatus(4);
		}else{
			order.setStatus(3);
		}
		orderService.doUpdate(order);
		
		data = new HashMap<String, Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,"新增成功！");
		
		HttpWebIOHelper._printWebJson(data, response);
	}

	
	
	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public void setList(List<Comments> list) {
		this.list = list;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setComments(Comments comments) {
		this.comments = comments;
	}
}
