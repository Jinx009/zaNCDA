package database.basicFunctions.dao;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.Admin;

@Repository("adminDao")
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao {

	@SuppressWarnings("unchecked")
	public Admin doAdminUserLogin(Admin nbAdminUser) {
		String hql = " from NbAdminUser where userName = '"+nbAdminUser.getUserName()+"'  and password = '"+nbAdminUser.getPassword()+"' ";
		Query query = em.createQuery(hql);
		List<Admin> list = query.getResultList();
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

}
