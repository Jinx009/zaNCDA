package database.basicFunctions.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.Tutor;

@Repository("tutorDao")
public class TutorDaoImpl extends BaseDaoImpl<Tutor> implements TutorDao{

	@SuppressWarnings("unchecked")
	public Tutor getByHql(String hql) {
		Query query = em.createQuery(hql);
		List<Tutor> list = query.getResultList();
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

}
