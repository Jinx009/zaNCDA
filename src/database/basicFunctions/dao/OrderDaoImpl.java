package database.basicFunctions.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import common.helper.ConstantUtil;
import common.helper.StringUtil;
import database.common.BaseDaoImpl;
import database.common.PageDataList;
import database.common.QueryParam;
import database.common.SearchFilter.Operators;
import database.models.Order;

@Repository("orderDao")
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao{

	public PageDataList<Order> findPageList(Order order, int pageNum) {
		QueryParam param = QueryParam.getInstance().addPage(pageNum,ConstantUtil.PAGE_SIZE);
		
		if (StringUtil.isNotBlank(order.getqTutor().getRealName())){
			param.addParam("qTutor.realName",order.getqTutor().getRealName());
		}
		if (StringUtil.isNotBlank(order.getqTutor().getMobilePhone())){
			param.addParam("qTutor.mobilePhone",order.getqTutor().getMobilePhone());
		}
		if(StringUtil.isNotBlank(order.getqCustomer().getRealName())){
			param.addParam("qCustomer.realName",order.getqCustomer().getRealName());
		}
		if(StringUtil.isNotBlank(order.getqCustomer().getMobilePhone())){
			param.addParam("qCustomer.mobilePhone",order.getqCustomer().getMobilePhone());
		}
		if (null!=order.getAddTime()) {
			param.addParam("addTime", Operators.GTE,order.getAddTime());
		}
		if (null!=order.getUpdateTime()) {
			param.addParam("addTime", Operators.LTE,order.getAddTime());
		}
		PageDataList<Order> pageDataList = super.findPageList(param);
		
		return pageDataList;
	}

	@SuppressWarnings("unchecked")
	public List<Order> getByHql(String hql) {
		Query query = em.createQuery(hql);
		List<Order> list = query.getResultList();
		if(null!=list&&!list.isEmpty()){
			return list;
		}
		return null;
	}

}
