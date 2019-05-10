package app.manager;

import app.Page;
import bl.NewCarModelBl;

public class NewCarModelPage extends Page {
	public String[] getOptions() {
		return new String[] {
				"Add car", "Go Back",
		};
	}
	
	public Page[] getPages() {
		return new Page[] { Pages.MANAGER };
	}

	public void open() {
		String make = readMake();
		String model = readModel();
		int miles_a = readServiceAMiles();
		int months_a = readServiceAMonths();
		String parts_a = readServiceAPartsList();
		int miles_b = readServiceBMiles();
		int months_b = readServiceBMonths();
		String parts_b = readServiceBPartsList();
		int miles_c = readServiceCMiles();
		int months_c = readServiceCMonths();
		String parts_c = readServiceCPartsList();
		displayOptions();

		int option = readOption();
		Page backPage = getPages()[0];
		if (option == 1) {
			NewCarModelBl newCarModelBl = new NewCarModelBl();
			newCarModelBl.registerCarModel(make, model, miles_a, months_a, parts_a, miles_b, months_b, parts_b, miles_c, months_c, parts_c);
		}
		else {
				System.out.println("Error while adding the car!");
		}
		backPage.open();
	}
	
	private String readMake() {
		System.out.println("Enter Make:");
		String make = readString();
		return make;
	}

	private String readModel() {
		System.out.println("Enter Model:");
		String model = readString();
		return model;
	}
	
	private int readYear() {
		System.out.println("Enter Year:");
		int year = readNumber();
		return year;
	}

	private int readServiceAMiles() {
		System.out.println("Enter Service A Miles:");
		int miles = readNumber();
		return miles;
	}

	private int readServiceAMonths() {
		System.out.println("Enter Service A Months:");
		int months = readNumber();
		return months;
	}

	private String readServiceAPartsList() {
		System.out.println("Enter Service A Parts List:");
		String partsList = readString();
		return partsList;
	}
	
	private int readServiceBMiles() {
		System.out.println("Enter Service B Miles:");
		int miles = readNumber();
		return miles;
	}

	private int readServiceBMonths() {
		System.out.println("Enter Service B Months:");
		int months = readNumber();
		return months;
	}

	private String readServiceBPartsList() {
		System.out.println("Enter Service B Parts List:");
		String partsList = readString();
		return partsList;
	}

	private int readServiceCMiles() {
		System.out.println("Enter Service C Miles:");
		int miles = readNumber();
		return miles;
	}

	private int readServiceCMonths() {
		System.out.println("Enter Service C Months:");
		int months = readNumber();
		return months;
	}

	private String readServiceCPartsList() {
		System.out.println("Enter Service C Parts List:");
		String partsList = readString();
		return partsList;
	}
	
}
