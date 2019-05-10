package app.customer;

import app.Page;

public class CustomerPage extends Page {
	
	public String[] getOptions() {
		return new String[] {
				"Profile",
				"Register Car",
				"Service",
				"Invoices",
				"Logout",
		};
	}
	
	public Page[] getPages() {
		return new Page[] {
				Pages.PROFILE,
				Pages.REGISTER_CAR,
				Pages.SERVICE,
				Pages.INVOICE,
				Pages.LOGOUT

		};
	}

}
