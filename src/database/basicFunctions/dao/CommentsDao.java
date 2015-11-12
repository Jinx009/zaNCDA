package database.basicFunctions.dao;

import java.util.List;

import database.common.BaseDao;
import database.models.Comments;

public interface CommentsDao extends BaseDao<Comments>{

	public List<Comments> getByHql(String hql);

}
