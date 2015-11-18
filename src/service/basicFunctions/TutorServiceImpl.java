package service.basicFunctions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.helper.MD5Util;
import common.helper.tool.util.ResultUtil;
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

	public List<Tutor> findAll() {
		return tutorDao.findAll();
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

	public Tutor find(Integer id) {
		return tutorDao.find(id);
	}
	
	public List<Tutor> findSelectList(Tutor tutor, Integer type,Integer workYears) {
		buffer = new StringBuffer();
		
		String typeResult = ResultUtil.getType(type);
		
		buffer.append(" SELECT id FROM q_tutor WHERE ");
		buffer.append(" ( ");
		buffer.append(" good_trade_one = ");
		buffer.append(tutor.getTradeOne().getId());
		buffer.append(" OR ");
		buffer.append(" good_trade_two = ");
		buffer.append(tutor.getTradeOne().getId());
		buffer.append(" OR ");
		buffer.append(" good_trade_three = ");
		buffer.append(tutor.getTradeOne().getId());
		buffer.append(" ) ");
		buffer.append(" AND ");
		buffer.append(" ( ");
		buffer.append(" good_area_one = ");
		buffer.append(tutor.getAreaOne().getId());
		buffer.append(" OR ");
		buffer.append(" good_area_two = ");
		buffer.append(tutor.getAreaOne().getId());
		buffer.append(" OR ");
		buffer.append(" good_area_three = ");
		buffer.append(tutor.getAreaOne().getId());
		buffer.append(" ) ");
		buffer.append(" AND ");
		buffer.append("( work_years = ");
		buffer.append(workYears);
		buffer.append(") AND ");
		buffer.append(typeResult);
		buffer.append("  =  1 ");
		
		System.out.println(buffer.toString());
		
		return tutorDao.getBySql(buffer.toString());
	}
	
	
	
	
	
	public StringBuffer getBuffer() {
		return buffer;
	}
	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}
}
