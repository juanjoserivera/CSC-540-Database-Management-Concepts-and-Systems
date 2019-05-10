package util;

import java.time.LocalDate;
import java.util.List;

import be.BasicServiceBe;
import be.MaintenanceServiceBe;
import be.PartNumberBe;
import be.RepairServiceBe;
import bl.BasicServiceBl;

public class ServiceCost {
	
	private static BasicServiceBl basicServiceBl = new BasicServiceBl();
	
	public static double computeForMaintenanceService(MaintenanceServiceBe serviceBe, String plateNumber) {
		System.out.println("Parts:");

		double totalCost = 0;
		for (BasicServiceBe be : serviceBe.getBasicServiceBeList()) {
			totalCost += computeForBasicService(be, plateNumber);
		}	
		return totalCost;
	}
	
	public static double computeForRepairService(RepairServiceBe serviceBe, String plateNumber) {
		System.out.println("Parts:");

		double totalCost = serviceBe.getDiagFee();
		for (BasicServiceBe be : serviceBe.getBasicServiceBeList()) {
			totalCost += computeForBasicService(be, plateNumber);
		}	
		return totalCost;
	}

	private static double computeForBasicService(BasicServiceBe be, String plateNumber) {
		double totalCost = 0;

		List<PartNumberBe> parts = be.getPartNumberBeList();

		PartNumberBe part = parts.get(0);

		double partCost = part.getNum() * part.getPartBe().getUnitPrice();

		System.out.format("    * %s cost $%s\n", part.getPartBe().getName(), partCost);

		LocalDate lastDateUsed = basicServiceBl.getLastServiceDate(be.getId(), plateNumber);
		
		if (lastDateUsed == null) {
			System.out.format("    	First time using Service '%s'!\n", be.getName());
			return partCost;
		}

		LocalDate lastWarrantyDate = lastDateUsed.plusMonths(part.getPartBe().getWarrantyMonths());

		if (LocalDate.now().isBefore(lastWarrantyDate)) {
			double totalHours = be.getExpectedHours(); 
			int costPerHour = be.getChargeRateBe().getCostPerHour();
			totalCost += (partCost + totalHours * costPerHour);
		} else {
			System.out.println("Applying Warranty!");
		}

		return totalCost;
	}
}
