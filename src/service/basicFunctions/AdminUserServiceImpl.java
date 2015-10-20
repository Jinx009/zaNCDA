package service.basicFunctions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.AdminUserDao;
import database.models.NbAdminUser;

@Service("adminUserService")
public class AdminUserServiceImpl implements AdminUserService{

	@Autowired
	private AdminUserDao adminUserDao;
	
	public NbAdminUser doAdminUserLogin(NbAdminUser nbAdminUser) {
		NbAdminUser nbAdminUser2 = adminUserDao.doAdminUserLogin(nbAdminUser);
		return nbAdminUser2;
	}

}
