package app.manager;

import java.util.ArrayList;

import app.Page;
import be.DisplayInventoryBe;
import be.ServiceCenterInventoryBe;
import bl.ServiceCenterInventoryBl;
import session.SESSION;

public class InventoryPage extends Page {

	public String[] getOptions() {
		return new String[] { "Go Back", };
	}

	public Page[] getPages() {
		return new Page[] { Pages.MANAGER };
	}

	public void open() {
		displayInventory(); // todo my service center id
		super.open();
	}
	
	private void displayInventory() {
		// todo fetch from db
		ServiceCenterInventoryBl serviceCenterInventoryBl = new ServiceCenterInventoryBl();
		ArrayList<DisplayInventoryBe> displayInventoryBeList = new ArrayList<DisplayInventoryBe>();
		//getId from "session"
		
		printHeader();
		displayInventoryBeList=serviceCenterInventoryBl.displayInventory(SESSION.getSession().getServiceCenterId());
		
		
		
		for(int i =0; i<displayInventoryBeList.size();i++) {
			
			printRecord(displayInventoryBeList.get(i));
			

		}
	
		
		//System.out.println("Displaying inventory");
	}
	
	private void printRecord(DisplayInventoryBe displayInventoryBe) {
		System.out.format("%15s%30s%15s%15s%15s%15s\n", 
				displayInventoryBe.getPartId(),
				
				displayInventoryBe.getPartMake()+" "+displayInventoryBe.getPartName(),
				displayInventoryBe.getQuantity(),
				displayInventoryBe.getUnitPrice(),
				displayInventoryBe.getMinQty(),
				displayInventoryBe.getMinOrdThr()
				
		
				);
	}
	private void printHeader() {
		System.out.format("%15s%30s%15s%15s%15s%15s\n", 
				"PART ID",
				"PART NAME",
				"QUANTITY",
				"UNIT PRICE",
				"MIN QTY THR",
				"MIN ORD THR"
				);
	    System.out.format("%s\n", "--------------------------------------------------"
	    		+ "--------------------------------------------------"
	    		+ "--------------------------------------------------");
	}
	

}
