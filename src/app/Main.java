
package app;

import app.home.HomePage;
import app.manager.ManagerPage;
import app.receptionist.ReceptionistPage;

public class Main {

	public static void main(String[] args) {
//		ManagerPage page = new ManagerPage();
		HomePage page = new HomePage();
//		ReceptionistPage page = new ReceptionistPage();
		page.open();
	}

}
