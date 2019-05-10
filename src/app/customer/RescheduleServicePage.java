package app.customer;

import app.Page;

public class RescheduleServicePage extends Page{

	@Override
	public String[] getOptions() {
		return new String[] {"Pick a service", "Go Back"}; 
	}

	@Override
	public Page[] getPages() {
		return new Page[] {Pages.RESCHEDULE_SERVICE2, Pages.CUSTOMER};
	}

	public void open() {
		System.out.println("Enter Customer Email Address:");
		String email = readString();
		displayUpcomingServices(email);
		super.open();
	}
	
	public void displayUpcomingServices(String email) {
		
	}
}
