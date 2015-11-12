package database.basicFunctions.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.Comments;

@Repository("commentsDao")
public class CommentsDaoImpl extends BaseDaoImpl<Comments> implements CommentsDao {

	@SuppressWarnings("unchecked")
	public List<Comments> getByHql(String hql) {
		Query query = em.createQuery(hql);
		List<Comments> list = query.getResultList();
		if(null!=list&&!list.isEmpty()){
			return list;
		}
		return null;
	}
}
