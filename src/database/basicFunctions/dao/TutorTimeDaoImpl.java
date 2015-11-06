package database.basicFunctions.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.TutorTime;

@Repository("tutorTimeDao")
public class TutorTimeDaoImpl extends BaseDaoImpl<TutorTime> implements TutorTimeDao {

	@SuppressWarnings("unchecked")
	public List<TutorTime> getByHql(String hql) {
		Query query = em.createQuery(hql);
		List<TutorTime> list = query.getResultList();
		if(null!=list&&!list.isEmpty()){
			return list;
		}
		return null;
	}

}
