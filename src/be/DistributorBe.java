package be;

import java.util.ArrayList;

/**
 * @author Juan Jose Rivera
 *
 */
public class DistributorBe {
	private int id;
	private String name;
	private ArrayList<DistributorPartBe> distributorPartList;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<DistributorPartBe> getDistributorPartList() {
		return distributorPartList;
	}
	public void setDistributorPartList(ArrayList<DistributorPartBe> distributorPartList) {
		this.distributorPartList = distributorPartList;
	}
	

}
