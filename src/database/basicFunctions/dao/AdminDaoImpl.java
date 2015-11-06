package database.basicFunctions.dao;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.Admin;

@Repository("adminDao")
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao {

	private StringBuffer buffer;
	
	@SuppressWarnings("unchecked")
	public Admin doLogin(Admin admin) {
		
		buffer = new StringBuffer();
		
		buffer.append(" FROM Admin  ");
		buffer.append(" WHERE userName = '");
		buffer.append(admin.getUserName());
		buffer.append("' AND ");
		buffer.append(" pwd = '");
		buffer.append(admin.getPwd());
		buffer.append("' ");
		
		Query query = em.createQuery(buffer.toString());
		List<Admin> list = query.getResultList();
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public StringBuffer getBuffer() {
		return buffer;
	}
	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}
}
