package app.manager;

import app.Page;

public class ManagerPage extends Page {
	
	public String[] getOptions() {
		return new String[] {
				"Profile",
				"View Customer Profile",
				"Add New Employees",
				"Payroll",
				"Inventory",
				"Orders",
				"Notifications",
				"New Car Model",
				"Car Service Details",
				"Service History",
				"Invoices",
				"Logout",
		};
	}
	
	public Page[] getPages() {
		return new Page[] {
				Pages.PROFILE,
				Pages.VIEW_CUSTOMER_PROFILE,
				Pages.NEW_EMPLOYEE,
				Pages.PAYROLL,
				Pages.INVENTORY,
				Pages.ORDERS,
				Pages.NOTIFICATIONS,
				Pages.NEW_CAR_MODEL,
				Pages.CAR_SERVICE_DETAILS,
				Pages.SERVICE_HISTORY,
				Pages.INVOICES,
				Pages.LOGOUT
		};
	}

}
