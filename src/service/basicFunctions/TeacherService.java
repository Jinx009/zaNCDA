package service.basicFunctions;

import database.common.PageDataList;
import database.models.NbTeachersUser;

public interface TeacherService {

	public PageDataList<NbTeachersUser> findPageList(NbTeachersUser nbTeachersUser,int pageNum,int pageSize);
	
	public void doDelete(int id);
	
	public Object doSave(NbTeachersUser nbTeachersUser);
	
	public NbTeachersUser findById(int id);
	
	public void doUpdate(NbTeachersUser nbTeachersUser);
	
}
