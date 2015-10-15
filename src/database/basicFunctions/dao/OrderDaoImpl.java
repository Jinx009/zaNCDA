package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.NbOrder;


@Repository("orderDao")
public class OrderDaoImpl extends BaseDaoImpl<NbOrder> implements OrderDao {

}