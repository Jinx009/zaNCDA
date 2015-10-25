package service.basicFunctions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.CommentsFromTeacherDao;
import database.models.NbCommentsFromTeacher;

@Service("commentsFromTeacherService")
public class CommentsFromTeacherServiceImpl implements CommentsFromTeacherService{

	@Autowired
	private CommentsFromTeacherDao commentsFromTeacherDao;
	
	public List<NbCommentsFromTeacher> getByOrderId(int orderId) {
		return commentsFromTeacherDao.getByOrderId(orderId);
	}

}
