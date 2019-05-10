package app.customer;

import app.Page;

public class ScheduleRepairPage2 extends Page {

	@Override
	public String[] getOptions() {
		return new String[] {"Repair on Date", "Go Back"};
	}

	@Override
	public Page[] getPages() {
		return new Page[] {Pages.SCHEDULE_SERVICE, Pages.SCHEDULE_REPAIR};
	}

	public void open() {
		displayAvailableDates();
		displayOptions();
		int option = readOption();
		Page page = getPages()[option - 1];
		if(option == 1) {
			System.out.println("Pick one of the two dates shown ");
			updateAppointment();
		}
		page.open();
	}

	private void createDiagnosticReport(String string) {
		
		
	}

	private void displayAvailableDates() {
		
		
	}
	
	private void updateAppointment() {
		int dateOption = readNumber();
	}
}
