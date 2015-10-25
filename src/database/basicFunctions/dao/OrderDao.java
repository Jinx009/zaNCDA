package database.basicFunctions.dao;

import database.common.BaseDao;
import database.common.PageDataList;
import database.models.NbOrder;

public interface OrderDao extends BaseDao<NbOrder> {

	PageDataList<NbOrder> findTeacherPageList(NbOrder nbOrder, int pageNum,
			int pageSize);

}