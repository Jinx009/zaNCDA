package service.basicFunctions;

import java.util.List;

import database.common.PageDataList;
import database.models.Customer;
import database.models.Order;
import database.models.Tutor;

public interface OrderService {

	public Order getById(Integer id);
	
	public void  delete(Integer id);
	
	public void doUpdate(Order order);
	
	public Order save(Order order);

	public PageDataList<Order> findPageList(Order order, int pageNum);

	public List<Order> findAll();

	public List<Order> findTutorList(Tutor tutor);

	public List<Order> findCustomerList(Customer customer);

	public PageDataList<Order> findPagePayList(Order order, int pageNum);

	public void checkOne();

	public void checkTwo();

	public void checkThree();

	public void checkFour();
	
}
