package database.basicFunctions.dao;

import database.common.BaseDao;
import database.common.PageDataList;
import database.models.Customer;

public interface CustomerDao extends BaseDao<Customer>{

	public Customer getByHql(String hql);

	public PageDataList<Customer> findTeacherPageList(Customer customer,int pageNum);
	
}
