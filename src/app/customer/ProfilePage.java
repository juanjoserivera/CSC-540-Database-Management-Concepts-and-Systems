package app.customer;

import app.Page;

public class ProfilePage extends Page {

	public String[] getOptions() {
		return new String[] { 
				"View Profile", 
				"Update Profile", 
				"Go Back", };
	}

	public Page[] getPages() {
		return new Page[] { 
				Pages.VIEW_PROFILE, 
				Pages.UPDATE_PROFILE, 
				Pages.CUSTOMER };
	}



}
