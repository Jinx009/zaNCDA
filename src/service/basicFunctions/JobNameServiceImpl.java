package service.basicFunctions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.JobNameDao;
import database.models.StaticJobName;

@Service("jobNameService")
public class JobNameServiceImpl implements JobNameService{

	@Autowired
	private JobNameDao jobNameDao;

	public Object saveJobName(StaticJobName staticJobName) {
		return jobNameDao.save(staticJobName);
	}

	public void updateJobName(StaticJobName staticJobName) {
		jobNameDao.update(staticJobName);
	}

	public void deleteJobName(int id) {
		jobNameDao.delete(id);
	}

	public StaticJobName findById(int id) {
		return jobNameDao.find(id);
	}

	public List<StaticJobName> findAll() {
		return jobNameDao.findAllJobName();
	}
	
}
