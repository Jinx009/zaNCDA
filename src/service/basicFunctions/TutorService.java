package service.basicFunctions;

import java.util.List;

import database.models.Tutor;

public interface TutorService {

	public Tutor saveTutor(Tutor tutor);
	
	public List<Tutor> getByParam(Tutor tutor);
	
	public Tutor getByOpenid(String openid);
	
	public Tutor doLogin(Tutor tutor);
	
	public void delete(Integer id);
	
	public void update(Tutor tutor);
	
}
