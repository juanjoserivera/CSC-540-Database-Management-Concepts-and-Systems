package app.home;

import app.Page;
import be.CustomerBe;
import bl.CustomerBl;

public class SignUpPage extends Page {
	public String[] getOptions() {
		return new String[] {
				"Sign Up", "Go Back",
		};
	}
	
	public Page[] getPages() {
		return new Page[] { Pages.HOME };
	}

	public void open() {
		String email = readEmail();
		String password = readPassword();
		String name = readName();
		String address = readAddress();
		long phone = readPhone();
		int serviceCenterId = readServiceCenter();

		displayOptions();

		int option = readOption();
		Page backPage = getPages()[0];
		if (option == 1) {
			// todo sign up
			// if success getPages()[1]
			CustomerBl customerBl = new CustomerBl();
			CustomerBe customerBe = new CustomerBe();
			String createdUserId;
			customerBe.setEmail(email);
			customerBe.setName(name);
			customerBe.setAddress(address);
			customerBe.setPhoneNumber(phone);
			customerBe.setServiceCenterId(serviceCenterId);
			createdUserId = customerBl.setCustomer(customerBe, password);
			
			if (createdUserId != null) {
				System.out.println("Customer Created with User ID: "+createdUserId);
			}else {
				System.out.println("Error while signing up!");

			}
			System.out.println("Success!");
			backPage.open();
		} else {
			backPage.open();
		}
	}
	
	private long readPhone() {
		System.out.println("Enter Phone number:");
		return readLong();
	}
	
	private int readServiceCenter() {
		System.out.println("Enter Service Center:");
		return readNumber();
	}
	private String readEmail() {
		System.out.println("Enter Email Address:");
		return readString();
	}

	private String readPassword() {
		System.out.println("Enter Password:");
		return readString();
	}

	private String readName() {
		System.out.println("Enter Name:");
		return readString();
	}

	private String readAddress() {
		System.out.println("Enter Address:");
		return readString();
	}
}
