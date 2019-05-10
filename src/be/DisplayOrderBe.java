package be;

import java.sql.Date;

/**
 * @author Juan Jose Rivera
 *
 */
public class DisplayOrderBe {
	private int orderId;
	private String suplierName;
	private String purchaserName;
	private double cost;
	private double unitPrice;
	private Date orderPlacementDate;
	private Date expectedDeliveryDate;
	private Date actualDeliveryDate;
	private String makeName;
	private String partName;
	private int qty;
	private String origin;
	private int originId;
	private int destinationId;
	private String Status;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getSuplierName() {
		return suplierName;
	}
	public void setSuplierName(String suplierName) {
		this.suplierName = suplierName;
	}
	public String getPurchaserName() {
		return purchaserName;
	}
	public void setPurchaserName(String purchaserName) {
		this.purchaserName = purchaserName;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public Date getOrderPlacementDate() {
		return orderPlacementDate;
	}
	public void setOrderPlacementDate(Date orderPlacementDate) {
		this.orderPlacementDate = orderPlacementDate;
	}
	public Date getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}
	public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}
	public Date getActualDeliveryDate() {
		return actualDeliveryDate;
	}
	public void setActualDeliveryDate(Date actualDeliveryDate) {
		this.actualDeliveryDate = actualDeliveryDate;
	}
	public String getMakeName() {
		return makeName;
	}
	public void setMakeName(String makeName) {
		this.makeName = makeName;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public int getOriginId() {
		return originId;
	}
	public void setOriginId(int originId) {
		this.originId = originId;
	}
	public int getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	
	

}
