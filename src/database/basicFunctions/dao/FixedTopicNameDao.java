package database.basicFunctions.dao;

import java.util.List;

import database.common.BaseDao;
import database.models.StaticFixedTopicName;

public interface FixedTopicNameDao extends BaseDao<StaticFixedTopicName> {

	public List<StaticFixedTopicName> findListByHql(String hql);

}