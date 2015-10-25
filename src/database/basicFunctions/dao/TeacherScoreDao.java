package database.basicFunctions.dao;

import java.util.List;

import database.common.BaseDao;
import database.models.NbTeacherScore;

public interface TeacherScoreDao extends BaseDao<NbTeacherScore> {

	public List<NbTeacherScore> getByOrderId(int orderId);
	
}