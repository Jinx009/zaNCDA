package database.basicFunctions.dao;

import database.common.BaseDao;
import database.models.Customer;

public interface CustomerDao extends BaseDao<Customer>{

	public Customer getByHql(String hql);
	
}
