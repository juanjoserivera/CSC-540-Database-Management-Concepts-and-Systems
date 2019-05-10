package be;

import java.util.ArrayList;

/**
 * @author Juan Jose Rivera
 *
 */
public class RepairServiceBe {
	private int id;
	private String problem;
	private double diagFee;
	private int carTypeId;
	private CarTypeBe carTypeBe;
	private ArrayList<CauseBe> causeBeList;
	private ArrayList<BasicServiceBe> basicServiceBeList;
	
	
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
	public double getDiagFee() {
		return diagFee;
	}
	public void setDiagFee(double diagFee) {
		this.diagFee = diagFee;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public ArrayList<CauseBe> getCauseBeList() {
		return causeBeList;
	}
	public void setCauseBeList(ArrayList<CauseBe> causeBeList) {
		this.causeBeList = causeBeList;
	}
	public ArrayList<BasicServiceBe> getBasicServiceBeList() {
		return basicServiceBeList;
	}
	public void setBasicServiceBeList(ArrayList<BasicServiceBe> basicServiceBeList) {
		this.basicServiceBeList = basicServiceBeList;
	}
	@Override
	public String toString() {
		return "RepairServiceBe [id=" + id + ", problem=" + problem + ", diagFee=" + diagFee + ", carTypeId="
				+ carTypeId + ", carTypeBe=" + carTypeBe + ", causeBeList=" + causeBeList + ", basicServiceBeList="
				+ basicServiceBeList + "]";
	}
	
	
	
	
	

}
