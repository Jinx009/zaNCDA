package service.basicFunctions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.TeacherScoreDao;
import database.models.NbTeacherScore;

@Service("teacherScoreService")
public class TeacherScoreServieImpl  implements TeacherScoreServie{

	@Autowired
	private TeacherScoreDao teacherScoreDao;
	
	public List<NbTeacherScore> getByOrderId(int orderId) {
		return teacherScoreDao.getByOrderId(orderId);
	}

}
