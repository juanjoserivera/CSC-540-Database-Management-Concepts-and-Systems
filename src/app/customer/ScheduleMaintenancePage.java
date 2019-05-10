package app.customer;

import app.Page;

public class ScheduleMaintenancePage extends Page{

	@Override
	public String[] getOptions() {
		
		return new String[] {"Find Service Date", "Go Back"};
	}

	@Override
	public Page[] getPages() {
		return new Page[] {Pages.SCHEDULE_MAINTENANCE2, Pages.SCHEDULE_SERVICE};
	}
	
	public void open() {
		displayOptions();
		int option = readOption();
		Page page = getPages()[option - 1];
		if(!datesAvailable()) {
			System.out.println("Cannot find a service date!! Try again after " 
					+ getLatestDateAvailable());
			Pages.SCHEDULE_SERVICE.open();
		}
			page.open();
	}
		
	
	private boolean datesAvailable() {
		return true;
	}
	
	private String getLatestDateAvailable() {
		return "";
	}

}
