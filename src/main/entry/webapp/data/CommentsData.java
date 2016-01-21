package main.entry.webapp.data;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import common.helper.tool.message.MsgUtil;
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
		}
		if(order.getStatus()<4&&2!=order.getStatus()){
			order.setStatus(3);
		}
		StringBuffer buffer = new StringBuffer();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		buffer.append(order.getqCustomer().getRealName());
		buffer.append("同学，您好！");
		buffer.append("才知道特别提醒，");
		buffer.append(order.getqTutor().getRealName());
		buffer.append("导师已经提交你的辅导报告。你可以随时登录预约辅导页面，点击约谈管理了解详情。谢谢！");
		buffer.append("预约单号：");
		buffer.append(order.getId());
		buffer.append("导师姓名：");
		buffer.append(order.getqTutor().getRealName());
		buffer.append("，手机号码：");
		buffer.append(order.getqTutor().getUserName());
		buffer.append("，微信号：");
		buffer.append(order.getqTutor().getWechatName());
		buffer.append("，辅导日期：");
		buffer.append(sdf.format(order.getqTutorTime().getRealDate()));
		buffer.append("，辅导时间");
		buffer.append(order.getqTutorTime().getRealTime());
		buffer.append("，场景主题：");
		if(null!=order.getTopic()){
			buffer.append(order.getTopic().getName());
		}else{
			buffer.append(order.getTopicContent());
		}
		MsgUtil.sendMsg(order.getqCustomer().getUserName(),buffer.toString());
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
