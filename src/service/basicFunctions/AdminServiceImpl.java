package service.basicFunctions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.AdminDao;
import database.models.Admin;

@Service("adminService")
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDao adminDao;
	
	public Admin doLogin(Admin admin) {
		Admin admin2 = adminDao.doLogin(admin);
		return admin2;
	}

}
