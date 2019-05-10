package be;

import java.sql.Date;

public class CarBe {
	
	private String plateNumber;
	private int customerId;
	private int year;
	private CarTypeBe carType = new CarTypeBe();
	private Date lastServiceDate;
	private int lastServiceMileage;
	private String lastServiceType;
	private Date purchaseDate;
	private WorkAppointmentBe lastWorkAppointment;
	
	
	public WorkAppointmentBe getLastWorkAppointment() {
		return lastWorkAppointment;
	}
	
	public void setLastWorkAppointment(WorkAppointmentBe lastWorkAppointment) {
		this.lastWorkAppointment = lastWorkAppointment;
	}
	
	public RepairServiceBe getLastRepair() {
		return lastRepair;
	}
	public void setLastRepair(RepairServiceBe lastRepair) {
		this.lastRepair = lastRepair;
	}
	private RepairServiceBe lastRepair;
	
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public CarTypeBe getCarType() {
		return carType;
	}
	public void setCarType(CarTypeBe carType) {
		this.carType = carType;
	}
	public Date getLastServiceDate() {
		return lastServiceDate;
	}
	public void setLastServiceDate(Date lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}
	public int getLastServiceMileage() {
		return lastServiceMileage;
	}
	public void setLastServiceMileage(int lastServiceMileage) {
		this.lastServiceMileage = lastServiceMileage;
	}
	public String getLastServiceType() {
		return lastServiceType;
	}
	public void setLastServiceType(String lastServiceType) {
		this.lastServiceType = lastServiceType;
	}
	@Override
	
	public String toString() {
		return "CarBe [plateNumber=" + plateNumber + ", customerId=" + customerId + ", year=" + year + ", carType="
				+ carType + ", lastServiceDate=" + lastServiceDate + ", lastServiceMileage=" + lastServiceMileage
				+ ", lastServiceType=" + lastServiceType + ", getYear()=" + getYear() + ", getPlateNumber()="
				+ getPlateNumber() + ", getCustomerId()=" + getCustomerId() + ", getCarType()=" + getCarType()
				+ ", getLastServiceDate()=" + getLastServiceDate() + ", getLastServiceMileage()="
				+ getLastServiceMileage() + ", getLastServiceType()=" + getLastServiceType() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	
}
