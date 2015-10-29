package service.basicFunctions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.OrderDao;
import database.common.PageDataList;
import database.models.NbOrder;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao orderDao;
	
	public PageDataList<NbOrder> findPageList(NbOrder nbOrder, int pageNum,int pageSize) {
		
		return orderDao.findTeacherPageList(nbOrder,pageNum,pageSize);
	}

	public List<NbOrder> findByAttr(String attrName, int id) {
		String hql = " from NbOrder where "+attrName+".id = "+id+"  ";
		return orderDao.findByHql(hql);
	}

}
