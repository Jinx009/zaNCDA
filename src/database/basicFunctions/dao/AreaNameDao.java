package database.basicFunctions.dao;

import java.util.List;

import database.common.BaseDao;
import database.models.StaticAreaName;

public interface AreaNameDao extends BaseDao<StaticAreaName> {

	public List<StaticAreaName> findAllAreaName();

}