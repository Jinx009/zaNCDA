package service.basicFunctions;

import java.util.List;

import database.models.StaticFixedTopicName;

public interface FixedTopicNameService {
	
	public List<StaticFixedTopicName> getByHql(String hql);
	
	public List<StaticFixedTopicName> findAll();
	
	public StaticFixedTopicName getById(int id);
}
