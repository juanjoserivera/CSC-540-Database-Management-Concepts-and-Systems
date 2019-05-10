package app.receptionist;

import app.Page;

public class ReceptionistPage extends Page {

	public String[] getOptions() {
		return new String[] {
				"Profile",
				"View Customer Profile",
				"Register Car",
				"Service History",
				"Schedule Service",
				"Reschedule Service",
				"Invoices",
				"Daily Task-Update Inventory",
				"Daily Task-Record Deliveries",
				"Logout",
		};
	}

	public Page[] getPages() {
		return new Page[] {
				Pages.PROFILE,
				Pages.VIEW_CUSTOMER_PROFILE,
				Pages.REGISTER_CAR,
				Pages.SERVICE_HISTORY,
				Pages.SCHEDULE_SERVICE,
				Pages.RESCHEDULE_SERVICE,
				Pages.INVOICES,
				Pages.UPDATE_INVENTORY,
				Pages.RECORD_DELIVERIES,
				Pages.LOGOUT
		};
	}

	// public void open() { getPages()[4].open(); }

}
