package app.home;

import app.Page;

public class HomePage extends Page {
	public String[] getOptions() {
		return new String[] {
				"Login", "Sign Up", "Exit"
		};
	}
	
	public Page[] getPages() {
		return new Page[] { Pages.LOGIN, Pages.SIGN_UP };
	}

	public void open() {
		displayOptions();
		int option = readOption();
		if (option <= 2) {
			int pageIndex = option - 1;
			Page page = getPages()[pageIndex];
			page.open();
		} else {
			System.out.println("Good Bye!!!");
			System.exit(0);
		}
	}

}
