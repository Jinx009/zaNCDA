package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.TutorTime;

@Repository("tutorTimeDao")
public class TutorTimeDaoImpl extends BaseDaoImpl<TutorTime> implements TutorTimeDao {

}
