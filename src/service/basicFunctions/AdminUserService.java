package service.basicFunctions;

import database.models.NbAdminUser;

public interface AdminUserService {
	public NbAdminUser doAdminUserLogin(NbAdminUser nbAdminUser);
}
