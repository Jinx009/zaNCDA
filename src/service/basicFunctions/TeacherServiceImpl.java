package service.basicFunctions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.TeachersUserDao;
import database.common.PageDataList;
import database.models.NbTeachersUser;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeachersUserDao teachersUserDao;
	
	public PageDataList<NbTeachersUser> findPageList(NbTeachersUser nbTeachersUser,int pageNum,int pageSize) {
		return teachersUserDao.findTeacherPageList(nbTeachersUser,pageNum,pageSize);
	}

}
