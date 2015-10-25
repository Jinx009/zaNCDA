package database.basicFunctions.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.NbCommentsFromTeacher;


@Repository("commentsFromTeacherDao")
public class CommentsFromTeacherDaoImpl extends BaseDaoImpl<NbCommentsFromTeacher> implements CommentsFromTeacherDao {

	@SuppressWarnings("unchecked")
	public List<NbCommentsFromTeacher> getByOrderId(int orderId) {
		String hql = " from NbCommentsFromTeacher where orderId = "+orderId+"  ";
		
		Query query = em.createQuery(hql);
		List<NbCommentsFromTeacher> list = query.getResultList();
		if(null!=list&&!list.isEmpty()){
			return list;
		}
		
		return null;
	}

}