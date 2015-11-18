package service.basicFunctions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.TutorTimeDao;
import database.models.TutorTime;

@Service("tutorTimeService")
public class TutorTimeServiceImpl implements TutorTimeService {

	private StringBuffer buffer;
	
	@Autowired
	private TutorTimeDao tutorTimeDao;
	
	public void update(TutorTime tutorTime) {
		tutorTimeDao.update(tutorTime);
	}

	public TutorTime save(TutorTime tutorTime) {
		return tutorTimeDao.save(tutorTime);
	}

	public void delete(Integer id) {
		tutorTimeDao.delete(id);
	}

	public List<TutorTime> getByTutorId(Integer id) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		
		buffer = new StringBuffer();
		
		buffer.append(" FROM TutorTime  ");
		buffer.append(" WHERE ");
		buffer.append(" qTutor.id =  ");
		buffer.append(id);
		buffer.append(" AND ");
		buffer.append(" status = 0 ");
		buffer.append(" AND ");
		buffer.append(" realTime > '");
		buffer.append(format.format(new Date()));
		buffer.append("'  ");
		
		return tutorTimeDao.getByHql(buffer.toString());
	}

	
	
	
	public StringBuffer getBuffer() {
		return buffer;
	}

	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}
}
