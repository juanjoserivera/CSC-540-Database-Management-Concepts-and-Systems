package app.receptionist;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import app.Page;
import be.CarBe;
import be.CustomerBe;
import be.EmployeeBe;
import be.PartNumberBe;
import be.RepairServiceBe;
import bl.RepairServiceBl;
import dao.PartDao;
import dao.RepairServiceDao;
import util.ServiceScheduler;
import util.UI;

public class ScheduleRepairPage extends Page {

	private static RepairServiceBl repairServiceBl = new RepairServiceBl();
	private static RepairServiceDao repairServiceDao = new RepairServiceDao();
	private static PartDao partDao = new PartDao();

	@Override
	public String[] getOptions() {
		return new String[] { "Engine knock", "Car drifts in a particular direction", "Battery does not hold charge",
				"Black/unclean exhaust", "A/C-Heater not working", "Headlamps/Taillamps not working",
				"Check engine light", "Go back" };
	}

	@Override
	public Page[] getPages() {
		return new Page[] { Pages.SCHEDULE_REPAIR2, Pages.SCHEDULE_SERVICE };
	}

	public void open(CustomerBe customer, CarBe car, int currentMileage, EmployeeBe mechanic) {
		displayOptions();
		int option = readOption();

		if (option >= 1 && option <= 7) {
			String problem = getOptions()[option - 1];

			RepairServiceBe service = repairServiceBl.getByProblem(problem.toUpperCase(), car.getCarType().getId());

			List<PartNumberBe> partsForService = partDao.getPartsByRepairServiceId(service.getId());

			if (!ServiceScheduler.validateEnoughParts(getServiceCenterId(), partsForService)) {
				System.out.println("Try again?");
				getPages()[1].open();
				return;
			}

			int durationMinutes = repairServiceDao.getDurationMinutes(service.getId());

			UI.displayServiceDuration(durationMinutes);

			LocalDate date = LocalDate.now().plusDays(1);

			List<LocalDateTime> availableDateTimes = ServiceScheduler.getAvailableDateTimesForRepairService(
					mechanic.getId(), durationMinutes, date, getServiceCenterId());

			((ScheduleRepairPage2) Pages.SCHEDULE_REPAIR2).open(customer, car, currentMileage, mechanic,
					availableDateTimes, service, durationMinutes);
		} else {
			Pages.SCHEDULE_SERVICE.open();
		}
	}

}
