package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.Tutor;

@Repository("tutorDao")
public class TutorDaoImpl extends BaseDaoImpl<Tutor> implements TutorDao{

}
