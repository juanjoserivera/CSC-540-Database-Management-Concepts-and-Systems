package app.customer;

import app.Page;
import bl.CustomerBl;
import bl.EmployeeBl;
import bl.UsersBl;
import session.SESSION;

public class UpdateProfilePage extends Page {
	public String[] getOptions() {
		return new String[] { 
				"Name", 
				"Address", 
				"Phone Number", 
				"Password", 
				"Go Back"
				};
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
			updatePhoneNumber();
			open();
			break;
		case 4:
			updatePassword();
			open();
			break;
		case 5:
			getPages()[0].open();
		}
	}

	private void updateName() {
		System.out.println("Enter the new name:");
		String name = readString();
		
		
		if(!CustomerBl.updateName(SESSION.getSession().getUserId(), name)) {
			System.out.println("Update failed!");
			updateName();
		}

		System.out.println("Successfully updated!");
	}

	private void updateAddress() {
		System.out.println("Enter the new address:");
		String address = readString();
		
		if(!CustomerBl.updateAddress(SESSION.getSession().getUserId(), address)) {
			System.out.println("Update failed!");
			updateAddress();
		}

		
		System.out.println("Successfully updated!");

	}
	
	
	
	private void updatePhoneNumber() {
		System.out.println("Enter the new phone number:");
		long phone = readLong();
		
		if(!CustomerBl.updatePhone(SESSION.getSession().getUserId(), phone)) {
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
