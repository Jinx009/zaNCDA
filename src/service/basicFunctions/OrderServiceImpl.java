package service.basicFunctions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.OrderDao;
import database.common.PageDataList;
import database.models.Order;

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

	public void update(Order order) {
		orderDao.update(order);
	}

	public Order save(Order order) {
		return orderDao.save(order);
	}

	public PageDataList<Order> findPageList(Order order, int pageNum) {
		return orderDao.findPageList(order,pageNum);
	}
	
	
	
	
	
	
	
	
	
	
	
	public StringBuffer getBuffer() {
		return buffer;
	}

	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}

}
