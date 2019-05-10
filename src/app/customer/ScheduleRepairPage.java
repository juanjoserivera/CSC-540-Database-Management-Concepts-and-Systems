package app.customer;

import app.Page;

public class ScheduleRepairPage extends Page {

	@Override
	public String[] getOptions() {
		return new String[] {"Engine knock", "Car drifts in a particular direction", "Battery does not hold charge", "Black/unclean exhaust", "A/C-Heater not working", "Headlamps/Taillamps not working", "Check engine light", "Go back"};
	}

	@Override
	public Page[] getPages() {
		return new Page[] {Pages.SCHEDULE_REPAIR2, Pages.SCHEDULE_SERVICE};
	}

	public void open() {
		displayOptions();
		int option = readOption();
		if(option >=1 && option <= 7) {
			createDiagnosticReport(getOptions()[option - 1]);
			Pages.SCHEDULE_REPAIR2.open();
		} 
		Pages.SCHEDULE_SERVICE.open();
	}
	
	private void displayAvailableDates() {
		
		
	}

	public void createDiagnosticReport(String repairType) {
		
	}
	
}
