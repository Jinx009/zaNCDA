package database.basicFunctions.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.StaticJobName;


@Repository("jobNameDao")
public class JobNameDaoImpl extends BaseDaoImpl<StaticJobName> implements JobNameDao {

	@SuppressWarnings("unchecked")
	public List<StaticJobName> findAllJobName() {
		String hql = "  from StaticJobName order by id desc ";
		Query query = em.createQuery(hql);
		List<StaticJobName> list = query.getResultList();
		
		if(null!=list&&!list.isEmpty()){
			return list;
		}
		return null;
	}
}