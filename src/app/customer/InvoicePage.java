package app.customer;

import java.util.ArrayList;

import app.Page;
import be.PartBe;
import be.PartNumberBe;
import be.WorkAppointmentBe;
import bl.InvoiceBl;
import dao.PartDao;
import session.SESSION;
import util.DateFormatUtil;
import util.ServiceType;

/**
 * @author Juan Jose Rivera
 *
 */
public class InvoicePage extends Page {
	private static final String TABLE_FORMAT = "%15s%25s%25s%15s%15s%25s%25s\n";
	private static PartDao partDao = new PartDao();

	public String[] getOptions() {
		return new String[] { 
				"View Invoice Detail", 
				"Go Back",

		};
	}

	public Page[] getPages() {
		return new Page[] { 
				Pages.VIEW_INVOICE_DET, 
				Pages.CUSTOMER,

		};

	}
	
	public void open() {
		displayInvoices();
		super.open();
	}
	
	public void displayInvoices() {
		InvoiceBl bl = new InvoiceBl();
		printHeader();
		int serviceCenterId = SESSION.getSession().getServiceCenterId();
		int cust_id = SESSION.getSession().getCustomerId();
		ArrayList<WorkAppointmentBe> records = bl.getAllForCustomer(serviceCenterId,cust_id);
		// todo get total labor hours
		// todo get total labor wages per hour
		// todo get total service cost
		for (WorkAppointmentBe record : records) {
			printRecord(record);
		}
	}
	private void printRecord(WorkAppointmentBe workAppointmentBe) {
		System.out.format(TABLE_FORMAT, 
			workAppointmentBe.getId(),
			//workAppointmentBe.getCustomerName(),
			DateFormatUtil.formatDateTime(workAppointmentBe.getStartDate()),
			DateFormatUtil.formatDateTime(workAppointmentBe.getEndDate()),
	
			workAppointmentBe.getCarId(),
			workAppointmentBe.getServiceType(),
			workAppointmentBe.getMechanicName(),
			"Total Cost"
	
		);
	}
	


	private void printHeader() {
		System.out.format(TABLE_FORMAT, 
			"Service ID",
			"Service Start Date/Time",
			"Service End Date/Time",
			"Licence Plate",
			"Service Type",
			"Mechanic Name",
			"Total Service Cost"
		);
	    System.out.format("%s\n", "--------------------------------------------------"
	    		+ "--------------------------------------------------"
	    		+ "--------------------------------------------------"
	    		+ "--------------------------------------------------"
	    		+ "--------------------------------");
	}
}

