package database.basicFunctions.dao;

import java.util.List;

import database.common.BaseDao;
import database.models.Area;

public interface AreaDao extends BaseDao<Area>{

	public List<Area> getByHql(String hql);

}
