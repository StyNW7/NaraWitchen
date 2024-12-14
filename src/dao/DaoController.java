package dao;

import entity.Admin;
import entity.Entity;
import entity.Organization;
import entity.User;

public class DaoController {
	
	AdminController adminController = new AdminController();
	UserController userController = new UserController();
	OrganizationController organizationController = new OrganizationController();
	
	public void Menu(Admin admin, Entity activeUser) {
		
		if (activeUser instanceof Admin) {
			adminController.AdminMenu(activeUser);
		}
		
		else if (activeUser instanceof User) {
			userController.UserMenu(admin, activeUser);
		}
		
		else if (activeUser instanceof Organization) {
			organizationController.UserMenu(admin, activeUser);
		}
		
	}

}
