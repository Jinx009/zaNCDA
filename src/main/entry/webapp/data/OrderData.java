package main.entry.webapp.data;

import java.io.IOException;
import java.text.ParseException;
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

import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import common.helper.StringUtil;
import service.basicFunctions.CommentsService;
import service.basicFunctions.OrderService;
import service.basicFunctions.ScoreService;
import database.common.PageDataList;
import database.models.Comments;
import database.models.Customer;
import database.models.Order;
import database.models.Score;
import database.models.Tutor;

@Controller
public class OrderData {

	private Map<String,Object> data;
	
	private Order order;
	
	private List<Order> list;
	
	private Tutor tutor;
	
	private Customer customer;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private CommentsService commentService;

	@RequestMapping(value = "/admin/data/order")
	public void orderList(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
		data = new HashMap<String,Object>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null,endDate = null;
		if(StringUtil.isNotBlank(request.getParameter("startDate"))){
			startDate = sdf.parse(request.getParameter("startDate"));
		}
		if(StringUtil.isNotBlank(request.getParameter("endDate"))){
			endDate = sdf.parse(request.getParameter("endDate"));
		}
		int pageNum = Integer.valueOf(request.getParameter("pageNum"));
		String tutorName = request.getParameter("tutorName");
		String tutorPhone = request.getParameter("tutorPhone");
		String customerName = request.getParameter("customerName");
		String customerPhone = request.getParameter("customerPhone");
		
		
		order = new Order();
		tutor = new Tutor();
		customer = new Customer();
		tutor.setRealName(tutorName);
		tutor.setMobilePhone(tutorPhone);
		customer.setRealName(customerName);
		customer.setMobilePhone(customerPhone);
		order.setqTutor(tutor);
		order.setqCustomer(customer);
		order.setAddTime(startDate);
		order.setUpdateTime(endDate);
		
		PageDataList<Order> list = orderService.findPageList(order,pageNum);
		data.put("data",list);
		
		HttpWebIOHelper._printWebJson(data, response);
		
		
	}
	
	/**
	 * 导师订单列表
	 * @param response
	 * @param request
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/tutor/data/order")
	public void tutorOrderList(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
		data = new HashMap<String,Object>();
		
		tutor = (Tutor) request.getSession().getAttribute(ConstantUtil.TUTOR_SESSION);
		List<Order> list = orderService.findTutorList(tutor);
		
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,list);
		
		HttpWebIOHelper._printWebJson(data, response);
		
		
	}
	
	@RequestMapping(value = "/tutor/data/orderDetail")
	public void tutorOrderDetail(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
		
		Integer id = Integer.valueOf(request.getParameter("id"));
		tutor = (Tutor) request.getSession().getAttribute(ConstantUtil.TUTOR_SESSION);
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		order = orderService.getById(id);
		List<Score> scoreList = scoreService.getByAttr(id);
		List<Comments> commentsList = commentService.getByOrderId(id);
 		
		map.put("order",order);
		map.put("score",scoreList);
		map.put("comments",commentsList);
		
		data = new HashMap<String,Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,map);
		
		HttpWebIOHelper._printWebJson(data, response);
		
		
	}
	
	
	
	public List<Order> getList() {
		return list;
	}

	public void setList(List<Order> list) {
		this.list = list;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
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
	
}
