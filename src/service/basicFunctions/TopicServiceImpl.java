package service.basicFunctions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.TopicDao;
import database.models.Topic;

@Service("topicService")
public class TopicServiceImpl implements TopicService{

	private StringBuffer buffer;
	
	@Autowired
	private TopicDao topicDao;
	
	public List<Topic> getByParentId(Integer id) {
		buffer = new StringBuffer();
		
		buffer.append(" FROM  Topic ");
		buffer.append("  WHERE ");
		buffer.append(" parentId =  '");
		buffer.append(id);
		buffer.append("'  ");
		
		return topicDao.getByHql(buffer.toString());
	}
	
	public Topic find(Integer topicId) {
		return topicDao.find(topicId);
	}

	
	
	
	public StringBuffer getBuffer() {
		return buffer;
	}

	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}
}
