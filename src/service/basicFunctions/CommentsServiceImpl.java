package service.basicFunctions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.CommentsDao;
import database.models.Comments;

@Service("commentsService")
public class CommentsServiceImpl implements CommentsService {

	private StringBuffer buffer;
	
	@Autowired
	private CommentsDao commentsDao;
	
	public List<Comments> getByOrderId(Integer id) {
		buffer = new StringBuffer();
		
		buffer.append(" FROM Comments ");
		buffer.append(" WHERE ");
		buffer.append(" order.id =  ");
		buffer.append(id);
		buffer.append(" ORDER BY addTime DESC ");
		
		return commentsDao.getByHql(buffer.toString());
	}

	
	
	public StringBuffer getBuffer() {
		return buffer;
	}
	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}
}
