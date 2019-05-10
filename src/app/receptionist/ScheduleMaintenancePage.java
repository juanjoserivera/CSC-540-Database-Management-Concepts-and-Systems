package app.receptionist;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import app.Page;
import be.CarBe;
import be.CustomerBe;
import be.EmployeeBe;
import be.MaintenanceServiceBe;
import be.PartNumberBe;
import dao.MaintenanceServiceDao;
import dao.PartDao;
import util.MaintenanceServicePicker;
import util.ServiceScheduler;
import util.UI;

public class ScheduleMaintenancePage extends Page {

	private static MaintenanceServiceDao maintenanceServiceDao = new MaintenanceServiceDao();
	private static PartDao partDao = new PartDao();

	@Override
	public String[] getOptions() {
		return new String[] { "Find Service Date", "Go Back" };
	}

	@Override
	public Page[] getPages() {
		return new Page[] { Pages.SCHEDULE_MAINTENANCE2, Pages.SCHEDULE_SERVICE };
	}

	public void open(CustomerBe customer, CarBe car, int currentMileage, EmployeeBe mechanic) {
		displayOptions();
		int option = readOption();
		Page page = getPages()[option - 1];

		if (option == 1) {
			MaintenanceServiceBe service = MaintenanceServicePicker.pick(car, currentMileage, mechanic);

			List<PartNumberBe> partsForService = partDao.getPartsByMaintenanceServiceId(service.getId());

			if (!ServiceScheduler.validateEnoughParts(getServiceCenterId(), partsForService)) {
				System.out.println("Try again?");
				getPages()[1].open();
				return;
			}

			int durationMinutes = maintenanceServiceDao.getDurationMinutes(service.getId());

			UI.displayServiceDuration(durationMinutes);

			LocalDate date = LocalDate.now().plusDays(1);

			List<LocalDateTime> availableDateTimes = ServiceScheduler.getAvailableDateTimesForMaintenanceService(
					mechanic.getId(), durationMinutes, date, getServiceCenterId());

			((ScheduleMaintenancePage2) page).open(customer, car, currentMileage, mechanic, availableDateTimes, service,
					durationMinutes);
		} else {
			page.open();
		}
	}
}
