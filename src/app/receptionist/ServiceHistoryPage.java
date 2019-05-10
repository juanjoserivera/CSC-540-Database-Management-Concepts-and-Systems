package app.receptionist;

import java.util.ArrayList;

import app.Page;
import be.WorkAppointmentBe;
import bl.WorkAppointmentBl;
import session.SESSION;
import util.DateFormatUtil;

public class ServiceHistoryPage extends Page {

	private static final String TABLE_FORMAT = "%15s%30s%15s%15s%20s%25s%30s%20s\n";

	public String[] getOptions() {
		return new String[] { "Go Back", };
	}

	public Page[] getPages() {
		return new Page[] { Pages.RECEPTIONIST };
	}

	public void open() {
		displayServiceHistory();
		super.open();
	}

	private void displayServiceHistory() {
		WorkAppointmentBl bl = new WorkAppointmentBl();
		printHeader();
		int serviceCenterId = SESSION.getSession().getServiceCenterId();
		ArrayList<WorkAppointmentBe> records = bl.getByServiceCenter(serviceCenterId);
		for (WorkAppointmentBe record : records) {
			printRecord(record);
		}
	}

	private void printRecord(WorkAppointmentBe record) {
		System.out.format(TABLE_FORMAT,
			(record.getRepairServiceId()!=0?record.getRepairServiceId():record.getMaintenanceServiceId()),
			record.getCustomerName(),
			record.getCarId(),
			record.getServiceType(),
			record.getMechanicName(),
			record.getStartDate().format(DateFormatUtil.dateTimeFormatter),
			record.getEndDate().format(DateFormatUtil.dateTimeFormatter),
			record.getStatus()
		);
	}

	private void printHeader() {
		System.out.format(TABLE_FORMAT,
			"Service ID",
			"Customer Name",
			"Licence Plate",
			"Service Type",
			"Mechanic Name",
			"Service Start Date/Time",
			"Service End Date/Time",
			"Service Status"
		);
	    System.out.format("%s\n", "--------------------------------------------------"
	    		+ "-------------------------------------------------------"
	    		+ "-----------------------------------------------------------------");
	}
}
