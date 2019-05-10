package be;

/**
 * @author Juan Jose Rivera
 *
 */
public class ServiceCenterInventoryBe {
	
	private int minOrderQty;
	private int orderThreshold;
	private int bookedQty;
	private int freeQty;
	private int partId;
	private PartBe partBe;
	private int serviceCenterId;
	
	public int getMinOrderQty() {
		return minOrderQty;
	}
	public void setMinOrderQty(int minOrderQty) {
		this.minOrderQty = minOrderQty;
	}
	public int getOrderThreshold() {
		return orderThreshold;
	}
	public void setOrderThreshold(int orderThreshold) {
		this.orderThreshold = orderThreshold;
	}
	public int getBookedQty() {
		return bookedQty;
	}
	public void setBookedQty(int bookedQty) {
		this.bookedQty = bookedQty;
	}
	public int getFreeQty() {
		return freeQty;
	}
	public void setFreeQty(int freeQty) {
		this.freeQty = freeQty;
	}
	public int getPartId() {
		return partId;
	}
	public void setPartId(int partId) {
		this.partId = partId;
	}
	public PartBe getPartBe() {
		return partBe;
	}
	public void setPartBe(PartBe partBe) {
		this.partBe = partBe;
	}
	public int getServiceCenterId() {
		return serviceCenterId;
	}
	public void setServiceCenterId(int serviceCenterId) {
		this.serviceCenterId = serviceCenterId;
	}
	@Override
	public String toString() {
		return "ServiceCenterInventoryBe [minOrderQty=" + minOrderQty + ", orderThreshold=" + orderThreshold
				+ ", bookedQty=" + bookedQty + ", freeQty=" + freeQty + ", partId=" + partId + ", partBe=" + partBe
				+ ", serviceCenterId=" + serviceCenterId + "]";
	}

	
}
