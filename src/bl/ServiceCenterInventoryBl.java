package bl;

import java.util.ArrayList;

import be.DisplayInventoryBe;
import be.ServiceCenterInventoryBe;
import dao.ServiceCenterInventoryDao;

/**
 * @author Juan Jose Rivera
 *
 */
public class ServiceCenterInventoryBl {
	
	public ArrayList<ServiceCenterInventoryBe> getServCentPartInvbyServCentId(int serviceCenterId) {
		ArrayList<ServiceCenterInventoryBe> serviceCenterPartList = new ArrayList<ServiceCenterInventoryBe>();
		ServiceCenterInventoryDao serviceCenterInventoryDao = new ServiceCenterInventoryDao();
		
		serviceCenterPartList = serviceCenterInventoryDao.getServCentPartInvbyServCentId(serviceCenterId);
		populatePart(serviceCenterPartList);
		
		return serviceCenterPartList;

	}
	
	public ArrayList<DisplayInventoryBe> displayInventory(int serviceCenterId) {
		
		ArrayList<DisplayInventoryBe> displayInventorytList = new ArrayList<DisplayInventoryBe>();
		ServiceCenterInventoryDao serviceCenterInventoryDao = new ServiceCenterInventoryDao();

		displayInventorytList=serviceCenterInventoryDao.displayInventory(serviceCenterId);
		
		return displayInventorytList;

	}
	
	private boolean populatePart(ArrayList<ServiceCenterInventoryBe> serviceCenterPartList ) {
		PartBl partBl = new PartBl();
		
		for (int i =0 ; i<serviceCenterPartList.size(); i++) {
			serviceCenterPartList.get(i).setPartBe(partBl.getPart(serviceCenterPartList.get(i).getPartId()));
		}
		
		return true;
	}
	
	public boolean bookPartForService(int partId, int qty) {
		ServiceCenterInventoryDao serviceCenterInventoryDao = new ServiceCenterInventoryDao();
		return serviceCenterInventoryDao.bookPartForService(partId,qty);
	}

}
