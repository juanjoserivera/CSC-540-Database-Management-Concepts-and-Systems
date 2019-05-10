package be;

public class ChargeRateBe {

	private int id;
	private String type;
	private int costPerHour;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCostPerHour() {
		return costPerHour;
	}

	public void setCostPerHour(int costPerHour) {
		this.costPerHour = costPerHour;
	}

	@Override
	public String toString() {
		return "ChargeRateBe [type=" + type + ", costPerHour=" + costPerHour + "]";
	}

	
}
