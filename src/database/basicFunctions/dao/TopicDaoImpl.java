package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.Topic;

@Repository("topicDao")
public class TopicDaoImpl extends BaseDaoImpl<Topic> implements TopicDao{

}
