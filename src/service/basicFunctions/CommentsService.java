package service.basicFunctions;

import java.util.List;

import database.models.Comments;

public interface CommentsService {
	
	public List<Comments> getByOrderId(Integer id);
	
}
