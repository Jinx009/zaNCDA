package service.basicFunctions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.ScoreDao;
import database.models.Score;

@Service("scoreService")
public class ScoreServiceImpl implements ScoreService{
	
	private StringBuffer buffer;
	
	@Autowired
	private ScoreDao scoreDao;

	public List<Score> getByAttr(Integer id) {
		buffer = new StringBuffer();
		
		buffer.append(" FROM  Score ");
		buffer.append(" WHERE ");
		buffer.append(" qOrder.id = ");
		buffer.append(id);
		buffer.append(" ORDER BY addTime DESC ");
		
		return scoreDao.getByHql(buffer.toString());
	}
	
	public void save(Score score) {
		scoreDao.save(score);
	}

	public StringBuffer getBuffer() {
		return buffer;
	}

	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}

}
