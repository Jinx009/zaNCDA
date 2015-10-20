package database.basicFunctions.dao;

import java.util.List;

import database.common.BaseDao;
import database.models.StaticJobName;

public interface JobNameDao extends BaseDao<StaticJobName> {

	public List<StaticJobName> findAllJobName();

}