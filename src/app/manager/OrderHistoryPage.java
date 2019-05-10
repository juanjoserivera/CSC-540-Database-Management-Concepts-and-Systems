package app.manager;

import java.util.ArrayList;

import app.Page;
import be.DisplayOrderBe;
import bl.PartOrderBl;
import util.DateFormatUtil;

public class OrderHistoryPage extends Page {

	public String[] getOptions() {
		return new String[] { "Go Back", };
	}

	public Page[] getPages() {
		return new Page[] { Pages.ORDERS };
	}

	public void open() {
		displayOrderHistory(); // todo my service center id
		super.open();
	}
	
	private void displayOrderHistory() {
		// todo fetch from db
		PartOrderBl partOrderBl = new PartOrderBl();
		ArrayList<DisplayOrderBe> displayOrderList;
		printHeader();
		displayOrderList = partOrderBl.displayAllOrders();
		
		for(int i=0; i<displayOrderList.size(); i++) {
			printRecord(displayOrderList.get(i));
		}
		
		//System.out.println("Displaying Order History");
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
