package app.manager;

import app.Page;

public class NotificationsDetailPage extends Page {

	public String[] getOptions() {
		return new String[] { "Go Back", };
	}

	public Page[] getPages() {
		return new Page[] { Pages.NOTIFICATIONS };
	}

	public void open() { // todo get notification id
		displayNotificationsDetail(0);
		super.open();
	}
	
	private void displayNotificationsDetail(int id) {
		// todo fetch from db
		System.out.println("Notifications Detail info...");
	}

}
