package app.receptionist;

import java.time.LocalDateTime;
import java.util.List;

import app.Page;
import be.CarBe;
import be.CustomerBe;
import be.EmployeeBe;
import be.RepairServiceBe;
import be.WorkAppointmentBe;
import dao.CarDao;
import dao.WorkAppointmentDao;
import util.ServiceCost;
import util.UI;

public class ScheduleRepairPage2 extends Page {

	private static WorkAppointmentDao workAppointmentDao = new WorkAppointmentDao();
	private static CarDao carDao = new CarDao();

	@Override
	public String[] getOptions() {
		return new String[] { "Repair on Date", "Go Back" };
	}

	@Override
	public Page[] getPages() {
		return new Page[] { Pages.RECEPTIONIST };
	}

	public void open(CustomerBe customer, CarBe car, int currentMileage, EmployeeBe mechanic,
			List<LocalDateTime> availableDateTimes, RepairServiceBe service, int durationMinutes) {

		double totalCost = ServiceCost.computeForRepairService(service, car.getPlateNumber());

		UI.displayAvailableDateTimes(availableDateTimes);
		UI.displayMechanic(mechanic);
		UI.displayServiceCost(totalCost);

		displayOptions();
		int option = readOption();

		if (option == 1) {
			UI.displayPickDateTimeOptions(availableDateTimes);

			option = readOption(2);

			LocalDateTime startDate = availableDateTimes.get(option - 1);

			WorkAppointmentBe be = new WorkAppointmentBe();

			be.setId(workAppointmentDao.count() + 1);
			be.setStatus("Pending");
			be.setCarId(car.getPlateNumber());
			be.setCustomerId(customer.getId());
			be.setServiceCenterId(getServiceCenterId());
			be.setEmployeeId(mechanic.getId());
			be.setRepairServiceId(service.getId());
			be.setTotalCost(totalCost);
			be.setStartDate(startDate);
			be.setExpectedHours(durationMinutes / 60.0);

			workAppointmentDao.insertForRepair(be);	
			
			carDao.updateLastServiceInfo(car.getPlateNumber(), startDate, currentMileage);

			System.out.format("Repair Service for problem %s was successfully scheduled!", service.getProblem());
		}

		getPages()[0].open();
	}
}
