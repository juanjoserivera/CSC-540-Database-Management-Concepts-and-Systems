package be;

import java.util.ArrayList;

/**
 * @author Juan Jose Rivera
 *
 */
public class BasicServiceBe {
	private int id;
	private String name;
	private double expectedHours;
	private int chargeRateId;
	private ChargeRateBe chargeRateBe;
	private int carTypeId;
	private CarTypeBe carTypeBe;
	private ArrayList<PartNumberBe> partNumberBeList;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getExpectedHours() {
		return expectedHours;
	}
	public void setExpectedHours(double expectedHours) {
		this.expectedHours = expectedHours;
	}
	public int getChargeRateId() {
		return chargeRateId;
	}
	public void setChargeRateId(int chargeRateId) {
		this.chargeRateId = chargeRateId;
	}
	public ChargeRateBe getChargeRateBe() {
		return chargeRateBe;
	}
	public void setChargeRateBe(ChargeRateBe chargeRateBe) {
		this.chargeRateBe = chargeRateBe;
	}
	public int getCarTypeId() {
		return carTypeId;
	}
	public void setCarTypeId(int carTypeId) {
		this.carTypeId = carTypeId;
	}
	public CarTypeBe getCarTypeBe() {
		return carTypeBe;
	}
	public void setCarTypeBe(CarTypeBe carTypeBe) {
		this.carTypeBe = carTypeBe;
	}

	
	
	public ArrayList<PartNumberBe> getPartNumberBeList() {
		return partNumberBeList;
	}
	public void setPartNumberBeList(ArrayList<PartNumberBe> partNumberBeList) {
		this.partNumberBeList = partNumberBeList;
	}
	@Override
	public String toString() {
		return "BasicServiceBe [id=" + id + ", name=" + name + ", expectedHours=" + expectedHours + ", chargeRateId="
				+ chargeRateId + ", chargeRateBe=" + chargeRateBe + ", carTypeId=" + carTypeId + ", carTypeBe="
				+ carTypeBe + ", partNumberBeList=" + partNumberBeList + "]";
	}

	
	
	
	
	

}
