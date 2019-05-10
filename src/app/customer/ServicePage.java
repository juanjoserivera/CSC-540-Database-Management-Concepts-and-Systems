package app.customer;

import app.Page;

/**
 * @author Juan Jose Rivera
 *
 */
public class ServicePage extends Page{

	public  String[] getOptions() {
		return new String[] { 
				"View Service History", 
				"Schedule Service", 
				"Reschedule Service",
				"Go Back", };
	}

	public  Page[] getPages() {
		return new Page[] { 
				Pages.VIEW_SERVICE_HIST, 
				Pages.SCHEDULE_SERVICE, 
				Pages.RESCHEDULE_SERVICE };
	}
}
