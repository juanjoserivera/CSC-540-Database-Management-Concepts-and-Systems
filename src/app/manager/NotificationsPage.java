package app.manager;

import java.text.DateFormat;
import java.util.ArrayList;

import app.Page;
import be.DisplayOrderBe;
import be.NotificationBe;
import bl.NotificationBl;
import bl.PartOrderBl;
import util.DateFormatUtil;

public class NotificationsPage extends Page {

	public String[] getOptions() {
		return new String[] { "Order ID", "Go Back", };
	}

	public Page[] getPages() {
		return new Page[] { Pages.NOTIFICATIONS_DETAIL, Pages.MANAGER, };
	}

	public void open() {
		displayNotifications();
		displayOptionMenu();
	}
	
	private void displayOptionMenu() {
		displayOptions();
		int option = readOption();
		if (option == 1) {
			displayOrder();
		} else {
			getPages()[1].open();
		}
	}
	
	private void displayOrder() {
		System.out.println("Enter Order ID:");
		int orderId = readNumber();
		if (orderId == 0) {
			getPages()[0].open();
		}
		PartOrderBl partOrderBl = new PartOrderBl();
		DisplayOrderBe displayOrderBe;
		displayOrderBe = partOrderBl.displayOrder(orderId);
		if(displayOrderBe != null) {
			printHeader();
		    printRecord(displayOrderBe);
		}
		 else {
			System.out.println("Order not found");
			
		}
		displayOptionMenu();
	}

	private void displayNotifications() {
		System.out.format("%20s%20s%20s%25s%5s%10s%15s\n", 
				"NOTIFICATION ID",
				"NOTIFICATION DATE",
				"ORDER ID",
				"SUPPLIER NAME",
				"",
				"EXPECTED DATE",
				"DELAY DAYS"
				);
	    System.out.format("%s\n", "--------------------------------------------------"
	    		+ "--------------------------------------------------"
	    		+ "--------------------------------------------------");
	    ArrayList<NotificationBe> notificationBeList;
	    NotificationBl notificationBl = new NotificationBl();
	    notificationBeList = notificationBl.getAllNotifications();
	    for(int i =0; i<notificationBeList.size();i++) {
	    	NotificationBe nb = new NotificationBe();
	    	nb = notificationBeList.get(i);
	    	System.out.format("%20s%20s%20s%25s%5s%10s%15s\n", 
					nb.getId(),
					DateFormatUtil.formatDate(nb.getNotificationDate()),
					nb.getPartOrderBe().getId(),
					nb.getFromName(),
					"",
					DateFormatUtil.formatDate(nb.getPartOrderBe().getExpectedDate()),
					nb.getDelay()
					);
	    }
	}
	private void printRecord(DisplayOrderBe displayOrderBe) {
		
		System.out.format("%10s%12s%25s%25s%25s%5s%12s%12s%15s\n", 
				displayOrderBe.getOrderId(),
				DateFormatUtil.formatDate(displayOrderBe.getOrderPlacementDate()),
				displayOrderBe.getMakeName()+" "+displayOrderBe.getPartName(),
				displayOrderBe.getSuplierName(),
				displayOrderBe.getPurchaserName(),
				displayOrderBe.getQty(),
				displayOrderBe.getUnitPrice(),
				displayOrderBe.getCost(),
				displayOrderBe.getStatus()
				);
	}
	private void printHeader() {
		System.out.format("%10s%12s%25s%25s%25s%5s%12s%12s%15s\n", 
				"ORDER ID",
				"ORDER DATE",
				"PART",
				"SUPLIER NAME",
				"PURCHASER NAME",
				"QTY",
				"UNIT PRICE",
				"COST",
				"STATUS"

				);
	    System.out.format("%s\n", "--------------------------------------------------"
	    		+ "--------------------------------------------------"
	    		+ "--------------------------------------------------");
	}
}
