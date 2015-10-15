package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.StaticLocationName;


@Repository("locationNameDao")
public class LocationNameDaoImpl extends BaseDaoImpl<StaticLocationName> implements LocationNameDao {

}