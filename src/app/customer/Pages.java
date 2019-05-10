package app.customer;

import app.Page;
import app.home.LogOutPage;


public class Pages {
	public static final Page CUSTOMER = new CustomerPage();
	
	public static final Page PROFILE = new ProfilePage();
	public static final Page VIEW_PROFILE = new ViewProfilePage();
	public static final Page UPDATE_PROFILE = new UpdateProfilePage();
	public static final Page REGISTER_CAR = new RegisterCarPage();
	public static final Page SERVICE = new ServicePage();
	public static final Page VIEW_SERVICE_HIST = new ViewServiceHistoryPage();
	
	
	public static final Page SCHEDULE_SERVICE = new ScheduleServicePage();
	public static final Page RESCHEDULE_SERVICE = new RescheduleServicePage();
	public static final Page SCHEDULE_MAINTENANCE = new ScheduleMaintenancePage();
	public static final Page SCHEDULE_REPAIR = new ScheduleRepairPage();
	public static final Page SCHEDULE_MAINTENANCE2 = new ScheduleMaintenancePage2();
	public static final Page SCHEDULE_REPAIR2 = new ScheduleRepairPage2();
	public static final Page RESCHEDULE_SERVICE2 = new RescheduleServicePage2();
	
	public static final Page INVOICE = new InvoicePage();
	public static final Page VIEW_INVOICE_DET = new ViewInvoiceDetailPage();



	
	public static final Page LOGOUT = new LogOutPage();
	

}
