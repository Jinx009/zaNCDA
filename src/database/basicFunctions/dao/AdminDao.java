package database.basicFunctions.dao;

import database.common.BaseDao;
import database.models.Admin;

public interface AdminDao extends BaseDao<Admin>{

	Admin doAdminUserLogin(Admin nbAdminUser);
}
