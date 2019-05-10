package bl;

import java.util.ArrayList;

import be.*;
import dao.*;

/**
 * @author Juan Jose Rivera
 *
 */
public class InvoiceBl {
	
	private static InvoiceDao dao = new InvoiceDao();
	private static WorkAppointmentDao workAppointmentDao = new WorkAppointmentDao();
	
	public ArrayList<WorkAppointmentBe> getByServiceCenter(int serviceCenterId) {
		return workAppointmentDao.getCompletedByServiceCenter(serviceCenterId);
	}
	
	public ArrayList<WorkAppointmentBe> getAllForCustomer(int serviceCenterId, int cust_id) {
		return workAppointmentDao.getAllForCustomer(serviceCenterId,cust_id);
	}
	
	public WorkAppointmentBe getWorkAppointmet(int id) {
		return workAppointmentDao.get(id);
		
	}
	
	
	

	public InvoiceBl get(int id) {
		InvoiceBe be = dao.get(id);
		populateCustomerBe(be);
		populateWorkAppointment(be);
		return null;
	}
	
	public boolean insert(InvoiceBe be) {
		dao.insert(be);
		dao.insertInvoiceCustomer(be);
		dao.insertInvoiceWorkApp(be);
		return true;
	}
	
	private boolean populateCustomerBe(InvoiceBe be) {
		return false;
	}

	private boolean populateWorkAppointment(InvoiceBe be) {
		return false;
	}
}
