package service.basicFunctions;

import database.common.PageDataList;
import database.models.NbTeachersUser;

public interface TeacherService {

	public PageDataList<NbTeachersUser> findPageList(NbTeachersUser nbTeachersUser,int pageNum,int pageSize);
	
}
