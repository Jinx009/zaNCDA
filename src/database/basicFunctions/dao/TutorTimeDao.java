package database.basicFunctions.dao;

import java.util.List;

import database.common.BaseDao;
import database.models.TutorTime;

public interface TutorTimeDao extends BaseDao<TutorTime>{

	public List<TutorTime> getByHql(String hql);

}
