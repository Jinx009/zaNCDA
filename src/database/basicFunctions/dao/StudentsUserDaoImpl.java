package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import common.helper.StringUtil;

import database.common.BaseDaoImpl;
import database.common.PageDataList;
import database.common.QueryParam;
import database.models.NbStudentsUser;


@Repository("studentsUserDao")
public class StudentsUserDaoImpl extends BaseDaoImpl<NbStudentsUser> implements StudentsUserDao {

	public PageDataList<NbStudentsUser> findTeacherPageList(NbStudentsUser nbStudentsUser, int pageNum, int pageSize) {
		QueryParam param = QueryParam.getInstance().addPage(pageNum,pageSize);
		
		if (StringUtil.isNotBlank(nbStudentsUser.getRealName())){
			param.addParam("realName",nbStudentsUser.getRealName());
		}
		if (StringUtil.isNotBlank(nbStudentsUser.getMobilePhone())){
			param.addParam("mobilePhone",nbStudentsUser.getMobilePhone());
		}
		PageDataList<NbStudentsUser> pageDataList = super.findPageList(param);
		
		return pageDataList;
	}
}