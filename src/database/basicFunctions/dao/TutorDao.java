package database.basicFunctions.dao;

import database.common.BaseDao;
import database.common.PageDataList;
import database.models.Tutor;

public interface TutorDao extends BaseDao<Tutor>{

	public Tutor getByHql(String hql);

	public PageDataList<Tutor> findTeacherPageList(Tutor tutor, int pageNum);
}
