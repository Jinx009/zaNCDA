package service.basicFunctions;

import java.util.List;

import database.models.StaticJobName;

public interface JobNameService {

	public Object saveJobName(StaticJobName staticJobName);
	
	public void updateJobName(StaticJobName staticJobName);
	
	public void deleteJobName(int id);
	
	public StaticJobName findById(int id);
	
	public List<StaticJobName> findAll();
	
}
