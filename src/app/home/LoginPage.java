package app.home;

import app.Page;
import app.customer.CustomerPage;
import app.manager.ManagerPage;
import app.receptionist.ReceptionistPage;
import be.EmployeeBe.Role;
import session.SESSION;

public class LoginPage extends Page {
	public String[] getOptions() {
		return new String[] {
				"Sign-In", "Go back"
		};
	}
	
	public Page[] getPages() {
		return new Page[] { Pages.HOME, new ManagerPage(), new ReceptionistPage(), new CustomerPage() };
	}

	public void open() {
		String userId = readUserId();
		String password = readPassword();
		
		displayOptions();
		int option = readOption();
		Page backPage = getPages()[0];
		
		Boolean successfulLogin = false;
		
		successfulLogin = SESSION.login(userId, password);
		
		if (option == 1) {
			if (successfulLogin) {
				System.out.println("Welcome "+SESSION.getSession().getFullName()+" ("+SESSION.getSession().getRoleName()+")\n");
				String roleName = SESSION.getSession().getRoleName();
				if (roleName == Role.MANAGER.toString()) {
					Page managerPage = getPages()[1];
					managerPage.open();
				}else if(roleName == Role.RECEPTIONIST.toString()) {
					Page receptionistPage = getPages()[2];
					receptionistPage.open();
				}else if (roleName == Role.CUSTOMER.toString()) {
					Page customerPage = getPages()[3];
					customerPage.open();
				}
			}else{
				System.out.println("Wrong User or Password, Please try again");
				backPage.open();

			}
			

		} else {
			backPage.open();
		}
	}
	
	private String readUserId() {
		System.out.println("Enter User ID:");
		return readString();
	}

	private String readPassword() {
		System.out.println("Enter Password:");
		return readString();
	}

}
