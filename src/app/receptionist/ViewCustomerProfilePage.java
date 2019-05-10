package app.receptionist;

import app.Page;
import be.CustomerBe;
import bl.CustomerBl;

public class ViewCustomerProfilePage extends Page {
	public String[] getOptions() {
		return new String[] {
				"Go Back",
		};
	}
	
	public Page[] getPages() {
		return new Page[] { Pages.RECEPTIONIST };
	}

	public void open() {
		System.out.println("Enter customer Email address:");
		String email = readString();
		displayProfile(email);
		super.open();
	}

	private void displayProfile(String email) {
		// get from db
		CustomerBl.displayCustomerProfile(email);
	}
	
}
