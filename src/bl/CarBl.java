package bl;

import java.util.List;

import be.CarBe;
import be.WorkAppointmentBe;
import dao.CarDao;
import dao.WorkAppointmentDao;

public class CarBl {
	private static final String TABLE_FORMAT = "%5s%20s%30s%30s%22s%20s%25s\n";
	private static final String TABLE_FORMAT_HEADER = "%5s%17s%30s%30s%25s%25s%18s\n";

	public static List<CarBe> getCarsOfCustomer(int customerId){
		CarDao carDao = new CarDao();
		return carDao.getAllCarsByCustomer(customerId);
	}
	
	public static void displayCarInfo(CarBe carBe) {
		
		WorkAppointmentDao workAppointmentDao = new WorkAppointmentDao();
		WorkAppointmentBe workAppointment = workAppointmentDao.getLatestForCar(carBe.getPlateNumber());
		if(workAppointment != null) {
			carBe.setLastWorkAppointment(workAppointment);
		}
		
		String lastService = "";
		if(carBe.getLastWorkAppointment() != null) {
			if(carBe.getLastWorkAppointment().getMaintenanceServiceId() != 0) {
				int serviceId = carBe.getLastWorkAppointment().getMaintenanceServiceId();
				MaintenanceServiceBl maintenanceServiceBl = new MaintenanceServiceBl();
				lastService = "Service " + maintenanceServiceBl.get(serviceId).getType();
			} else if(carBe.getLastWorkAppointment().getRepairServiceId() != 0) {
				int serviceId = carBe.getLastWorkAppointment().getRepairServiceId();
				RepairServiceBl repairServiceBl = new RepairServiceBl();
				lastService = "Repair (" + repairServiceBl.get(serviceId).getProblem() + ")";
			}
		}
	
		System.out.format(TABLE_FORMAT, carBe.getPlateNumber(),
											   carBe.getCarType().getModel(),
											   carBe.getYear(),
											   carBe.getCarType().getCarMake().getName(),
											   carBe.getLastServiceDate(),
											   carBe.getLastServiceMileage(),
											   lastService);
	}
	
	private static void printHeader() {
		System.out.format(TABLE_FORMAT_HEADER, "Plate Number",
											   "Model",
											   "Year",
											   "Make",
											   "Last Service Date",
											   "Last Service Mileage",
											   "Last Service");
		 System.out.format("%s\n", "--------------------------------------------------"
		    		+ "-------------------------------------------------------"
		    		+ "-----------------------------------------------------");
	}
	
	public static void displayCarsInfo(List<CarBe> cars) {
		System.out.format("\n%s\n", "*************************************************"
	    		+ "****************Cars Info****************"
	    		+ "*************************************************"
	    		+ "*******************************"
	    		);
		int carIndex = 1;
		printHeader();
		for(CarBe car : cars) {
			displayCarInfo(car);
		}
		System.out.format("%s\n", "*************************************************"
				+ "*******************************"
	    		+ "******************************************"
	    		+ "*************************************************"
	    		);
	}

	public static boolean insertCar(CarBe car) {
		
		CarDao carDao = new CarDao();
		return carDao.insertCar(car);
	}
}
