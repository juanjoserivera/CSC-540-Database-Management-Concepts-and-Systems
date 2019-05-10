package app.manager;

import java.util.ArrayList;

import app.Page;
import be.ServiceDetailsBe;
import bl.ServiceDetailsBl;

public class CarServiceDetailsPage extends Page {

	public String[] getOptions() {
		return new String[] { "Go Back", };
	}

	public Page[] getPages() {
		return new Page[] { Pages.MANAGER };
	}

	public void open() {
		displayCarServiceDetails(); // todo get my service center id
		super.open();
	}
	
	private void displayCarServiceDetails() {
		ArrayList<ServiceDetailsBe> arrayList;
		ServiceDetailsBl bl = new ServiceDetailsBl();
		printHeader();
		arrayList = bl.getMS();
		for(int i =0; i<arrayList.size();i++)
		{
			printRecord(arrayList.get(i));
		}
		//System.out.println("Displaying Car Service Details...");
	}
	private void printRecord(ServiceDetailsBe serviceDetailsBe) {
		
		System.out.format("%20s%20s%20s%20s%50s\n", 
				serviceDetailsBe.getMake(),
				serviceDetailsBe.getModel(),
				serviceDetailsBe.getType(),
				serviceDetailsBe.getMiles(),
				serviceDetailsBe.getBasicServices()
				);
	}
	private void printHeader() {
		System.out.format("%20s%20s%20s%20s%50s\n", 
				"MAKE",
				"MODEL",
				"TYPE",
				"MILES",
				"BASIC SERVICES"
				);
	    System.out.format("%s\n", "--------------------------------------------------"
	    		+ "--------------------------------------------------"
	    		+ "--------------------------------------------------");
	}

}
