package service.basicFunctions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.AreaNameDao;
import database.models.StaticAreaName;

@Service("areaNameService")
public class AreaNameServiceImpl implements AreaNameService {

	@Autowired
	private AreaNameDao areaNameDao;
	
	public Object saveAreaName(StaticAreaName staticAreaName) {
		return areaNameDao.save(staticAreaName);
	}

	public void deleteAreaName(int id) {
		areaNameDao.delete(id);
	}

	public void updateAreaName(StaticAreaName staticAreaName) {
		areaNameDao.update(staticAreaName);
	}

	public List<StaticAreaName> findAll() {
		return areaNameDao.findAllAreaName();
	}

	public StaticAreaName findById(int id) {
		return areaNameDao.find(id);
	}

}
