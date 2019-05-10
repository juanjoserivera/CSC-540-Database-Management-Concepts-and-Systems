package app.customer;

import app.Page;

public class ScheduleServicePage extends Page{

	@Override
	public String[] getOptions() {
		return new String[] { "Schedule Maintenance", "Schedule Repair", "Go Back"};
	}

	@Override
	public Page[] getPages() {
		return new Page[] {Pages.SCHEDULE_MAINTENANCE, Pages.SCHEDULE_REPAIR, Pages.CUSTOMER};
	}
	
	public void open() {
		System.out.println("Enter customer Email address:");
		String customerEmail = readString();
		System.out.println("Enter License Plate:");
		String licensePlate = readString();
		System.out.println("Enter Current Mileage:");
		int currentMileage = readNumber();
		System.out.println("Enter Mechanic Name(optional):");
		String mechanicName = readStringOptional();
		super.open();
	}

}
