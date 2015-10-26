package database.basicFunctions.dao;

import java.util.List;

import javax.persistence.Query;

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

	@SuppressWarnings("unchecked")
	public NbTeachersUser doLogin(NbTeachersUser nbTeachersUser) {
		String hql = " from NbTeachersUser where username = '"+nbTeachersUser.getUsername()+"' and password = '"+nbTeachersUser.getPassword()+"' ";
		Query query = em.createQuery(hql);
		List<NbTeachersUser> list = query.getResultList();
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public NbTeachersUser getByOpenid(String openid) {
		String hql = " from NbTeachersUser where openid = '"+openid+"' order by updateTime desc ";
		Query query = em.createQuery(hql);
		List<NbTeachersUser> list = query.getResultList();
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

}