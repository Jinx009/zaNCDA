package service.basicFunctions;

import java.util.List;

import database.models.Topic;

public interface TopicService {

	public List<Topic> getByParentId(Integer id);
	
}
