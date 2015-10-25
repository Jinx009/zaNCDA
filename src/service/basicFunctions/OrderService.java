package service.basicFunctions;

import database.common.PageDataList;
import database.models.NbOrder;

public interface OrderService {
	
	public PageDataList<NbOrder> findPageList(NbOrder nbOrder,int pageNum,int pageSize);
}
