package service.basicFunctions;

import java.util.List;

import database.models.StaticAreaName;

public interface AreaNameService {
	
	public Object saveAreaName(StaticAreaName staticAreaName);
	
	public void deleteAreaName(int id);
	
	public void updateAreaName(StaticAreaName staticAreaName);
	
	public List<StaticAreaName> findAll();
	
	public StaticAreaName findById(int id);
	
}
