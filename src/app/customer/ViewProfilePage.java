package app.customer;

import app.Page;
import be.CustomerBe;
import bl.CustomerBl;
import session.SESSION;

/**
 * @author Juan Jose Rivera
 *
 */
public class ViewProfilePage extends Page {
	
	public String[] getOptions() {
		return new String[] {
				"Go Back",
		};
	}
	
	public Page[] getPages() {
		return new Page[] { Pages.PROFILE };
	}
	
	public void open() {
		displayProfile();
		super.open();
	}
	
	public void displayProfile() {
		CustomerBl.displayCustomerProfile(SESSION.getSession().getCustomerEmail());
	}

}
