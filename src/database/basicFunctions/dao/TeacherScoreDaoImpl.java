package database.basicFunctions.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.NbTeacherScore;


@Repository("teacherScoreDao")
public class TeacherScoreDaoImpl extends BaseDaoImpl<NbTeacherScore> implements TeacherScoreDao {

	@SuppressWarnings("unchecked")
	public List<NbTeacherScore> getByOrderId(int orderId) {
		String hql = "  from NbTeacherScore where orderId = "+orderId+" ";
	
		Query query = em.createQuery(hql);
		List<NbTeacherScore> list = query.getResultList();
		
		if(null!=list&&!list.isEmpty()){
			return list;
		}
		
		return null;
	}

}