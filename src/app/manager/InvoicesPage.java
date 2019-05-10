package app.manager;

import java.util.ArrayList;

import app.Page;
import be.PartBe;
import be.PartNumberBe;
import be.WorkAppointmentBe;
import bl.InvoiceBl;
import dao.InvoiceDao;
import dao.PartDao;
import session.SESSION;
import util.DateFormatUtil;
import util.ServiceType;

public class InvoicesPage extends Page {

	private static PartDao partDao = new PartDao();
	private static InvoiceDao invoiceDao = new InvoiceDao();

	private static final String TABLE_FORMAT = "%15s%25s%25s%25s%15s%15s%25s%30s%20s%25s%20s\n";

	public String[] getOptions() {
		return new String[] { "Go Back", };
	}

	public Page[] getPages() {
		return new Page[] { Pages.MANAGER };
	}

	public void open() {
		displayInvoices();
		super.open();
	}

	private void displayInvoices() {
		InvoiceBl bl = new InvoiceBl();
		printHeader();
		int serviceCenterId = SESSION.getSession().getServiceCenterId();
		ArrayList<WorkAppointmentBe> records = bl.getByServiceCenter(serviceCenterId);
		for (WorkAppointmentBe record : records) {
			printRecord(record);
		}
	}

	private void printRecord(WorkAppointmentBe workAppointmentBe) {
		ArrayList<PartNumberBe> parts = getParts(workAppointmentBe);
		System.out.format(TABLE_FORMAT,
			workAppointmentBe.getId(),
			workAppointmentBe.getCustomerName(),
			workAppointmentBe.getStartDate().format(DateFormatUtil.dateTimeFormatter),
			workAppointmentBe.getEndDate().format(DateFormatUtil.dateTimeFormatter),
			workAppointmentBe.getCarId(),
			workAppointmentBe.getServiceType(),
			workAppointmentBe.getMechanicName(),
			"",
			workAppointmentBe.getExpectedHours(),
			getLaborWages(workAppointmentBe),
			"$" + workAppointmentBe.getTotalCost() + ".00"
		);
		printPartsUsed(parts);
	}

	private ArrayList<PartNumberBe> getParts(WorkAppointmentBe workAppointmentBe) {
		int maintenanceServiceId = workAppointmentBe.getMaintenanceServiceId();
		int repairServiceId = workAppointmentBe.getRepairServiceId();
		ServiceType serviceType = workAppointmentBe.getServiceType();
		return (serviceType == ServiceType.MAINTENANCE) ?
				partDao.getPartsByMaintenanceServiceId(maintenanceServiceId) :
					partDao.getPartsByRepairServiceId(repairServiceId);
	}

	private String getLaborWages(WorkAppointmentBe workAppointmentBe) {
		int maintenanceServiceId = workAppointmentBe.getMaintenanceServiceId();
		int repairServiceId = workAppointmentBe.getRepairServiceId();
		ServiceType serviceType = workAppointmentBe.getServiceType();
		return (serviceType == ServiceType.MAINTENANCE) ?
				invoiceDao.getLaborWagesByMaintenanceService(maintenanceServiceId) :
					invoiceDao.getLaborWagesByRepairService(repairServiceId);
	}

	private void printPartsUsed(ArrayList<PartNumberBe> parts) {
		for (PartNumberBe partNumber : parts) {
			PartBe partBe = partNumber.getPartBe();
			String s = partBe.getName() + " (" + partNumber.getNum() + ") $" + partBe.getUnitPrice();
			System.out.format("%175s\n", s);
		}
	}

	private void printHeader() {
		System.out.format(TABLE_FORMAT,
			"Service ID",
			"Customer Name",
			"Service Start Date/Time",
			"Service End Date/Time",
			"Licence Plate",
			"Service Type",
			"Mechanic Name",
			"Parts Used in service",
			"Total labor hours",
			"Labor wages per hour",
			"Total Service Cost"
		);
	    System.out.format("%s\n", "--------------------------------------------------"
	    		+ "--------------------------------------------------"
	    		+ "--------------------------------------------------"
	    		+ "--------------------------------------------------"
	    		+ "----------------------------------------");
	}
}
