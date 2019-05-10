package util;

import java.util.List;

import be.CarBe;
import be.EmployeeBe;
import be.MaintenanceServiceBe;
import bl.MaintenanceServiceBl;
import dao.MaintenanceServiceDao;

public class MaintenanceServicePicker {
	private static MaintenanceServiceBl maintenanceServiceBl = new MaintenanceServiceBl();
	private static MaintenanceServiceDao maintenanceServiceDao = new MaintenanceServiceDao();
	
	public static MaintenanceServiceBe pick(CarBe car, int currentMileage, EmployeeBe mechanic) {
		System.out.format("Your last recorded mileage is %s Miles.\n", car.getLastServiceMileage());

		int diffMileage = Math.abs(currentMileage - car.getLastServiceMileage());

		System.out.format("Service is computed based on mileage %s Miles.\n", diffMileage);

		List<MaintenanceServiceBe> services = maintenanceServiceDao.getByPlateNumber(car.getPlateNumber());

		System.out.println("Choosing from services:");

		for (MaintenanceServiceBe be : services) {
			System.out.format("    * %s %s %s\n", be.getId(), be.getType(), be.getMileage());
		}
		
		MaintenanceServiceBe service = pickByMileage(diffMileage, services);

		System.out.format("Service %s %s Chosen!\n", service.getId(), service.getType());

		return maintenanceServiceBl.get(service.getId());
	}
	
	private static MaintenanceServiceBe pickByMileage(int mileage, List<MaintenanceServiceBe> services) {
		for (MaintenanceServiceBe s : services) {
			if (mileage < s.getMileage()) {
				return s;
			}
		}
		return services.get(services.size() - 1);
	}	
}
