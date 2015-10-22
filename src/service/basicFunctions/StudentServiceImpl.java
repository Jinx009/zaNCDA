package service.basicFunctions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.StudentsUserDao;
import database.common.PageDataList;
import database.models.NbStudentsUser;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentsUserDao studentsUserDao;
	
	public NbStudentsUser findById(int id) {
		return studentsUserDao.find(id);
	}

	public PageDataList<NbStudentsUser> findPageList(
			NbStudentsUser nbStudentsUser, int pageNum, int pageSize) {
		return studentsUserDao.findTeacherPageList(nbStudentsUser,pageNum,pageSize);
	}
	
}