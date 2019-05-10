package be;

/**
 * @author Juan Jose Rivera
 *
 */
public class CarMakeBe {
	private int id;
	private String name;
	
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
	@Override
	public String toString() {
		return "CarMakeBe [id=" + id + ", name=" + name + "]";
	}
	
	

}
