package service.basicFunctions;

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
		
		buffer = new StringBuffer();
		
		buffer.append(" FROM TutorTime  ");
		buffer.append(" WHERE ");
		buffer.append(" qTutor.id =  ");
		buffer.append(id);
		
		
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
		buffer.append("'  AND status = 0 ");
		
		return tutorTimeDao.getByHql(buffer.toString());
	}
	
	
	
	public StringBuffer getBuffer() {
		return buffer;
	}

	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}

	public TutorTime getById(Integer timeId) {
		return tutorTimeDao.find(timeId);
	}

	public boolean checkByDate(String date, String time,Integer tutorId) {
	buffer = new StringBuffer();
		
		buffer.append(" FROM TutorTime ");
		buffer.append(" WHERE ");
		buffer.append(" realTime =  '");
		buffer.append(time);
		buffer.append("' AND ");
		buffer.append(" realDate =  '");
		buffer.append(date);
		buffer.append("' AND qTutor.id= ");
		buffer.append(tutorId);
		
		List<TutorTime> list = tutorTimeDao.getByHql(buffer.toString());
		if(null!=list&&!list.isEmpty()){
			return true;
		}
		return false;
	}

}
