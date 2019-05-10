package app;

import java.util.Scanner;

import app.manager.Validators;
import session.SESSION;

public abstract class Page {
	private static Scanner sc = new Scanner(System.in);

	public abstract String[] getOptions();

	public abstract Page[] getPages();


	
	public void open() {
		displayOptions();
		int option = readOption();
		Page page = getPages()[option - 1];
		page.open();
	}
	
	protected int getServiceCenterId() {
		return 1;
//		return SESSION.getSession().getServiceCenterId();
	}
	
	protected void displayOptions() {
		String[] options = getOptions();
		for (int i = 0; i < options.length; i++) {
			System.out.println((i + 1) + ". " + options[i]);
		}
		System.out.println("Enter choice (1-" + getOptions().length + "):");
	}
	
	protected int readOption() {
		int numOptions = getOptions().length;
		return readOption(numOptions);
	}
	
	protected int readOption(int numOptions) {
		int choice = readNumber();
		if (choice > 0 && choice <= numOptions) {
			return choice;
		} else {
			System.out.println("Error! Options must be between 1-" + numOptions);
			return readOption(numOptions);
		}
	}
	
	protected String readDate() {
		String date = readString();
		if (Validators.isDateValid(date)) {
			return date;
		} else {
			System.out.println("Error! Invalid date");
			return readDate();
		}
	}
	
	protected int readNumber() {
		try {
			String number = sc.next();
			return Integer.parseInt(number);
		} catch (NumberFormatException e) {
			System.out.println("Error! Invalid number");
			return readNumber();
		}
	}
	
	protected long readLong() {
		try {
			String number = sc.next();
			return Long.parseLong(number);
		} catch (NumberFormatException e) {
			System.out.println("Error! Invalid number");
			return readLong();
		}
	}
	
	protected String readString() {
		try {
			String line;
			while((line = sc.nextLine()).isEmpty());
			return line.trim();
		} catch (Exception e) {
			System.out.println("Error! Invalid input");
			return readString();
		}
	}
	
	protected String readStringOptional() {
		try {
			String line = sc.nextLine();
			return line.trim();
		} catch (Exception e) {
			System.out.println("Error! Invalid input");
			return readString();
		}
	}
}
