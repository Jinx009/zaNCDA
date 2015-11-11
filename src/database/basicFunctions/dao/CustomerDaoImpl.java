package database.basicFunctions.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import common.helper.ConstantUtil;
import common.helper.StringUtil;

import database.common.BaseDaoImpl;
import database.common.PageDataList;
import database.common.QueryParam;
import database.models.Customer;

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao{

	@SuppressWarnings("unchecked")
	public Customer getByHql(String hql) {
		Query query = em.createQuery(hql);
		List<Customer> list = query.getResultList();
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public PageDataList<Customer> findTeacherPageList(Customer customer,int pageNum) {
		QueryParam param = QueryParam.getInstance().addPage(pageNum,ConstantUtil.PAGE_SIZE);
		
		if (StringUtil.isNotBlank(customer.getRealName())){
			param.addParam("realName",customer.getRealName());
		}
		if (StringUtil.isNotBlank(customer.getMobilePhone())){
			param.addParam("mobilePhone",customer.getMobilePhone());
		}
		PageDataList<Customer> pageDataList = super.findPageList(param);
		
		return pageDataList;
	}

}
