package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.common.PageDataList;
import database.common.QueryParam;
import database.models.NbTeachersUser;


@Repository("TeachersUserDao")
public class TeachersUserDaoImpl extends BaseDaoImpl<NbTeachersUser> implements TeachersUserDao {

	public PageDataList<NbTeachersUser> findTeacherPageList(NbTeachersUser nbTeachersUser,int pageNum, int pageSize) {
		QueryParam param = QueryParam.getInstance().addPage(pageNum,pageSize);
		
		if (null!=nbTeachersUser.getUsername()&&!"".equals(nbTeachersUser.getUsername())){
			param.addParam("username",nbTeachersUser.getUsername());
		}
		if (null!=nbTeachersUser.getMobilePhone()&&!"".equals(nbTeachersUser.getMobilePhone())){
			param.addParam("mobilePhone",nbTeachersUser.getMobilePhone());
		}
		PageDataList<NbTeachersUser> pageDataList = super.findPageList(param);
		
		return pageDataList;
	}

}