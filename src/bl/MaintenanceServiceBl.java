package bl;

import be.*;
import dao.*;

/**
 * @author Juan Jose Rivera
 *
 */

public class MaintenanceServiceBl {

	
	public MaintenanceServiceBe get(int id) {
		MaintenanceServiceDao maintenanceServiceDao = new MaintenanceServiceDao();
		MaintenanceServiceBe maintenanceServiceBe;
		
		BasicServiceBl basicServiceBl = new BasicServiceBl();
		CarTypeBl carTypeBl = new CarTypeBl();
		
		maintenanceServiceBe = maintenanceServiceDao.get(id);
		
		maintenanceServiceBe.setCarTypeBe(carTypeBl.getCarType(maintenanceServiceBe.getCarTypeId()));
		maintenanceServiceBe.setBasicServiceBeList(basicServiceBl.getBasicServiceByMaintId(maintenanceServiceBe.getId()));
		
		
		return maintenanceServiceBe;
	}
	

}
