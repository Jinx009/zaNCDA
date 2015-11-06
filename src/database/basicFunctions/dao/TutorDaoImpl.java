package database.basicFunctions.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import common.helper.ConstantUtil;
import common.helper.StringUtil;
import database.common.BaseDaoImpl;
import database.common.PageDataList;
import database.common.QueryParam;
import database.models.Tutor;

@Repository("tutorDao")
public class TutorDaoImpl extends BaseDaoImpl<Tutor> implements TutorDao{

	@SuppressWarnings("unchecked")
	public Tutor getByHql(String hql) {
		Query query = em.createQuery(hql);
		List<Tutor> list = query.getResultList();
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public PageDataList<Tutor> findTeacherPageList(Tutor tutor, int pageNum) {
		QueryParam param = QueryParam.getInstance().addPage(pageNum,ConstantUtil.PAGE_SIZE);
		
		if (StringUtil.isNotBlank(tutor.getRealName())){
			param.addParam("realName",tutor.getRealName());
		}
		if (StringUtil.isNotBlank(tutor.getMobilePhone())){
			param.addParam("mobilePhone",tutor.getMobilePhone());
		}
		PageDataList<Tutor> pageDataList = super.findPageList(param);
		
		return pageDataList;
	}

}
