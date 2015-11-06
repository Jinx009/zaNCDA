package database.basicFunctions.dao;

import java.util.List;

import database.common.BaseDao;
import database.models.Topic;

public interface TopicDao extends BaseDao<Topic>{

	public List<Topic> getByHql(String hql);

}
