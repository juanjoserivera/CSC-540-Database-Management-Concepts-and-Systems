package app.receptionist;

import java.util.List;
import java.util.Random;

import app.Page;
import be.CarBe;
import be.CustomerBe;
import be.EmployeeBe;
import dao.CarDao;
import dao.CustomerDao;
import dao.EmployeeDao;

public class ScheduleServicePage extends Page {

	private static EmployeeDao employeeDao = new EmployeeDao();
	private static CustomerDao customerDao = new CustomerDao();
	private static CarDao carDao = new CarDao();

	@Override
	public String[] getOptions() {
		return new String[] { "Schedule Maintenance", "Schedule Repair", "Go Back" };
	}

	@Override
	public Page[] getPages() {
		return new Page[] { Pages.SCHEDULE_MAINTENANCE, Pages.SCHEDULE_REPAIR, Pages.RECEPTIONIST };
	}

	public void open() {
		List<EmployeeBe> employees = employeeDao.getByServiceCenterId(getServiceCenterId());

		CustomerBe customer = readEmail();
		CarBe car = readLicensePlate();
		int currentMileage = readCurrentMileage(car.getLastServiceMileage());
		EmployeeBe mechanic = readMechanic(employees);

//		CustomerBe customer = customerDao.getCustomerByEmail("ethanhunt@gmail.com");
//		CarBe car = carDao.getCar("XYZ-5643");
//		CarBe car = carDao.getCar("DEL-8888");
//		int currentMileage = 10000;
//		EmployeeBe mechanic = employees.get(0);

		displayOptions();
		int option = readOption();
		Page page =	getPages()[option - 1];

		switch (option) {
		case 1:
			((ScheduleMaintenancePage) page).open(customer, car, currentMileage, mechanic);
			break;
		case 2:
			((ScheduleRepairPage) page).open(customer, car, currentMileage, mechanic);
			break;
		case 3:
			page.open();
			break;
		}
	}

	private CustomerBe readEmail() {
		System.out.println("Enter customer Email address:");
		String email = readString();
		CustomerBe customer = customerDao.getCustomerByEmail(email);
		if (customer == null) {
			System.out.format("Customer with email %s Not found!\n", email);
			return readEmail();
		} else {
			return customer;
		}
	}

	private CarBe readLicensePlate() {
		System.out.println("Enter License Plate:");
		String licensePlate = readString();
		CarBe car = carDao.getCar(licensePlate);
		if (car == null) {
			System.out.format("Car with License Plate %s Not found!\n", licensePlate);
			return readLicensePlate();
		} else {
			return car;
		}
	}

	private int readCurrentMileage(int lastRecordedMileage) {
		System.out.println("Enter Current Mileage:");
		int currentMileage = readNumber();
		if (currentMileage <= lastRecordedMileage) {
			System.out.format("Current Mileage (%s) should be greater than Last Recorded Mileage (%s)!\n",
					currentMileage, lastRecordedMileage);
			return readCurrentMileage(lastRecordedMileage);
		} else {
			return currentMileage;
		}
	}
	
	private EmployeeBe readMechanic(List<EmployeeBe> employees) {
		System.out.println("Enter Mechanic Name (Optional, type Enter to skip):");
		String mechanicName = readStringOptional();	
		if (mechanicName.equals("")) {
			return employees.get(new Random().nextInt(employees.size()));
		} else {
			for (EmployeeBe e : employees) {
				if (e.getName().equals(mechanicName)) {
					return e;
				}
			}
			System.out.format("Mechanic with the name '%s' does not exist! Try again\n", mechanicName);
			return readMechanic(employees);
		}
	}

}
