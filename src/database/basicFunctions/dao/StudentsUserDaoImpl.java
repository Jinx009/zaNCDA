package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.common.PageDataList;
import database.common.QueryParam;
import database.models.NbStudentsUser;


@Repository("studentsUserDao")
public class StudentsUserDaoImpl extends BaseDaoImpl<NbStudentsUser> implements StudentsUserDao {

	public PageDataList<NbStudentsUser> findTeacherPageList(NbStudentsUser nbStudentsUser, int pageNum, int pageSize) {
		QueryParam param = QueryParam.getInstance().addPage(pageNum,pageSize);
		
		if (null!=nbStudentsUser.getUsername()&&!"".equals(nbStudentsUser.getUsername())){
			param.addParam("username",nbStudentsUser.getUsername());
		}
		if (null!=nbStudentsUser.getMobilePhone()&&!"".equals(nbStudentsUser.getMobilePhone())){
			param.addParam("mobilePhone",nbStudentsUser.getMobilePhone());
		}
		PageDataList<NbStudentsUser> pageDataList = super.findPageList(param);
		
		return pageDataList;
	}
}