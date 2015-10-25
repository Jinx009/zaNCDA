package service.basicFunctions;

import java.util.List;

import database.models.NbCommentsFromTeacher;

public interface CommentsFromTeacherService {
	
	public List<NbCommentsFromTeacher> getByOrderId(int orderId);
	
}
