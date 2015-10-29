package service.basicFunctions;

import java.util.List;

import database.common.PageDataList;
import database.models.NbOrder;

public interface OrderService {
	
	public PageDataList<NbOrder> findPageList(NbOrder nbOrder,int pageNum,int pageSize);

	public List<NbOrder> findByAttr(String attrName, int id);
}
