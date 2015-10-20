package database.basicFunctions.dao;

import database.common.BaseDao;
import database.common.PageDataList;
import database.models.NbTeachersUser;

public interface TeachersUserDao extends BaseDao<NbTeachersUser> {

	public PageDataList<NbTeachersUser> findTeacherPageList(NbTeachersUser nbTeachersUser, int pageNum, int pageSize);

}