package bl;

import be.RepairServiceBe;
import dao.RepairServiceDao;

/**
 * @author Juan Jose Rivera
 *
 */
public class RepairServiceBl {
	
	
	public RepairServiceBe get(int id) {
		RepairServiceDao repairServiceDao = new RepairServiceDao();
		RepairServiceBe repairServiceBe;
		
		CarTypeBl carTypeBl = new CarTypeBl();
		BasicServiceBl basicServiceBl = new BasicServiceBl();
		CauseBl causeBl = new CauseBl();
		
		
		repairServiceBe = repairServiceDao.get(id);
		
		repairServiceBe.setCarTypeBe(carTypeBl.getCarType(repairServiceBe.getCarTypeId()));
		repairServiceBe.setCauseBeList(causeBl.getCausesByServId(repairServiceBe.getId()));
		repairServiceBe.setBasicServiceBeList(basicServiceBl.getBasicServiceByRepId(repairServiceBe.getId()));
		
		return repairServiceBe;
	}
	
	
	public RepairServiceBe getByProblem(String problem, int carTypeId) {
		RepairServiceDao repairServiceDao = new RepairServiceDao();
		RepairServiceBe repairServiceBe;
		
		CarTypeBl carTypeBl = new CarTypeBl();
		BasicServiceBl basicServiceBl = new BasicServiceBl();
		CauseBl causeBl = new CauseBl();
		
		
		repairServiceBe = repairServiceDao.getByProblem(problem, carTypeId);
		
		repairServiceBe.setCarTypeBe(carTypeBl.getCarType(repairServiceBe.getCarTypeId()));
		repairServiceBe.setCauseBeList(causeBl.getCausesByServId(repairServiceBe.getId()));
		repairServiceBe.setBasicServiceBeList(basicServiceBl.getBasicServiceByRepId(repairServiceBe.getId()));
		
		return repairServiceBe;
	}
}
