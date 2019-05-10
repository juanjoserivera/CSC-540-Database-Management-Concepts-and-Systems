package be;

/**
 * @author Juan Jose Rivera
 *
 */
public class CauseBe {
	private int id;
	private String definition;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	@Override
	public String toString() {
		return "CauseBe [id=" + id + ", definition=" + definition + "]";
	}
	
	
}
