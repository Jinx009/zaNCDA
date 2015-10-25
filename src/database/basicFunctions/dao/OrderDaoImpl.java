package database.basicFunctions.dao;

import org.springframework.stereotype.Repository;

import common.helper.StringUtil;
import database.common.BaseDaoImpl;
import database.common.PageDataList;
import database.common.QueryParam;
import database.common.SearchFilter.Operators;
import database.models.NbOrder;


@Repository("orderDao")
public class OrderDaoImpl extends BaseDaoImpl<NbOrder> implements OrderDao {

	public PageDataList<NbOrder> findTeacherPageList(NbOrder nbOrder,
			int pageNum, int pageSize) {
		QueryParam param = QueryParam.getInstance().addPage(pageNum,pageSize);
		
		if (StringUtil.isNotBlank(nbOrder.getNbTeachersUser().getRealName())){
			param.addParam("nbTeacherUser.realName",nbOrder.getNbTeachersUser().getRealName());
		}
		if (StringUtil.isNotBlank(nbOrder.getNbTeachersUser().getMobilePhone())){
			param.addParam("nbTeacherUser.mobilePhone",nbOrder.getNbTeachersUser().getMobilePhone());
		}
		if(StringUtil.isNotBlank(nbOrder.getNbStudentsUser().getRealName())){
			param.addParam("nbStudentUser.realName",nbOrder.getNbStudentsUser().getRealName());
		}
		if(StringUtil.isNotBlank(nbOrder.getNbStudentsUser().getMobilePhone())){
			param.addParam("nbStudentUser.mobilePhone",nbOrder.getNbStudentsUser().getMobilePhone());
		}
		if (null!=nbOrder.getFirstPayTime()) {
			param.addParam("firstPayTime", Operators.GTE,nbOrder.getFirstPayTime());
		}
		if (null!=nbOrder.getSecondPayTime()) {
			param.addParam("secondPayTime", Operators.LTE,nbOrder.getFirstPayTime());
		}
		PageDataList<NbOrder> pageDataList = super.findPageList(param);
		
		return pageDataList;
	}

}