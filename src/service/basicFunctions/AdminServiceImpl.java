package service.basicFunctions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.AdminDao;
import database.models.Admin;

@Service("adminService")
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminDao adminUserDao;
	
	public Admin doAdminUserLogin(Admin nbAdminUser) {
		Admin nbAdminUser2 = adminUserDao.doAdminUserLogin(nbAdminUser);
		return nbAdminUser2;
	}

}
