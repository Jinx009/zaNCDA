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
		buffer.append(" realDate > '");
		buffer.append(format.format(new Date()));
		buffer.append("'  ");
		
		System.out.println("---------------"+buffer.toString());
		
		return tutorTimeDao.getByHql(buffer.toString());
	}

	public List<TutorTime> getDate(Integer id) {
		buffer = new StringBuffer();
	
		buffer.append(" SELECT status,id,real_date,tutor_id, COUNT(DISTINCT real_date) as real_time ");
		buffer.append(" FROM ");
		buffer.append(" q_tutor_time ");
		buffer.append(" WHERE ");
		buffer.append(" STATUS = 0  ");
		buffer.append(" AND tutor_id =  ");
		buffer.append(id);
		buffer.append(" AND real_date > now() ");
		buffer.append(" GROUP BY real_date ORDER by real_date ");
		
		return tutorTimeDao.getDate(buffer.toString());
	}

	public List<TutorTime> getTime(Integer id, String realDate) {
		buffer = new StringBuffer();
		
		buffer.append(" FROM TutorTime ");
		buffer.append(" WHERE ");
		buffer.append(" qTutor.id =  ");
		buffer.append(id);
		buffer.append(" AND ");
		buffer.append(" realDate =  '");
		buffer.append(realDate);
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
