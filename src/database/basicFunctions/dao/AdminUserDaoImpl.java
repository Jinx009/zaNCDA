package database.basicFunctions.dao;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.NbAdminUser;

@Repository("adminUserDao")
public class AdminUserDaoImpl extends BaseDaoImpl<NbAdminUser> implements AdminUserDao {

	@SuppressWarnings("unchecked")
	public NbAdminUser doAdminUserLogin(NbAdminUser nbAdminUser) {
		String hql = " from NbAdminUser where userName = '"+nbAdminUser.getUserName()+"'  and password = '"+nbAdminUser.getPassword()+"' ";
		Query query = em.createQuery(hql);
		List<NbAdminUser> list = query.getResultList();
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

}
