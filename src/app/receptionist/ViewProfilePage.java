package app.receptionist;

import app.Page;
import be.EmployeeBe;
import bl.EmployeeBl;
import session.SESSION;

public class ViewProfilePage extends Page {
	public String[] getOptions() {
		return new String[] {
				"Go Back",
		};
	}
	
	public Page[] getPages() {
		return new Page[] { Pages.PROFILE };
	}

	public void open() {
		displayProfile();
		super.open();
	}
	
	private void displayProfile() {
		System.out.println("my profile");
		EmployeeBe employeeBe = EmployeeBl.getEmployee(SESSION.getSession().getEmployeeId()); 
		EmployeeBl.displayEmployeeProfile(employeeBe);
	}
}
