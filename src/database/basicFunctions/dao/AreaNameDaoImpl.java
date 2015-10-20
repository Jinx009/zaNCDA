package database.basicFunctions.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.StaticAreaName;

@Repository("areaNameDao")
public class AreaNameDaoImpl extends BaseDaoImpl<StaticAreaName> implements AreaNameDao {

	@SuppressWarnings("unchecked")
	public List<StaticAreaName> findAllAreaName() {
		String hql = " from StaticAreaName order by id desc ";
		Query query = em.createQuery(hql);
		List<StaticAreaName> list = query.getResultList();
		if(null!=list&&!list.isEmpty()){
			return list;
		}
		return null;
	}

}
