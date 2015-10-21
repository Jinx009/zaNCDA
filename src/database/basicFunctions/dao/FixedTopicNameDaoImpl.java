package database.basicFunctions.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.StaticFixedTopicName;


@Repository("fixedTopicNameDao")
public class FixedTopicNameDaoImpl extends BaseDaoImpl<StaticFixedTopicName> implements FixedTopicNameDao {

	@SuppressWarnings("unchecked")
	public List<StaticFixedTopicName> findListByHql(String hql) {
		Query query = em.createQuery(hql);
		
		List<StaticFixedTopicName> list = query.getResultList();
		return list;
	}

}