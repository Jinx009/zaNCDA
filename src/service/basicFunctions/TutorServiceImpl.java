package service.basicFunctions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.helper.MD5Util;
import database.basicFunctions.dao.TutorDao;
import database.common.PageDataList;
import database.models.Tutor;

@Service("tutorService")
public class TutorServiceImpl implements TutorService{

	@Autowired
	private TutorDao tutorDao;
	private StringBuffer buffer;
	
	public Tutor saveTutor(Tutor tutor) {
		return tutorDao.save(tutor);
	}

	public List<Tutor> getByParam(Tutor tutor) {
		return null;
	}

	public Tutor getByOpenid(String openid) {
		buffer = new StringBuffer();
		
		buffer.append(" FROM Tutor  ");
		buffer.append(" WHERE openid = '");
		buffer.append(openid);
		buffer.append("' ");
		buffer.append(" ORDER BY updateTime DESC ");
		
		return tutorDao.getByHql(buffer.toString());
	}

	public Tutor doLogin(Tutor tutor) {
		buffer = new StringBuffer();
		
		buffer.append(" FROM Tutor ");
		buffer.append(" WHERE userName = '");
		buffer.append(tutor.getUserName());
		buffer.append("' AND pwd = '");
		buffer.append(MD5Util.toMD5(tutor.getPwd()));
		buffer.append("' ");
		
		return tutorDao.getByHql(buffer.toString());
	}

	public void delete(Integer id) {
		tutorDao.delete(id);
	}

	public void update(Tutor tutor) {
		tutorDao.update(tutor);
	}
	
	public PageDataList<Tutor> findPageList(Tutor tutor, int pageNum) {
		return tutorDao.findTeacherPageList(tutor,pageNum);
	}
	
	
	
	
	
	
	
	public StringBuffer getBuffer() {
		return buffer;
	}
	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}
}
