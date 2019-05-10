package app.customer;

import java.util.Date;

import app.Page;

public class ScheduleMaintenancePage2 extends Page {

	@Override
	public String[] getOptions() {
		return new String[] {"Schedule on Date", "Go Back"};
	}

	@Override
	public Page[] getPages() {
		return new Page[] {Pages.SCHEDULE_SERVICE, Pages.SCHEDULE_MAINTENANCE};
	}
	
	public void open() {
		displayAvailableDates();
		displayOptions();
		int option = readOption();
		Page page = getPages()[option - 1];
		if(option == 1) {
			System.out.println("Pick one of the available dates");
			Date date = readDateOption();
			updateServiceRecord(date);
		}  
		page.open();
	}

	private void updateServiceRecord(Date date) {
		
		
	}
	
	private Date readDateOption() {
		return new Date();
	}

	private void displayAvailableDates() {
		
		
	}

}
