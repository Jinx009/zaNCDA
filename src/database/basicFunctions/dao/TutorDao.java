package database.basicFunctions.dao;

import java.util.List;

import database.common.BaseDao;
import database.common.PageDataList;
import database.models.Tutor;

public interface TutorDao extends BaseDao<Tutor>{

	public Tutor getByHql(String hql);

	public PageDataList<Tutor> findTeacherPageList(Tutor tutor, int pageNum);

	public List<Tutor> getBySql(String sql);
}
