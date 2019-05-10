package app.receptionist;

import app.Page;
import bl.EmployeeBl;
import bl.UsersBl;
import session.SESSION;

public class UpdateProfilePage extends Page {
	int receptionistId = SESSION.getSession().getEmployeeId();
	public String[] getOptions() {
		return new String[] { "Name", "Address", "Email Address", "Phone Number", "Password", "Go Back"};
	}

	public Page[] getPages() {
		return new Page[] { Pages.PROFILE };
	}

	public void open() {
		displayOptions();
		int option = readOption();
		switch (option) {
		case 1:
			updateName();
			open();
			break;
		case 2:
			updateAddress();
			open();
			break;
		case 3:
			updateEmail();
			open();
			break;
		case 4:
			updatePhoneNumber();
			open();
			break;
		case 5:
			updatePassword();
			open();
			break;
		case 6:
			getPages()[0].open();
		}
	}

	private void updateName() {
		System.out.println("Enter the new name:");
		String name = readString();
		if(!EmployeeBl.updateName(receptionistId, name)) {
			System.out.println("Update failed!");
			updateName();
		}
		System.out.println("Successfully updated!");
	}

	private void updateAddress() {
		System.out.println("Enter the new address:");
		String address = readString();
		if(!EmployeeBl.updateAddress(receptionistId, address)) {
			System.out.println("Update failed!");
			updateAddress();
		}
		System.out.println("Successfully updated!");

	}
	
	private void updateEmail() {
		System.out.println("Enter the new email address:");
		String email = readString();
		if(!EmployeeBl.updateEmail(receptionistId, email)) {
			System.out.println("Update failed!");
			updateEmail();
		}
		System.out.println("Successfully updated!");
	}
	
	private void updatePhoneNumber() {
		System.out.println("Enter the new phone number:");
		long phone = readLong();
		if(!EmployeeBl.updatePhone(receptionistId, phone)) {
			System.out.println("Update failed!");
			updatePhoneNumber();
		}
		System.out.println("Successfully updated!");
	}
	
	private void updatePassword() {
		System.out.println("Enter the new password:");
		String password = readString();
		if(!UsersBl.updatePassword(SESSION.getSession().getUserId(), password)) {
			System.out.println("Update failed!");
			updatePassword();
		}
		System.out.println("Successfully updated!");
	}
}
