package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.NbStudentsUser;


@Repository("studentsUserDao")
public class StudentsUserDaoImpl extends BaseDaoImpl<NbStudentsUser> implements StudentsUserDao {

}