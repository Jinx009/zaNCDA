package main.entry.webapp.data;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import common.helper.StringUtil;
import common.helper.tool.message.MsgUtil;
import service.basicFunctions.CommentsService;
import service.basicFunctions.OrderService;
import service.basicFunctions.ScoreService;
import service.basicFunctions.TopicService;
import service.basicFunctions.TradeService;
import service.basicFunctions.TutorService;
import service.basicFunctions.TutorTimeService;
import database.common.PageDataList;
import database.models.Comments;
import database.models.Customer;
import database.models.Order;
import database.models.OrderModel;
import database.models.Score;
import database.models.Topic;
import database.models.Tutor;
import database.models.TutorTime;

@Controller
public class OrderData {

	private Map<String,Object> data;
	private Order order;
	private Tutor tutor;
	private Customer customer;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private CommentsService commentService;
	
	@Autowired
	private TradeService tradeService;
	
	@Autowired
	private TutorService tutorService;
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private TutorTimeService tutorTimeService;

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
		PageDataList<OrderModel> result = null;
		List<OrderModel> model = null;
		if(null!=list){
			result = new PageDataList<OrderModel>();
			result.setPage(list.getPage());
			model = new ArrayList<OrderModel>();
			if(null!=list.getList()){
				for(int i = 0;i<list.getList().size();i++){
					OrderModel orderModel = new OrderModel();
					orderModel = OrderModel.instance(list.getList().get(i));
					orderModel.settName(list.getList().get(i).getqTutor().getRealName());
					orderModel.setcName(list.getList().get(i).getqCustomer().getRealName());
					orderModel.setqName(list.getList().get(i).getTopic().getName());
					
					model.add(orderModel);
				}
			}
		}
		result.setList(model);
		data.put("data",result);
		
		HttpWebIOHelper._printWebJson(data, response);
		
		
	}
	
	/**
	 * 兑付管理数据
	 * @param response
	 * @param request
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/admin/data/orderPay")
	public void orderPayList(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
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
		Integer payStatus = Integer.valueOf(request.getParameter("payStatus"));
		
		
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
		order.setPayStatus(payStatus);
		
		PageDataList<Order> list = orderService.findPagePayList(order,pageNum);
		PageDataList<OrderModel>  result = null;
		List<OrderModel> model = null;
		if(null!=list){
			model = new ArrayList<OrderModel>();
			result = new PageDataList<OrderModel>();
			result.setPage(list.getPage());
			for(int i = 0;i<list.getList().size();i++){
				OrderModel orderModel = new OrderModel();
				orderModel = OrderModel.instance(list.getList().get(i));
				orderModel.setqName(list.getList().get(i).getqTutor().getRealName());
				orderModel.setcName(list.getList().get(i).getqCustomer().getRealName());
				model.add(orderModel);
			}
		}
		result.setList(model);
		data.put("data",result);
		
		HttpWebIOHelper._printWebJson(data, response);
		
		
	}
	
	/**
	 * 更新兑付状态
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value = "/order/data/update",method = RequestMethod.POST)
	public void updatePayOrder(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		order  = orderService.getById(Integer.valueOf(request.getParameter("id")));
		order.setPayMoney(Double.valueOf(request.getParameter("payMoney")));
		order.setReason(request.getParameter("reason"));
		order.setBank(request.getParameter("bank"));
		order.setBankName(request.getParameter("bankName"));
		order.setPayStatus(1);
		order.setProcedures(Double.valueOf(request.getParameter("procedures")));
		order.setPayOkDate(new Date());
		
		orderService.doUpdate(order);
		
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,"保存成功!");
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 取消订单
	 * @param response
	 * @param request
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/customer/data/cacleOrder")
	public void cacleOrder(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
		data = new HashMap<String,Object>();
		Integer orderId = Integer.valueOf(request.getParameter("orderId"));
		String bank = request.getParameter("bank");
		
		order = orderService.getById(orderId);
		order.setReason(request.getParameter("reason"));
		order.setStatus(5);
		order.setCacleDate(new Date());
		order.setBank(bank);
		if(4==order.getStatus()){
			data.put(ConstantUtil.RESULT,ConstantUtil.FAILURE);
			data.put(ConstantUtil.ERROR_MSG,"已互评订单不能取消!");
		}else{
			orderService.doUpdate(order);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			order = orderService.save(order);
			StringBuffer buffer = new StringBuffer();
			buffer.append(tutor.getRealName());
			buffer.append("导师，您好！很抱歉地通知您，才知道平台上的一位学员刚刚取消了您的辅导。");
			buffer.append("预约单号：");
			buffer.append(order.getId());
			buffer.append("，学员姓名：");
			buffer.append(customer.getRealName());
			buffer.append("，手机号码：");
			buffer.append(customer.getUserName());
			buffer.append("，微信号：");
			buffer.append(customer.getWechatName());
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
			buffer.append("您还可以登录才知道导师页面了解具体详情。");
			MsgUtil.sendMsg(tutor.getUserName(),buffer.toString());
			data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
			data.put(ConstantUtil.ERROR_MSG,"取消成功!");
			
		}
		
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
		List<OrderModel> result = null;
		if(null!=list){
			result = new ArrayList<OrderModel>();
			for(int i = 0;i<list.size();i++){
				OrderModel orderModel = new OrderModel();
				orderModel = OrderModel.instance(list.get(i));
				orderModel.setcName(list.get(i).getqCustomer().getRealName());
				orderModel.settName(list.get(i).getqTutor().getRealName());
				if(null!=list.get(i).getTopic().getName()){
					orderModel.setTopicContent(list.get(i).getTopic().getName());
				}else{
					orderModel.setTopicContent(list.get(i).getTopicContent());
				}
				result.add(orderModel);
			}
		}
		
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,result);
		
		HttpWebIOHelper._printWebJson(data, response);
		
		
	}
	
	/**
	 * 顾客订单
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/customer/data/order")
	public void customerOrderList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String, Object>();
		
		customer = (Customer) request.getSession().getAttribute(ConstantUtil.CUSTOMER_SESSION);
		List<OrderModel> orderList = new ArrayList<OrderModel>();
		List<Order> list = orderService.findCustomerList(customer);
		if(null!=list&&!list.isEmpty()){
			for(int i = 0;i<list.size();i++){
				order = list.get(i);
				if(null!=list.get(i).getTopic()){
					order.setTopicContent(list.get(i).getTopic().getName());
				}else{
					order.setTopicContent(list.get(i).getTopicContent());
				}
				OrderModel orderModel = new OrderModel();
				orderModel =  OrderModel.instance(order);
				orderModel.settName(order.getqTutor().getRealName());
				orderModel.setcName(order.getqCustomer().getRealName());
				orderModel.setPhotoPath(order.getqTutor().getPhotoPath());
				orderList.add(orderModel);
			}
		}
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,orderList);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 订单详情
	 * @param response
	 * @param request
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/tutor/data/orderDetail")
	public void tutorOrderDetail(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
		
		Integer id = Integer.valueOf(request.getParameter("id"));
		tutor = (Tutor) request.getSession().getAttribute(ConstantUtil.TUTOR_SESSION);
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		order = orderService.getById(id);
		List<Score> scoreList = scoreService.getByAttr(id);
		List<Comments> commentsList = commentService.getByOrderId(id);
 		
		if(null!=order.getqCustomer().getTrade()){
			map.put("tradeName",order.getqCustomer().getTrade().getTradeName());
		}else{
			map.put("tradeName","");
		}
		if(null!=order.getqCustomer().getJob()){
			map.put("jobName",order.getqCustomer().getJob().getTradeName());
		}else{
			map.put("jobName","");
		}
		if(null!=order.getTopic()){
			order.setTopicContent(order.getTopic().getName());
		}else{
			order.setTopicContent(order.getTopicContent());
		}
		map.put("order",order);
		map.put("score",scoreList);
		map.put("comments",commentsList);
		
		data = new HashMap<String,Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,map);
		
		HttpWebIOHelper._printWebJson(data, response);
		
	}
	
	/**
	 * 保存订单
	 * @param response
	 * @param request
	 * @throws IOException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/tutor/data/saveOrder")
	public void saveOrderDetail(HttpServletResponse response,HttpServletRequest request) throws IOException, ParseException{
		
		Integer topicId = Integer.valueOf(request.getParameter("topicId"));
		Integer tutorId = Integer.valueOf(request.getParameter("tutorId"));
		Integer timeId = Integer.valueOf(request.getParameter("timeId"));
		customer = (Customer) request.getSession().getAttribute(ConstantUtil.CUSTOMER_SESSION);
		Topic topic = topicService.find(topicId);
		tutor = tutorService.find(tutorId);
		TutorTime time = tutorTimeService.getById(timeId);
		time.setStatus(1);
		tutorTimeService.update(time);
		String topicContent = request.getParameter("topicContent");
		
		order = new Order();
		order.setAddTime(new Date());
		order.setPayStatus(0);
		order.setPrice(tutor.getFacePrice());
		order.setProcedures(tutor.getFacePrice()*0.03);
		order.setqCustomer(customer);
		order.setqTutor(tutor);
		order.setStatus(1);
		order.setTopic(topic);
		order.setTopicContent(topicContent);
		order.setUpdateTime(new Date());
		order.setPayTime(new Date());
		order.setqTutorTime(time);
		
		System.out.println("保存数据!");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		order = orderService.save(order);
		StringBuffer buffer = new StringBuffer();
		buffer.append(tutor.getRealName());
		buffer.append("导师，您好！才知道平台上的一位学员刚刚预约了您的辅导，并且已经完成支付。");
		buffer.append("预约单号：");
		buffer.append(order.getId());
		buffer.append("，学员姓名：");
		buffer.append(customer.getRealName());
		buffer.append("，手机号码：");
		buffer.append(customer.getUserName());
		buffer.append("，微信号：");
		buffer.append(customer.getWechatName());
		buffer.append("，辅导日期：");
		buffer.append(sdf.format(time.getRealDate()));
		buffer.append("，辅导时间");
		buffer.append(time.getRealTime());
		buffer.append("，场景主题：");
		if(null!=topic){
			buffer.append(topic.getName());
		}else{
			buffer.append(topicContent);
		}
		buffer.append("您还可以登录才知道导师页面了解具体详情。");
		MsgUtil.sendMsg(tutor.getUserName(),buffer.toString());
		data = new HashMap<String,Object>();
		data.put(ConstantUtil.RESULT,ConstantUtil.SUCCESS);
		data.put(ConstantUtil.ERROR_MSG,order);
		
		HttpWebIOHelper._printWebJson(data, response);
		
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
