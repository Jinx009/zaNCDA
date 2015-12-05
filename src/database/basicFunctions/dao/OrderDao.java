package database.basicFunctions.dao;

import java.util.List;

import database.common.BaseDao;
import database.common.PageDataList;
import database.models.Order;

public interface OrderDao extends BaseDao<Order>{

	public PageDataList<Order> findPageList(Order order, int pageNum);

	public List<Order> getByHql(String hql);

	public PageDataList<Order> findPagePayList(Order order, int pageNum);

}
