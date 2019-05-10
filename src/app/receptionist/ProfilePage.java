package app.receptionist;
import app.Page;
import static app.receptionist.Pages.*;

public class ProfilePage extends Page {

	public String[] getOptions() {
		return new String[] { "View Profile", "Update Profile", "Go Back", };
	}

	public Page[] getPages() {
		return new Page[] { VIEW_PROFILE, UPDATE_PROFILE, RECEPTIONIST };
	}

	public void open() {
		displayOptions();
		int option = readOption();
		Page page = getPages()[option - 1];
		page.open();
	}

}
