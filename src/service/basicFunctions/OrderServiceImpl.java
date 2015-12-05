package service.basicFunctions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.OrderDao;
import database.common.PageDataList;
import database.models.Customer;
import database.models.Order;
import database.models.Tutor;

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
		buffer.append(" AND status <=4   ");
		buffer.append(" ORDER BY addTime DESC ,status DESC ");
		
		return orderDao.getByHql(buffer.toString());
	}
	
	
	
	
	
	
	
	public StringBuffer getBuffer() {
		return buffer;
	}

	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}


}
