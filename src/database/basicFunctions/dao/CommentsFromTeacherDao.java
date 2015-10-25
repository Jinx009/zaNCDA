package database.basicFunctions.dao;

import java.util.List;

import database.common.BaseDao;
import database.models.NbCommentsFromTeacher;

public interface CommentsFromTeacherDao extends BaseDao<NbCommentsFromTeacher> {
	
	public List<NbCommentsFromTeacher> getByOrderId(int orderId);
	
}
