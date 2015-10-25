package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import common.helper.StringUtil;

import database.common.BaseDaoImpl;
import database.common.PageDataList;
import database.common.QueryParam;
import database.models.NbTeachersUser;


@Repository("TeachersUserDao")
public class TeachersUserDaoImpl extends BaseDaoImpl<NbTeachersUser> implements TeachersUserDao {

	public PageDataList<NbTeachersUser> findTeacherPageList(NbTeachersUser nbTeachersUser,int pageNum, int pageSize) {
		QueryParam param = QueryParam.getInstance().addPage(pageNum,pageSize);
		
		if (StringUtil.isNotBlank(nbTeachersUser.getRealName())){
			param.addParam("realName",nbTeachersUser.getRealName());
		}
		if (StringUtil.isNotBlank(nbTeachersUser.getMobilePhone())){
			param.addParam("mobilePhone",nbTeachersUser.getMobilePhone());
		}
		PageDataList<NbTeachersUser> pageDataList = super.findPageList(param);
		
		return pageDataList;
	}

}