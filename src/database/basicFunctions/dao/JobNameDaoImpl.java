package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.StaticJobName;


@Repository("jobNameDao")
public class JobNameDaoImpl extends BaseDaoImpl<StaticJobName> implements JobNameDao {

}