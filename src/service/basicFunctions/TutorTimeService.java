package service.basicFunctions;

import java.util.List;

import database.models.TutorTime;

public interface TutorTimeService {
	
	public void update(TutorTime tutorTime);
	
	public TutorTime save(TutorTime tutorTime);
	
	public void delete(Integer id);
	
	public List<TutorTime> getByTutorId(Integer id);
	
	public List<TutorTime> getDate(Integer id);
	
	public List<TutorTime> getTime(Integer id,String realDate);

	public TutorTime getById(Integer timeId);
}
