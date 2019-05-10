package be;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import bl.EmployeeBl;
import dao.CustomerDao;
import util.ServiceType;

/**
 * @author Juan Jose Rivera
 *
 */
public class WorkAppointmentBe {

	private static CustomerDao customerBl = new CustomerDao();

	private int id;
	private String status;
	private int customerId;
	private String carId;
	private int serviceCenterId;
	private int employeeId;
	private int repairServiceId;
	private int maintenanceServiceId;
	private double totalCost;
	private LocalDateTime startDate;
	private double expectedHours;
	private RepairServiceBe repairService;
	private MaintenanceServiceBe maintenanceService;

	public int getDurationMinutes() {
		return (int)(expectedHours * 60);
	}

	public ServiceType getServiceType() {
		return maintenanceServiceId > 0 ? ServiceType.MAINTENANCE : ServiceType.REPAIR;
	}

	public LocalDateTime getEndDate() {
		LocalDateTime endDate = LocalDateTime.from(startDate);
		return endDate.plusMinutes((long) (expectedHours * 60));
	}

	public String getMechanicName() {
		return EmployeeBl.getEmployee(employeeId).getName();
	}

	public String getCustomerName() {
		return customerBl.getCustomer(customerId).getName();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public int getServiceCenterId() {
		return serviceCenterId;
	}

	public void setServiceCenterId(int serviceCenterId) {
		this.serviceCenterId = serviceCenterId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getRepairServiceId() {
		return repairServiceId;
	}

	public void setRepairServiceId(int repairServiceId) {
		this.repairServiceId = repairServiceId;
	}

	public int getMaintenanceServiceId() {
		return maintenanceServiceId;
	}

	public void setMaintenanceServiceId(int maintenanceServiceId) {
		this.maintenanceServiceId = maintenanceServiceId;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public double getExpectedHours() {
		return expectedHours;
	}

	public void setExpectedHours(double expectedHours) {
		this.expectedHours = expectedHours;
	}

	public RepairServiceBe getRepairService() {
		return repairService;
	}

	public void setRepairService(RepairServiceBe repairService) {
		this.repairService = repairService;
	}

	public MaintenanceServiceBe getMaintenanceService() {
		return maintenanceService;
	}

	public void setMaintenanceService(MaintenanceServiceBe maintenanceService) {
		this.maintenanceService = maintenanceService;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "WorkAppointmentBe [id=" + id + ", status=" + status + ", carId=" + carId + ", serviceCenterId="
				+ serviceCenterId + ", employeeId=" + employeeId + ", repairServiceId=" + repairServiceId
				+ ", maintenanceServiceId=" + maintenanceServiceId + ", totalCost=" + totalCost + ", startDate="
				+ startDate + ", expectedTime=" + expectedHours + "]";
	}
}
