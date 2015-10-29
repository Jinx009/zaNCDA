package database.basicFunctions.dao;

import java.util.List;

import database.common.BaseDao;
import database.common.PageDataList;
import database.models.NbOrder;

public interface OrderDao extends BaseDao<NbOrder> {

	PageDataList<NbOrder> findTeacherPageList(NbOrder nbOrder, int pageNum,int pageSize);

	public List<NbOrder> findByHql(String hql);

}