package be;

/**
 * @author Juan Jose Rivera
 *
 */
public class CarTypeBe {

	private int id;
	private String model;
	private CarMakeBe carMakeBe = new CarMakeBe();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}


	public CarMakeBe getCarMake() {
		return carMakeBe;
	}

	public void setCarMake(CarMakeBe carMakeBe) {
		this.carMakeBe = carMakeBe;
	}

	@Override
	public String toString() {
		return "CarTypeBe [id=" + id + ", model=" + model + ", carMakeBe=" + carMakeBe + ", getId()=" + getId()
				+ ", getModel()=" + getModel() + ", getCarMake()=" + getCarMake() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}


	
}
