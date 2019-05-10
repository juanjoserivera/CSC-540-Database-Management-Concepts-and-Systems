package be;

import java.sql.Date;

/**
 * @author Juan Jose Rivera
 *
 */
public class PartOrderBe {
	private int id;
	private int qty;
	private Date orderDate;
	private Date expectedDate;
	private Date deliveryDate;
	private double cost;
	private String status;
	private int fromServiceId;
	private int toId;
	private int partId;
	private int fromSupplierId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getExpectedDate() {
		return expectedDate;
	}
	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getFromServiceId() {
		return fromServiceId;
	}
	public void setFromServiceId(int fromServiceId) {
		this.fromServiceId = fromServiceId;
	}
	public int getToId() {
		return toId;
	}
	public void setToId(int toId) {
		this.toId = toId;
	}
	public int getPartId() {
		return partId;
	}
	public void setPartId(int partId) {
		this.partId = partId;
	}
	public int getFromSupplierId() {
		return fromSupplierId;
	}
	public void setFromSupplierId(int fromSupplierId) {
		this.fromSupplierId = fromSupplierId;
	}
	@Override
	public String toString() {
		return "PartOrderBe [id=" + id + ", qty=" + qty + ", orderDate=" + orderDate + ", expectedDate=" + expectedDate
				+ ", deliveryDate=" + deliveryDate + ", cost=" + cost + ", status=" + status + ", fromServiceId="
				+ fromServiceId + ", toId=" + toId + ", partId=" + partId + ", fromSupplierId=" + fromSupplierId + "]";
	}	
}
