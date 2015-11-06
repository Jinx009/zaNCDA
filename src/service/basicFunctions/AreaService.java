package service.basicFunctions;

import java.util.List;

import database.models.Area;

public interface AreaService {

	public List<Area> getByParentId(Integer id);
	
}
