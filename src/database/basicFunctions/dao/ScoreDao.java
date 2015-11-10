package database.basicFunctions.dao;

import java.util.List;

import database.common.BaseDao;
import database.models.Score;

public interface ScoreDao extends BaseDao<Score>{

	public List<Score> getByHql(String hql);

}
