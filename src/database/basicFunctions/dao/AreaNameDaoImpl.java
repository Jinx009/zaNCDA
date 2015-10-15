package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.StaticAreaName;

@Repository("areaNameDao")
public class AreaNameDaoImpl extends BaseDaoImpl<StaticAreaName> implements AreaNameDao {

}
