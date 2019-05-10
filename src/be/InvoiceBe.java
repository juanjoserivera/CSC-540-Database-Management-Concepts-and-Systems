package be;

import java.sql.Date;

/**
 * @author Juan Jose Rivera
 *
 */
public class InvoiceBe {
	
	private int id;
	private String status;
	private double totalCost;
	private Date date;
	private WorkAppointmentBe workAppointmentBe;
	private CustomerBe customerBe;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public WorkAppointmentBe getWorkAppointmentBe() {
		return workAppointmentBe;
	}
	public void setWorkAppointmentBe(WorkAppointmentBe workAppointmentBe) {
		this.workAppointmentBe = workAppointmentBe;
	}
	public CustomerBe getCustomerBe() {
		return customerBe;
	}
	public void setCustomerBe(CustomerBe customerBe) {
		this.customerBe = customerBe;
	}
	
	

}
