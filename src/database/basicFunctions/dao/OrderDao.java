package database.basicFunctions.dao;

import database.common.BaseDao;
import database.common.PageDataList;
import database.models.Order;

public interface OrderDao extends BaseDao<Order>{

	public PageDataList<Order> findPageList(Order order, int pageNum);

}
