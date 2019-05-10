package app.receptionist;

import app.Page;
import app.home.LogOutPage;

public class Pages {
	public static final Page RECEPTIONIST = new ReceptionistPage();
	public static final Page PROFILE = new ProfilePage();
	public static final Page VIEW_PROFILE = new ViewProfilePage();
	public static final Page UPDATE_PROFILE = new UpdateProfilePage();
	public static final Page VIEW_CUSTOMER_PROFILE = new ViewCustomerProfilePage();
	public static final Page SCHEDULE_SERVICE = new ScheduleServicePage();
	public static final Page RESCHEDULE_SERVICE = new RescheduleServicePage();
	public static final Page REGISTER_CAR = new RegisterCarPage();
	public static final Page UPDATE_INVENTORY = new UpdateInventoryPage();
	public static final Page RECORD_DELIVERIES = new RecordDeliveriesPage();
	public static final Page SERVICE_HISTORY = new ServiceHistoryPage();
	public static final Page INVOICES = new InvoicesPage();
	public static final Page SCHEDULE_MAINTENANCE = new ScheduleMaintenancePage();
	public static final Page SCHEDULE_REPAIR = new ScheduleRepairPage();
	public static final Page SCHEDULE_MAINTENANCE2 = new ScheduleMaintenancePage2();
	public static final Page SCHEDULE_REPAIR2 = new ScheduleRepairPage2();
	
	public static final Page LOGOUT = new LogOutPage();


}
