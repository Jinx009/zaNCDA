package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.StaticFixedTopicName;


@Repository("fixedTopicNameDao")
public class FixedTopicNameDaoImpl extends BaseDaoImpl<StaticFixedTopicName> implements FixedTopicNameDao {

}