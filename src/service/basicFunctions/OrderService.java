package service.basicFunctions;

import database.common.PageDataList;
import database.models.Order;

public interface OrderService {

	public Order getById(Integer id);
	
	public void  delete(Integer id);
	
	public void update(Order order);
	
	public Order save(Order order);

	public PageDataList<Order> findPageList(Order order, int pageNum);
	
}
