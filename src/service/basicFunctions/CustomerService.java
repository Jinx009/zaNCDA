package service.basicFunctions;

import database.models.Customer;

public interface CustomerService {
	
	public Customer getById(Integer id);
	
	public void update(Customer customer);
	
	public Customer save(Customer customer);
	
	public void delete(Integer id);

	public Customer getByOpenid(String openid);

	public Customer doLogin(Customer customer);
	
}
