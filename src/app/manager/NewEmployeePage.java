package app.manager;

import java.sql.Date;
import java.text.ParseException;

import app.Page;
import be.EmployeeBe;
import be.UsersBe;
import be.EmployeeBe.Role;
import be.EmployeeBe.WorkFrequency;
import bl.EmployeeBl;
import bl.UsersBl;
import dao.EmployeeDao;
import session.SESSION;
import util.DateFormatUtil;

public class NewEmployeePage extends Page {
	private EmployeeBe newEmployee;
	private UsersBe newUser;
	public String[] getOptions() {
		return new String[] {
				"Add", "Go Back",
		};
	}
	
	public Page[] getPages() {
		return new Page[] { Pages.MANAGER };
	}

	public void open() {
		newEmployee = new EmployeeBe();
		newUser = new UsersBe();
		updateName();
		//users		
		updateServiceCenterId();
		updateRole();
		updateFrequency();
		updateAddress();
		updateEmail();
		updatePhoneNumber();
		updateStartDate();
		updateCompensation();
		
		displayOptions();
		int option = readOption();
		Page backPage = getPages()[0];
		if (option == 1) {
			if(EmployeeBl.setEmployee(newEmployee)){
				System.out.println("New Employee has been Added with user id:" + newEmployee.getUser().getId());
			} else System.out.println("Error while Adding Employee!!");

			backPage.open();
		} else {
			backPage.open();
		}
	}
	
	private void updateName() {
		System.out.println("Enter employee name:");
		newEmployee.setName(readString());
		// update db
	}
	//added
	private void updateUserId() {
		System.out.println("Enter system user id name:");
		newUser.setId(readString().trim());
	
		// update db
	}
	
	private void updateServiceCenterId() {
		newEmployee.setServiceCenterId(SESSION.getSession().getServiceCenterId());
	}
	
	private void updateRole() {
		System.out.println("Choose Employee Role :\n1. Receptionist\n2. Mechanic");
		int option = readNumber();
		if(option == 1) {
			newEmployee.setRole(Role.RECEPTIONIST);
			//newUser.setRoleId(Role.RECEPTIONIST);
		} else if(option == 2) {
			newEmployee.setRole(Role.MECHANIC);
			//newUser.setRoleId(Role.RECEPTIONIST);

		} else {
			System.out.println("Error! Invalid input");
			updateRole();
		}
		// update db
	}

	private void updateFrequency() {
		if(newEmployee.getRole().equals(Role.RECEPTIONIST)) {
			newEmployee.setFrequency(WorkFrequency.MONTHLY);
		} else {
			newEmployee.setFrequency(WorkFrequency.HOURLY);
		}
	}
	
	private void updateAddress() {
		System.out.println("Enter employee address:");
		newEmployee.setAddress(readString());
		// update db
	}
	
	private void updateEmail() {
		System.out.println("Enter employee email address:");
		newEmployee.setEmail(readString());
		// update db
	}
	
	private void updatePhoneNumber() {
		System.out.println("Enter employee phone number:");
		newEmployee.setPhoneNumber(readLong());
		// update db
	}
	
	private void updateStartDate() {
		System.out.println("Enter employee start date (" + Validators.DATE_FORMAT + "):");
		try {
			newEmployee.setStartDate(DateFormatUtil.getDateFromString(readDate()));
		} catch (ParseException e) {
			
			System.out.println("Error! Invalid Date");
			updateStartDate();
		}
		// update db
	}

	private void updateCompensation() {
		System.out.println("Enter employee compensation:");
		newEmployee.setWages(readNumber());
		// update db
	}
	
	private UsersBe updateUser() {
		if(newEmployee.getRole().equals(Role.RECEPTIONIST)) {
			
		// update db
		}
		newEmployee.setUser(newUser);
		return newUser;
		
	}
	
}
