package app.customer;

import app.Page;

public class RescheduleServicePage2 extends Page {

	@Override
	public String[] getOptions() {
		return new String[] {"Rechedule Date", "Go Back"};
	}

	@Override
	public Page[] getPages() {
		return new Page[] {Pages.CUSTOMER, Pages.RESCHEDULE_SERVICE};
	}

	public void open() {
		System.out.println("Enter Service id to be rescheduled");
		int serviceId = readNumber();
		displayAvailableDates();
		displayOptions();
		int option = readOption();
		Page page = getPages()[option - 1];
		if(option == 1) {
			System.out.println("Pick a date to reschedule");
			int dateOption = readNumber();
			updateAppointment();
		}
		page.open();
	}

	private void updateAppointment() {
		
		
	}

	private void displayAvailableDates() {
		
		
	}
}
