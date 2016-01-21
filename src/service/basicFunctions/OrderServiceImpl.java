package service.basicFunctions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.helper.tool.message.MsgUtil;

import database.basicFunctions.dao.OrderDao;
import database.common.PageDataList;
import database.models.Customer;
import database.models.Order;
import database.models.OrderModel;
import database.models.Tutor;
import database.models.TutorTime;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	private StringBuffer buffer;
	
	@Autowired
	private OrderDao orderDao;
	
	public Order getById(Integer id) {
		return orderDao.find(id);
	}

	public void delete(Integer id) {
		orderDao.delete(id);
	}

	public void doUpdate(Order order) {
		orderDao.update(order);
	}

	public Order save(Order order) {
		return orderDao.save(order);
	}

	public PageDataList<Order> findPageList(Order order, int pageNum) {
		return orderDao.findPageList(order,pageNum);
	}
	
	public PageDataList<Order> findPagePayList(Order order, int pageNum) {
		return orderDao.findPagePayList(order,pageNum);
	}

	
	public List<Order> findAll() {
		return orderDao.findAll();
	}
	

	public List<Order> findTutorList(Tutor tutor) {
		buffer = new StringBuffer();
		
		buffer.append(" FROM  Order ");
		buffer.append(" WHERE ");
		buffer.append(" qTutor.id = ");
		buffer.append(tutor.getId());
		buffer.append(" ORDER BY addTime DESC ");
		
		return orderDao.getByHql(buffer.toString());
	}
	
	public List<Order> findCustomerList(Customer customer) {
		buffer = new StringBuffer();
		
		buffer.append(" FROM Order ");
		buffer.append(" WHERE ");
		buffer.append(" qCustomer.id = ");
		buffer.append(customer.getId());
		buffer.append(" AND status <=5   ");
		buffer.append(" ORDER BY addTime DESC ,status DESC ");
		
		return orderDao.getByHql(buffer.toString());
	}
	
	public void checkOne() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" FROM Order WHERE payStatus==0 ");
		List<Order> list = orderDao.getByHql(buffer.toString());
		if(null!=list&&!list.isEmpty()){
			for(int i = 0;i<list.size();i++){
				Order order= list.get(i);
				TutorTime tutorTime = order.getqTutorTime();
				Tutor tutor = order.getqTutor();
				Customer customer = order.getqCustomer();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				if(isSameDate(tutorTime.getRealDate())){
					buffer = new StringBuffer();
					buffer.append(tutor.getRealName());
					buffer.append("导师，您好！才知道特别提醒您，请记得准时出席明天的预约辅导。谢谢！");
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
					buffer = new StringBuffer();
					buffer.append(customer.getRealName());
					buffer.append("学员，您好！才知道特别提醒，记得准时出席明天的预约辅导哦~~ 加油!");
					buffer.append("预约单号：");
					buffer.append(order.getId());
					buffer.append("，导师姓名：");
					buffer.append(tutor.getRealName());
					buffer.append("，手机号码：");
					buffer.append(tutor.getUserName());
					buffer.append("，微信号：");
					buffer.append(tutor.getWechatName());
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
					buffer.append("您还可以登录才知道学员页面了解具体详情。");
					MsgUtil.sendMsg(tutor.getUserName(),buffer.toString());
				}
			}
		}
	}

	public void checkTwo() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" FROM Order WHERE payStatus==0 ");
		List<Order> list = orderDao.getByHql(buffer.toString());
		if(null!=list&&!list.isEmpty()){
			for(int i = 0;i<list.size();i++){
				Order order= list.get(i);
				TutorTime tutorTime = order.getqTutorTime();
				Tutor tutor = order.getqTutor();
				Customer customer = order.getqCustomer();
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				if(isSameDate2(tutorTime.getRealDate())){
					buffer = new StringBuffer();
					buffer.append(tutor.getRealName());
					buffer.append("导师，您好！才知道特别提醒您，请为");
					buffer.append(customer.getRealName());
					buffer.append("学员编写小结。谢谢！");
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
					buffer = new StringBuffer();
					buffer.append(customer.getRealName());
					buffer.append("学员，您好！才知道特别提醒，");
					buffer.append(tutor.getRealName());
					buffer.append("导师做出评价。谢谢！");
					buffer.append("预约单号：");
					buffer.append(order.getId());
					buffer.append("，导师姓名：");
					buffer.append(tutor.getRealName());
					buffer.append("，手机号码：");
					buffer.append(tutor.getUserName());
					buffer.append("，微信号：");
					buffer.append(tutor.getWechatName());
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
					buffer.append("您还可以登录才知道学员页面了解具体详情。");
					MsgUtil.sendMsg(tutor.getUserName(),buffer.toString());
				}
			}
		}
	}

	public void checkThree() {
		
	}

	public void checkFour() {
		
	}
	
	
	@SuppressWarnings("static-access")
	private static boolean isSameDate(Date date2) {
	       Calendar cal1 = Calendar.getInstance();
	       cal1.setTime(new Date());
	       cal1.add(cal1.DATE,1);

	       Calendar cal2 = Calendar.getInstance();
	       cal2.setTime(date2);

	       boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
	       boolean isSameMonth = isSameYear&& cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
	       boolean isSameDate = isSameMonth&& cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

	       return isSameDate;
	   }
	
	
	@SuppressWarnings("static-access")
	private static boolean isSameDate2(Date date2) {
	       Calendar cal1 = Calendar.getInstance();
	       cal1.setTime(new Date());

	       Calendar cal2 = Calendar.getInstance();
	       cal2.setTime(date2);
	       cal2.add(cal2.DATE,1);

	       boolean isSameYear = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
	       boolean isSameMonth = isSameYear&& cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
	       boolean isSameDate = isSameMonth&& cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);

	       return isSameDate;
	   }
	
	public StringBuffer getBuffer() {
		return buffer;
	}

	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}
}
