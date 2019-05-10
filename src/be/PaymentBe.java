package be;

import java.sql.Date;


public class PaymentBe {
	
	private int id;
	private int employeeId;
	private Date paymentDate;
	private String compFrequency;
	private Date startDate;
	private Date endDate;
	private int hoursOrDaysWorked;
	private int earningsYearToDate;
	private int earnings;
	private int compensation;
	private EmployeeBe employeeBe;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	public String getCompFrequency() {
		return compFrequency;
	}
	public void setCompFrequency(String compFrequency) {
		this.compFrequency = compFrequency;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getHoursOrDaysWorked() {
		return hoursOrDaysWorked;
	}
	public void setHoursOrDaysWorked(int hoursOrDaysWorked) {
		this.hoursOrDaysWorked = hoursOrDaysWorked;
	}
	public int getEarningsYearToDate() {
		return earningsYearToDate;
	}
	public void setEarningsYearToDate(int earningsYearToDate) {
		this.earningsYearToDate = earningsYearToDate;
	}
	public int getEarnings() {
		return earnings;
	}
	public void setEarnings(int earnings) {
		this.earnings = earnings;
	}
	public int getCompensation() {
		return compensation;
	}
	public void setCompensation(int compensation) {
		this.compensation = compensation;
	}
	public EmployeeBe getEmployeeBe() {
		return employeeBe;
	}
	public void setEmployeeBe(EmployeeBe employeeBe) {
		this.employeeBe = employeeBe;
	}
	
	
}
