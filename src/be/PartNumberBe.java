package be;

/**
 * @author Juan Jose Rivera
 *
 */
public class PartNumberBe {
	private PartBe partBe;
	private int num;
	
	public PartBe getPartBe() {
		return partBe;
	}
	public void setPartBe(PartBe partBe) {
		this.partBe = partBe;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "PartNumberBe [partBe=" + partBe + ", num=" + num + "]";
	}
	
	
}
