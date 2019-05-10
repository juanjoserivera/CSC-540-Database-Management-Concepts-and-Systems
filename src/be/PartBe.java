package be;

/**
 * @author Juan Jose Rivera
 *
 */
public class PartBe {
	private int id;
	private String name;
	private int unitPrice;
	private int carMakeId;
	private CarMakeBe carMakeBe;
	private int warrantyMonths;
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
	public int getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getCarMakeId() {
		return carMakeId;
	}
	public void setCarMakeId(int carMakeId) {
		this.carMakeId = carMakeId;
	}
	public int getWarrantyMonths() {
		return warrantyMonths;
	}
	public void setWarrantyMonths(int warrantyMonths) {
		this.warrantyMonths = warrantyMonths;
	}
	
	
	public CarMakeBe getCarMakeBe() {
		return carMakeBe;
	}
	public void setCarMakeBe(CarMakeBe carMakeBe) {
		this.carMakeBe = carMakeBe;
	}
	@Override
	public String toString() {
		return "PartBe [id=" + id + ", name=" + name + ", unitPrice=" + unitPrice + ", carMakeId=" + carMakeId
				+ ", warrantyMonths=" + warrantyMonths + "]";
	}
	
	
	
}
