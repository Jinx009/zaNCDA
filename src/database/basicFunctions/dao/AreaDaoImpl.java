package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.Area;

@Repository("areaDao")
public class AreaDaoImpl extends BaseDaoImpl<Area> implements AreaDao{

}
