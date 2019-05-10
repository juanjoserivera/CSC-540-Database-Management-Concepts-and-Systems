package app.receptionist;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import app.Page;
import be.CarBe;
import be.CustomerBe;
import be.WorkAppointmentBe;
import dao.CarDao;
import dao.CustomerDao;
import dao.WorkAppointmentDao;
import util.DateFormatUtil;
import util.ServiceScheduler;
import util.ServiceType;

public class RescheduleServicePage extends Page {
	private static final String TABLE_FORMAT = "%15s%15s%25s%20s%25s\n";

	private static CustomerDao customerDao = new CustomerDao();
	private static WorkAppointmentDao workAppointmentDao = new WorkAppointmentDao();
	private static CarDao carDao = new CarDao();

	@Override
	public String[] getOptions() {
		return new String[] { "Pick a service", "Go Back" };
	}

	@Override
	public Page[] getPages() {
		return new Page[] { Pages.RECEPTIONIST };
	}

	public void open() {
		CustomerBe customer = readEmail();
//		CustomerBe customer = customerDao.getCustomerByEmail("ethanhunt@gmail.com");

		ArrayList<WorkAppointmentBe> records = workAppointmentDao.getPendingByCustomerId(customer.getId());
		displayWorkAppointments(records);

		displayOptions();
		int option = readOption();

		if (option == 1) {
			WorkAppointmentBe workAppointment = readWorkAppointment(records);

			LocalDate date = workAppointment.getStartDate().toLocalDate().plusDays(1);

			int durationMinutes = workAppointment.getDurationMinutes();

			int mechanicId = workAppointment.getEmployeeId();

			boolean isMaintenance = workAppointment.getServiceType() == ServiceType.MAINTENANCE;

			List<LocalDateTime> availableDateTimes = isMaintenance
					? ServiceScheduler.getAvailableDateTimesForMaintenanceService(mechanicId, durationMinutes, date,
							getServiceCenterId())
					: ServiceScheduler.getAvailableDateTimesForRepairService(mechanicId, durationMinutes, date,
							getServiceCenterId());

			displayAvailableDatesToPick(availableDateTimes);

			option = readOption(2);

			LocalDateTime newStartDate = availableDateTimes.get(option - 1);

			workAppointmentDao.reschedule(workAppointment.getId(), newStartDate);

			CarBe car = carDao.getCar(workAppointment.getCarId());
			carDao.updateLastServiceInfo(car.getPlateNumber(), newStartDate, car.getLastServiceMileage());

			System.out.println("Service was rescheduled!");
			getPages()[0].open();
		}

		getPages()[0].open();
	}

	private WorkAppointmentBe readWorkAppointment(ArrayList<WorkAppointmentBe> workAppointments) {
		System.out.println("Enter Service ID:");
		int workAppointmentId = readNumber();
		for (WorkAppointmentBe be : workAppointments) {
			if (be.getId() == workAppointmentId) {
				return be;
			}
		}
		System.out.format("Error Service ID %s is not in the list\n", workAppointmentId);
		return readWorkAppointment(workAppointments);
	}

	private void displayWorkAppointments(List<WorkAppointmentBe> records) {
		printHeader();
		for (WorkAppointmentBe record : records) {
			printRecord(record);
		}
	}

	private void printRecord(WorkAppointmentBe record) {
		System.out.format(TABLE_FORMAT, record.getCarId(), record.getId(),
				record.getStartDate().format(DateFormatUtil.dateTimeFormatter), record.getServiceType(),
				record.getMechanicName());
	}

	private void printHeader() {
		System.out.format(TABLE_FORMAT, "Licence Plate", "Service ID", "Service Date", "Service Type", "Mechanic Name");
		System.out.format("%s\n", "--------------------------------------------------"
				+ "-----------------------------------------------------------------");
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

	private void displayAvailableDatesToPick(List<LocalDateTime> availableDateTimes) {
		System.out.println("Pick the Service Date:");
		for (int i = 0; i < availableDateTimes.size(); i++) {
			System.out.println((i + 1) + ". " + availableDateTimes.get(i).format(DateFormatUtil.dateTimeFormatter));
		}
	}
}
