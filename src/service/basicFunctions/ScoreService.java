package service.basicFunctions;

import java.util.List;

import database.models.Score;

public interface ScoreService {
	
	public List<Score> getByAttr(Integer id);

	public void save(Score score);
	
}
