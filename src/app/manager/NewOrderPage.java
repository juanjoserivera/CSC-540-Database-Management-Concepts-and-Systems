package app.manager;

import java.util.HashMap;

import app.Page;
import bl.PartOrderBl;
import util.DateFormatUtil;

public class NewOrderPage extends Page {
	public String[] getOptions() {
		return new String[] { "Place Order", "Go Back", };
	}

	public Page[] getPages() {
		return new Page[] { Pages.ORDERS };
	}

	public void open() {
		int partId = readPartID();
		int qty = readQuantity();
		displayOptions();
		int option = readOption();
		Page backPage = getPages()[0];
		if (option == 1) {
			// todo add to db
			PartOrderBl partOrderBl = new PartOrderBl();
			HashMap<String, String> result;
			result = partOrderBl.insetPartOrderFromPartId(partId, qty);
			if (result != null) {
				printHeader();
				printResult(result);

				System.out.println("Your order has been placed!");

			} else {
				System.out.println("There is an error with your order!");

			}

			backPage.open();
		} else {
			backPage.open();
		}
	}

	private void printResult(HashMap<String, String> result) {
		System.out.format("%20s%20s\n", 
				result.get("orderId"),
				DateFormatUtil.formatStringDate(result.get("expectedDeliveryDate")));

	}

	private void printHeader() {
		System.out.format("%20s%20s\n", "ORDER ID", "EXPECTED DATE");
		System.out.format("%s\n",
				"--------------------------------------------------"
						+ "--------------------------------------------------"
						+ "--------------------------------------------------");
	}

	private int readPartID() {
		System.out.println("Enter Part ID:");
		int number = readNumber();
		// todo update db
		return number;
	}

	private int readQuantity() {
		System.out.println("Enter Quantity:");
		int number = readNumber();
		// todo update db
		return number;

	}

}
