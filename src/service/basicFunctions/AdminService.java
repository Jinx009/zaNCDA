package service.basicFunctions;

import database.models.Admin;

public interface AdminService {
	
	public Admin doLogin(Admin admin);
	
	public void update(Admin admin);
}
