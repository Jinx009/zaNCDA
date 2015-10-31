package service.basicFunctions;

import database.common.PageDataList;
import database.models.NbStudentsUser;

public interface StudentService {
	
	public NbStudentsUser findById(int id);
	
	public PageDataList<NbStudentsUser> findPageList(NbStudentsUser nbStudentsUser,int pageNum,int pageSize);

	public NbStudentsUser findByOpenid(String openid);
	
}
