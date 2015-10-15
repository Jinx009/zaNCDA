package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.NbTeachersUser;


@Repository("TeachersUserDao")
public class TeachersUserDaoImpl extends BaseDaoImpl<NbTeachersUser> implements TeachersUserDao {

}