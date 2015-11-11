package service.basicFunctions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.helper.MD5Util;
import database.basicFunctions.dao.CustomerDao;
import database.common.PageDataList;
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
	
	public Customer getByOpenid(String openid) {
		buffer = new StringBuffer();
		
		buffer.append(" FROM Customer ");
		buffer.append(" WHERE  ");
		buffer.append(" openid = '");
		buffer.append(openid);
		buffer.append("'  ");
		buffer.append(" ORDER BY loginTime DESC ");
		
		return customerDao.getByHql(buffer.toString());
	}

	public Customer doLogin(Customer customer) {
		buffer = new StringBuffer();
		
		buffer.append(" FROM Customer ");
		buffer.append(" WHERE ");
		buffer.append(" userName = '");
		buffer.append(customer.getUserName());
		buffer.append("' AND pwd = '");
		buffer.append(MD5Util.toMD5(customer.getPwd()));
		buffer.append("' ");
		
		return customerDao.getByHql(buffer.toString());
	}
	
	public PageDataList<Customer> findPageList(Customer customer, int pageNum) {
		return customerDao.findTeacherPageList(customer,pageNum);
	}
	
	
	
	
	
	
	
	
	public StringBuffer getBuffer() {
		return buffer;
	}
	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}
}
