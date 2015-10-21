package service.basicFunctions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.FixedTopicNameDao;
import database.models.StaticFixedTopicName;

@Service("fixedTopicNameService")
public class FixedTopicNameServiceImpl implements FixedTopicNameService {

	@Autowired
	private FixedTopicNameDao fixedTopicNameDao;
	
	public List<StaticFixedTopicName> getByHql(String hql) {
		return fixedTopicNameDao.findListByHql(hql);
	}

}
