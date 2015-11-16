package service.basicFunctions;

import java.util.List;

import database.common.PageDataList;
import database.models.Tutor;

public interface TutorService {

	public Tutor saveTutor(Tutor tutor);
	
	public List<Tutor> findAll();
	
	public Tutor getByOpenid(String openid);
	
	public Tutor doLogin(Tutor tutor);
	
	public void delete(Integer id);
	
	public void update(Tutor tutor);

	public PageDataList<Tutor> findPageList(Tutor tutor, int pageNum);
	
	public Tutor find(Integer id);

	public List<Tutor> findSelectList(Tutor tutor, Integer type,Integer workYears);
	
}
