package service.basicFunctions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.CustomerDao;
import database.models.Customer;

@Service("customerService")
public class CustomerServiceImpl  implements CustomerService{
	
	private StringBuffer buffer;
	
	@Autowired
	private CustomerDao customerDao;

	public Customer getById(Integer id) {
		return customerDao.find(id);
	}

	public void update(Customer customer) {
		customerDao.update(customer);
	}

	public Customer save(Customer customer) {
		return customerDao.save(customer);
	}
	
	public void delete(Integer id) {
		customerDao.delete(id);
	}

	
	
	
	
	
	
	
	
	
	
	
	public StringBuffer getBuffer() {
		return buffer;
	}
	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}
}
