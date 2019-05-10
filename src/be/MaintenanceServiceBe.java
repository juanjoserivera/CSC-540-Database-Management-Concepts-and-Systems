package be;

import java.util.ArrayList;

/**
 * @author Juan Jose Rivera
 *
 */

public class MaintenanceServiceBe {
	
	private int id;
	private int numberOfMonths;
	private int mileage;
	private String type;
	private int carTypeId;
	private CarTypeBe carTypeBe;
	private ArrayList<BasicServiceBe> basicServiceBeList;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumberOfMonths() {
		return numberOfMonths;
	}
	public void setNumberOfMonths(int numberOfMonths) {
		this.numberOfMonths = numberOfMonths;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public CarTypeBe getCarTypeBe() {
		return carTypeBe;
	}
	public void setCarTypeBe(CarTypeBe carTypeBe) {
		this.carTypeBe = carTypeBe;
	}
	public ArrayList<BasicServiceBe> getBasicServiceBeList() {
		return basicServiceBeList;
	}
	public void setBasicServiceBeList(ArrayList<BasicServiceBe> basicServiceBeList) {
		this.basicServiceBeList = basicServiceBeList;
	}
	public int getCarTypeId() {
		return carTypeId;
	}
	public void setCarTypeId(int carTypeId) {
		this.carTypeId = carTypeId;
	}
	@Override
	public String toString() {
		return "MaintenanceServiceBe [id=" + id + ", numberOfMonths=" + numberOfMonths + ", mileage=" + mileage
				+ ", type=" + type + ", carTypeId=" + carTypeId + ", carTypeBe=" + carTypeBe + ", basicServiceBeList="
				+ basicServiceBeList + "]";
	}
	
	
	

}
