package database.basicFunctions.dao;

import database.common.BaseDao;
import database.models.NbAdminUser;

public interface AdminUserDao extends BaseDao<NbAdminUser>{

	NbAdminUser doAdminUserLogin(NbAdminUser nbAdminUser);
}
