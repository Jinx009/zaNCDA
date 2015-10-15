package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.NbTeacherScore;


@Repository("teacherScoreDao")
public class TeacherScoreDaoImpl extends BaseDaoImpl<NbTeacherScore> implements TeacherScoreDao {

}