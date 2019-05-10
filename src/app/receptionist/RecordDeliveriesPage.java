package app.receptionist;

import java.util.Date;

import app.Page;
import bl.RecordDeliveryBl;

public class RecordDeliveriesPage extends Page{

	@Override
	public String[] getOptions() {
		return new String[] { "Enter Order ID(CSV)", "Go Back"};
	}

	@Override
	public Page[] getPages() {
		return new Page[] { Pages.RECEPTIONIST };
	}
	
	public void open() {
		displayOptions();
		int option = readOption();
		if(option == 1) {
			System.out.println("Enter comma separated list of order ids:");
			String orderIds = readString();
			if(updateOrderStatus(orderIds))
				System.out.println("Order Status updated");
			else System.out.println("Order Status update failed!!");
		}  
		Pages.RECEPTIONIST.open();
	}

	private boolean updateOrderStatus(String orderIds) {
		RecordDeliveryBl bl = new RecordDeliveryBl();
 		return bl.recordDelivery(orderIds);
		
	}
	
	
	
}
