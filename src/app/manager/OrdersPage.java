package app.manager;

import app.Page;

public class OrdersPage extends Page {
	
	public String[] getOptions() {
		return new String[] {
				"Order History",
				"New Order",
				"Go Back",
		};
	}
	
	public Page[] getPages() {
		return new Page[] {
				Pages.ORDER_HISTORY,
				Pages.NEW_ORDER,
				Pages.MANAGER,
		};
	}

}
