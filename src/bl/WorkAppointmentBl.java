package bl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import be.WorkAppointmentBe;
import dao.WorkAppointmentDao;
import util.TimeSlot;

public class WorkAppointmentBl {

	private static WorkAppointmentDao dao = new WorkAppointmentDao();

	public WorkAppointmentBe get(int id) {
		return dao.get(id);
	}

	public ArrayList<WorkAppointmentBe> getAll() {
		return dao.getAll();
	}

	public ArrayList<WorkAppointmentBe> getByServiceCenter(int serviceCenterId) {
		return dao.getByServiceCenter(serviceCenterId);
	}
	
	public ArrayList<WorkAppointmentBe> getByCustomer(int cust) {
		return dao.getByCustomer(cust);
	}

	public List<TimeSlot> getTimeSlots(int serviceCenterId, LocalDate date, int employeeId) {
		return dao.getByServiceCenterAndDateAndEmployee(serviceCenterId, date, employeeId).stream()
				.map(e -> new TimeSlot(e.getStartDate().toLocalTime(), (long) (e.getExpectedHours() * 60)))
				.collect(Collectors.toList());
	}

	public boolean delete(int id) {
		return dao.delete(id);
	}
	
	public WorkAppointmentBe getLastAppointmentForCar(String carPlateNumber) {
		return dao.getLatestForCar(carPlateNumber);
	}
	


}
