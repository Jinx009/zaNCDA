package database.basicFunctions.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.Area;

@Repository("areaDao")
public class AreaDaoImpl extends BaseDaoImpl<Area> implements AreaDao{

	@SuppressWarnings("unchecked")
	public List<Area> getByHql(String hql) {
		Query query = em.createQuery(hql);
		List<Area> list = query.getResultList();
		if(null!=list&&!list.isEmpty()){
			return list;
		}
		return null;
	}

}
