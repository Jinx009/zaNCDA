package service.basicFunctions;

import java.util.List;

import database.models.NbTeacherScore;

public interface TeacherScoreServie {

	public List<NbTeacherScore> getByOrderId(int orderId);
	
}
