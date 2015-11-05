package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.Customer;

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao{

}
