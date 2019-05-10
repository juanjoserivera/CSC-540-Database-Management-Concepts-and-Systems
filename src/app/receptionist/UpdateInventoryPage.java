package app.receptionist;

import app.Page;
import bl.UpdateInventoryBl;

public class UpdateInventoryPage extends Page{

	@Override
	public String[] getOptions() {
		return new String[] { "Go Back", };
	}
	
	@Override
	public Page[] getPages() {
		return new Page[] { Pages.RECEPTIONIST };
	}
	
	public void open() {
		updateInventory();
		super.open();
	}

	private void updateInventory() {
		UpdateInventoryBl ubl = new UpdateInventoryBl();
		if(ubl.updateInventory()) {
			System.out.println("Inventory Update Successful!");
		}
	}

}
