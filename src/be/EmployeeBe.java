package be;

import java.sql.Date;

public class EmployeeBe {
	
	private int id;
	private int serviceCenterId;
	private String name;
	private String address;
	private String email;
	private long phoneNumber;
	private WorkFrequency frequency;
	private Role role;
	private int wages;
	private Date startDate;
	private String username;
	private UsersBe user = new UsersBe();
	
	public UsersBe getUser() {
		return user;
	}
	public void setUser(UsersBe user) {
		this.user = user;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public enum WorkFrequency {
		HOURLY,MONTHLY;
	}
	
	public enum Role {
		MANAGER,RECEPTIONIST,MECHANIC,CUSTOMER;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getServiceCenterId() {
		return serviceCenterId;
	}
	public void setServiceCenterId(int serviceCenterId) {
		this.serviceCenterId = serviceCenterId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public WorkFrequency getFrequency() {
		return frequency;
	}
	public void setFrequency(WorkFrequency frequency) {
		this.frequency = frequency;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getWages() {
		return wages;
	}
	public void setWages(int wages) {
		this.wages = wages;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}
