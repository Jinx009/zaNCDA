package database.basicFunctions.dao;

import database.common.BaseDao;
import database.common.PageDataList;
import database.models.NbStudentsUser;

public interface StudentsUserDao extends BaseDao<NbStudentsUser> {

	public PageDataList<NbStudentsUser> findTeacherPageList(
			NbStudentsUser nbStudentsUser, int pageNum, int pageSize);

	public NbStudentsUser findByHql(String hql);

}