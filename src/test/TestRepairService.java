package test;

import be.RepairServiceBe;
import bl.*;
import dao.RepairServiceDao;

/**
 * @author Juan Jose Rivera
 *
 */
public class TestRepairService {
	
	public static void main(String[] args) {

	RepairServiceBl repairServiceBl = new RepairServiceBl();
	//System.out.println(repairServiceBl.get(1));
	//System.out.print(repairServiceBl.get(1).getBasicServiceBeList());
	//System.out.println(repairServiceBl.getByProblem("ENGINE KNOCk", 1));

	RepairServiceDao repairServiceDao = new RepairServiceDao();
	RepairServiceBe repairServiceBe;
	
	CarTypeBl carTypeBl = new CarTypeBl();
	BasicServiceBl basicServiceBl = new BasicServiceBl();
	CauseBl causeBl = new CauseBl();
	
	
	repairServiceBe = repairServiceDao.getByProblem("ENGINE KNOCK", 1);
	System.out.println(repairServiceBe);
	
	}
}
